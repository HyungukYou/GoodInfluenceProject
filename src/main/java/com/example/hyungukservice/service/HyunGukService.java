package com.example.hyungukservice.service;

import com.example.hyungukservice.domain.HyunGuk;
import com.example.hyungukservice.repository.HyunGukRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class HyunGukService {
    @Autowired
    private HyunGukRepository hyunGukRepository;

    private static String HYUN_SERVICE_URL = "http://16680010.com/gnuboard5/bbs/board.php?bo_table=branches"; // 선한영양력 URL
    @PostConstruct
    public void getGoodInfluence() throws IOException {

        Document doc = Jsoup.connect(HYUN_SERVICE_URL).get();
        Elements contents = doc.select("table tbody tr");

        Document document = Jsoup.connect(HYUN_SERVICE_URL).get();
        Elements elements = document.select("span a");
        List<Element> str = new ArrayList<>(elements.subList(10,11));
        String str1 = str.toString();
        String str2 = str1.substring(49,51);
        int i = Integer.parseInt(str2);

        for (int i1=1 ; i1<= i;i1++) {
            String NEXT_URL = "http://16680010.com/gnuboard5/bbs/board.php?bo_table=branches&page="+i1; // 페이지 목록 하나씩 돌리면서 파싱하기
            Document document1 = Jsoup.connect(NEXT_URL).get();
            Elements contents1 = document1.select("table tbody tr");
            for (Element content : contents1){ // 반복문 사용으로 DB에 저장하
                Elements tdContents = content.select("td");
                hyunGukRepository.save(HyunGuk.builder()
                        .area(tdContents.get(0).text())
                        .shop(tdContents.get(1).text())
                        .address(tdContents.get(2).text())
                        .phone(tdContents.get(3).text())
                        .sponsor(tdContents.get(4).text())
                        .build());
            }
        }

    }
    public List<HyunGuk> getShop(){ // 모든 정보 출력하는 기능
        List<HyunGuk> list = hyunGukRepository.findAll();
        return list;
    }
    public String getShopName(String shop) throws IllegalAccessException{ // 가게 이름을 주면 있으면 가게 정보가 나오고 없으면 에러 표시를 한다.
        List<HyunGuk> list = hyunGukRepository.findAll();
        for (HyunGuk hyunGuk : list){
            if (hyunGuk.getShop().equals(shop)){
                return hyunGuk.getShop();
            }
        }throw new IllegalAccessException("error");
    }
    public Long getShopIdByShopName(String shop) throws IllegalAccessException { // 가게 이름을 주면 가게에 할당된 id를 주는 기능
        List<HyunGuk> list = hyunGukRepository.findAll();
        for(HyunGuk hyunGuk : list){
            if(hyunGuk.getShop().equals(shop)){
                return hyunGuk.getId();
            }
        }throw new IllegalAccessException("error");
    }
    public void AllDeleteShop(){ // 모든 정보 삭제하는 기능
        hyunGukRepository.deleteAll();
    }
    public void DeleteShopById(Long id){ // 가게에 해당된 id를 입력하면 id에 해당된 가게 정보는 전부 삭제하는 기능
        List<HyunGuk> list = hyunGukRepository.findAll();
        for(HyunGuk hyunGuk : list){
            if(hyunGuk.getId().equals(id));
            hyunGukRepository.deleteById(id);
        }
    }
    public boolean CheckShop(String shop){ // 가게 이름을 입력하여 있으면 true 없으면 false 나오는 기능
        List<HyunGuk> list = hyunGukRepository.findAll();
        for (HyunGuk hyunGuk : list){
            if (hyunGuk.getShop().equals(shop))
                return true;
        }
        return false;
    }
    public HyunGuk CreateShopImFormationCheckDuplication(String area, String shop, String address, String phone, String sponsor) throws IllegalAccessException { // 중복있는지 확인 하고 있으면 에러를 표시하고 없으면 생성하면서 DB에 저
        List<HyunGuk> list = hyunGukRepository.findAll();
        for (HyunGuk hyunGuk : list){
            if (hyunGuk.getArea().equals(area)){
                throw new IllegalAccessException("error");
            }else if (hyunGuk.getShop().equals(shop)){
                throw new IllegalAccessException("error");
            }else if (hyunGuk.getAddress().equals(address)){
                throw new IllegalAccessException("error");
            }else if (hyunGuk.getPhone().equals(phone)){
                throw new IllegalAccessException("error");
            }else if (hyunGuk.getShop().equals(sponsor)){
                throw new IllegalAccessException("error");
            }
        }HyunGuk hyunGuk = HyunGuk.builder()
                .area(area)
                .shop(shop)
                .address(address)
                .phone(phone)
                .sponsor(sponsor)
                .build();
        return hyunGukRepository.save(hyunGuk);
    }
}



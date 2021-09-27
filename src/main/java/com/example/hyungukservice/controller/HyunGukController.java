package com.example.hyungukservice.controller;


import com.example.hyungukservice.domain.HyunGuk;
import com.example.hyungukservice.service.HyunGukService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequiredArgsConstructor
public class HyunGukController {
    @Autowired
    private HyunGukService hyunGukService;

    @GetMapping("getShop") // 모든 가게 정보 가져오기
    @ResponseBody
    public List<HyunGuk> getShop(){
        return hyunGukService.getShop();
    }
    @GetMapping("getShopIdByShopName") // 가게이름을 주면 해당 id를 준다
    @ResponseBody
    public Long getShopIdByShopName(String shop) throws IllegalAccessException {
       return hyunGukService.getShopIdByShopName(shop);
    }
    @DeleteMapping("AllDeleteShop") // 모든 가게 정보를 삭제한다
    @ResponseBody
    public void AllDeleteShop(){
        hyunGukService.AllDeleteShop();
    }
    @DeleteMapping("DeleteShopById") // id를 주면 id에 해당하는 가게 정보를 전부 삭제
    @ResponseBody
    public void DeleteShopById(Long id){
        hyunGukService.DeleteShopById(id);
    }
    @GetMapping("CheckShop") // 가게가 있는지 없는지 확인
    @ResponseBody
    public boolean CheckShop(String shop){
        return hyunGukService.CheckShop(shop);
    }
    @PostMapping("CreateShopImFormationCheckDuplication") // 중복 가게가 있는지 확인하고 있으면 에러 없으면 생성
    @ResponseBody
    public HyunGuk CreateShopImFormationCheckDuplication(String area, String shop, String address, String phone, String sponsor) throws IllegalAccessException {
        return hyunGukService.CreateShopImFormationCheckDuplication(area,shop,address,phone,sponsor);
    }
    @GetMapping("getShopName") // 가게 이름을 주면 그 가게 해당하는 정보를 가져온
    @ResponseBody
    public String getShopName(String shop) throws IllegalAccessException {
        return hyunGukService.getShopName(shop);
    }
}

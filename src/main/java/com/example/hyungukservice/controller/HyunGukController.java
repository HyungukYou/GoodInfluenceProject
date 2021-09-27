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

    @GetMapping("text")
    @ResponseBody
    public List<HyunGuk> getShop(){
        return hyunGukService.getShop();
    }
    @GetMapping("text1")
    @ResponseBody
    public Long getShopIdByShopName(String shop) throws IllegalAccessException {
       return hyunGukService.getShopIdByShopName(shop);
    }
    @DeleteMapping("text2")
    @ResponseBody
    public void AllDeleteShop(){
        hyunGukService.AllDeleteShop();
    }
    @DeleteMapping("text3")
    @ResponseBody
    public void DeleteShopById(Long id){
        hyunGukService.DeleteShopById(id);
    }
    @GetMapping("text4")
    @ResponseBody
    public boolean CheckShop(String shop){
        return hyunGukService.CheckShop(shop);
    }
    @PostMapping("text5")
    @ResponseBody
    public HyunGuk CreateShopImFormationCheckDuplication(String area, String shop, String address, String phone, String sponsor) throws IllegalAccessException {
        return hyunGukService.CreateShopImFormationCheckDuplication(area,shop,address,phone,sponsor);
    }
    @GetMapping("text6")
    @ResponseBody
    public String getShopName(String shop) throws IllegalAccessException {
        return hyunGukService.getShopName(shop);
    }
}

//package com.sell.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RequestMapping("/weixin")
@RestController
@Slf4j
public class WeixinController {

    @GetMapping("/auth")
    public void auth(@RequestParam("code")String code){
        log.info("【进入auth】....");

        log.info("【获取code】code={}",code);
        String url="https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx68c1e83f731d1c75&secret=6363c7aa4178ac8669c3565c0ec2421b&code="+code+"&grant_type=authorization_code";
        RestTemplate restTemplate=new RestTemplate();
        String response=restTemplate.getForObject(url,String.class);
        log.info("【获取token】response={}",response);
    }
}

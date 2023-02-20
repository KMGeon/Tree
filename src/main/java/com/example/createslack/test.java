//package com.example.createslack;
//
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpMethod;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Controller
//public class test {
//
//    @PostMapping("/publish/slack")
//    public void send(@RequestBody String message) {
//        RestTemplate restTemplate = new RestTemplate();
//        Map<String, Object> request = new HashMap<>();
//        request.put("username", "하하"); //slack bot name
//        request.put("text", message); //전송할 메세지
//        request.put("icon_emoji", ":slack_emoji:"); //slack bot image
//
//        HttpEntity<Map<String, Object>> entity = new HttpEntity<Map<String, Object>>(request);
//
//        String url = "https://hooks.slack.com/services/????"; //복사한 Webhook URL 입력
//
//        restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
//    }
//}

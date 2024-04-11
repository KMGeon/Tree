package com.example.mybatis.event;

import com.example.mybatis.application.SseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EventListener {

    private final SseService sseService;


    @org.springframework.context.event.EventListener(EntityCreated.class)
    public void handle(EntityCreated entityCreated){
        log.info("EntityCreated event is published");
        sseService.send(1);
    }
}

package com.example.mybatis.repository;

import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class EmitterRepository {
    private static final String SSE_KEY = "key";
    private Map<String, SseEmitter> emitterMap = new ConcurrentHashMap<>();


    public SseEmitter save(SseEmitter sseEmitter) {
        emitterMap.put(SSE_KEY, sseEmitter);
        return sseEmitter;
    }

    public Optional<SseEmitter> get() {
        return Optional.ofNullable(emitterMap.get(SSE_KEY));
    }

    public void delete() {
        emitterMap.remove(SSE_KEY);
    }
}

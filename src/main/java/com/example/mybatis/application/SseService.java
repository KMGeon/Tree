package com.example.mybatis.application;

import com.example.mybatis.repository.EmitterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@Service
@Slf4j
@RequiredArgsConstructor
public class SseService {

    private final static Long DEFAULT_TIMEOUT = 10L * 1000;
    private static final String CONNECTED = "CONNECTED";
    private final static String SSE_NAME = "board";

    private final EmitterRepository emitterRepository;

    public SseEmitter connectSse() {
        SseEmitter sseEmitter = new SseEmitter(DEFAULT_TIMEOUT);
        emitterRepository.save(sseEmitter);
        sseEmitter.onCompletion(() -> {
            log.info("onCompletion onCompletion");
            sseEmitter.complete();
        });
        sseEmitter.onTimeout(() -> {
            log.info("onTimeout onTimeout");
            sseEmitter.complete();
        });
        sseEmitter.onError(throwable -> {
            sseEmitter.complete();
        });

        try {
//            name 은 ajax에서 이름
            sseEmitter.send(SseEmitter.event()
                    .id(CONNECTED)
                    .name(SSE_NAME)
                    .data("connect complete"));
        } catch (IOException e) {

            emitterRepository.delete();
            log.info("exception", e);
            throw new RuntimeException("sse 오류");
        }
        return sseEmitter;
    }

    public void send(Integer boardId) {
        emitterRepository.get().ifPresent(sseEmitter -> {
            try {
                sseEmitter.send(SseEmitter.event().
                        id(boardId.toString())
                        .name(SSE_NAME).data("new board"));
            } catch (Exception e) {
                emitterRepository.delete();
                throw new RuntimeException("send 오류");
            }
        });
    }
}

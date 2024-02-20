package com.example.mybatis.application;

import com.example.mybatis.repository.EmitterRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class SseService {

    private final static Long DEFAULT_TIMEOUT = 60L * 60 * 1000;
    private static final String CONNECTED = "CONNECTED";
    private final static String SSE_NAME = "board";

    private final EmitterRepository emitterRepository;
    private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

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
            sseEmitter.send(SseEmitter.event()
                    .id(CONNECTED)
                    // name은 클라이언트에서 이름과 동일하게 해야된다.
                    /**
                      eventSource.addEventListener("board", function (event) {
                          handleReloadBoard();
                      });
                     */
                    .name(SSE_NAME)
                    // 처음에 sse응답을 할 때 아무런 이벤트도 보내면 재연결 요청을 보낼때나 아니면 연결 요청 자체에서 오류가 발생한다.
                    // 그래서 처음에 dummy data를 보낸다.
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
                        .name("send")
                        .data("new board"));
            } catch (Exception e) {
                emitterRepository.delete();
                throw new RuntimeException("send 오류");
            }
        });
    }
}

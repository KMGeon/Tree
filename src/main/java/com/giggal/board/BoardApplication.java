package com.giggal.board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import static com.giggal.board.global.dicord.DiscordBot.setupDiscordBot;

@EnableJpaAuditing
@SpringBootApplication
public class BoardApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(BoardApplication.class, args);
        setupDiscordBot(context);
    }

}

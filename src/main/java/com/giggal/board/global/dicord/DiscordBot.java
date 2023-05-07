package com.giggal.board.global.dicord;

import com.giggal.board.domain.post.application.PostService;
import com.giggal.board.global.Listener.DiscordListener;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
@Component
public class DiscordBot {
    public static void setupDiscordBot(ConfigurableApplicationContext context) {
        DiscordBotToken discordBotTokenEntity = context.getBean(DiscordBotToken.class);
        PostService post = context.getBean(PostService.class);
        String discordBotToken = discordBotTokenEntity.getDiscordBotToken();

        JDABuilder.createDefault(discordBotToken)
                .setActivity(Activity.playing("메시지 기다리는 중!"))
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .addEventListeners(new DiscordListener(post))
                .build();
    }
}

@Component
class DiscordBotToken {
    @Value("${discord.bot.token}")
    private String discordBotToken;

    public String getDiscordBotToken() {
        return discordBotToken;
    }
}
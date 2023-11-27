package com.giggal.board.global.Listener;

import com.giggal.board.domain.post.application.PostService;
import com.giggal.board.domain.post.entity.Post;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class DiscordListener extends ListenerAdapter {

    private final PostService postService;

    public DiscordListener(PostService postService) {
        this.postService = postService;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        User user = event.getAuthor();
        TextChannel textChannel = event.getChannel().asTextChannel();
        Message message = event.getMessage();

        log.info("get message : " + message.getContentDisplay());

        if (user.isBot()) {
            return;
        } else if (message.getContentDisplay().equals("")) {
            log.info("디스코드 Message 문자열 값 공백");
        }
        String[] messageArray = message.getContentDisplay().split(" ");

        if (messageArray[0].equalsIgnoreCase("무건아")) {
            String[] messageArgs = Arrays.copyOfRange(messageArray, 1, messageArray.length);

            for (String msg : messageArgs) {
                String returnMessage = sendMessage(event, msg);
                textChannel.sendMessage(returnMessage).queue();
            }
        }

    }

    private String sendMessage(MessageReceivedEvent event, String message) {
        User user = event.getAuthor();

        String returnMessage = "";
        switch (message) {
            case "안녕":
                returnMessage = user.getName() + "님 안녕하세요? 오늘도 화이팅 입니다.";
                break;
            case "일정":
                List<Post> search = postService.findDiscord();
                StringBuilder answer = viewDiscord(search);
                returnMessage = String.valueOf(answer);
                break;
            case "너무 힘들어":
                returnMessage = user.getName() + "님 힘들어도 꾸준히 하면 좋은 결과가 있을거에요.";
                break;

            default:
                break;
        }
        return returnMessage;
    }

    @NotNull
    private static StringBuilder viewDiscord(List<Post> search) {
        StringBuilder answer = new StringBuilder();
        answer.append("**\uD83D\uDE46 오늘 Todo List**" + "\n").append("\n");
        search.forEach(post -> {
            answer.append("\uD83D\uDEA9  ").append(post.getId()).append(" ")
                    .append("해야될 일")
                    .append("\n")
                    .append(post.getTitle())
                    .append("\n");

            answer.append("  -").append(post.getContent())
                    .append("\n");

            answer.append("\n");
        });
        return answer;
    }
}
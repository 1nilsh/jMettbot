package bz.nils.dev.jmettbot.Business;

import bz.nils.dev.jmettbot.Infrastructure.Mettbot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class SpotifyNewsService {
    @Value("${mettbot.group}")
    private String groupId;

    private Mettbot mettbot;

    @Autowired
    public SpotifyNewsService(Mettbot mettbot) {
        this.mettbot = mettbot;
    }

    public void sendSpotifyNewsNotification(String news) {
        SendMessage sendMessage = SendMessage.builder()
                .chatId(groupId)
                .text(news)
                .build();

        try {
            mettbot.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}

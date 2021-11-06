package bz.nils.dev.jmettbot.Infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class SetupTelegramApi {
    private Mettbot mettbot;

    @Autowired
    public SetupTelegramApi(Mettbot mettbot) {
        this.mettbot = mettbot;
    }

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        SetWebhook setWebhook = SetWebhook.builder()
                .url(mettbot.getBotPath())
                .build();
        try {
            mettbot.execute(setWebhook);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}

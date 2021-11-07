package bz.nils.dev.jmettbot.Infrastructure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class Mettbot extends TelegramWebhookBot {
    @Value("${mettbot.bot.name}")
    private String BOT_NAME;

    @Value("${mettbot.bot.apitoken}")
    private String API_TOKEN;

    @Value("${mettbot.bot.webhook}")
    private String WEBHOOK_URL;

    @Value("${mettbot.owner}")
    private String BOT_OWNER_ID;

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return API_TOKEN;
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        return null;
    }

    @Override
    public String getBotPath() {
        return WEBHOOK_URL;
    }

    public String getBotOwnerId() {
        return BOT_OWNER_ID;
    }
}

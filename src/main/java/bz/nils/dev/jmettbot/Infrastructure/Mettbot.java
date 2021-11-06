package bz.nils.dev.jmettbot.Infrastructure;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class Mettbot extends TelegramWebhookBot {
    private static final String BOT_NAME = "<some name>";
    private static final String API_TOKEN = "<api token>";
    private static final String WEBHOOK_URL = "<webhook url>";
    private static final String BOT_OWNER_ID = "<id of bot owner>";

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

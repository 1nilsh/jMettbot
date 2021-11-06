package bz.nils.dev.jmettbot.Business;

import bz.nils.dev.jmettbot.Infrastructure.Mettbot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class DailyBulletinService {
    private final Mettbot mettbot;

    @Autowired
    public DailyBulletinService(Mettbot mettbot) {
        this.mettbot = mettbot;
    }

    public void sendBulletin() throws TelegramApiException {
        SendMessage msg = new SendMessage();
        msg.setChatId("");
        msg.setText("Test Test Test Bulletin");
        mettbot.execute(msg);
    }
}

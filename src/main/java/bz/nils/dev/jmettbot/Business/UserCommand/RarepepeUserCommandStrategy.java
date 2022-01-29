package bz.nils.dev.jmettbot.Business.UserCommand;

import bz.nils.dev.jmettbot.Business.PepeScraperService;
import bz.nils.dev.jmettbot.Infrastructure.Mettbot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class RarepepeUserCommandStrategy implements UserCommandStrategy {
    private final Mettbot mettbot;
    private final PepeScraperService pepeScraperService;
    private final Logger logger = Logger.getLogger(this.getClass().toString());

    @Autowired
    public RarepepeUserCommandStrategy(Mettbot mettbot, PepeScraperService pepeScraperService) {
        this.mettbot = mettbot;
        this.pepeScraperService = pepeScraperService;
    }

    @Override
    public void processCommand(Message message) {
        InputFile pepe = new InputFile();
        pepe.setMedia(getRandomPepeUrl());

        SendPhoto sendPhoto = SendPhoto.builder()
                .chatId(String.valueOf(message.getChatId()))
                .photo(pepe)
                .replyToMessageId(message.getMessageId())
                .build();

        try {
            mettbot.execute(sendPhoto);
        } catch (TelegramApiException e) {
            logger.log(Level.WARNING, "Could not send pepe.", e);
        }
    }

    @Override
    public UserCommandStrategyName getStrategyName() {
        return UserCommandStrategyName.RarepepeUserCommandStrategy;
    }

    private String getRandomPepeUrl() {
        List<String> pepes = pepeScraperService.getPepes();
        int index = (int)(Math.random() * pepes.size());

        return pepes.get(index);
    }
}

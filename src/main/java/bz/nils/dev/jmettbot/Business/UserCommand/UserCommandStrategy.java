package bz.nils.dev.jmettbot.Business.UserCommand;

import org.telegram.telegrambots.meta.api.objects.Message;

public interface UserCommandStrategy {
    void processCommand(Message message);

    UserCommandStrategyName getStrategyName();
}

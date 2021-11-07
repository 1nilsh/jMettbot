package bz.nils.dev.jmettbot.Api;

import bz.nils.dev.jmettbot.Business.UserCommand.UserCommandStrategy;
import bz.nils.dev.jmettbot.Business.UserCommand.UserCommandStrategyFactory;
import bz.nils.dev.jmettbot.Business.UserCommand.UserCommandStrategyName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@RequestMapping("api/update/{apikey}")
@RestController
public class TelegramUpdateRestController {
    private final Logger logger = Logger.getLogger(this.getClass().toString());

    private UserCommandStrategyFactory userCommandStrategyFactory;

    @Autowired
    public TelegramUpdateRestController(UserCommandStrategyFactory userCommandStrategyFactory) {
        this.userCommandStrategyFactory = userCommandStrategyFactory;
    }

    @PostMapping
    public void postUpdate(@RequestBody Update update) {
        logger.info(update.toString());

        UserCommandStrategy userCommandStrategy = null;

        switch (update.getMessage().getText()) {
            case "/rarepepe":
                userCommandStrategy = userCommandStrategyFactory.findStrategy(UserCommandStrategyName.RarepepeUserCommandStrategy);
                break;
        }

        if (userCommandStrategy != null) {
            userCommandStrategy.processCommand(update.getMessage());
        }
    }

    @GetMapping
    public Map<String, String> getUpdate() {
        Map<String, String> map = new HashMap<>();
        map.put("status", "OK");
        map.put("info", "This is only a placeholder to confirm that the REST endpoint is working.");
        return map;
    }
}

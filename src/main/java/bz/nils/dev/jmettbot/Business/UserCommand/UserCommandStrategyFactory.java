package bz.nils.dev.jmettbot.Business.UserCommand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class UserCommandStrategyFactory {
    private Map<UserCommandStrategyName, UserCommandStrategy> strategies;

    @Autowired
    public UserCommandStrategyFactory(Set<UserCommandStrategy> strategySet) {
        createStrategy(strategySet);
    }

    public UserCommandStrategy findStrategy(UserCommandStrategyName strategyName) {
        return strategies.get(strategyName);
    }
    private void createStrategy(Set<UserCommandStrategy> strategySet) {
        strategies = new HashMap<>();
        strategySet.forEach(
                strategy ->strategies.put(strategy.getStrategyName(), strategy));
    }
}

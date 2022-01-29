package bz.nils.dev.jmettbot.Business;

import bz.nils.dev.jmettbot.Data.Covid.CovidDataService;
import bz.nils.dev.jmettbot.Data.Covid.CovidData;
import bz.nils.dev.jmettbot.Data.Crypto.CryptoDataService;
import bz.nils.dev.jmettbot.Data.Crypto.CryptoExchangeRates;
import bz.nils.dev.jmettbot.Infrastructure.Mettbot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class DailyBulletinService {
    @Value("${mettbot.group}")
    private String groupId;

    private final Mettbot mettbot;
    private final CovidDataService covidApiRequestService;
    private final CryptoDataService cryptoDataService;
    private final Logger logger = Logger.getLogger(this.getClass().toString());

    @Autowired
    public DailyBulletinService(Mettbot mettbot, CovidDataService covidApiRequestService, CryptoDataService cryptoDataService) {
        this.mettbot = mettbot;
        this.covidApiRequestService = covidApiRequestService;
        this.cryptoDataService = cryptoDataService;
    }

    public void sendBulletin() {
        SendMessage msg = new SendMessage();
        msg.setChatId(groupId);
        msg.setText(formatCovidData() + "\n" + formatCryptoData());
        msg.enableHtml(true);

        try {
            mettbot.execute(msg);
        } catch (TelegramApiException e) {
            logger.log(Level.WARNING, "Could not send bulletin message.", e);
        }
    }

    private String formatCovidData() {
        Map<String, CovidData> covidData = covidApiRequestService.getCovidData();

        String formattedData = "\uD83E\uDDA0 Corona-Update \uD83E\uDDA0\n";
        formattedData += "<b>7-Tage Inzidenz für LG</b>: " + covidData.get("LG").getCases7per100k();
        formattedData += " (Stand: " + covidData.get("LG").getLastUpdate() + ")\n";

        formattedData += "<b>7-Tage Inzidenz für HH</b>: " + covidData.get("HH").getCases7per100k();
        formattedData += " (Stand: " + covidData.get("HH").getLastUpdate() + ")\n";

        return formattedData;
    }

    private String formatCryptoData() {
        CryptoExchangeRates cryptoExchangeRates = cryptoDataService.getCryptoExchangeRates();

        String formattedData = "\uD83D\uDCB8 Crypto-Update \uD83D\uDCB8\n";
        formattedData += "1 BTC = " + cryptoExchangeRates.getBtcRate() + " " + cryptoExchangeRates.getBaseCurrency() + "\n";
        formattedData += "1 DOGE = " + cryptoExchangeRates.getDogeRate() + " " + cryptoExchangeRates.getBaseCurrency();

        return formattedData;
    }
}

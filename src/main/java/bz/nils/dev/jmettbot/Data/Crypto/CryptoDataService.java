package bz.nils.dev.jmettbot.Data.Crypto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CryptoDataService {
    private final String REST_URI_BASE = "https://rest.coinapi.io/v1/exchangerate/";

    @Value("${mettbot.api.coinapi.key}")
    private String API_KEY;

    private RestTemplateBuilder restTemplateBuilder;

    @Autowired
    public CryptoDataService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public CryptoExchangeRates getCryptoExchangeRates() {
        CoinApiResponse btcResponse = getApi("BTC");
        CoinApiResponse dogeResponse = getApi("DOGE");

        return new CryptoExchangeRates(btcResponse.rate, dogeResponse.rate);
    }

    private CoinApiResponse getApi(String coin) {
        RestTemplate restTemplate = restTemplateBuilder
                .defaultHeader("X-CoinAPI-Key", API_KEY)
                .build();

        return restTemplate.getForObject(REST_URI_BASE + coin + "/EUR", CoinApiResponse.class);
    }
}

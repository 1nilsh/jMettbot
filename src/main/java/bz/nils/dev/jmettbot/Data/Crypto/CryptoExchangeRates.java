package bz.nils.dev.jmettbot.Data.Crypto;

public class CryptoExchangeRates {
    private final String baseCurrency;
    private final Float btcRate;
    private final Float dogeRate;

    public CryptoExchangeRates(float btcRate, float dogeRate) {
        this.baseCurrency = "EUR";
        this.btcRate = btcRate;
        this.dogeRate = dogeRate;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public Float getBtcRate() {
        return btcRate;
    }

    public Float getDogeRate() {
        return dogeRate;
    }
}

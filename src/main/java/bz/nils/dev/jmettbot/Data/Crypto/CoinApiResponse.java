package bz.nils.dev.jmettbot.Data.Crypto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CoinApiResponse {
    public Date time;
    public String asset_id_base;
    public String asset_id_quote;
    public Float rate;
}

package com.alphaserve.test.services;

import com.alphaserve.test.domain.CurrencyRate;
import org.json.JSONObject;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
public class CurrencyRateService {
    private static final String LIVE_RATES = "http://apilayer.net/api/live";
    private static final String HISTORICAL_RATES = "http://api.currencylayer.com/historical";
    private static final String ACCESS_KEY = "?access_key=9bae9ca7dc21568bfe214cdef09cbffd";

    private String paramCurrencies = "&currencies=UAH,EUR,GBP,NZD,AUD,JPY";
    private String paramFormatJSON = "&format=1";


    private final RestTemplate restTemplate;
    private final CurrencyRate currencyRate;

    public CurrencyRateService(RestTemplateBuilder restTemplateBuilder, CurrencyRate currencyRate) {
        this.restTemplate = restTemplateBuilder.build();
        this.currencyRate = currencyRate;
    }

    public CurrencyRate getLive() {
        currencyRate.setToday();
        String url = LIVE_RATES + ACCESS_KEY + paramCurrencies + paramFormatJSON;

        return currencyRate.setCurrencyRate(getJSONResponse(url));
    }

    public String getYesterday() {
        currencyRate.setYesterday();
        return currencyRate.getDate();
    }

    public CurrencyRate getArchive(String date) {
        currencyRate.setDate(date);
        String paramDate = "&date=" + date;

        String url = HISTORICAL_RATES + ACCESS_KEY + paramDate + paramCurrencies + paramFormatJSON;

        return currencyRate.setCurrencyRate(getJSONResponse(url));
    }


    private JSONObject getJSONResponse (String url) {
        return new JSONObject(Objects.requireNonNull(this.restTemplate.getForObject(url, String.class)));
    }
}

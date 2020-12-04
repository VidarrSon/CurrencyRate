package com.alphaserve.test.domain;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class CurrencyRate {
    private LocalDate date;
    private Double UAH, EUR, GBP, NZD, AUD, JPY;

    public CurrencyRate() {
    }

    public CurrencyRate setCurrencyRate(JSONObject jsonObject) throws JSONException {

        JSONObject obj = jsonObject.getJSONObject("quotes");

        this.setUAH(obj.getDouble("USDUAH"));
        this.setEUR(obj.getDouble("USDEUR"));
        this.setGBP(obj.getDouble("USDGBP"));
        this.setNZD(obj.getDouble("USDNZD"));
        this.setAUD(obj.getDouble("USDAUD"));
        this.setJPY(obj.getDouble("USDJPY"));

        return this;
    }

    public void setYesterday() {
        this.date = LocalDate.now().minusDays(1);
    }

    public void setToday() {
        this.date = LocalDate.now();
    }

    public String getPreviousDate() {
        if (this.date == null) this.date = LocalDate.now();
        this.date = this.date.minusDays(1);
        return this.getDate();
    }

    public String getDate() {
        if (date == null) return "Today";
        return date.toString();
    }

    public void setDate(String date) {
        this.date = LocalDate.parse(date);
    }

    public Double getUAH() {
        return UAH;
    }

    public void setUAH(Double UAH) {
        this.UAH = UAH;
    }

    public Double getEUR() {
        return EUR;
    }

    public void setEUR(Double EUR) {
        this.EUR = EUR;
    }

    public Double getGBP() {
        return GBP;
    }

    public void setGBP(Double GBP) {
        this.GBP = GBP;
    }

    public Double getNZD() {
        return NZD;
    }

    public void setNZD(Double NZD) {
        this.NZD = NZD;
    }

    public Double getAUD() {
        return AUD;
    }

    public void setAUD(Double AUD) {
        this.AUD = AUD;
    }

    public Double getJPY() {
        return JPY;
    }

    public void setJPY(Double JPY) {
        this.JPY = JPY;
    }
}

package com.alphaserve.test.controllers;

import com.alphaserve.test.domain.CurrencyRate;
import com.alphaserve.test.services.CurrencyRateService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ExchangeController {
    private final CurrencyRateService currencyRateService;

    public ExchangeController(CurrencyRateService currencyRateService) {
        this.currencyRateService = currencyRateService;
    }

    @GetMapping("/")
    public String main() {
        return "main";
    }

    @GetMapping("/live")
    public String live(Model model) {
        CurrencyRate currencyRate = currencyRateService.getLive();
        model.addAttribute("currency", currencyRate);

        return "show";
    }

    @GetMapping("/archive")
    public String archive() {

        return "redirect:/archive/" + currencyRateService.getYesterday();
    }

    @GetMapping("/archive/{date}")
    public String archiveDate(@PathVariable("date") String date, Model model) {
        CurrencyRate currencyRate = currencyRateService.getArchive(date);
        model.addAttribute("currency", currencyRate);

        return "show";
    }
}

package com.dent.dentclinicapp.rates.rates.scheduler;

import com.dent.dentclinicapp.rates.rates.controller.ElementNotFoundException;
import com.dent.dentclinicapp.rates.rates.domain.ExchangeRateDto;
import com.dent.dentclinicapp.rates.rates.domain.Rate;
import com.dent.dentclinicapp.rates.rates.domain.TaxRateDto;
import com.dent.dentclinicapp.rates.rates.exchange.client.ExchangeClient;
import com.dent.dentclinicapp.rates.rates.service.RateService;
import com.dent.dentclinicapp.rates.rates.tax.client.TaxClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RatesUpdateScheduler
{
    private final ExchangeClient exchangeClient;
    private final TaxClient taxClient;
    private final RateService service;

    @Scheduled(cron = "0 0 1 * * *")
    public void updateUsd() throws ElementNotFoundException     /*All in one method to save client's request limit*/
    {
        ExchangeRateDto rates = exchangeClient.getRatesToPln();

        Rate eurRate = service.getRateByName(ExchangeClient.EUR);
        Rate usdRate = service.getRateByName(ExchangeClient.USD);
        Rate gbpRate = service.getRateByName(ExchangeClient.GBP);

        log.info("Starting currencies rate update preparation...");
        try {
            service.saveRate(new Rate(eurRate.getId(), eurRate.getName(), rates.getExchangeRates().getEur()));
            service.saveRate(new Rate(usdRate.getId(), usdRate.getName(), rates.getExchangeRates().getUsd()));
            service.saveRate(new Rate(gbpRate.getId(), gbpRate.getName(), rates.getExchangeRates().getGbp()));
            log.info("Currencies rates has been updated successfully!");
        } catch (Exception e) {
            log.error("Failed to process currencies rates update: " + e.getMessage(), e);
        }
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void updateTax() throws ElementNotFoundException {
        TaxRateDto rateDto = taxClient.getPolandTaxRate();
        Rate taxRate = service.getRateByName("TAX");
        log.info("Starting tax rate update preparation...");
        try {
            service.saveRate(new Rate(taxRate.getId(), taxRate.getName(), rateDto.getStandardRateDto().getTaxRate()));
            log.info("Tax rate has been updated successfully!");
        } catch (Exception e) {
            log.error("Failed to process tax rate update: " + e.getMessage(), e);
        }
    }
}
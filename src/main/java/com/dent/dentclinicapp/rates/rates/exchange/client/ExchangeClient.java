package com.dent.dentclinicapp.rates.rates.exchange.client;

import com.dent.dentclinicapp.rates.rates.domain.ExchangeRateDto;
import com.dent.dentclinicapp.rates.rates.exchange.config.ExchangeConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class ExchangeClient
{
    private final RestTemplate restTemplate;

    private final ExchangeConfig exchangeConfig;

    public final static String USD = "USD";
    public final static String EUR = "EUR";
    public final static String GBP = "GBP";

    public ExchangeRateDto getRatesToPln() {
        URI url = UriComponentsBuilder.fromHttpUrl(exchangeConfig.getExchangeApiEndpoint() + "/latest")
                .queryParam("apikey", exchangeConfig.getApiKey())
                .queryParam("base", "PLN")
                .queryParam("symbols", USD + "," + EUR + "," + GBP)
                .build()
                .encode()
                .toUri();

        return restTemplate.getForObject(url, ExchangeRateDto.class);
    }
}
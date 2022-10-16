package com.dent.dentclinicapp.rates.rates.exchange.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ExchangeConfig
{
    @Value("${exchange.api.endpoint.prod}")
    private String exchangeApiEndpoint;
    @Value("${api.key}")
    private String apiKey;
}
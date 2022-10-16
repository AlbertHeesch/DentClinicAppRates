package com.dent.dentclinicapp.rates.rates.tax.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class TaxConfig
{
    @Value("${tax.api.endpoint.prod}")
    private String taxApiEndpoint;
    @Value("${api.key}")
    private String apiKey;
}

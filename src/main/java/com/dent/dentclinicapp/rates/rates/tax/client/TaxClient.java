package com.dent.dentclinicapp.rates.rates.tax.client;

import com.dent.dentclinicapp.rates.rates.domain.TaxRateDto;
import com.dent.dentclinicapp.rates.rates.tax.config.TaxConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class TaxClient
{
    private final RestTemplate restTemplate;

    private final TaxConfig taxConfig;

    public TaxRateDto getPolandTaxRate() {
        URI url = UriComponentsBuilder.fromHttpUrl(taxConfig.getTaxApiEndpoint() + "/tax_rates")
                .queryParam("apikey", taxConfig.getApiKey())
                .queryParam("country", "PL")
                .build()
                .encode()
                .toUri();

        return restTemplate.getForObject(url, TaxRateDto.class);
    }
}
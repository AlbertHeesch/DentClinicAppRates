package com.dent.dentclinicapp.rates.rates.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyRatesDto
{
    @JsonProperty("USD")
    private Double usd;

    @JsonProperty("EUR")
    private Double eur;

    @JsonProperty("GBP")
    private Double gbp;
}

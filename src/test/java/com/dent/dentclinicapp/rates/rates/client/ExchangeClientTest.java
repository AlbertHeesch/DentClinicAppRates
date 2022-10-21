package com.dent.dentclinicapp.rates.rates.client;

import com.dent.dentclinicapp.rates.rates.domain.CurrencyRatesDto;
import com.dent.dentclinicapp.rates.rates.domain.ExchangeRateDto;
import com.dent.dentclinicapp.rates.rates.exchange.client.ExchangeClient;
import com.dent.dentclinicapp.rates.rates.exchange.config.ExchangeConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExchangeClientTest {

    @InjectMocks
    private ExchangeClient client;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private ExchangeConfig config;

    @Test
    void shouldFetchRatesToPln() throws URISyntaxException {
        //Given
        when(config.getExchangeApiEndpoint()).thenReturn("http://test.com");
        when(config.getApiKey()).thenReturn("test");

        ExchangeRateDto exchangeRateDto = new ExchangeRateDto(new CurrencyRatesDto(1.1, 2.2, 3.3));
        URI uri = new URI("http://test.com/latest?apikey=test&base=PLN&symbols=USD,EUR,GBP");
        when(restTemplate.getForObject(uri, ExchangeRateDto.class)).thenReturn(exchangeRateDto);

        //When
        ExchangeRateDto fetchedExchange = client.getRatesToPln();

        //Then
        assertEquals(1.1, fetchedExchange.getExchangeRates().getUsd());
        assertEquals(2.2, fetchedExchange.getExchangeRates().getEur());
        assertEquals(3.3, fetchedExchange.getExchangeRates().getGbp());
    }
}
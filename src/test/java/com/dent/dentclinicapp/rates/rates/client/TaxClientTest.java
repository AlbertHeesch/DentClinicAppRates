package com.dent.dentclinicapp.rates.rates.client;

import com.dent.dentclinicapp.rates.rates.domain.StandardRateDto;
import com.dent.dentclinicapp.rates.rates.domain.TaxRateDto;
import com.dent.dentclinicapp.rates.rates.tax.client.TaxClient;
import com.dent.dentclinicapp.rates.rates.tax.config.TaxConfig;
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
class TaxClientTest {

    @InjectMocks
    private TaxClient client;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private TaxConfig config;

    @Test
    void shouldFetchPolandTaxRate() throws URISyntaxException {
        //Given
        when(config.getTaxApiEndpoint()).thenReturn("http://test.com");
        when(config.getApiKey()).thenReturn("test");

        TaxRateDto taxRateDto = new TaxRateDto(new StandardRateDto(0.5));
        URI uri = new URI("http://test.com/tax_rates?apikey=test&country=PL");
        when(restTemplate.getForObject(uri, TaxRateDto.class)).thenReturn(taxRateDto);

        //When
        TaxRateDto fetchedTaxRate = client.getPolandTaxRate();

        //Then
        assertEquals(0.5, fetchedTaxRate.getStandardRateDto().getTaxRate());
    }
}
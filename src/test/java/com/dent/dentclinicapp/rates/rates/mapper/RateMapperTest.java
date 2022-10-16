package com.dent.dentclinicapp.rates.rates.mapper;

import com.dent.dentclinicapp.rates.rates.domain.Rate;
import com.dent.dentclinicapp.rates.rates.domain.RateDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class RateMapperTest {

    @Autowired
    private RateMapper mapper;

    @Test
    void mapToCurrency()
    {
        //Given
        RateDto currencyDto = new RateDto(1L, "currency1", 3.33);

        //When
        Rate currency = mapper.mapToRate(currencyDto);

        //Then
        assertEquals(1L, currency.getId());
        assertEquals("currency1", currency.getName());
        assertEquals(3.33, currency.getValue());
    }

    @Test
    void mapToCurrencyDto()
    {
        //Given
        Rate currency = new Rate(1L, "currency1", 3.33);

        //When
        RateDto rateDto = mapper.mapToRateDto(currency);

        //Then
        assertEquals(1L, rateDto.getId());
        assertEquals("currency1", rateDto.getName());
        assertEquals(3.33, rateDto.getValue());
    }

    @Test
    void mapToCurrencyDtoList()
    {
        //Given
        Rate currency1 = new Rate(1L, "currency1", 3.33);
        Rate currency2 = new Rate(2L, "currency2", 4.44);
        Rate currency3 = new Rate(3L, "currency3", 5.55);

        List<Rate> currencyList = new ArrayList<>();
        currencyList.add(currency1);
        currencyList.add(currency2);
        currencyList.add(currency3);

        //When
        List<RateDto> currencyDtoList = mapper.mapToRateDtoList(currencyList);

        //Then
        assertEquals(3, currencyDtoList.size());
        assertEquals(1L, currencyDtoList.get(0).getId());
        assertEquals(2L, currencyDtoList.get(1).getId());
        assertEquals(3L, currencyDtoList.get(2).getId());
        assertEquals("currency1", currencyDtoList.get(0).getName());
        assertEquals("currency2", currencyDtoList.get(1).getName());
        assertEquals("currency3", currencyDtoList.get(2).getName());
        assertEquals(3.33, currencyDtoList.get(0).getValue());
        assertEquals(4.44, currencyDtoList.get(1).getValue());
        assertEquals(5.55, currencyDtoList.get(2).getValue());
    }
}
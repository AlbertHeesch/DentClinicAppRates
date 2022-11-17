package com.dent.dentclinicapp.rates.rates.facade;

import com.dent.dentclinicapp.rates.rates.controller.ElementNotFoundException;
import com.dent.dentclinicapp.rates.rates.domain.Rate;
import com.dent.dentclinicapp.rates.rates.domain.RateDto;
import com.dent.dentclinicapp.rates.rates.mapper.RateMapper;
import com.dent.dentclinicapp.rates.rates.service.RateService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RateFacadeTest {

    @InjectMocks
    private RateFacade facade;

    @Mock
    private RateService service;

    @Mock
    private RateMapper mapper;

    @Test
    void fetchRatesTest() {
        //Given
        Rate rate1 = new Rate(1L, "Name1", 1.1);
        Rate rate2 = new Rate(2L, "Name2", 2.2);
        Rate rate3 = new Rate(3L, "Name3", 3.3);

        List<Rate> rates = List.of(rate1, rate2, rate3);

        RateDto rateDto1 = new RateDto(1L, "Name1", 1.1);
        RateDto rateDto2 = new RateDto(2L, "Name2", 2.2);
        RateDto rateDto3 = new RateDto(3L, "Name3", 3.3);

        List<RateDto> rateDtos = List.of(rateDto1, rateDto2, rateDto3);

        when(service.getAllRates()).thenReturn(rates);
        when(mapper.mapToRateDtoList(anyList())).thenReturn(rateDtos);

        //When
        List<RateDto> fetchedRatesList = facade.fetchRates();

        //Then
        assertEquals(3, fetchedRatesList.size());
        assertEquals(1L, fetchedRatesList.get(0).getId());
        assertEquals(2L, fetchedRatesList.get(1).getId());
        assertEquals(3L, fetchedRatesList.get(2).getId());
        assertEquals("Name1", fetchedRatesList.get(0).getName());
        assertEquals("Name2", fetchedRatesList.get(1).getName());
        assertEquals("Name3", fetchedRatesList.get(2).getName());
        assertEquals(1.1, fetchedRatesList.get(0).getValue());
        assertEquals(2.2, fetchedRatesList.get(1).getValue());
        assertEquals(3.3, fetchedRatesList.get(2).getValue());
    }

    @Test
    void fetchRateTest() throws ElementNotFoundException {
        //Given
        Rate rate1 = new Rate(1L, "Name1", 1.1);
        RateDto rateDto1 = new RateDto(1L, "Name1", 1.1);

        when(service.getRateById(any(long.class))).thenReturn(rate1);
        when(mapper.mapToRateDto(any(Rate.class))).thenReturn(rateDto1);

        //When
        RateDto fetchedRateDto = facade.fetchRate(1L);

        //Then
        assertEquals(1L, fetchedRateDto.getId());
        assertEquals("Name1", fetchedRateDto.getName());
        assertEquals(1.1, fetchedRateDto.getValue());
    }

    @Test
    void updateRateTest() throws ElementNotFoundException {
        //Given
        Rate rate1 = new Rate(1L, "Name1", 1.1);
        RateDto rateDto1 = new RateDto(1L, "Name1", 1.1);

        when(service.getRateById(any(long.class))).thenReturn(rate1);
        when(service.saveRate(any(Rate.class))).thenReturn(rate1);


        //When
        RateDto updatedRateDto = facade.updateRate(rateDto1);

        //Then
        assertEquals(1L, updatedRateDto.getId());
        assertEquals("Name1", updatedRateDto.getName());
        assertEquals(1.1, updatedRateDto.getValue());
    }
}
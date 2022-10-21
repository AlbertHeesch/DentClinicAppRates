package com.dent.dentclinicapp.rates.rates.service;

import com.dent.dentclinicapp.rates.rates.controller.ElementNotFoundException;
import com.dent.dentclinicapp.rates.rates.domain.Rate;
import com.dent.dentclinicapp.rates.rates.repository.RateDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RateServiceTest {

    @InjectMocks
    private RateService service;

    @Mock
    private RateDao repository;

    @Test
    void getAllRates()
    {
        //Given
        Rate currency1 = new Rate(1L, "currency1", 3.33);
        Rate currency2 = new Rate(2L, "currency2", 4.44);
        Rate currency3 = new Rate(3L, "currency3", 5.55);
        List<Rate> rates = List.of(currency1, currency2, currency3);

        when(repository.findAll()).thenReturn(rates);
        //When
        List<Rate> rateList = service.getAllRates();

        //Then
        assertEquals(3, rateList.size());
        assertEquals(1L, rateList.get(0).getId());
        assertEquals(2L, rateList.get(1).getId());
        assertEquals(3L, rateList.get(2).getId());
        assertEquals("currency1", rateList.get(0).getName());
        assertEquals("currency2", rateList.get(1).getName());
        assertEquals("currency3", rateList.get(2).getName());
        assertEquals(3.33, rateList.get(0).getValue());
        assertEquals(4.44, rateList.get(1).getValue());
        assertEquals(5.55, rateList.get(2).getValue());

    }

    @Test
    void getRateById() throws ElementNotFoundException {
        //Given
        Rate currency1 = new Rate(1L, "currency1", 3.33);
        when(repository.findById(any(long.class))).thenReturn(Optional.of(currency1));

        //When
        Rate foundRate = service.getRateById(1L);

        //Then
        assertEquals(1L, foundRate.getId());
        assertEquals("currency1", foundRate.getName());
        assertEquals(3.33, foundRate.getValue());
    }

    @Test
    void getRateByName() throws ElementNotFoundException {
        //Given
        Rate currency1 = new Rate(1L, "currency1", 3.33);
        when(repository.findRateByName(any(String.class))).thenReturn(Optional.of(currency1));

        //When
        Rate foundRate = service.getRateByName("currency1");

        //Then
        assertEquals(1L, foundRate.getId());
        assertEquals("currency1", foundRate.getName());
        assertEquals(3.33, foundRate.getValue());
    }

    @Test
    void saveRate()
    {
        //Given
        Rate currency1 = new Rate(1L, "currency1", 3.33);
        when(repository.save(any(Rate.class))).thenReturn(currency1);

        //When
        Rate foundRate = service.saveRate(currency1);

        //Then
        assertEquals(1L, foundRate.getId());
        assertEquals("currency1", foundRate.getName());
        assertEquals(3.33, foundRate.getValue());
    }
}
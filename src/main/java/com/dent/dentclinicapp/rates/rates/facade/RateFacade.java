package com.dent.dentclinicapp.rates.rates.facade;

import com.dent.dentclinicapp.rates.rates.controller.ElementNotFoundException;
import com.dent.dentclinicapp.rates.rates.domain.Rate;
import com.dent.dentclinicapp.rates.rates.domain.RateDto;
import com.dent.dentclinicapp.rates.rates.mapper.RateMapper;
import com.dent.dentclinicapp.rates.rates.service.RateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RateFacade
{
    private final RateService service;
    private final RateMapper mapper;

    public List<RateDto> fetchRates()
    {
        List<Rate> rates = service.getAllRates();
        return mapper.mapToRateDtoList(rates);
    }

    public RateDto fetchRate(Long id) throws ElementNotFoundException {
        return mapper.mapToRateDto(service.getRateById(id));
    }

    public void deleteRate(@PathVariable Long rateId) throws ElementNotFoundException {
        service.deleteRate(service.getRateById(rateId));
    }

    public void createRate(@RequestBody RateDto rateDto) {
        Rate rate = mapper.mapToRate(rateDto);
        service.saveRate(rate);
    }

    public RateDto updateRate(@RequestBody RateDto rateDto) {
        Rate rate = mapper.mapToRate(rateDto);
        Rate savedRate = service.saveRate(rate);
        return mapper.mapToRateDto(savedRate);
    }
}
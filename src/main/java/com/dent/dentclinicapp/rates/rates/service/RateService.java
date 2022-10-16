package com.dent.dentclinicapp.rates.rates.service;

import com.dent.dentclinicapp.rates.rates.controller.ElementNotFoundException;
import com.dent.dentclinicapp.rates.rates.domain.Rate;
import com.dent.dentclinicapp.rates.rates.repository.RateDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RateService {

    private final RateDao rateDao;

    public List<Rate> getAllRates()
    {
        return rateDao.findAll();
    }

    public Rate getRateById(final Long id) throws ElementNotFoundException {
        return rateDao.findById(id).orElseThrow(ElementNotFoundException::new);
    }

    public Rate getRateByName(final String name) throws ElementNotFoundException {
        return rateDao.findRateByName(name).orElseThrow(ElementNotFoundException::new);
    }

    public Rate saveRate(final Rate rate)
    {
        return rateDao.save(rate);
    }

    public void deleteRate(final Rate rate)
    {
        rateDao.delete(rate);
    }
}
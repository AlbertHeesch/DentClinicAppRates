package com.dent.dentclinicapp.rates.rates.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RateDaoTest {

    @Autowired
    private RateDao rateDao;

    @Test
    void testCurrencyDaoFindAll()
    {
//        //Given
//        CurrencyToPln currency = new CurrencyToPln();
//
//
//        //When
//        int sizeBefore = currencyDao.findAll().size();
//        currencyDao.save(currency);
//
//        //Then
//        List<CurrencyToPln> currencyList = currencyDao.findAll();
//        int sizeAfter = currencyList.size();
//        Assertions.assertEquals(1, (sizeAfter - sizeBefore));
//
//        //CleanUp
//        currencyDao.delete(currency);
    }
}
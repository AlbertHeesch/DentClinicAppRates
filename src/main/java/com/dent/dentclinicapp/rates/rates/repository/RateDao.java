package com.dent.dentclinicapp.rates.rates.repository;

import com.dent.dentclinicapp.rates.rates.domain.Rate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface RateDao extends CrudRepository<Rate, Long>
{
    @Override
    List<Rate> findAll();

    @Override
    Optional<Rate> findById(Long id);

    Optional<Rate> findRateByName(String name);

    @Override
    Rate save(Rate rate);
}
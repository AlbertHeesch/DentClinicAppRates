package com.dent.dentclinicapp.rates.rates.controller;

import com.dent.dentclinicapp.rates.rates.domain.RateDto;
import com.dent.dentclinicapp.rates.rates.facade.RateFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/rate")
@RequiredArgsConstructor
public class RateController
{
    private final RateFacade facade;

    @GetMapping
    public ResponseEntity<List<RateDto>> getRates() {
        return ResponseEntity.ok(facade.fetchRates());
    }

    @GetMapping(value = "{rateId}")
    public ResponseEntity<RateDto> getRate(@PathVariable Long rateId) throws ElementNotFoundException {
        return ResponseEntity.ok(facade.fetchRate(rateId));
    }

    @DeleteMapping(value = "{rateId}")
    public ResponseEntity<Void> deleteRate(@PathVariable Long rateId) throws ElementNotFoundException {
        facade.deleteRate(rateId);
        return ResponseEntity.ok().build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createRate(@RequestBody RateDto rateDto) {
        facade.createRate(rateDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<RateDto> updateRate(@RequestBody RateDto rateDto) {
        return ResponseEntity.ok(facade.updateRate(rateDto));
    }
}
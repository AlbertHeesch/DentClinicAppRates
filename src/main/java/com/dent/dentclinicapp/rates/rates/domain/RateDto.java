package com.dent.dentclinicapp.rates.rates.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
public class RateDto
{
    private Long id;
    private String name;
    private Double value;
}

package com.dent.dentclinicapp.rates.rates.controller;

import com.dent.dentclinicapp.rates.rates.domain.RateDto;
import com.dent.dentclinicapp.rates.rates.facade.RateFacade;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(RateController.class)
class RateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RateFacade facade;

    private final RateDto rateDto1 = new RateDto(1L, "Name1", 1.1);
    private final RateDto rateDto2 = new RateDto(2L, "Name2", 2.2);
    private final RateDto rateDto3 = new RateDto(3L, "Name3", 3.3);

    private final List<RateDto> rateDtos = List.of(rateDto1, rateDto2, rateDto3);

    @Test
    void shouldFetchEmptyRateBoards() throws Exception {
        // Given
        when(facade.fetchRates()).thenReturn(List.of());

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/rate")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    void shouldFetchRateBoards() throws Exception {
        // Given
        when(facade.fetchRates()).thenReturn(rateDtos);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/rate")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].id", Matchers.is(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("Name1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name", Matchers.is("Name2")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].name", Matchers.is("Name3")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].value", Matchers.is(1.1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].value", Matchers.is(2.2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].value", Matchers.is(3.3)));
    }

    @Test
    void shouldFetchRate() throws Exception {
        // Given
        when(facade.fetchRate(any(long.class))).thenReturn(rateDto1);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/rate/" + "1")
                        .characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Name1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.value", Matchers.is(1.1)));
    }

    @Test
    void shouldDeleteRate() throws Exception {
        // Given
        doNothing().when(facade).deleteRate(any(long.class));

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/rate" + "/1"))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldCreateRate() throws Exception {
        // Given
        doNothing().when(facade).createRate(any(RateDto.class));

        Gson gson = new Gson();
        String jsonContent = gson.toJson(rateDto1);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/rate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldUpdateRate() throws Exception {
        // Given
        when(facade.updateRate(any(RateDto.class))).thenReturn(rateDto1);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(rateDto1);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/rate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Name1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.value", Matchers.is(1.1)));
    }
}
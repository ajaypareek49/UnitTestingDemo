package com.epam.jamp2.service.impl;

import com.epam.jamp2.model.UnknownCurrencyException;
import com.epam.jamp2.service.FxRatesService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by Alexey on 05.12.2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FxRatesServiceImpIT {
    private MockMvc mockMvc;

    @Autowired
    FxRatesService underTest;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Before
    public void before() {
        mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void shouldConvert() throws IOException, UnknownCurrencyException {
        underTest.convert("usd", "rub", BigDecimal.valueOf(1));
    }

    @Test(expected = UnknownCurrencyException.class)
    public void shouldThrowExceptionForWrongCcy() throws IOException, UnknownCurrencyException {
        underTest.convert("btc", "rub", BigDecimal.valueOf(1));
    }

    @Test
    public void should() throws Exception {
        mockMvc.perform(get("test")).andExpect(status().isNotFound());
    }
}

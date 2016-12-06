package com.epam.jamp2.service.impl;

import com.epam.jamp2.model.UnknownCurrencyException;
import com.epam.jamp2.service.FxRatesService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * Created by Alexey on 05.12.2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FxRatesServiceImpIT {
    @Autowired
    FxRatesService underTest;

    @Test
    public void shouldConvert() throws IOException, UnknownCurrencyException {
        underTest.convert("usd", "rub", BigDecimal.valueOf(1));
    }

    @Test(expected = UnknownCurrencyException.class)
    public void shouldThrowExceptionForWrongCcy() throws IOException, UnknownCurrencyException {
        underTest.convert("btc", "rub", BigDecimal.valueOf(1));
    }
}

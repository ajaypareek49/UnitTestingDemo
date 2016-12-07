package com.epam.jamp2.model;

import org.junit.Test;

import java.math.BigDecimal;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by Alexey on 05.12.2016.
 */
public class CalculationCommandTest {
    @Test
    public void shouldParseFromString() throws CommandFormatException {
        CalculationCommand parsedCommand = CalculationCommand.parseFromString("usd100.5+200.99654=");
        assertEquals("usd", parsedCommand.getLeftOperand().getCurrencyCode().get());
        assertThat(parsedCommand.getLeftOperand().getValue(), is(equalTo(BigDecimal.valueOf(100.5))));
        assertTrue(!parsedCommand.getRightOperand().getCurrencyCode().isPresent());
        assertTrue(parsedCommand.getRightOperand().getValue().equals(BigDecimal.valueOf(200.99654)));
        assertTrue(parsedCommand.getOperation().equals(Operation.ADD));
        assertTrue(!parsedCommand.getResultCurrencyCode().isPresent());
    }

    @Test(expected = CommandFormatException.class)
    public void shouldThrowFormatExceptionWhenWrongFormat() throws CommandFormatException {
        CalculationCommand parsedCommand = CalculationCommand.parseFromString("test");
    }
}

package com.epam.jamp2.service.impl;

import com.epam.jamp2.model.CalculationCommand;
import com.epam.jamp2.model.Operation;
import com.epam.jamp2.model.UnknownCurrencyException;
import com.epam.jamp2.model.Value;
import com.epam.jamp2.service.CalculationCommandExecutionService;
import com.epam.jamp2.service.FxRatesService;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by Alexey on 06.12.2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class CalculationExecutionServiceImplTest {
    @Mock
    FxRatesService fxRatesService;

    @InjectMocks
    CalculationCommandExecutionServiceImpl underTest;

    @Test
    public void shouldDoSimpleCalculationWithoutConversion() {
        Value operand = new Value(null, BigDecimal.ONE);
        CalculationCommand command = new CalculationCommand(operand, operand, Operation.ADD, null);
        assertThat(underTest.calculate(command).getValue(), is(equalTo(BigDecimal.valueOf(2))));
        verifyZeroInteractions(fxRatesService);
    }

    @Test
    public void shouldDoConversionWhenNeededOnly() throws IOException, UnknownCurrencyException {
        when(fxRatesService.convert(any(), any(), any())).thenReturn(BigDecimal.valueOf(1));

        Value leftOperand = new Value("rub", BigDecimal.ONE);
        Value rightOperand = new Value("usd", BigDecimal.ONE);
        CalculationCommand command = new CalculationCommand(leftOperand, rightOperand, Operation.ADD, "rub");

        underTest.calculate(command);
        verify(fxRatesService, times(1)).convert(any(), any(), any());
    }
}

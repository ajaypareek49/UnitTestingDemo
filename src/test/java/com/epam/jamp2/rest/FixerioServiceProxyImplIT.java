package com.epam.jamp2.rest;

import com.epam.jamp2.model.FixerioResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FixerioServiceProxyImplIT {
	@Autowired
    FixerioServiceProxy underTest;

	@Test
    public void shouldReturnRates() throws IOException {
	    String currencyCode = "USD";
        FixerioResponse response = underTest.getRates(currencyCode).execute().body();
        assertEquals(currencyCode, response.getBase());
        assertTrue(response.getRates().size() > 0);
    }
}

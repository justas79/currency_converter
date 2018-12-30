package com.curencyconv.enumas.currencyconverter;

import com.enumas.curconv.mvp.utils.Utils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Local unit tests, which will execute on the development machine (host).
 */
public class UnitTests {
    /**
     * Tests if parsing done right
     */
    @Test
    public void testUtilConversion() {
        assertEquals(Double.valueOf(2), Utils.parseRate("2"));
        assertEquals(Double.valueOf(2), Utils.parseRate("2.0"));
        assertEquals(Double.valueOf(2.3), Utils.parseRate("2.3"));
        assertEquals(Double.valueOf(22419.344692), Utils.parseRate("22419.344692"));
        assertEquals(Double.valueOf(0.4311042), Utils.parseRate("0.4311042"));
    }

    /**
     * Tests if formatter returns correct string from Double
     */
    @Test
    public void testUtilFormat() {
        assertEquals("2.2", Utils.formatRate(2.2));
        assertEquals("2.2", Utils.formatRate(2.20));
        assertEquals("0.12345", Utils.formatRate(0.12345241234132));
    }
}
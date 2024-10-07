package net.htlgrieskirchen.pos3.streams;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StatisticsTest {

    @Test
    public void testAverage() {
        int[] numbers = {1, 2, 3, 4, 5};
        assertEquals(3.0, Statistics.average(numbers), 0.001);

        int[] emptyNumbers = {};
        assertEquals(0.0, Statistics.average(emptyNumbers), 0.001);
    }
}
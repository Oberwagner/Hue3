package net.htlgrieskirchen.pos3.streams;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {
    @Test
    public void testUpperCase() {
        String[] input = {"hello", "world"};
        List<String> result = StringUtils.upperCase(input);
        assertEquals(Arrays.asList("HELLO", "WORLD"), result);
    }
}
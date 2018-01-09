package tests.models;

import com.colonolnutty.module.shareddata.models.BaseConfig;
import org.junit.Test;

import static junit.framework.TestCase.*;

/**
 * User: Jack's Computer
 * Date: 01/07/2018
 * Time: 1:07 PM
 */
public class BaseConfigTests {
    private TestConfig _config;

    public BaseConfigTests() {
        _config = new TestConfig();
    }

    @Test
    public void should_give_null_for_null_name() {
        String expectedResult = "";
        String name = null;
        boolean escapeValue = false;
        String value = "Something";
        String result = _config.formatStrings(name, escapeValue, value);
        assertEquals(expectedResult, result);
    }

    @Test
    public void should_give_empty_string_for_empty_name() {
        String expectedResult = "";
        String name = "";
        boolean escapeValue = false;
        String value = "Something";
        String result = _config.formatStrings(name, escapeValue, value);
        assertEquals(expectedResult, result);
    }

    @Test
    public void should_give_empty_string_for_whitespace_name() {
        String expectedResult = "";
        String name = "  ";
        boolean escapeValue = false;
        String value = "Something";
        String result = _config.formatStrings(name, escapeValue, value);
        assertEquals(expectedResult, result);
    }

    @Test
    public void should_format_string() {
        String expectedResult = " .one \"what\"";
        String name ="one";
        boolean escapeValue = true;
        String value = "what";
        String result = _config.formatStrings(name, escapeValue, value);
        assertEquals(expectedResult, result);
    }

    @Test
    public void should_format_unescaped_string() {
        String expectedResult = " .one what";
        String name ="one";
        boolean escapeValue = false;
        String value = "what";
        String result = _config.formatStrings(name, escapeValue, value);
        assertEquals(expectedResult, result);
    }

    @Test
    public void should_format_multiple_strings() {
        String expectedResult = " .one \"what\" \"what2\"";
        String name ="one";
        boolean escapeValue = true;
        String value = "what";
        String valueTwo = "what2";
        String result = _config.formatStrings(name, escapeValue, value, valueTwo);
        assertEquals(expectedResult, result);
    }

    @Test
    public void should_format_double_no_decimals_exclude_percent() {
        String expectedResult = " .one 24.0";
        String name ="one";
        Double value = 24.0;
        String result = _config.formatDoubles(name, false, false, value);
        assertEquals(expectedResult, result);
    }

    @Test
    public void should_format_double_no_decimals() {
        String expectedResult = " .one 24%";
        String name ="one";
        boolean escapeValue = false;
        Double value = 24.0;
        String result = _config.formatDoubles(name, true, true, value);
        assertEquals(expectedResult, result);
    }

    @Test
    public void should_format_double_decimals() {
        String expectedResult = " .one 24.5%";
        String name ="one";
        Double value = 24.5;
        String result = _config.formatDoubles(name, false, true, value);
        assertEquals(expectedResult, result);
    }

    @Test
    public void should_format_integer() {
        String expectedResult = " .one 20";
        String name ="one";
        Integer value = 20;
        String result = _config.formatObjects(name, value);
        assertEquals(expectedResult, result);
    }

    @Test
    public void should_format_boolean() {
        String expectedResult = " .one true";
        String name ="one";
        Boolean value = true;
        String result = _config.formatObjects(name, value);
        assertEquals(expectedResult, result);
    }

    private class TestConfig extends BaseConfig {

    }
}

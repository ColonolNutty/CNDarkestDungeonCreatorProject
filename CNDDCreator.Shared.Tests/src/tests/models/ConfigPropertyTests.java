package tests.models;

import com.colonolnutty.module.shareddata.models.ConfigProperty;
import com.colonolnutty.module.shareddata.models.PropertyName;
import com.colonolnutty.module.shareddata.models.ValueType;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

/**
 * User: Jack's Computer
 * Date: 01/13/2018
 * Time: 3:13 PM
 */
public class ConfigPropertyTests {
    private ConfigProperty _property;

    public ConfigPropertyTests() {
        _property = new ConfigProperty(PropertyName.name, null);
    }

    @Test
    public void toString_should_format_properly() {
        String expectedResult = ".name \"one\" \"two\" \"three\"";
        ArrayList<String> values = new ArrayList<String>();
        values.add("one");
        values.add("two");
        values.add("three");
        ConfigProperty property = new ConfigProperty(PropertyName.name, values);
        String result = property.toString();
        assertEquals(expectedResult, result);
    }

    @Test
    public void should_format_string_escacpe_property() {
        String expectedResult = "\"what\"";
        String value = "what";
        String result = _property.formatValue(value, ValueType.StrEsc);
        assertEquals(expectedResult, result);
    }

    @Test
    public void should_format_string_non_escape_property() {
        String expectedResult = "what";
        String value = "what";
        String result = _property.formatValue(value, ValueType.StrNonEsc);
        assertEquals(expectedResult, result);
    }

    @Test
    public void should_format_double() {
        String expectedResult = "24.0";
        String value = "24.0";
        String result = _property.formatValue(value, ValueType.Dbl);
        assertEquals(expectedResult, result);
    }

    @Test
    public void should_format_double_percent() {
        String expectedResult = "24.0%";
        String value = "24.0";
        String result = _property.formatValue(value, ValueType.DblPerc);
        assertEquals(expectedResult, result);
    }

    @Test
    public void should_format_integer() {
        String expectedResult = "24";
        String value = "24";
        String result = _property.formatValue(value, ValueType.Int);
        assertEquals(expectedResult, result);
    }

    @Test
    public void should_format_bool() {
        String expectedResult = "true";
        String value = "true";
        String result = _property.formatValue(value, ValueType.Bool);
        assertEquals(expectedResult, result);
    }

    @Test
    public void should_format_unknown() {
        String expectedResult = "\" nonono";
        String value = "\" nonono";
        String result = _property.formatValue(value, ValueType.Unk);
        assertEquals(expectedResult, result);
    }
}

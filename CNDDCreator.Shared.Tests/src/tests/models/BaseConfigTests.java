package tests.models;

import com.colonolnutty.module.shareddata.models.BaseConfig;
import com.colonolnutty.module.shareddata.models.ConfigProperty;
import com.colonolnutty.module.shareddata.models.PropertyName;
import com.colonolnutty.module.shareddata.models.ValueType;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.*;

/**
 * User: Jack's Computer
 * Date: 01/07/2018
 * Time: 1:07 PM
 */
public class BaseConfigTests {
    private BaseConfig _config;

    public BaseConfigTests() {
        _config = BaseConfig.parseEmpty();
    }

    @Test
    public void should_format_toString_properly() {
        String line = "armour: .name \"some armor_1\" \"what\" .def 15% 20% .card_chance 2.5 2.8 .hp 35 40 .spd 3 .upgradeRequirementCode 1 .self_target_valid true .target  .spd 5";
        BaseConfig baseConfig = BaseConfig.parse(line);
        String result = baseConfig.toString();
        assertTrue(result.contains(" .name \"some armor_1\" \"what\""));
        assertTrue(result.contains(" .def 15% 20%"));
        assertTrue(result.contains(" .card_chance 2.5 2.8"));
        assertTrue(result.contains(" .hp 35 40"));
        assertTrue(result.contains(" .spd 3"));
        assertTrue(result.contains(" .upgradeRequirementCode 1"));
        assertTrue(result.contains(" .self_target_valid true"));
        assertTrue(result.contains(" .target  "));
        assertTrue(result.contains(" .spd 5"));
        BaseConfig baseConfigTwo = BaseConfig.parse(result);
        assertEquals(result, baseConfigTwo.toString());
    }

    @Test
    public void should_format_properties() {
        String line = "armour: .name \"some armor_1\" \"what\" .def 15% 20% .prot 2.5 2.8 .hp 35 40 .spd 3 .upgradeRequirementCode 1 .level true .target  .crit 5";
        BaseConfig baseConfig = BaseConfig.parse(line);
        assertContains(baseConfig.getProperty(PropertyName.name), "some armor_1", "what");
        assertContains(baseConfig.getProperty(PropertyName.def), "15", "20");
        assertContains(baseConfig.getProperty(PropertyName.prot), "2.5", "2.8");
        assertContains(baseConfig.getProperty(PropertyName.hp), "35", "40");
        assertContains(baseConfig.getProperty(PropertyName.spd), "3");
        assertContains(baseConfig.getProperty(PropertyName.upgradeRequirementCode), "1");
        assertContains(baseConfig.getProperty(PropertyName.level), "true");
        assertContains(baseConfig.getProperty(PropertyName.target), " ");
        assertContains(baseConfig.getProperty(PropertyName.crit), "5");
    }

    private void assertContains(ConfigProperty property, String... vals) {
        ArrayList<String> arr = property.getValues();
        for(String val : vals) {
            assertTrue(arr.contains(val));
        }
    }
}

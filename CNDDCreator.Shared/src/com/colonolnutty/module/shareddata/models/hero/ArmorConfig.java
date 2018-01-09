package com.colonolnutty.module.shareddata.models.hero;

import com.colonolnutty.module.shareddata.models.BaseConfig;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * User: Jack's Computer
 * Date: 01/07/2018
 * Time: 12:24 PM
 */
public class ArmorConfig extends BaseConfig {
    public String Name;
    public Double Defense;
    public Double Protection;
    public Integer Health;
    public Integer Speed;
    public Integer UpgradeRequirementCode;

    public static String getName() {
        return "armour";
    }

    public static boolean canParse(String str) {
        return str.startsWith(getName());
    }

    public static ArmorConfig parse(String str) {
        if(!canParse(str)) {
            return new ArmorConfig();
        }

        Hashtable<String, ArrayList<Object>> properties = parseProperties(getName(), str);

        ArmorConfig config = new ArmorConfig();

        config.Name = getSingleOrNull(properties, "name");
        config.Defense = getSingleOrNull(properties, "def");
        config.Protection = getSingleOrNull(properties, "prot");
        config.Health = getSingleOrNull(properties, "hp");
        config.Speed = getSingleOrNull(properties, "spd");
        config.UpgradeRequirementCode = getSingleOrNull(properties, "upgradeRequirementCode");

        return config;
    }

    @Override
    public String toString() {
        String startingStr = getName() + ":";
        StringBuilder builder = new StringBuilder();
        builder.append(startingStr);

        builder.append(formatStrings("name", true, Name));
        builder.append(formatDoubles("def", false, true, Defense));
        builder.append(formatDoubles("prot", false, true, Protection));
        builder.append(formatObjects("hp", Health));
        builder.append(formatObjects("spd", Speed));
        builder.append(formatObjects("upgradeRequirementCode", UpgradeRequirementCode));

        String result = builder.toString();
        if(result.equals(startingStr)) {
            return null;
        }
        return builder.toString();
    }
}

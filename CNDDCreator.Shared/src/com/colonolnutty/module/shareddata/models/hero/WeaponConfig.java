package com.colonolnutty.module.shareddata.models.hero;

import com.colonolnutty.module.shareddata.models.BaseConfig;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * User: Jack's Computer
 * Date: 01/07/2018
 * Time: 12:22 PM
 */
public class WeaponConfig extends BaseConfig {
    public String Name;
    public Double Attack;
    public Integer DamageMin;
    public Integer DamageMax;
    public Double Critical;
    public Integer Speed;
    public Integer UpgradeRequirementCode;

    public static String getName() {
        return "weapon";
    }

    public static boolean canParse(String str) {
        return str.startsWith(getName());
    }

    public static WeaponConfig parse(String str) {
        if(!canParse(str)) {
            return new WeaponConfig();
        }

        Hashtable<String, ArrayList<Object>> properties = parseProperties(getName(), str);

        WeaponConfig config = new WeaponConfig();

        config.Name = getSingleOrNull(properties, "name");
        config.Attack = getSingleOrNull(properties, "atk");
        ArrayList<Integer> damage = getOrNull(properties, "dmg");
        if(damage != null && damage.size() == 2) {
            config.DamageMin = damage.get(0);
            config.DamageMax = damage.get(1);
        }
        config.Critical = getSingleOrNull(properties, "crit");
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
        builder.append(formatDoubles("atk", true, true, Attack));
        builder.append(formatObjects("dmg", DamageMin, DamageMax));
        builder.append(formatDoubles("crit", false, true, Critical));
        builder.append(formatObjects("spd", Speed));
        builder.append(formatObjects("upgradeRequirementCode", UpgradeRequirementCode));

        String result = builder.toString();
        if(result.equals(startingStr)) {
            return null;
        }
        return builder.toString();
    }
}

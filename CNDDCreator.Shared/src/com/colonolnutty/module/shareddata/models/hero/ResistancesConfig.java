package com.colonolnutty.module.shareddata.models.hero;

import com.colonolnutty.module.shareddata.models.BaseConfig;
import com.colonolnutty.module.shareddata.utils.CNStringUtils;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * User: Jack's Computer
 * Date: 01/07/2018
 * Time: 12:39 PM
 */
public class ResistancesConfig extends BaseConfig {
    public Double Stun;
    public Double Poison;
    public Double Bleed;
    public Double Disease;
    public Double Move;
    public Double Debuff;
    public Double DeathBlow;
    public Double Trap;

    public static String getName() {
        return "resistances";
    }

    public static boolean canParse(String str) {
        return str.startsWith(getName());
    }

    public static ResistancesConfig parse(String str) {
        if(!canParse(str)) {
            return new ResistancesConfig();
        }

        Hashtable<String, ArrayList<Object>> properties = parseProperties(getName(), str);

        ResistancesConfig config = new ResistancesConfig();

        config.Stun = getSingleOrNull(properties, "stun");
        config.Poison = getSingleOrNull(properties, "poison");
        config.Bleed = getSingleOrNull(properties, "bleed");
        config.Disease = getSingleOrNull(properties, "disease");
        config.Move = getSingleOrNull(properties, "move");
        config.Debuff = getSingleOrNull(properties, "debuff");
        config.DeathBlow = getSingleOrNull(properties, "death_blow");
        config.Trap = getSingleOrNull(properties, "trap");

        return config;
    }

    @Override
    public String toString() {
        String startingStr = getName() + ":";
        StringBuilder builder = new StringBuilder();
        builder.append(startingStr);

        builder.append(formatDoubles("stun", false, true, Stun));
        builder.append(formatDoubles("poison", false, true, Poison));
        builder.append(formatDoubles("bleed", false, true, Bleed));
        builder.append(formatDoubles("disease", false, true, Disease));
        builder.append(formatDoubles("move", false, true, Move));
        builder.append(formatDoubles("debuff", false, true, Debuff));
        builder.append(formatDoubles("death_blow", false, true, DeathBlow));
        builder.append(formatDoubles("trap", false, true, Trap));

        String result = builder.toString();
        if(result.equals(startingStr)) {
            return null;
        }

        return builder.toString();
    }
}

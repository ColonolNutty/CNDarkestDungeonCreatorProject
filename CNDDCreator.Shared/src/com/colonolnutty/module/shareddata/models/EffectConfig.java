package com.colonolnutty.module.shareddata.models;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * User: Jack's Computer
 * Date: 01/09/2018
 * Time: 11:22 AM
 */
public class EffectConfig extends BaseConfig {
    public String Name;
    public EffectTarget Target;
    public String CurioResultType;
    public Double Chance;
    public Integer HealStress;
    public Boolean OnHit;
    public Boolean OnMiss;
    public Boolean Queue;

    public static String getName() {
        return "deaths_door";
    }

    public static boolean canParse(String str) {
        return str.startsWith(getName());
    }

    public static EffectConfig parse(String str) {
        if(!canParse(str)) {
            return new EffectConfig();
        }

        Hashtable<String, ArrayList<Object>> properties = parseProperties(getName(), str);

        EffectConfig config = new EffectConfig();

        config.Name = getSingleOrNull(properties, "name");
        config.Target = getSingleOrNull(properties, "target");
        config.CurioResultType = getSingleOrNull(properties, "curio_result_type");
        config.Chance = getSingleOrNull(properties, "chance");
        config.HealStress = getSingleOrNull(properties, "healstress");
        config.OnHit = getSingleOrNull(properties, "on_hit");
        config.OnMiss = getSingleOrNull(properties, "on_miss");
        config.Queue = getSingleOrNull(properties, "queue");

        return config;
    }


    @Override
    public String toString() {
        String startingStr = getName() + ":";
        StringBuilder builder = new StringBuilder();
        builder.append(startingStr);

        builder.append(formatStrings("name", true, Name));
        builder.append(formatStrings("target", true, Target.toString()));
        builder.append(formatStrings("curio_result_type", true, CurioResultType));
        builder.append(formatDoubles("chance", false, true, Chance));
        builder.append(formatObjects("healstress", HealStress));
        builder.append(formatObjects("on_hit", OnHit));
        builder.append(formatObjects("on_miss", OnMiss));
        builder.append(formatObjects("queue", Queue));

        String result = builder.toString();
        if(result.equals(startingStr)) {
            return null;
        }
        return builder.toString();
    }
}

package com.colonolnutty.module.shareddata.models.hero;

import com.colonolnutty.module.shareddata.models.BaseConfig;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * User: Jack's Computer
 * Date: 01/07/2018
 * Time: 12:32 PM
 */
public class ControlledConfig extends BaseConfig {
    public Integer TargetRank;

    public static String getName() {
        return "controlled";
    }

    public static boolean canParse(String str) {
        return str.startsWith(getName());
    }

    public static ControlledConfig parse(String str) {
        if(!canParse(str)) {
            return new ControlledConfig();
        }

        Hashtable<String, ArrayList<Object>> properties = parseProperties(getName(), str);

        ControlledConfig config = new ControlledConfig();

        config.TargetRank = getSingleOrNull(properties, "target_rank");

        return config;
    }

    @Override
    public String toString() {
        String startingStr = getName() + ":";
        StringBuilder builder = new StringBuilder();
        builder.append(startingStr);

        builder.append(formatObjects("target_rank", TargetRank));

        String result = builder.toString();
        if(result.equals(startingStr)) {
            return null;
        }
        return builder.toString();
    }
}

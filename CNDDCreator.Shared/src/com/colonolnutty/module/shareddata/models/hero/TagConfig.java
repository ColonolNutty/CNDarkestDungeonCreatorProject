package com.colonolnutty.module.shareddata.models.hero;

import com.colonolnutty.module.shareddata.models.BaseConfig;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * User: Jack's Computer
 * Date: 01/07/2018
 * Time: 12:36 PM
 */
public class TagConfig extends BaseConfig {
    public String Id;

    public static String getName() {
        return "tag";
    }

    public static boolean canParse(String str) {
        return str.startsWith(getName());
    }

    public static TagConfig parse(String str) {
        if(!canParse(str)) {
            return new TagConfig();
        }

        Hashtable<String, ArrayList<Object>> properties = parseProperties(getName(), str);

        TagConfig config = new TagConfig();

        config.Id = getSingleOrNull(properties, "id");

        return config;
    }

    @Override
    public String toString() {
        String startingStr = getName() + ":";
        StringBuilder builder = new StringBuilder();
        builder.append(startingStr);

        builder.append(formatStrings("id", true, Id));

        String result = builder.toString();
        if(result.equals(startingStr)) {
            return null;
        }
        return builder.toString();
    }
}

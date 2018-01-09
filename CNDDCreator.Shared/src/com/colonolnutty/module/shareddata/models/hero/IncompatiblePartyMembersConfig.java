package com.colonolnutty.module.shareddata.models.hero;

import com.colonolnutty.module.shareddata.models.BaseConfig;
import com.colonolnutty.module.shareddata.utils.CNCollectionUtils;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * User: Jack's Computer
 * Date: 01/07/2018
 * Time: 12:33 PM
 */
public class IncompatiblePartyMembersConfig extends BaseConfig {
    public String Id;
    public ArrayList<String> HeroTags;

    public static String getName() {
        return "incompatible_party_member";
    }

    public static boolean canParse(String str) {
        return str.startsWith(getName());
    }

    public static IncompatiblePartyMembersConfig parse(String str) {
        if(!canParse(str)) {
            return new IncompatiblePartyMembersConfig();
        }

        Hashtable<String, ArrayList<Object>> properties = parseProperties(getName(), str);

        IncompatiblePartyMembersConfig config = new IncompatiblePartyMembersConfig();

        config.Id = getSingleOrNull(properties, "id");
        config.HeroTags = getOrNull(properties, "hero_tag");

        return config;
    }

    @Override
    public String toString() {
        String startingStr = getName() + ":";
        StringBuilder builder = new StringBuilder();
        builder.append(startingStr);

        builder.append(formatStrings("id", false, Id));
        builder.append(formatStrings("hero_tag", false, CNCollectionUtils.toStringArray(HeroTags)));

        String result = builder.toString();
        if(result.equals(startingStr)) {
            return null;
        }
        return builder.toString();
    }
}

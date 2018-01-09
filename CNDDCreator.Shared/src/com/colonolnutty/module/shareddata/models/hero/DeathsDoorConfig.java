package com.colonolnutty.module.shareddata.models.hero;

import com.colonolnutty.module.shareddata.models.BaseConfig;
import com.colonolnutty.module.shareddata.utils.CNCollectionUtils;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * User: Jack's Computer
 * Date: 01/07/2018
 * Time: 12:31 PM
 */
public class DeathsDoorConfig extends BaseConfig {
    public ArrayList<String> Buffs;
    public ArrayList<String> RecoveryBuffs;
    public ArrayList<String> RecoveryHeartAttackBuffs;

    public static String getName() {
        return "deaths_door";
    }

    public static boolean canParse(String str) {
        return str.startsWith(getName());
    }

    public static DeathsDoorConfig parse(String str) {
        if(!canParse(str)) {
            return new DeathsDoorConfig();
        }

        Hashtable<String, ArrayList<Object>> properties = parseProperties(getName(), str);

        DeathsDoorConfig config = new DeathsDoorConfig();

        config.Buffs = getOrNull(properties, "buffs");
        config.RecoveryBuffs = getOrNull(properties, "recovery_buffs");
        config.RecoveryHeartAttackBuffs = getOrNull(properties, "recovery_heart_attack_buffs");

        return config;
    }


    @Override
    public String toString() {
        String startingStr = getName() + ":";
        StringBuilder builder = new StringBuilder();
        builder.append(startingStr);

        builder.append(formatStrings("buffs", false, CNCollectionUtils.toStringArray(Buffs)));
        builder.append(formatStrings("recovery_buffs", false, CNCollectionUtils.toStringArray(RecoveryBuffs)));
        builder.append(formatStrings("recovery_heart_attack_buffs", false, CNCollectionUtils.toStringArray(RecoveryHeartAttackBuffs)));

        String result = builder.toString();
        if(result.equals(startingStr)) {
            return null;
        }
        return builder.toString();
    }
}

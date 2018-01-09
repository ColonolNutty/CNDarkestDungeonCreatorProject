package com.colonolnutty.module.shareddata.models.hero;

import com.colonolnutty.module.shareddata.models.BaseConfig;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * User: Jack's Computer
 * Date: 01/07/2018
 * Time: 12:37 PM
 */
public class SkillSelectionConfig extends BaseConfig {
    public Boolean CanSelectCombatSkills;
    public Integer NumberOfSelectedCombatSkillsMax;

    public static String getName() {
        return "skill_selection";
    }

    public static boolean canParse(String str) {
        return str.startsWith(getName());
    }

    public static SkillSelectionConfig parse(String str) {
        if(!canParse(str)) {
            return new SkillSelectionConfig();
        }

        Hashtable<String, ArrayList<Object>> properties = parseProperties(getName(), str);

        SkillSelectionConfig config = new SkillSelectionConfig();

        config.CanSelectCombatSkills = getSingleOrNull(properties, "can_select_combat_skills");
        config.NumberOfSelectedCombatSkillsMax = getSingleOrNull(properties, "number_of_selected_combat_skills_max");

        return config;
    }

    @Override
    public String toString() {
        String startingStr = getName() + ":";
        StringBuilder builder = new StringBuilder();
        builder.append(startingStr);

        builder.append(formatObjects("can_select_combat_skills", CanSelectCombatSkills));
        builder.append(formatObjects("number_of_selected_combat_skills_max", NumberOfSelectedCombatSkillsMax));

        String result = builder.toString();
        if(result.equals(startingStr)) {
            return null;
        }
        return builder.toString();
    }
}

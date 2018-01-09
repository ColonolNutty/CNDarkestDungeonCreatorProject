package com.colonolnutty.module.shareddata.models.hero;

import com.colonolnutty.module.shareddata.models.BaseConfig;
import com.colonolnutty.module.shareddata.prettyprinters.BasePrettyPrinter;
import com.colonolnutty.module.shareddata.utils.CNStringUtils;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * User: Jack's Computer
 * Date: 01/07/2018
 * Time: 12:20 PM
 */
public class HeroInfo extends BaseConfig {
    public ResistancesConfig Resistance;
    public ArrayList<WeaponConfig> Weapons;
    public ArrayList<ArmorConfig> Armors;
    public ArrayList<SkillConfig> Skills;
    public ArrayList<TagConfig> Tags;
    public DeathsDoorConfig DeathsDoor;
    public ControlledConfig Controlled;
    public int IdIndex;
    public IncompatiblePartyMembersConfig IncompatiblePartyMembers;
    public SkillSelectionConfig SkillSelection;
    public GenerationConfig Generation;

    public HeroInfo() {
        Weapons = new ArrayList<WeaponConfig>();
        Armors = new ArrayList<ArmorConfig>();
        Skills = new ArrayList<SkillConfig>();
        Tags = new ArrayList<TagConfig>();
    }

    @Override
    public String toString() {
        String startingStr = "";
        StringBuilder builder = new StringBuilder();
        builder.append(startingStr);

        builder.append(getStringOrEmpty(Resistance));
        for(WeaponConfig config : Weapons) {
            builder.append(getStringOrEmpty(config));
        }
        for(ArmorConfig config : Armors) {
            builder.append(getStringOrEmpty(config));
        }
        for(SkillConfig config : Skills) {
            builder.append(getStringOrEmpty(config));
        }
        for(TagConfig config : Tags) {
            builder.append(getStringOrEmpty(config));
        }
        builder.append(getStringOrEmpty(DeathsDoor));
        builder.append(getStringOrEmpty(Controlled));
        builder.append(formatId(formatObjects("index", IdIndex)));
        builder.append(getStringOrEmpty(IncompatiblePartyMembers));
        builder.append(getStringOrEmpty(SkillSelection));
        builder.append(getStringOrEmpty(Generation));

        String result = builder.toString();
        if(result.equals(startingStr)) {
            return null;
        }
        return builder.toString();
    }

    public String getStringOrEmpty(BaseConfig value) {
        if(value == null) {
            return "";
        }
        return getStringOrEmpty(value.toString());
    }


    public String getStringOrEmpty(String value) {
        if(CNStringUtils.isNullOrWhitespace(value)) {
            return "";
        }
        return BasePrettyPrinter.NEW_LINE + value;
    }

    public String formatId(String idResult) {
        if(CNStringUtils.isNullOrWhitespace(idResult)) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        builder.append("id_index:");
        builder.append(getStringOrEmpty(idResult));
        return builder.toString();
    }

    public static String getName() {
        return "id_index";
    }

    public static boolean canParse(String str) {
        return str.startsWith("id_index");
    }

    public static int parseId(String str) {
        if(!canParse(str)) {
            return -1;
        }

        Hashtable<String, ArrayList<Object>> properties = parseProperties(getName(), str);

        return getSingleOrNull(properties, "index");
    }
}

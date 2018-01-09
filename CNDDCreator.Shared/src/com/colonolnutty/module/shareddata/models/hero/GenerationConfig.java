package com.colonolnutty.module.shareddata.models.hero;

import com.colonolnutty.module.shareddata.models.BaseConfig;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * User: Jack's Computer
 * Date: 01/07/2018
 * Time: 12:37 PM
 */
public class GenerationConfig extends BaseConfig {
    public Boolean IsGenerationEnabled;
    public Integer NumberOfPositiveQuirksMin;
    public Integer NumberOfPositiveQuirksMax;
    public Integer NumberOfNegativeQuirksMin;
    public Integer NumberOfNegativeQuirksMax;
    public Integer NumberOfClassSpecificCampingSkills;
    public Integer NumberOfSharedCampingSkills;
    public Integer NumberOfRandomCombatSkills;
    public Integer NumberOfCardsInDeck;
    public Double CardChance;

    public static String getName() {
        return "generation";
    }

    public static boolean canParse(String str) {
        return str.startsWith(getName());
    }

    public static GenerationConfig parse(String str) {
        if(!canParse(str)) {
            return new GenerationConfig();
        }

        Hashtable<String, ArrayList<Object>> properties = parseProperties(getName(), str);

        GenerationConfig config = new GenerationConfig();

        config.IsGenerationEnabled = getSingleOrNull(properties, "is_generation_enabled");
        config.NumberOfPositiveQuirksMin = getSingleOrNull(properties, "number_of_positive_quirks_min");
        config.NumberOfPositiveQuirksMax = getSingleOrNull(properties, "number_of_positive_quirks_max");
        config.NumberOfPositiveQuirksMax = getSingleOrNull(properties, "number_of_positive_quirks_max");
        config.NumberOfNegativeQuirksMin = getSingleOrNull(properties, "number_of_negative_quirks_min");
        config.NumberOfNegativeQuirksMax = getSingleOrNull(properties, "number_of_negative_quirks_max");
        config.NumberOfClassSpecificCampingSkills = getSingleOrNull(properties, "number_of_class_specific_camping_skills");
        config.NumberOfSharedCampingSkills = getSingleOrNull(properties, "number_of_shared_camping_skills");
        config.NumberOfRandomCombatSkills = getSingleOrNull(properties, "number_of_random_combat_skills");
        config.NumberOfCardsInDeck = getSingleOrNull(properties, "number_of_cards_in_deck");
        config.CardChance = getSingleOrNull(properties, "card_chance");

        return config;
    }


    @Override
    public String toString() {
        String startingStr = getName() + ":";
        StringBuilder builder = new StringBuilder();
        builder.append(startingStr);

        builder.append(formatObjects("is_generation_enabled", IsGenerationEnabled));
        builder.append(formatObjects("number_of_positive_quirks_min", NumberOfPositiveQuirksMin));
        builder.append(formatObjects("number_of_positive_quirks_max", NumberOfPositiveQuirksMax));
        builder.append(formatObjects("number_of_negative_quirks_min", NumberOfNegativeQuirksMin));
        builder.append(formatObjects("number_of_negative_quirks_max", NumberOfNegativeQuirksMax));
        builder.append(formatObjects("number_of_class_specific_camping_skills", NumberOfClassSpecificCampingSkills));
        builder.append(formatObjects("number_of_shared_camping_skills", NumberOfSharedCampingSkills));
        builder.append(formatObjects("number_of_random_combat_skills", NumberOfRandomCombatSkills));
        builder.append(formatObjects("number_of_cards_in_deck", NumberOfCardsInDeck));
        builder.append(formatDoubles("card_chance", false, false, CardChance));

        String result = builder.toString();
        if(result.equals(startingStr)) {
            return null;
        }
        return builder.toString();
    }
}

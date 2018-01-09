package com.colonolnutty.module.shareddata.models.buffs;

import com.colonolnutty.module.shareddata.utils.CNCollectionUtils;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * User: Jack's Computer
 * Date: 01/09/2018
 * Time: 11:38 AM
 */
public class BuffsShared {
    private static Hashtable<String, ArrayList<String>> _buffTypes;
    private static ArrayList<String> _ruleTypes;
    private static ArrayList<String> _floatRules;
    private static ArrayList<String> _stringRules;

    public ArrayList<String> getRuleTypes() {
        setupRuleTypes();
        return _ruleTypes;
    }

    public boolean isFloatRule(String rule) {
        setupFloatRules();
        return _floatRules.contains(rule);
    }

    public boolean isStringRule(String rule) {
        setupStringRules();
        return _stringRules.contains(rule);
    }

    private void setupRuleTypes() {
        if(_ruleTypes != null) {
            return;
        }
        ArrayList<String> ruleTypes = new ArrayList<String>();
        _ruleTypes.add("always");
        _ruleTypes.add("afflicted");
        _ruleTypes.add("virtued");
        _ruleTypes.add("meleeonly");
        _ruleTypes.add("rangedonly");
        _ruleTypes.add("firstroundonly");
        _ruleTypes.add("at_deaths_door");
        _ruleTypes.add("in_camp");
        _ruleTypes.add("in_room");
        _ruleTypes.add("in_corridor");
        _ruleTypes.add("walking_backwards");
        _ruleTypes.add("riposte");
        setupFloatRules();
        ruleTypes.addAll(_floatRules);
        setupStringRules();
        ruleTypes.addAll(_stringRules);
        _ruleTypes = ruleTypes;
    }

    private void setupFloatRules() {
        if(_floatRules != null) {
            return;
        }
        _floatRules = new ArrayList<String>();
        _floatRules.add("monsterSize");
        _floatRules.add("lightbelow");
        _floatRules.add("lightabove");
        _floatRules.add("hpbelow");
        _floatRules.add("hpabove");
        _floatRules.add("stress_below");
        _floatRules.add("stress_above");
        _floatRules.add("in_rank");
    }

    private void setupStringRules() {
        if(_stringRules != null) {
            return;
        }
        _stringRules = new ArrayList<String>();
        _stringRules.add("actorStatus");
        _stringRules.add("monsterType");
        _stringRules.add("in_dungeon");
        _stringRules.add("in_activity");
        _stringRules.add("skill");
        _stringRules.add("in_mode");
    }

    public Hashtable<String, ArrayList<String>> getBuffTypes() {
        setup();
        return _buffTypes;
    }

    private void setup() {
        if(_buffTypes != null) {
            return;
        }
        _buffTypes = new Hashtable<String, ArrayList<String>>();
        _buffTypes.put("combat_stat_add", toArrayList("crit_chance", "defense_rating", "protection_rating", "speed_rating"));
        _buffTypes.put("combat_stat_multiply", toArrayList("max_hp", "damage_low", "damage_high"));
        _buffTypes.put("hp_heal_amount", toArrayList());
        _buffTypes.put("hp_heal_percent", toArrayList());
        _buffTypes.put("hp_heal_received_percent", toArrayList());
        _buffTypes.put("stress_dmg_percent", toArrayList());
        _buffTypes.put("stress_heal_percent", toArrayList());
        _buffTypes.put("stress_dmg_received_percent", toArrayList());
        _buffTypes.put("stress_heal_received_percent", toArrayList());
        _buffTypes.put("resistance", toArrayList("stun", "move", "poison", "bleed", "disease", "debuff", "death_blow", "trap"));
        _buffTypes.put("poison_chance", toArrayList());
        _buffTypes.put("bleed_chance", toArrayList());
        _buffTypes.put("stun_chance", toArrayList());
        _buffTypes.put("debuff_chance", toArrayList());
        _buffTypes.put("move_chance", toArrayList());
        _buffTypes.put("resolve_check_percent", toArrayList());
        _buffTypes.put("scouting_chance", toArrayList());
        _buffTypes.put("remove_negative_quirk_chance", toArrayList());
        _buffTypes.put("food_consumption_percent", toArrayList());
        _buffTypes.put("starving_damage_percent", toArrayList());
        _buffTypes.put("resolve_xp_bonus_percent", toArrayList());
        _buffTypes.put("upgrade_discount_weapon", toArrayList());
        _buffTypes.put("upgrade_discount_armour", toArrayList());
        _buffTypes.put("party_surprise_chance", toArrayList());
        _buffTypes.put("monsters_surprise_chance", toArrayList());
        _buffTypes.put("damage_received_percent", toArrayList());
    }

    private ArrayList<String> toArrayList(String... values) {
        return CNCollectionUtils.toStringArrayList(values);
    }
}

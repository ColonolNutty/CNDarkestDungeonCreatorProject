package com.colonolnutty.module.shareddata.models;

import java.util.Hashtable;

/**
 * User: Jack's Computer
 * Date: 01/13/2018
 * Time: 2:49 PM
 */
public class PropertyValueTypes {
    private static Hashtable<PropertyName, ValueType> _values;

    public static ValueType getValueType(PropertyName propertyName) {
        setup();
        if(!_values.containsKey(propertyName)) {
            return null;
        }
        return _values.get(propertyName);
    }

    private static void setup() {
        if(_values != null) {
            return;
        }
        _values = new Hashtable<PropertyName, ValueType>();
        _values.put(PropertyName.stun, ValueType.DblPerc);
        _values.put(PropertyName.poison, ValueType.DblPerc);
        _values.put(PropertyName.bleed, ValueType.DblPerc);
        _values.put(PropertyName.disease, ValueType.DblPerc);
        _values.put(PropertyName.moveResist, ValueType.DblPerc);
        _values.put(PropertyName.debuff, ValueType.DblPerc);
        _values.put(PropertyName.death_blow, ValueType.DblPerc);
        _values.put(PropertyName.trap, ValueType.DblPerc);
        _values.put(PropertyName.id, ValueType.StrEsc);
        _values.put(PropertyName.upgradeRequirementCode, ValueType.Int);
        _values.put(PropertyName.name, ValueType.StrEsc);
        _values.put(PropertyName.spd, ValueType.Int);
        _values.put(PropertyName.atk, ValueType.DblPerc);
        _values.put(PropertyName.dmg, ValueType.DblPerc);
        _values.put(PropertyName.crit, ValueType.DblPerc);
        _values.put(PropertyName.def, ValueType.DblPerc);
        _values.put(PropertyName.prot, ValueType.DblPerc);
        _values.put(PropertyName.hp, ValueType.Int);
        _values.put(PropertyName.level, ValueType.Int);
        _values.put(PropertyName.type, ValueType.StrEsc);
        _values.put(PropertyName.launch, ValueType.Int);
        _values.put(PropertyName.target, ValueType.StrNonEsc);
        _values.put(PropertyName.move, ValueType.Int);
        _values.put(PropertyName.heal, ValueType.Int);
        _values.put(PropertyName.is_crit_valid, ValueType.Bool);
        _values.put(PropertyName.self_target_valid, ValueType.Bool);
        _values.put(PropertyName.generation_guaranteed, ValueType.Bool);
        _values.put(PropertyName.per_battle_limit, ValueType.Bool);
        _values.put(PropertyName.effect, ValueType.StrEsc);
        _values.put(PropertyName.index, ValueType.Int);
        _values.put(PropertyName.buffs, ValueType.StrNonEsc);
        _values.put(PropertyName.recovery_buffs, ValueType.StrNonEsc);
        _values.put(PropertyName.recovery_heart_attack_buffs, ValueType.StrNonEsc);
        _values.put(PropertyName.target_rank, ValueType.Int);
        _values.put(PropertyName.can_select_combat_skills, ValueType.Bool);
        _values.put(PropertyName.number_of_selected_combat_skills_max, ValueType.Int);
        _values.put(PropertyName.is_generation_enabled, ValueType.Bool);
        _values.put(PropertyName.number_of_positive_quirks_min, ValueType.Int);
        _values.put(PropertyName.number_of_positive_quirks_max, ValueType.Int);
        _values.put(PropertyName.number_of_negative_quirks_min, ValueType.Int);
        _values.put(PropertyName.number_of_negative_quirks_max, ValueType.Int);
        _values.put(PropertyName.number_of_class_specific_camping_skills, ValueType.Int);
        _values.put(PropertyName.number_of_shared_camping_skills, ValueType.Int);
        _values.put(PropertyName.number_of_random_combat_skills, ValueType.Int);
        _values.put(PropertyName.number_of_cards_in_deck, ValueType.Int);
        _values.put(PropertyName.card_chance, ValueType.Dbl);
    }
}

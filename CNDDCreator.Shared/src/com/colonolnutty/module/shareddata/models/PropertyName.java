package com.colonolnutty.module.shareddata.models;

/**
 * User: Jack's Computer
 * Date: 01/13/2018
 * Time: 1:23 PM
 */
public enum PropertyName {
    //resistances
    stun,
    poison,
    bleed,
    moveResist,
    disease,
    debuff,
    death_blow,
    trap,
    //weapon, armour, combat skill, id
    id,
    //weapon, armour, combat skill
    //shared
    upgradeRequirementCode,
    //weapon, armour
    name,
    spd,
    //weapon
    atk,
    dmg,
    crit,
    //armour
    def,
    prot,
    hp,
    //combat skill
    level,
    type,
    launch,
    target,
    move,
    heal,
    is_crit_valid,
    self_target_valid,
    generation_guaranteed,
    per_battle_limit,
    effect,
    //id_index
    index,
    //deaths_door
    buffs,
    recovery_buffs,
    recovery_heart_attack_buffs,
    //controlled
    target_rank,
    //skill_selection
    can_select_combat_skills,
    number_of_selected_combat_skills_max,
    //generation
    is_generation_enabled,
    number_of_positive_quirks_min,
    number_of_positive_quirks_max,
    number_of_negative_quirks_min,
    number_of_negative_quirks_max,
    number_of_class_specific_camping_skills,
    number_of_shared_camping_skills,
    number_of_random_combat_skills,
    number_of_cards_in_deck,
    card_chance
}

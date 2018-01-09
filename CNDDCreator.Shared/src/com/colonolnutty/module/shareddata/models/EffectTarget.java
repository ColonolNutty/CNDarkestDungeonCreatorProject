package com.colonolnutty.module.shareddata.models;

/**
 * User: Jack's Computer
 * Date: 01/09/2018
 * Time: 11:28 AM
 */
public enum EffectTarget {
    //This effect will target the hero/enemy it is assigned to.
    performer,
    //This effect will target the hero/enemy in line with the skill's targeting parameters.
    target,
    //This effect will target the entire hero/enemy party it is assigned to
    performer_group,
    //This effect will target the entire hero/enemy party in line with the skill's targeting parameters.
    target_group,
    //This effect will target the entire hero/enemy party it is assigned to apart from the performer.
    performer_group_other,
    //This effect will target the entire hero/enemy party in line with the skill's targeting parameters apart from the target.
    target_group_other,
    //This is reserved for light modifier effects.
    global
}

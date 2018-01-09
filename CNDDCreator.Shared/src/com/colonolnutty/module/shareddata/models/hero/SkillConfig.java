package com.colonolnutty.module.shareddata.models.hero;

import com.colonolnutty.module.shareddata.models.BaseConfig;
import com.colonolnutty.module.shareddata.utils.CNCollectionUtils;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * User: Jack's Computer
 * Date: 01/07/2018
 * Time: 12:26 PM
 */
public class SkillConfig extends BaseConfig {
    public SkillType Name;
    public String Id;
    public Integer Level;
    public SkillAttackType Type;
    public Integer HealMin;
    public Integer HealMax;
    public Double Attack;
    public Double Damage;
    public Double Critical;
    public Integer Launch;
    public String Target;
    public Boolean IsCriticalValid;
    public Integer MoveMin;
    public Integer MoveMax;
    public ArrayList<String> Effects;
    public ArrayList<String> ValidModes;
    public Integer PerBattleLimit;
    public Boolean GenerationGuaranteed;
    public Boolean SelfTargetValid;

    public SkillConfig(SkillType type) {
        Name = type;
    }

    public static boolean canParse(String str) {
        return str.startsWith("combat_skill") || str.startsWith("riposte_skill") || str.startsWith("combat_move_skill");
    }

    public static SkillConfig parse(String str) {
        if(!canParse(str)) {
            return new SkillConfig(SkillType.None);
        }
        String name = str.split(":")[0];

        Hashtable<String, ArrayList<Object>> properties = parseProperties(name, str);

        SkillType type = SkillType.None;
        if(name.equals("combat_skill")){
            type = SkillType.CombatSkill;
        }
        else if(name.equals("riposte_skill")) {
            type = SkillType.ReposteSkill;
        }
        else if(name.equals("combat_move_skill")) {
            type = SkillType.CombatMoveSkill;
        }
        SkillConfig config = new SkillConfig(type);

        config.Id = getSingleOrNull(properties, "id");
        config.Level = getSingleOrNull(properties, "level");
        ArrayList<Integer> heals = getOrNull(properties, "heal");
        if(heals != null && heals.size() == 2) {
            config.HealMin = heals.get(0);
            config.HealMax = heals.get(1);
        }

        config.Type = Enum.valueOf(SkillAttackType.class, getSingleOrNull(properties, "type"));
        ArrayList<Integer> moves = getOrNull(properties, "move");
        if(moves != null && moves.size() == 2) {
            config.MoveMin = moves.get(0);
            config.MoveMax = moves.get(1);
        }
        config.Attack = getSingleOrNull(properties, "atk");
        config.Damage = getSingleOrNull(properties, "dmg");
        config.Critical = getSingleOrNull(properties, "crit");
        config.Launch = getSingleOrNull(properties, "launch");
        config.Target = getSingleOrNull(properties, "target");
        config.IsCriticalValid = getSingleOrNull(properties, "is_crit_valid");
        config.Effects = getOrNull(properties, "effect");
        config.ValidModes = getOrNull(properties, "valid_modes");
        config.PerBattleLimit = getSingleOrNull(properties, "per_battle_limit");
        config.GenerationGuaranteed = getSingleOrNull(properties, "generation_guaranteed");
        config.SelfTargetValid = getSingleOrNull(properties, "self_target_valid");

        return config;
    }

    @Override
    public String toString() {
        String skillName = getSkillName();
        if(skillName == null) {
            return null;
        }
        StringBuilder builder = new StringBuilder();
        builder.append(skillName);

        builder.append(formatStrings("id", true, Id));
        builder.append(formatObjects("level", Level));
        builder.append(formatObjects("heal", HealMin, HealMax));
        String typeStr = getTypeString();
        if(typeStr != null) {
            builder.append(formatStrings("type", true, typeStr));
        }
        builder.append(formatObjects("move", MoveMin, MoveMax));
        builder.append(formatDoubles("atk", true, true, Attack));
        builder.append(formatDoubles("dmg", true, true, Damage));
        builder.append(formatDoubles("crit", true, true, Critical));
        builder.append(formatObjects("launch", Launch));
        builder.append(formatStrings("target", false, Target));
        builder.append(formatObjects("is_crit_valid", IsCriticalValid));
        builder.append(formatStrings("effect", true, CNCollectionUtils.toStringArray(Effects)));
        builder.append(formatStrings("valid_modes", false, CNCollectionUtils.toStringArray(ValidModes)));
        builder.append(formatObjects("per_battle_limit", PerBattleLimit));
        builder.append(formatObjects("generation_guaranteed", GenerationGuaranteed));
        builder.append(formatObjects("self_target_valid", SelfTargetValid));

        String result = builder.toString();
        if(result.equals(skillName)) {
            return null;
        }
        return builder.toString();
    }

    private String getSkillName() {
        if(Name == null) {
            return null;
        }
        String startingStr = null;
        switch(Name) {
            case CombatSkill:
                startingStr = "combat_skill:";
                break;
            case ReposteSkill:
                startingStr = "riposte_skill:";
                break;
            case CombatMoveSkill:
                startingStr = "combat_move_skill:";
                break;
            default:
                break;
        }
        return startingStr;
    }

    private String getTypeString() {
        if(Type == null) {
            return null;
        }
        String typeStr = null;
        switch(Type) {
            case Melee:
                typeStr = "melee";
                break;
            case Ranged:
                typeStr = "ranged";
                break;
            case Move:
                typeStr = "move";
                break;
            default:
                break;
        }
        return typeStr;
    }
}

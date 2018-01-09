package com.colonolnutty.module.shareddata.loaders;

import com.colonolnutty.module.shareddata.io.IFileReader;
import com.colonolnutty.module.shareddata.io.IReadFiles;
import com.colonolnutty.module.shareddata.models.hero.*;
import com.colonolnutty.module.shareddata.utils.CNStringUtils;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

/**
 * User: Jack's Computer
 * Date: 01/07/2018
 * Time: 3:15 PM
 */
public class HeroStore implements IReadFiles {
    public Hashtable<String, Hero> _heroes = new Hashtable<String, Hero>();
    private IFileReader _fileReader;

    public Hero getHero(String name) {
        if(!_heroes.containsKey(name)) {
            return null;
        }
        return _heroes.get(name);
    }

    public Hero loadHero(String heroInfoFilePath) throws IOException {
        File heroInfoFile = new File(heroInfoFilePath);
        String heroInfoFileName = heroInfoFile.getName();
        String heroName = heroInfoFileName.replace("\\.info\\.darkest", "");
        Hero hero;
        if(_heroes.contains(heroName)) {
            hero = _heroes.get(heroName);
        }
        else {
            hero = new Hero(heroName);
            _heroes.put(heroName, hero);
        }
        if(hero.Info == null) {
            hero.Info = loadHeroInfo(_fileReader.readAllLines(heroInfoFile));
        }
        return hero;
    }

    private HeroInfo loadHeroInfo(List<String> heroInfoFileLines) {
        if(heroInfoFileLines == null || heroInfoFileLines.size() == 0) {
            return null;
        }
        HeroInfo heroInfo = new HeroInfo();

        for(String line : heroInfoFileLines) {
            if(CNStringUtils.isNullOrWhitespace(line)) {
                continue;
            }
            if(HeroInfo.canParse(line)) {
                heroInfo.IdIndex = HeroInfo.parseId(line);
            }
            else if(ArmorConfig.canParse(line)) {
                heroInfo.Armors.add(ArmorConfig.parse(line));
            }
            else if(ControlledConfig.canParse(line)) {
                heroInfo.Controlled = ControlledConfig.parse(line);
            }
            else if(DeathsDoorConfig.canParse(line)) {
                heroInfo.DeathsDoor = DeathsDoorConfig.parse(line);
            }
            else if(GenerationConfig.canParse(line)) {
                heroInfo.Generation = GenerationConfig.parse(line);
            }
            else if(IncompatiblePartyMembersConfig.canParse(line)) {
                heroInfo.IncompatiblePartyMembers = IncompatiblePartyMembersConfig.parse(line);
            }
            else if(ResistancesConfig.canParse(line)) {
                heroInfo.Resistance = ResistancesConfig.parse(line);
            }
            else if(SkillConfig.canParse(line)) {
                heroInfo.Skills.add(SkillConfig.parse(line));
            }
            else if(SkillSelectionConfig.canParse(line)) {
                heroInfo.SkillSelection = SkillSelectionConfig.parse(line);
            }
            else if(TagConfig.canParse(line)) {
                heroInfo.Tags.add(TagConfig.parse(line));
            }
            else if(WeaponConfig.canParse(line)) {
                heroInfo.Weapons.add(WeaponConfig.parse(line));
            }
        }
        return heroInfo;
    }

    @Override
    public void setFileReader(IFileReader iFileReader) {
        _fileReader = iFileReader;
    }
}

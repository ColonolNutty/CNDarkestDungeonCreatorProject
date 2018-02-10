package main.cnddmodeditormodule.controllers;

import com.colonolnutty.module.shareddata.loaders.HeroStore;
import com.colonolnutty.module.shareddata.models.BaseConfig;
import com.colonolnutty.module.shareddata.models.ConfigProperty;
import com.colonolnutty.module.shareddata.models.PropertyName;
import com.colonolnutty.module.shareddata.models.SkillType;
import com.colonolnutty.module.shareddata.models.hero.Hero;
import com.colonolnutty.module.shareddata.models.hero.HeroInfo;
import com.colonolnutty.module.shareddata.utils.CNCollectionUtils;
import main.cnddmodeditormodule.models.SaveHeroInfoRequest;
import main.cnddmodeditormodule.models.SkillNameRequest;
import main.cnddmodeditormodule.models.SkillsRequest;
import main.cnddmodeditormodule.models.SkillsResponse;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * User: Jack's Computer
 * Date: 01/13/2018
 * Time: 12:57 PM
 */
public class HeroController {
    private HeroStore _store;

    public HeroController(HeroStore heroStore) {
        _store = heroStore;
    }

    public ArrayList<String> getSkillNames(SkillNameRequest request) {
        Hero hero = _store.get(request.HeroName);
        if(hero == null) {
            return new ArrayList<String>();
        }
        HeroInfo info = hero.Info;
        ArrayList<BaseConfig> configs = info.getConfigs(request.SkillName.toString());
        ArrayList<String> names = new ArrayList<String>();
        for(BaseConfig config : configs) {
            String id = config.getProperty(PropertyName.id).getSingleValue();
            if(id == null) {
                continue;
            }
            names.add(id);
        }
        return names;
    }

    public SkillsResponse getSkills(SkillsRequest request) {
        Hashtable<SkillType, ArrayList<ConfigProperty>> properties = new Hashtable<SkillType, ArrayList<ConfigProperty>>();

        Hero hero = _store.get(request.HeroName);

        for(SkillType type : SkillType.values()) {
            ArrayList<BaseConfig> configs = hero.Info.getConfigs(type.toString());
        }

        return new SkillsResponse(properties);
    }

    public void saveHeroInfo(SaveHeroInfoRequest request) {

    }
}

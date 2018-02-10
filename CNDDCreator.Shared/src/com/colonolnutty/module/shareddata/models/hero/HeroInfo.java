package com.colonolnutty.module.shareddata.models.hero;

import com.colonolnutty.module.shareddata.models.BaseConfig;
import com.colonolnutty.module.shareddata.models.PropertyName;
import com.colonolnutty.module.shareddata.utils.CNStringUtils;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * User: Jack's Computer
 * Date: 01/07/2018
 * Time: 12:20 PM
 */
public class HeroInfo {
    private String _id;
    private Hashtable<String, ArrayList<BaseConfig>> _configs;

    protected HeroInfo(Hashtable<String, ArrayList<BaseConfig>> configs) {
        _configs = configs;
    }

    public static HeroInfo parse(ArrayList<String> infoLines) {
        Hashtable<String, ArrayList<BaseConfig>> configs = new Hashtable<String, ArrayList<BaseConfig>>();
        for(String infoLine : infoLines) {
            if(CNStringUtils.isNullOrWhitespace(infoLine)) {
                continue;
            }
            BaseConfig config = BaseConfig.parse(infoLine);
            if(config == null) {
                continue;
            }
            String configName = config.getConfigName();
            ArrayList<BaseConfig> baseConfigs = new ArrayList<BaseConfig>();
            if(configs.containsKey(configName)) {
                baseConfigs = configs.get(configName);
            }
            baseConfigs.add(config);
            configs.put(configName, baseConfigs);
        }
        return new HeroInfo(configs);
    }

    public ArrayList<BaseConfig> getConfigs(String configName) {
        if(configName == null || !_configs.containsKey(configName)) {
            return new ArrayList<BaseConfig>();
        }
        return _configs.get(configName);
    }

    public String getId() {
        if(_id != null) {
            return _id;
        }
        if(_configs == null || _configs.containsKey("id_index")) {
            return null;
        }
        ArrayList<BaseConfig> idArr = _configs.get("id_index");
        if(idArr.isEmpty() || idArr.size() > 1) {
            return null;
        }
        _id = idArr.get(0).getProperty(PropertyName.index).getSingleValue();
        return _id;
    }
}

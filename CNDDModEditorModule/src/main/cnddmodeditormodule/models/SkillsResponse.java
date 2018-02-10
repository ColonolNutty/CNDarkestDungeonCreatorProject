package main.cnddmodeditormodule.models;

import com.colonolnutty.module.shareddata.models.ConfigProperty;
import com.colonolnutty.module.shareddata.models.SkillType;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * User: Jack's Computer
 * Date: 01/13/2018
 * Time: 2:25 PM
 */
public class SkillsResponse {
    public Hashtable<SkillType, ArrayList<ConfigProperty>> Properties;

    public SkillsResponse(Hashtable<SkillType, ArrayList<ConfigProperty>> properties) {
        Properties = properties;
    }
}

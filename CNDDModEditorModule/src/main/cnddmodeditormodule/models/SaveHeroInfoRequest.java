package main.cnddmodeditormodule.models;

import com.colonolnutty.module.shareddata.models.PropertyName;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * User: Jack's Computer
 * Date: 01/13/2018
 * Time: 2:21 PM
 */
public class SaveHeroInfoRequest {
    public String HeroName;
    public Hashtable<PropertyName, ArrayList<String>> Properties;
}

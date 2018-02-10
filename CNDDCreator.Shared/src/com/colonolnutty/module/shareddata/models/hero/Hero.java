package com.colonolnutty.module.shareddata.models.hero;

/**
 * User: Jack's Computer
 * Date: 01/07/2018
 * Time: 3:15 PM
 */
public class Hero {
    public String Name;
    public HeroInfo Info;

    public Hero(String name) {
        Name = name;
    }

    public String getId() {
        if(Info == null) {
            return null;
        }
        return Info.getId();
    }
}

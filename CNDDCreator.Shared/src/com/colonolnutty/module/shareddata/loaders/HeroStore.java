package com.colonolnutty.module.shareddata.loaders;

import com.colonolnutty.module.shareddata.HeroInfoReader;
import com.colonolnutty.module.shareddata.debug.CNLog;
import com.colonolnutty.module.shareddata.io.FileFinder;
import com.colonolnutty.module.shareddata.io.IFileFinder;
import com.colonolnutty.module.shareddata.io.IFileReader;
import com.colonolnutty.module.shareddata.io.IReadFiles;
import com.colonolnutty.module.shareddata.models.hero.*;
import com.colonolnutty.module.shareddata.utils.CNCollectionUtils;
import com.colonolnutty.module.shareddata.utils.CNStringUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * User: Jack's Computer
 * Date: 01/07/2018
 * Time: 3:15 PM
 */
public class HeroStore {
    private Hashtable<String, Hero> _heroes = new Hashtable<String, Hero>();
    private HeroInfoReader _reader;
    private IFileFinder _fileFinder;
    private CNLog _log;

    public HeroStore(CNLog log) {
        _log = log;
        _reader = new HeroInfoReader(log);
        _fileFinder = new FileFinder();
    }

    public void loadHeroes(String modFolder) {
        _heroes = new Hashtable<String, Hero>();
        File modFolderFile = new File(modFolder);
        ArrayList<String> files = _fileFinder.findFiles(modFolderFile);
        ArrayList<String> heroInfoFiles = new ArrayList<String>();
        for(String fileName : files) {
            if(fileName.endsWith("info.darkest")) {
                heroInfoFiles.add(fileName);
            }
        }
        for(String heroFile : heroInfoFiles) {
            loadHero(new File(modFolderFile, heroFile).getAbsolutePath());
        }
    }

    public ArrayList<String> getHeroNames() {
        return CNCollectionUtils.toArrayList(_heroes.keys());
    }

    public Hero getFirstHero() {
        if(_heroes.isEmpty()) {
            return null;
        }
        return _heroes.get(_heroes.keys().nextElement());
    }

    public Hero get(String name) {
        if(!_heroes.containsKey(name)) {
            return null;
        }
        return _heroes.get(name);
    }

    private void loadHero(String heroInfoFilePath) {
        File heroInfoFile = new File(heroInfoFilePath);
                String heroInfoFileName = heroInfoFile.getName();
        String heroName = heroInfoFileName.replace(".info.darkest", "");
        Hero hero;
        if(_heroes.contains(heroName)) {
            hero = _heroes.get(heroName);
        }
        else {
            hero = new Hero(heroName);
            _heroes.put(heroName, hero);
        }
        hero.Info = _reader.read(heroInfoFilePath);
    }
}

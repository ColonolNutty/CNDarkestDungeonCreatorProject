package main.settings;

import com.colonolnutty.module.shareddata.debug.CNLog;
import com.colonolnutty.module.shareddata.models.settings.CRData;

import java.util.ArrayList;

/**
 * User: Jack's Computer
 * Date: 01/05/2018
 * Time: 4:14 PM
 */
public class MECRData extends CRData<MESettings> {
    @Override
    public ArrayList<String> getPropertyNames() {
        ArrayList<String> settings = new ArrayList<String>();
        settings.add("modFolder");
        settings.add("projectFile");
        return settings;
    }

    @Override
    public String getSettingsFilePath() {
        return "configuration\\modeditorconfig.json";
    }

    @Override
    public boolean settingsAreValid(MESettings mcSettings, CNLog cnLog) {
        return verifySettings(cnLog, mcSettings,
                mcSettings.modFolder,
                mcSettings.projectFile);
    }
}

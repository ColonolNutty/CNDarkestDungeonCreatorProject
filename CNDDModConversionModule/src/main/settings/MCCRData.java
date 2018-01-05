package main.settings;

import com.colonolnutty.module.shareddata.debug.CNLog;
import com.colonolnutty.module.shareddata.models.settings.CRData;

import java.util.ArrayList;

/**
 * User: Jack's Computer
 * Date: 01/05/2018
 * Time: 4:14 PM
 */
public class MCCRData extends CRData<MCSettings> {
    @Override
    public ArrayList<String> getPropertyNames() {
        ArrayList<String> settings = new ArrayList<String>();
        return settings;
    }

    @Override
    public String getSettingsFilePath() {
        return "configuration\\modconversionconfig.json";
    }

    @Override
    public boolean settingsAreValid(MCSettings mcSettings, CNLog cnLog) {
        return true;
    }
}

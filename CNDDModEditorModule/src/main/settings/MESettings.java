package main.settings;

import com.colonolnutty.module.shareddata.models.settings.CNBaseSettings;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * User: Jack's Computer
 * Date: 09/11/2017
 * Time: 2:31 PM
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MESettings extends CNBaseSettings {
    public String modFolder;
    public String projectFile;
}

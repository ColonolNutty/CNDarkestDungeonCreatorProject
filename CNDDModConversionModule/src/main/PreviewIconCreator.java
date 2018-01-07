package main;

import com.colonolnutty.module.shareddata.debug.CNLog;
import main.settings.MCSettings;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * User: Jack's Computer
 * Date: 01/06/2018
 * Time: 5:25 PM
 */
public class PreviewIconCreator {
    private CNLog _log;
    private MCSettings _settings;

    public PreviewIconCreator(CNLog log, MCSettings settings) {
        _log = log;
        _settings = settings;
    }

    public void createPreviewIcon(String modFolder) {
        File root = new File(modFolder);
        if(!root.isDirectory()) {
            return;
        }
        File previewIconTemplate = new File(_settings.previewIconTemplate);
        if(!previewIconTemplate.exists()) {
            _log.error("No preview icon template found at file path: " + previewIconTemplate.getAbsolutePath());
            return;
        }
        File newIcon = new File(root, previewIconTemplate.getName());
        if(newIcon.exists()) {
            _log.debug("Skipping preview icon creation, it exists already", 2);
            return;
        }
        try {
            _log.debug("Creating preview icon for mod: " + root.getName(), 2);
            Files.copy(previewIconTemplate.toPath(), newIcon.toPath());
        } catch (IOException e) {
            _log.error("When copying preview icon: " + newIcon.getAbsolutePath(), e);
        }
    }
}

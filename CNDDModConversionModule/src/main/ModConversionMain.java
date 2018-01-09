package main;

import com.colonolnutty.module.shareddata.MainFunctionModule;
import com.colonolnutty.module.shareddata.debug.CNLog;
import com.colonolnutty.module.shareddata.io.FileFinder;
import com.colonolnutty.module.shareddata.io.IFileFinder;
import com.colonolnutty.module.shareddata.ui.ProgressController;
import com.colonolnutty.module.shareddata.utils.StopWatchTimer;
import main.settings.MCSettings;

import java.io.File;

/**
 * User: Jack's Computer
 * Date: 09/16/2017
 * Time: 11:35 AM
 */
public class ModConversionMain extends MainFunctionModule {
    private CNLog _log;
    private ProgressController _progressController;
    private MCSettings _settings;

    public ModConversionMain(MCSettings settings,
                             CNLog log,
                             ProgressController progressController) {
        _settings = settings;
        _log = log;
        _progressController = progressController;
    }

    @Override
    public void run() {
        if(_settings == null) {
            _log.error("No configuration file found, exiting.");
            return;
        }
        StopWatchTimer timer = new StopWatchTimer(_log);
        timer.start();
        IFileFinder finder = new FileFinder();
        ProjectFileCreator projectFileCreator = new ProjectFileCreator(_log, _settings, finder);
        ModFilesCreator modFilesCreator = new ModFilesCreator(_log, finder);
        PreviewIconCreator previewIconCreator = new PreviewIconCreator(_log, _settings);
        File root = new File(_settings.modsFolder);
        if(!root.isDirectory()) {
            _log.debug("Not a valid mod directory \"" + _settings.modsFolder + "\"");
            timer.logTime();
            return;
        }
        File[] files = root.listFiles();
        _progressController.reset();
        _progressController.setMaximum(files.length);
        for(File file : files) {
            if(!file.isDirectory()) {
                continue;
            }
            String filePath = file.getAbsolutePath();
            _log.debug("Converting mod in folder: " + filePath);
            projectFileCreator.createProjectFile(filePath);
            previewIconCreator.createPreviewIcon(filePath);
            modFilesCreator.createModFilesFile(filePath);
            _progressController.add(1);
        }
        timer.logTime();
    }
}

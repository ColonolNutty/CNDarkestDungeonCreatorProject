package main;

import com.colonolnutty.module.shareddata.MainFunctionModule;
import com.colonolnutty.module.shareddata.debug.CNLog;
import com.colonolnutty.module.shareddata.ui.ProgressController;
import com.colonolnutty.module.shareddata.utils.StopWatchTimer;
import main.settings.MESettings;

/**
 * User: Jack's Computer
 * Date: 01/09/2018
 * Time: 12:53 PM
 */
public class ModEditorMain extends MainFunctionModule {
    private CNLog _log;
    private ProgressController _progressController;
    private MESettings _settings;

    public ModEditorMain(MESettings settings,
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
        timer.logTime();
    }
}

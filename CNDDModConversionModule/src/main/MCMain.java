package main;

import com.colonolnutty.module.shareddata.MainFunctionModule;
import com.colonolnutty.module.shareddata.debug.CNLog;
import com.colonolnutty.module.shareddata.ui.ProgressController;
import main.settings.MCSettings;

/**
 * User: Jack's Computer
 * Date: 01/05/2018
 * Time: 4:17 PM
 */
public class MCMain extends MainFunctionModule {

    private MCSettings _settings;
    private CNLog _log;
    private ProgressController _progressController;

    public MCMain(MCSettings balancerSettings,
                        CNLog log,
                        ProgressController progressController) {
        _settings = balancerSettings;
        _log = log;
        _progressController = progressController;
    }

    @Override
    public void run() {
        _log.debug(_settings.modsFolder);
    }
}

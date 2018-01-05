package main;

import com.colonolnutty.module.shareddata.MainFunctionModule;
import com.colonolnutty.module.shareddata.debug.CNLog;
import com.colonolnutty.module.shareddata.io.FileReaderWrapper;
import com.colonolnutty.module.shareddata.io.IFileReader;
import com.colonolnutty.module.shareddata.io.IReadFiles;
import com.colonolnutty.module.shareddata.ui.ProgressController;
import com.colonolnutty.module.shareddata.utils.StopWatchTimer;
import main.settings.MCSettings;

/**
 * User: Jack's Computer
 * Date: 09/16/2017
 * Time: 11:35 AM
 */
public class ModConversionMain extends MainFunctionModule implements IReadFiles {
    private CNLog _log;
    private ProgressController _progressController;
    private IFileReader _fileReader;
    private MCSettings _settings;

    public ModConversionMain(MCSettings settings,
                             CNLog log,
                             ProgressController progressController) {
        _settings = settings;
        _log = log;
        _progressController = progressController;
        _fileReader = new FileReaderWrapper();
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

    @Override
    public void setFileReader(IFileReader fileReader) {
        _fileReader = fileReader;
    }
}

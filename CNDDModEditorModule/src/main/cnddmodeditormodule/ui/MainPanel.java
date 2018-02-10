package main.cnddmodeditormodule.ui;

import com.colonolnutty.module.shareddata.debug.CNLog;
import com.colonolnutty.module.shareddata.debug.ConsoleDebugWriter;
import com.colonolnutty.module.shareddata.debug.DebugWriter;
import com.colonolnutty.module.shareddata.io.ConfigReader;
import com.colonolnutty.module.shareddata.io.ConfigWriter;
import com.colonolnutty.module.shareddata.loaders.HeroStore;
import com.colonolnutty.module.shareddata.ui.IMainFunctionPanel;
import main.ModEditorMain;
import main.settings.MECRData;
import main.settings.MESettings;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * User: Jack's Computer
 * Date: 01/05/2018
 * Time: 4:07 PM
 */
public class MainPanel implements IMainFunctionPanel {
    private CNLog _log;
    private MESettings _settings;

    @Override
    public JPanel create() {
        JPanel mainPanel = new JPanel();

        if(_log != null) {
            _log.dispose();
        }
        DebugWriter debugWriter = new ConsoleDebugWriter();
        CNLog tempLog = new CNLog(debugWriter);
        ConfigReader<MESettings> reader = new ConfigReader<MESettings>(tempLog);
        _settings = reader.readSettingsFile(new MECRData(), MESettings.class);
        tempLog.dispose();
        _log = new CNLog(debugWriter, _settings);
        HeroStore heroStore = new HeroStore(_log);
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.add("Hero", setupHeroDisplay(_log, _settings, heroStore));
        tabbedPane.add("Settings", setupSettingsDisplay(_log, _settings, heroStore));
        mainPanel.add(tabbedPane);
        mainPanel.setVisible(true);

        return mainPanel;
    }

    private JPanel setupHeroDisplay(CNLog log, MESettings settings, HeroStore heroStore) {
        HeroEditorDisplay editorDisplay = new HeroEditorDisplay(log, settings, heroStore);
        return editorDisplay.setup(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Save Hero
            }
        });
    }

    private JPanel setupSettingsDisplay(CNLog log, MESettings settings, HeroStore heroStore) {
        ConfigWriter writer = new ConfigWriter(log);
        MESettingsDisplay settingsDisplay = new MESettingsDisplay(writer, settings);
        return settingsDisplay.setup(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JButton source = (JButton) e.getSource();
                source.setEnabled(false);
                Thread thread = new Thread() {
                    public void run() {
                        try {
                            log.clear();
                            log.setupDebugLogFile();
                            settingsDisplay.disable();
                            //Save Settings and Hero Data
                            ModEditorMain mcMain = new ModEditorMain(settings,
                                    log);
                            mcMain.run();
                            heroStore.loadHeroes(settings.modFolder);
                        }
                        catch(Exception e1) {
                            e1.printStackTrace();
                        }
                        finally {
                            source.setEnabled(true);
                            settingsDisplay.enable();
                        }
                    }
                };
                thread.start();
            }
        });
    }

    @Override
    public String getName() {
        return "Mod Editor";
    }

    @Override
    public void dispose() {
        if(_log != null) {
            _log.dispose();
        }
    }
}

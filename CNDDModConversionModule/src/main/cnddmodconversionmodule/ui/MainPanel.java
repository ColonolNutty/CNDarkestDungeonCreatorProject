package main.cnddmodconversionmodule.ui;

import com.colonolnutty.module.shareddata.ui.DefaultMainPanel;
import com.colonolnutty.module.shareddata.ui.IMainFunctionPanel;
import main.ModConversionMain;
import main.settings.MCCRData;
import main.settings.MCSettings;

import javax.swing.*;

/**
 * User: Jack's Computer
 * Date: 01/05/2018
 * Time: 4:07 PM
 */
public class MainPanel implements IMainFunctionPanel {
    private DefaultMainPanel _mainPanel;

    @Override
    public JPanel create() {
        if(_mainPanel == null) {
            _mainPanel = new DefaultMainPanel(getName(),
                    MCSettings.class,
                    MCSettingsDisplay.class,
                    ModConversionMain.class,
                    MCCRData.class);
        }
        return _mainPanel.create();
    }

    @Override
    public String getName() {
        return "Mod Conversion";
    }

    @Override
    public void dispose() {
        if(_mainPanel != null) {
            _mainPanel.dispose();
            _mainPanel = null;
        }
    }
}

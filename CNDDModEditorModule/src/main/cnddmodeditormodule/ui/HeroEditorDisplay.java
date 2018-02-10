package main.cnddmodeditormodule.ui;

import com.colonolnutty.module.shareddata.debug.CNLog;
import com.colonolnutty.module.shareddata.loaders.HeroStore;
import com.colonolnutty.module.shareddata.models.hero.Hero;
import com.colonolnutty.module.shareddata.ui.SettingsDisplayBase;
import com.colonolnutty.module.shareddata.utils.CNCollectionUtils;
import main.settings.MESettings;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * User: Jack's Computer
 * Date: 01/09/2018
 * Time: 4:58 PM
 */
public class HeroEditorDisplay extends SettingsDisplayBase {
    private CNLog _log;
    private Hero _currentHero;
    private HeroStore _heroStore;
    private CombatSkillsDisplay _combatSkillsDisplay;

    public HeroEditorDisplay(CNLog log, MESettings settings, HeroStore heroStore) {
        _log = log;
        _heroStore = heroStore;
        _heroStore.loadHeroes(settings.modFolder);
        _currentHero = _heroStore.getFirstHero();
    }

    private JPanel createCombatSkillsDisplay(ActionListener onSave) {
        _combatSkillsDisplay = new CombatSkillsDisplay(_log);
        if(_currentHero != null) {
            _combatSkillsDisplay.loadHero(_currentHero.Info);
        }
        return _combatSkillsDisplay.setup(onSave);
    }

    @Override
    public JPanel setup(ActionListener onSave) {
        JPanel display = new JPanel();
        GroupLayout layout = new GroupLayout(display);
        String[] heroNames = CNCollectionUtils.toArray(String.class, _heroStore.getHeroNames());
        JComboBox box = createDropdown(_currentHero.Name, heroNames);
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.add("Combat Skills", createCombatSkillsDisplay(onSave));

        layout.setHorizontalGroup(
            layout.createParallelGroup()
                    .addComponent(box)
                    .addComponent(tabbedPane)
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                    .addComponent(box)
                    .addComponent(tabbedPane)
        );
        display.setLayout(layout);
        display.setVisible(true);
        return display;
    }

    protected JComboBox createDropdown(String selectedItem, String... items) {
        JComboBox comboBox = new JComboBox(items);
        comboBox.setSelectedItem(selectedItem);
        return comboBox;
    }
}

package main.cnddmodeditormodule.ui;

import com.colonolnutty.module.shareddata.debug.CNLog;
import com.colonolnutty.module.shareddata.models.*;
import com.colonolnutty.module.shareddata.models.hero.HeroInfo;
import com.colonolnutty.module.shareddata.ui.SettingsDisplayBase;
import com.colonolnutty.module.shareddata.utils.CNCollectionUtils;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.JTextComponent;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * User: Jack's Computer
 * Date: 01/09/2018
 * Time: 5:20 PM
 */
public class CombatSkillsDisplay extends SettingsDisplayBase {
    private HeroInfo _currentHeroInfo;
    private CNLog _log;
    private ActionListener _saveAction;
    private JPanel _combatSkillDisplay;
    private JPanel _combatSkillsDisplay;
    private ArrayList<String> _skillTypes;
    private JComboBox _skillTypeSelection;
    private JComboBox _combatSkillsSelection;
    private JComboBox _combatSkillLevels;
    private ArrayList<BaseConfig> _selectedConfigs;

    public CombatSkillsDisplay(CNLog log) {
        _log = log;
        _skillTypes = new ArrayList<String>();
        _skillTypes.add(SkillType.combat_skill.toString());
        _skillTypes.add(SkillType.combat_move_skill.toString());
        _skillTypes.add(SkillType.riposte_skill.toString());
    }

    @Override
    public JPanel setup(ActionListener saveAction) {
        _saveAction = saveAction;
        _combatSkillsDisplay = new JPanel();
        _combatSkillDisplay = new JPanel();
        JButton runButton = createButton("Save", saveAction);
        _combatSkillsDisplay.add(runButton);

        _skillTypeSelection = new JComboBox(CNCollectionUtils.toArray(String.class, _skillTypes));
        _skillTypeSelection.setSelectedItem(0);
        _skillTypeSelection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String action = e.getActionCommand();
                if(action.equals("comboBoxChanged")) {
                    changeToCombatSkills(_skillTypeSelection.getSelectedItem().toString());
                }
            }
        });

        _combatSkillLevels = new JComboBox();
        _combatSkillsSelection = new JComboBox();
        changeToCombatSkills(_skillTypes.get(0));

        _combatSkillsDisplay.add(_combatSkillLevels);
        _combatSkillsDisplay.add(_skillTypeSelection);
        _combatSkillsDisplay.add(_combatSkillDisplay);
        return _combatSkillsDisplay;
    }

    public void loadHero(HeroInfo heroInfo) {
        _currentHeroInfo = heroInfo;
        changeToCombatSkills(SkillType.combat_skill.toString());
    }

    private void changeToCombatSkills(String skillTypeSelected) {
        if(_combatSkillsSelection == null) {
            return;
        }
        _combatSkillsDisplay.remove(_combatSkillsSelection);
        _combatSkillsDisplay.remove(_combatSkillLevels);

        _combatSkillsSelection = new JComboBox();
        _combatSkillLevels = new JComboBox();
        ArrayList<BaseConfig> configs = _currentHeroInfo.getConfigs(skillTypeSelected);
        if(configs == null) {
            return;
        }
        ArrayList<BaseConfig> configsWithLevels = new ArrayList<BaseConfig>();
        ArrayList<String> uniqueConfigNames = new ArrayList<String>();
        for(BaseConfig config : configs) {
            String name = config.getProperty(PropertyName.id).getSingleValue();
            if(name == null) {
                continue;
            }
            if(!uniqueConfigNames.contains(name)) {
                uniqueConfigNames.add(name);
            }
        }
        if(uniqueConfigNames.isEmpty()) {
            return;
        }
        for(String name : uniqueConfigNames) {
            _combatSkillsSelection.addItem(name);
        }

        _combatSkillsSelection.setSelectedIndex(0);
        _combatSkillsSelection.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String skillName = e.getItem().toString();
                BaseConfig found = null;
                for(BaseConfig config : configs) {
                    String name = config.getProperty(PropertyName.id).getSingleValue();
                    if(name != null && name.equals(skillName)) {
                        found = config;
                        break;
                    }
                }
                if(found == null) {
                    return;
                }
                changeCombatSkillDisplay(found);
            }
        });

        BaseConfig firstConfig = configs.get(0);
        if(configsWithLevels.contains(firstConfig)) {
            String[] levels = new String[5];
            levels[0] = "0";
            levels[1] = "1";
            levels[2] = "2";
            levels[3] = "3";
            levels[4] = "4";
            _combatSkillLevels = new JComboBox(levels);
            _combatSkillLevels.setSelectedIndex(0);
            _combatSkillLevels.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    String selectedLevel = e.getItem().toString();
                    String selectedCombatSkill = _combatSkillsSelection.getSelectedItem().toString();
                    BaseConfig found = null;
                    for(BaseConfig baseConfig : configsWithLevels) {
                        String configName = baseConfig.getProperty(PropertyName.id).getSingleValue();
                        String configLevel = baseConfig.getProperty(PropertyName.level).getSingleValue();
                        if(configName == null || configLevel == null) {
                            return;
                        }
                        if(configName.equals(selectedCombatSkill) && configLevel.equals(selectedLevel)) {
                            found = baseConfig;
                            break;
                        }
                    }
                    if(found == null) {
                        return;
                    }
                    changeCombatSkillDisplay(found);
                }
            });
            _combatSkillsDisplay.add(_combatSkillLevels);
        }

        _combatSkillsDisplay.add(_combatSkillsSelection);
        changeCombatSkillDisplay(firstConfig);
    }

    private <T> T firstOrNull(ArrayList<T> arr) {
        if(arr == null || arr.isEmpty()) {
            return null;
        }
        return arr.get(0);
    }

    private void changeCombatSkillDisplay(BaseConfig config) {
        _combatSkillsDisplay.remove(_combatSkillDisplay);
        _combatSkillDisplay = new JPanel();
        if(config == null) {
            return;
        }
        GroupLayout layout = new GroupLayout(_combatSkillDisplay);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        _combatSkillDisplay.setLayout(layout);

        ArrayList<PropertyName> propertyNames = config.getPropertyNames();

        GroupLayout.ParallelGroup horizontalLayout = layout.createParallelGroup();
        GroupLayout.SequentialGroup verticalLayout = layout.createSequentialGroup();

        int minorTicks = 25;
        int majorTicks = 50;
        int min = 0;
        int max = 200;

        for(PropertyName propertyName : propertyNames) {
            ValueType propertyType = PropertyValueTypes.getValueType(propertyName);
            ConfigProperty property = config.getProperty(propertyName);
            switch(propertyType) {
                case Dbl:
                case DblPerc:
                    Double val = firstOrNull(property.getDoubleValues());
                    if(val != null) {
                        JSlider doubleSlider = createSlider(propertyName.toString(), min, max, minorTicks, majorTicks, val.intValue());
                        horizontalLayout.addComponent(doubleSlider);
                        verticalLayout.addComponent(doubleSlider);
                    }
                    break;
                case Int:
                    ArrayList<Integer> intValues = property.getIntValues();
                    if(intValues != null) {
                        if(intValues.size() == 2) {
                            JSlider minSlider = createSlider(propertyName + " min", min, max, minorTicks, majorTicks, intValues.get(0));
                            JSlider maxSlider = createSlider(propertyName + " max", min, max, minorTicks, majorTicks, intValues.get(1));
                            horizontalLayout = horizontalLayout.addGroup(
                                    layout.createSequentialGroup()
                                            .addComponent(minSlider)
                                            .addComponent(maxSlider)
                            );
                            verticalLayout = verticalLayout.addGroup(
                                    layout.createParallelGroup()
                                            .addComponent(minSlider)
                                            .addComponent(maxSlider)
                            );
                        }
                        else {
                            Integer intVal = firstOrNull(intValues);
                            if (intVal != null) {
                                JSlider intSlider = createSlider(propertyName.toString(), min, max, minorTicks, majorTicks, intVal);
                                horizontalLayout.addComponent(intSlider);
                                verticalLayout.addComponent(intSlider);
                            }
                        }
                    }
                    break;
                case Bool:
                    Boolean boolVal = firstOrNull(property.getBooleanValues());
                    if(boolVal != null) {
                        JPanel boolPanel = createField(FieldType.CheckBox,
                                propertyName + "?",
                                propertyName.toString(),
                                boolVal);
                        horizontalLayout.addComponent(boolPanel);
                        verticalLayout.addComponent(boolPanel);
                    }
                    break;
                case StrEsc:
                case StrNonEsc:
                    ArrayList<String> strValues = property.getValues();
                    if(strValues != null) {
                        for(String str : strValues) {
                            JPanel strPanel = createField(FieldType.TextField,
                                    propertyName + " 1",
                                    propertyName + " 1",
                                    str);
                            horizontalLayout.addComponent(strPanel);
                            verticalLayout.addComponent(strPanel);
                        }
                    }
                    break;
            }
        }

        setupChangeListeners();
        layout.setHorizontalGroup(horizontalLayout);
        layout.setVerticalGroup(verticalLayout);
        _combatSkillsDisplay.add(_combatSkillDisplay);
        _combatSkillsDisplay.revalidate();
        _combatSkillsDisplay.repaint();
    }

    private void setupChangeListeners() {
        setupTextEntryFocusListener("skillId", new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) { }

            @Override
            public void focusLost(FocusEvent e) {
                JTextComponent text = (JTextComponent)e.getSource();
                String str = text.getText();
            }
        });
        setupTextEntryFocusListener("target", new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) { }

            @Override
            public void focusLost(FocusEvent e) {
                JTextComponent text = (JTextComponent)e.getSource();
                String str = text.getText();
            }
        });
        setupTextEntryFocusListener("skillId", new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) { }

            @Override
            public void focusLost(FocusEvent e) {
                JTextComponent text = (JTextComponent)e.getSource();
                String str = text.getText();
            }
        });

        setupCheckBoxListener("isCriticalValid", new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JCheckBox checkbox = (JCheckBox)e.getSource();
            }
        });
    }
}

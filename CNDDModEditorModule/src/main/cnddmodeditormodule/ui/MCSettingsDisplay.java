package main.cnddmodeditormodule.ui;

import com.colonolnutty.module.shareddata.io.ConfigWriter;
import com.colonolnutty.module.shareddata.ui.SettingsDisplayBase;
import main.settings.MESettings;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.JTextComponent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class MCSettingsDisplay extends SettingsDisplayBase {
    private ConfigWriter _writer;
    private MESettings _settings;

    public MCSettingsDisplay(ConfigWriter writer, MESettings settings) {
        _writer = writer;
        this._settings = settings;
    }

    public JPanel setup(ActionListener onRun) {
        JPanel settingsDisplay = new JPanel();
        GroupLayout layout = new GroupLayout(settingsDisplay);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        settingsDisplay.setLayout(layout);
        JPanel currentDir = addCurrentDirectoryField();

        //TextFields
        JPanel logFile = createField(FieldType.TextField,
                "Relative Log File Path: ",
                "logFile",
                _settings.logFile);
        JPanel modsFolder = createField(FieldType.TextField,
                "Relative Mods Folder for conversion: ",
                "modFolder",
                _settings.modFolder);

        //CheckBox
        JPanel enableTreeView = createField(FieldType.CheckBox,
                "Enable Tree View",
                "enableTreeView",
                _settings.enableTreeView);
        JPanel enableConsoleDebug = createField(FieldType.CheckBox,
                "Enable Console Debug",
                "enableConsoleDebug",
                _settings.enableConsoleDebug);
        JPanel enableVerboseLogging = createField(FieldType.CheckBox,
                "Enable Verbose Logging",
                "enableVerboseLogging",
                _settings.enableVerboseLogging);
        JButton runButton = createButton("Run", onRun);

        layout.setHorizontalGroup(
                layout.createParallelGroup()
                        //Top
                        .addGroup(
                                layout.createSequentialGroup()
                                        .addComponent(currentDir)
                                        .addComponent(runButton)
                        )
                        //Bottom
                        .addGroup(
                                layout.createSequentialGroup()
                                        //Middle - Left
                                        .addGroup(
                                                layout.createParallelGroup()
                                                        .addGroup(
                                                                layout.createParallelGroup()
                                                        )
                                                        .addGroup(
                                                                layout.createParallelGroup()
                                                                        .addComponent(logFile)
                                                                        .addComponent(modsFolder)
                                                        )
                                        )
                                        //Middle - Middle
                                        .addGroup(
                                                layout.createParallelGroup()
                                        )
                        )
                        //Bottom
                        .addGroup(layout.createSequentialGroup()
                                        .addComponent(enableTreeView)
                                        .addComponent(enableConsoleDebug)
                                        .addComponent(enableVerboseLogging)
                        )
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        //Top
                        .addGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                        .addComponent(currentDir)
                                        .addComponent(runButton)
                        )
                        .addGroup(layout.createParallelGroup()
                                //Middle - Left
                                .addGroup(
                                        layout.createSequentialGroup()
                                                .addGroup(
                                                        layout.createSequentialGroup()
                                                )
                                                .addGroup(
                                                        layout.createSequentialGroup()
                                                                .addComponent(logFile)
                                                                .addComponent(modsFolder)
                                                )
                                )
                                //Middle - Middle
                                .addGroup(
                                        layout.createSequentialGroup()
                                )
                        )
                        //Middle - Right
                        .addGroup(
                                layout.createParallelGroup()
                                        .addComponent(enableTreeView)
                                        .addComponent(enableConsoleDebug)
                                        .addComponent(enableVerboseLogging)
                        )
        );

        setupChangeListeners();
        return settingsDisplay;
    }

    private void setupChangeListeners() {
        setupTextEntryFocusListener("logFile", new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) { }

            @Override
            public void focusLost(FocusEvent e) {
                JTextComponent text = (JTextComponent)e.getSource();
                _settings.logFile = text.getText();
                writeSettings();
            }
        });
        setupTextEntryFocusListener("modFolder", new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) { }

            @Override
            public void focusLost(FocusEvent e) {
                JTextComponent text = (JTextComponent)e.getSource();
                _settings.modFolder = text.getText();
                writeSettings();
            }
        });

        setupCheckBoxListener("enableTreeView", new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JCheckBox checkbox = (JCheckBox)e.getSource();
                _settings.enableTreeView = checkbox.isSelected();
                writeSettings();
            }
        });
        setupCheckBoxListener("enableConsoleDebug", new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JCheckBox checkbox = (JCheckBox)e.getSource();
                _settings.enableConsoleDebug = checkbox.isSelected();
                writeSettings();
            }
        });
        setupCheckBoxListener("enableVerboseLogging", new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JCheckBox checkbox = (JCheckBox)e.getSource();
                _settings.enableVerboseLogging = checkbox.isSelected();
                writeSettings();
            }
        });
    }

    private void writeSettings() {
        _writer.write(_settings);
    }
}

package main;

import com.colonolnutty.module.shareddata.debug.CNLog;
import com.colonolnutty.module.shareddata.io.FileWriterWrapper;
import com.colonolnutty.module.shareddata.io.IFileWriter;
import com.colonolnutty.module.shareddata.io.IWriteFiles;
import com.colonolnutty.module.shareddata.prettyprinters.BasePrettyPrinter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * User: Jack's Computer
 * Date: 01/06/2018
 * Time: 2:19 PM
 */
public class ModFilesCreator implements IWriteFiles {
    public static final String MOD_FILES_NAME = "modfiles.txt";
    private CNLog _log;
    private IFileFinder _fileFinder;
    private IFileWriter _fileWriter;

    public ModFilesCreator(CNLog log, IFileFinder fileFinder) {
        _log = log;
        _fileFinder = fileFinder;
        _fileWriter = new FileWriterWrapper();
    }

    public void createModFilesFile(String folderPath) {
        File root = new File(folderPath);
        if(!root.exists() || !root.isDirectory()) {
            return;
        }

        String modFileData = createModFileData(folderPath, "modfiles.txt");

        File modFiles = new File(root, MOD_FILES_NAME);
        if(!modFiles.exists()) {
            modFiles.delete();
        }

        try {
            _log.debug("Creating modfiles.txt for mod: " + modFiles.getParentFile().getName(), 2);
            modFiles = _fileWriter.createFile(modFiles.getAbsolutePath());
            _fileWriter.writeData(modFiles, modFileData);
        }
        catch(Exception e) {
            _log.error("Problem reading modfiles: " + modFiles.getAbsolutePath(), e);
        }
    }

    public String createModFileData(String root, String modFilesPath) {
        ArrayList<String> files = _fileFinder.findFiles(root);
        if(files.isEmpty()) {
            _log.debug("No mod files found within: " + root, 2);
            return "";
        }
        if(!files.contains(modFilesPath)) {
            files.add(modFilesPath);
        }
        _log.debug("Found \"" + files.size() + "\" entries", 2);
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < files.size(); i++) {
            String file  = files.get(i);
            _log.debug(file, 4);
            builder.append(file);
            builder.append(" ");
            builder.append(i + 1);
            if((i + 1) < files.size()) {
                builder.append(BasePrettyPrinter.NEW_LINE);
            }
        }
        return builder.toString();
    }

    @Override
    public void setFileWriter(IFileWriter fileWriter) {
        _fileWriter = fileWriter;
    }
}

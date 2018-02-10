package com.colonolnutty.module.shareddata;

import com.colonolnutty.module.shareddata.debug.CNLog;
import com.colonolnutty.module.shareddata.io.FileWriterWrapper;
import com.colonolnutty.module.shareddata.io.IFileWriter;
import com.colonolnutty.module.shareddata.io.IWriteFiles;
import com.colonolnutty.module.shareddata.models.hero.HeroInfo;

import java.io.File;

/**
 * User: Jack's Computer
 * Date: 01/09/2018
 * Time: 5:06 PM
 */
public class HeroInfoWriter implements IWriteFiles {

    private CNLog _log;
    private IFileWriter _writer;

    public HeroInfoWriter(CNLog log) {
        _log = log;
        _writer = new FileWriterWrapper();
    }

    public void write(String filePath, HeroInfo heroInfo) {
        File file = new File(filePath);
        try {
            _writer.writeData(file, heroInfo.toString());
        }
        catch(Exception e) {
            _log.error("Failed to write file: " + file.getAbsolutePath(), e);
        }
    }

    @Override
    public void setFileWriter(IFileWriter iFileWriter) {
        _writer = iFileWriter;
    }
}

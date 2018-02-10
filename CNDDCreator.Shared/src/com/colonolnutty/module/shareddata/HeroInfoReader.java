package com.colonolnutty.module.shareddata;

import com.colonolnutty.module.shareddata.debug.CNLog;
import com.colonolnutty.module.shareddata.io.FileReaderWrapper;
import com.colonolnutty.module.shareddata.io.IFileReader;
import com.colonolnutty.module.shareddata.io.IReadFiles;
import com.colonolnutty.module.shareddata.models.hero.*;
import com.colonolnutty.module.shareddata.utils.CNCollectionUtils;
import com.colonolnutty.module.shareddata.utils.CNStringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Jack's Computer
 * Date: 01/09/2018
 * Time: 5:10 PM
 */
public class HeroInfoReader implements IReadFiles {
    private CNLog _log;
    private IFileReader _fileReader;

    public HeroInfoReader(CNLog log) {
        _log = log;
        _fileReader = new FileReaderWrapper();
    }

    public HeroInfo read(String filePath) {
        File file = new File(filePath);
        List<String> fileLines;
        try {
            fileLines = _fileReader.readAllLines(file);
        }
        catch(Exception e) {
            _log.error("Failed to read hero info: " + file.getAbsolutePath(), e);
            return null;
        }
        if(fileLines == null || fileLines.size() == 0) {
            return null;
        }
        ArrayList<String> lines = new ArrayList<String>();
        for(String line : fileLines) {
            lines.add(line);
        }
        return HeroInfo.parse(lines);
    }

    @Override
    public void setFileReader(IFileReader iFileReader) {
        _fileReader = iFileReader;
    }
}

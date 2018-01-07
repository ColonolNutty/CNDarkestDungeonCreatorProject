package com.colonolnutty.module.shareddata.io;

import javax.xml.bind.JAXBException;
import java.io.IOException;

/**
 * User: Jack's Computer
 * Date: 01/06/2018
 * Time: 1:30 PM
 */
public interface IXmlWriter {
    <T> void write(String filePath, T obj) throws JAXBException, IOException;
}

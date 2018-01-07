package com.colonolnutty.module.shareddata.io;

import javax.xml.bind.JAXBException;

/**
 * User: Jack's Computer
 * Date: 01/06/2018
 * Time: 1:30 PM
 */
public interface IXmlReader {
    <T> T read(String filePath, Class<T> classOfType) throws JAXBException;
}

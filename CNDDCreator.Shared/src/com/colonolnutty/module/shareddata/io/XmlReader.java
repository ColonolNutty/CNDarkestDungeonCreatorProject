package com.colonolnutty.module.shareddata.io;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * User: Jack's Computer
 * Date: 01/06/2018
 * Time: 1:30 PM
 */
public class XmlReader implements IXmlReader {
    @Override
    public <T> T read(String filePath, Class<T> classOfType) throws JAXBException {
        File file = new File(filePath);
        JAXBContext jaxbContext = JAXBContext.newInstance(classOfType);

        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        return (T) jaxbUnmarshaller.unmarshal(file);
    }
}

package com.colonolnutty.module.shareddata.io;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * User: Jack's Computer
 * Date: 01/06/2018
 * Time: 1:30 PM
 */
public class XmlWriter implements IXmlWriter {
    public <T> void write(String filePath, T obj) throws JAXBException, IOException {
        JAXBContext contextObj = JAXBContext.newInstance(obj.getClass());

        Marshaller marshallerObj = contextObj.createMarshaller();
        marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(filePath);
            marshallerObj.marshal(obj, outputStream);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finally
        {
            if(outputStream != null) {
                outputStream.close();
            }
        }
    }
}

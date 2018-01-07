package tests.io;

import com.colonolnutty.module.shareddata.io.XmlReader;
import com.colonolnutty.module.shareddata.models.*;
import org.junit.Test;

import javax.xml.bind.JAXBException;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

/**
 * User: Jack's Computer
 * Date: 01/06/2018
 * Time: 1:29 PM
 */
public class XmlReaderTests {
    private XmlReader _reader;


    public XmlReaderTests() {
        _reader = new XmlReader();
    }

    @Test
    public void read_should_give_project_from_test_file() throws JAXBException {
        String fileName = "TestFiles\\testProjectFile.xml";
        Project result = _reader.read(fileName, Project.class);
        assertNotNull(result);
        assertEquals("preview_icon.png", result.PreviewIconFile);
        assertEquals("\\\"shorty\\\"", result.ItemDescriptionShort);
        assertEquals("I:/DD mods/WIP/something/", result.ModDataPath);
        assertEquals("Some class mod", result.Title);
        assertEquals(LanguageType.english, result.Language);
        assertEquals("nope", result.UpdateDetails);
        assertEquals("private", result.Visibility);
        assertEquals(UploadModeType.direct_upload, result.UploadMode);
        assertNotNull(result.VersionMajor);
        assertEquals(200, (int)result.VersionMajor);
        assertNotNull(result.VersionMinor);
        assertEquals(1, (int)result.VersionMinor);
        assertEquals("50.65.21", result.TargetBuild);
        assertEquals("New Class Mod", result.ItemDescription);
        assertNotNull(result.PublishedFileId);
        assertEquals(999999999, (long)result.PublishedFileId);
        assertNotNull(result.Tags);
        Tags tagsList = result.Tags;
        assertNotNull(tagsList);
        List<String> tags = tagsList.Tags;
        assertEquals(3, tags.size());
        assertTrue(tags.contains("Character Mod"));
        assertTrue(tags.contains("Class Mod"));
        assertTrue(tags.contains("New Class"));
    }
}

package tests.io;

import com.colonolnutty.module.shareddata.io.XmlReader;
import com.colonolnutty.module.shareddata.io.XmlWriter;
import com.colonolnutty.module.shareddata.models.project.Project;
import org.junit.Test;

import java.io.File;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

/**
 * User: Jack's Computer
 * Date: 01/06/2018
 * Time: 2:05 PM
 */
public class XmlWriterTests {
    private XmlWriter _writer;

    public XmlWriterTests() {
        _writer = new XmlWriter();
    }

    @Test
    public void should_write_project_to_file() throws Exception {
        String fileName = "TestFiles\\someFile.xml";
        try {
            deleteFile(fileName);
            Project project = new Project();
            project.ItemDescription = "Yes";
            project.VersionMajor = 24;
            project.PreviewIconFile = "No.png";
            _writer.write(fileName, project);
            File file = new File(fileName);
            assertTrue(file.exists());
            XmlReader reader = new XmlReader();
            Project readProject = reader.read(fileName, Project.class);
            assertNotNull(readProject);
            assertEquals(project.ItemDescription, readProject.ItemDescription);
            assertEquals(project.VersionMajor, readProject.VersionMajor);
            assertEquals(project.PreviewIconFile, readProject.PreviewIconFile);
        }
        catch(Exception e) {
            throw e;
        }
        finally {
            deleteFile(fileName);
        }
    }

    private void deleteFile(String path) throws Exception {
        File file = new File(path);
        if(!file.exists()) {
            return;
        }
        if(!file.delete()) {
            throw new Exception("Failed to delete file: " + path);
        }
    }
}

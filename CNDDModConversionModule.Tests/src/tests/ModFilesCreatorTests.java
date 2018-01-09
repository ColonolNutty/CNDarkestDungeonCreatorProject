package tests;

import com.colonolnutty.module.shareddata.debug.CNLog;
import com.colonolnutty.module.shareddata.debug.ConsoleDebugWriter;
import com.colonolnutty.module.shareddata.io.FileFinder;
import com.colonolnutty.module.shareddata.prettyprinters.BasePrettyPrinter;
import main.ModFilesCreator;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * User: Jack's Computer
 * Date: 01/06/2018
 * Time: 2:19 PM
 */
public class ModFilesCreatorTests {

    private ModFilesCreator _creator;

    public ModFilesCreatorTests() {
        _creator = new ModFilesCreator(new CNLog(new ConsoleDebugWriter()), new FileFinder());
    }

    @Test
    public void should_write_mod_file() {
        String testFolder = "TestFiles\\TestFolder";
        String expectedResult = "modfiles.txt 1" +
                BasePrettyPrinter.NEW_LINE + "SomeFolder/somefilethree.txt 2" +
                BasePrettyPrinter.NEW_LINE + "SomeFolder/SomeOtherFolder/someotherfile.txt 3" +
                BasePrettyPrinter.NEW_LINE + "SomeFolderTwo/somefile.txt 4" +
                BasePrettyPrinter.NEW_LINE + "somerootfile.txt 5";
        String result = _creator.createModFileData(testFolder, "modfiles.txt");
        assertEquals(expectedResult, result);
    }
}

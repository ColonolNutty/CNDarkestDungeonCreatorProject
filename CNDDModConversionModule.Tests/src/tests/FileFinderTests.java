package tests;

import main.FileFinder;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

/**
 * User: Jack's Computer
 * Date: 01/06/2018
 * Time: 3:27 PM
 */
public class FileFinderTests {
    private FileFinder _finder;

    public FileFinderTests() {
        _finder = new FileFinder();
    }

    @Test
    public void should_get_a_list_of_all_files_in_folder() {
        String testFolder = "TestFiles\\TestFolder";
        File file = new File(testFolder);
        ArrayList<String> fileList = _finder.getFiles(file, file);
        assertNotNull(fileList);
        assertEquals(5, fileList.size());
        assertTrue(fileList.contains("SomeFolder/SomeOtherFolder/someotherfile.txt"));
        assertTrue(fileList.contains("SomeFolder/somefilethree.txt"));
        assertTrue(fileList.contains("SomeFolderTwo/somefile.txt"));
        assertTrue(fileList.contains("somerootfile.txt"));
    }

    @Test
    public void should_change_file_path_to_relative() {
        String expectedResult = "SomeFolder\\SomeOtherFolder\\someotherfile.txt";
        String testFolder = "TestFiles\\TestFolder";
        File root = new File(testFolder);
        String filePath = "TestFiles\\TestFolder\\SomeFolder\\SomeOtherFolder\\someotherfile.txt";
        File file = new File(filePath);
        String result = _finder.toRelativePathFrom(root, file);
        assertEquals(expectedResult, result);
    }

    @Test
    public void should_flip_slashes() {
        String expectedResult = "SomePath/PathTwo/PathThree/what.txt";
        String testPath = "SomePath\\PathTwo\\PathThree\\what.txt";
        String result = _finder.flipSlashes(testPath);
        assertEquals(expectedResult, result);
    }

}

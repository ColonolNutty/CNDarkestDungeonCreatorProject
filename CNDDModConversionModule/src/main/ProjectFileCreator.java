package main;

import com.colonolnutty.module.shareddata.debug.CNLog;
import com.colonolnutty.module.shareddata.io.*;
import com.colonolnutty.module.shareddata.models.project.Project;
import main.settings.MCSettings;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.ArrayList;

/**
 * User: Jack's Computer
 * Date: 01/06/2018
 * Time: 3:24 PM
 */
public class ProjectFileCreator {
    private static final String BASE_PROJECT_FILE_NAME = "project.xml";
    private Project _templateProject;
    private CNLog _log;
    private IFileFinder _fileFinder;
    private IXmlReader _xmlReader;
    private IXmlWriter _xmlWriter;

    public ProjectFileCreator(CNLog log, MCSettings settings, IFileFinder fileFinder) {
        _log = log;
        _fileFinder = fileFinder;
        _xmlReader = new XmlReader();
        _xmlWriter = new XmlWriter();
        loadTemplateProject(settings.projectFileTemplate);
    }

    private void loadTemplateProject(String templateFile) {
        if(_templateProject == null) {
            _templateProject = readTemplateProject(templateFile);
        }
    }

    public void createProjectFile(String modFolder) {
        File root = new File(modFolder);
        if(_templateProject == null) {
            return;
        }
        File projectFileToCreate = new File(root, BASE_PROJECT_FILE_NAME);
        if(projectFileToCreate.exists()) {
            projectFileToCreate.delete();
        }
        String modName = findModName(root);
        if(modName == null) {
            return;
        }
        _log.debug("Found mod name: " + modName, 2);
        File projectFile = new File(root, modName + ".xml");
        if(projectFile.exists()) {
            projectFile.delete();
        }
        File tempRootProjectName = new File(root, root.getName() + ".xml");
        if(tempRootProjectName.exists()) {
            tempRootProjectName.delete();
        }
        Project modProject = createProject(_templateProject, modName);
        Project projectProject = modProject.copy();
        projectProject.Visibility = "public";
         try {
             _log.debug("Creating project files for mod with name \"" + projectFile.getName() + "\"", 2);
             _xmlWriter.write(projectFile.getAbsolutePath(), modProject);
             _xmlWriter.write(projectFileToCreate.getAbsolutePath(), projectProject);
         }
         catch(Exception e) {
             _log.error("Problem writing project file: " + projectFile.getAbsolutePath(), e);
             return;
         }
    }

    private Project createProject(Project template, String modName) {
        Project project = template.copy();
        project.Title = modName;
        project.ItemDescription = modName;
        project.VersionMajor = 1;
        project.VersionMinor = 0;
        project.PublishedFileId = null;
        project.ItemDescriptionShort = modName;
        project.Visibility = "private";
        project.ModDataPath = modName;
        return project;
    }

    private String findModName(File rootFolder) {
        ArrayList<String> files = _fileFinder.findFiles(rootFolder.getAbsolutePath());
        String foundFileName = null;
        for(int i = 0; i < files.size(); i++) {
            String filePath = files.get(i);
            if(!filePath.contains("heroes")) {
                continue;
            }
            File file = new File(filePath);
            String directoryPath = file.getPath();
            String[] split = directoryPath.split("heroes");
            if(split.length > 1) {
                String[] splitTwo = split[1].split("\\\\");
                if(splitTwo.length > 1) {
                    foundFileName = splitTwo[1];
                    i = files.size();
                }
            }
        }
        if(foundFileName == null) {
            return rootFolder.getName();
        }
        return foundFileName;
    }

    private Project readTemplateProject(String template) {
        try {
            return _xmlReader.read(template, Project.class);
        }
        catch (JAXBException e) {
            _log.error("Problem reading project template file: " + template, e);
        }
        return null;
    }

    public void setXmlReader(IXmlReader reader) {
        _xmlReader = reader;
    }

    public void setXmlWriter(IXmlWriter writer) {
        _xmlWriter = writer;
    }

    public void setFileFinder(IFileFinder fileFinder) {
        _fileFinder = fileFinder;
    }
}

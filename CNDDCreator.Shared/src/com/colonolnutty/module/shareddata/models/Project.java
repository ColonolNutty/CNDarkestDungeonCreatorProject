package com.colonolnutty.module.shareddata.models;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * User: Jack's Computer
 * Date: 01/06/2018
 * Time: 12:56 PM
 */
@XmlRootElement
public class Project {
    public String PreviewIconFile;
    public String ItemDescriptionShort;
    public String ModDataPath;
    public String Title;
    public LanguageType Language;
    public String UpdateDetails;
    public String Visibility;
    public UploadModeType UploadMode;
    public Integer VersionMajor;
    public Integer VersionMinor;
    public String TargetBuild;
    public Tags Tags;
    public String ItemDescription;
    public Long PublishedFileId;

    public Project copy() {
        Project project = new Project();
        project.PreviewIconFile = PreviewIconFile;
        project.ItemDescriptionShort = ItemDescriptionShort;
        project.ModDataPath = ModDataPath;
        project.Title = Title;
        project.Language = Language;
        project.UpdateDetails = UpdateDetails;
        project.Visibility = Visibility;
        project.UploadMode = UploadMode;
        project.VersionMajor = VersionMajor;
        project.VersionMinor = VersionMinor;
        project.TargetBuild = TargetBuild;
        project.Tags = Tags;
        project.ItemDescription = ItemDescription;
        project.PublishedFileId = PublishedFileId;
        return project;
    }
}

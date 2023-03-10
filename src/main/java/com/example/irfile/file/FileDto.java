package com.example.irfile.file;

import java.util.Date;

public class FileDto {
    private Long fileSeq;
    private String fileOriName;
    private String fileType;
    private Date CreatedAt;
    private Date ModifiedAt;
    private String fileNewName;
    private long fileSize;

    private String filePath;

    public FileDto() {
    }

    public FileDto(String fileOriName, String fileType, Date createdAt, Date modifiedAt, String fileNewName, int fileSize, String filePath) {
        this.fileOriName = fileOriName;
        this.fileType = fileType;
        CreatedAt = createdAt;
        ModifiedAt = modifiedAt;
        this.fileNewName = fileNewName;
        this.fileSize = fileSize;
        this.filePath = filePath;
    }

    public Long getFileSeq() {
        return fileSeq;
    }

    public void setFileSeq(Long fileSeq) {
        this.fileSeq = fileSeq;
    }

    public String getFileOriName() {
        return fileOriName;
    }

    public void setFileOriName(String fileOriName) {
        this.fileOriName = fileOriName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Date getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(Date createdAt) {
        CreatedAt = createdAt;
    }

    public Date getModifiedAt() {
        return ModifiedAt;
    }

    public void setModifiedAt(Date modifiedAt) {
        ModifiedAt = modifiedAt;
    }

    public String getFileNewName() {
        return fileNewName;
    }

    public void setFileNewName(String fileNewName) {
        this.fileNewName = fileNewName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String toString() {
        return "FileDto{" +
                "fileSeq=" + fileSeq +
                ", fileOriName='" + fileOriName + '\'' +
                ", fileType='" + fileType + '\'' +
                ", CreatedAt=" + CreatedAt +
                ", ModifiedAt=" + ModifiedAt +
                ", fileNewName='" + fileNewName + '\'' +
                ", fileSize=" + fileSize +
                ", filePath='" + filePath + '\'' +
                '}';
    }
}

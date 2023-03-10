package com.example.irfile.file;

import org.apache.commons.io.FilenameUtils;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
public class FileUploadService {

    @Autowired
    FileDao fileDao;

    @Value("${file.upload.dir}")
    private String uploadDir;
    public Long saveFile(MultipartFile file) throws IOException {

        JSONObject result = new JSONObject();
        Map<String, Object> singleRowData = new HashMap<>();
        List<Map<String, Object>> output = new ArrayList<>();

        String originalFilename = file.getOriginalFilename();
        String fileType = file.getContentType();
        long fileSize = file.getSize();
        String newFilename = generateNewFilename(originalFilename);

        FileDto fileDto = new FileDto();
        fileDto.setFileOriName(originalFilename);
        fileDto.setFileType(fileType);
        fileDto.setFileNewName(newFilename);
        fileDto.setFileSize(fileSize);
        fileDto.setCreatedAt(new Date());

        String filePath = uploadDir + File.separator + newFilename;
        File dest = new File(filePath);
        fileDto.setFilePath(filePath);
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            throw new IOException("cannot save file" + e);
        }

        fileDao.saveFile(fileDto);
        long fileSeq = fileDao.selectLastFileSeq();
        return fileSeq;
    }

    private String generateNewFilename(String originalFilename) {
        String extension = FilenameUtils.getExtension(originalFilename);
        String baseName = FilenameUtils.getBaseName(originalFilename);
        String newFilename = baseName + "_" + UUID.randomUUID().toString() + "." + extension;
        return newFilename;
    }

    public void deleteFile(String filePath) throws IOException {
        File fileToDelete = new File(filePath);

        if (fileToDelete.delete()) {
            System.out.println("File deleted successfully: " + filePath);
        } else {
            throw new IOException("Failed to delete file: " + filePath);
        }
    }
}

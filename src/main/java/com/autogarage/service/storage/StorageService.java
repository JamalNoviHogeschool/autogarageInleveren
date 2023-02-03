package com.autogarage.service.storage;

import com.autogarage.exception.AppsException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class StorageService {

    @Value("${image.save.path}")
    private String uploadPath;

    public String uploadFile(MultipartFile uploadFile) throws AppsException, IOException {
        String uuid = UUID.randomUUID().toString();
        String fileExtension;

        String filename = StringUtils.cleanPath(uploadFile.getOriginalFilename());

        String[] fileNameSplit = filename.split("\\.");
        if (fileNameSplit.length > 1) {
            fileExtension = fileNameSplit[fileNameSplit.length - 1];
            uuid = uuid + "." + fileExtension;
        }

        Path fileNameAndPath = Paths.get(uploadPath, uuid);
        Files.write(fileNameAndPath, uploadFile.getBytes());

        return uuid;
    }

    public byte[] loadFile(String fileName) throws AppsException, IOException {
        String pathName = uploadPath + fileName;
        File file = new File(pathName);

        return this.getBytesByFile(file);
    }

    public byte[] getBytesByFile(File file) throws IOException {

        // Creating an object of FileInputStream to
        // read from a file
        FileInputStream fl = new FileInputStream(file);

        // Now creating byte array of same length as file
        byte[] arr = new byte[(int) file.length()];

        // Reading file content to byte array
        // using standard read() method
        fl.read(arr);

        // lastly closing an instance of file input stream
        // to avoid memory leakage
        fl.close();

        // Returning above byte array
        return arr;
    }
}

package spring.ws.carrentaldirectoryweb.http.http.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/upload")
public class FileUploadController {

    @PostMapping("/")
    public String singleFileUpload(@RequestParam("file") MultipartFile file) {
        try {
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            String UPLOAD_FOLDER = "src/main/resources/uploads/";
            Path path = Paths.get(UPLOAD_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

            return "{\"status\":\"success\"}";

        } catch (IOException e) {
            e.printStackTrace();
            return "{\"status\":\"error\", \"message\":\"Failed to upload file\"}";
        }
    }
}
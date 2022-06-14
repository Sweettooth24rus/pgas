package com.kkoz.pgas.controls;

import com.kkoz.pgas.controls.exceptions.FileExtensionException;
import com.kkoz.pgas.controls.exceptions.FileStoreException;
import com.kkoz.pgas.dto.file.DtoFileDelete;
import com.kkoz.pgas.dto.file.DtoFileUpload;
import com.kkoz.pgas.services.ServiceFileUpload;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/file")
public class ApiFileUpload {

    private final ServiceFileUpload serviceFileUpload;

    @PostMapping("/upload")
    public ResponseEntity<DtoFileUpload> upload(@RequestParam("document") MultipartFile file) {

        log.info("-> POST: Uploading file [name: {}, size: {}] to file-tmp", file.getOriginalFilename(), file.getSize());

        if (!serviceFileUpload.checkExtension(file)) {
            log.info("<- POST: File [{}] has an invalid extension", file.getOriginalFilename());
            throw new FileExtensionException(file.getOriginalFilename());
        }

        DtoFileUpload response;
        try {
            response = serviceFileUpload.upload(file);
        } catch (IOException e) {
            log.info("<- POST: Can't write file [{}]: {}", file.getOriginalFilename(), e.getMessage());
            throw new FileStoreException();
        }

        log.info("<- POST: File [{}] uploaded to [{}]", file.getOriginalFilename(), response.getStorePath());

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Resource> show(@PathVariable UUID uuid) {
        log.info("-> GET: Showing file with uuid [{}]", uuid);
        Map<String, Object> response = serviceFileUpload.show(uuid);
        log.info("<- GET: Resource for file [{}]", response.get("file"));
        return (ResponseEntity<Resource>) response.get("resource");
    }

    @PostMapping("/application")
    public ResponseEntity<Map<String, String>> deleteFromApplication(@RequestBody DtoFileDelete dtoFileDelete) {
        log.info("-> DELETE: Deleting file with uuid [{}]", dtoFileDelete.getUuid());
        String filePath = serviceFileUpload.deleteFromApplication(dtoFileDelete);
        log.info("<- DELETE: File [{}] has been deleted from the server", filePath);

        Map<String, String> response = new HashMap<>();
        response.put("response", "Файл [" + filePath + "] успешно удалён");

        return ResponseEntity.ok(response);
    }
}

package com.erudio.SBJP_Studies.controller;

import com.erudio.SBJP_Studies.controller.docs.FileControllerDocs;
import com.erudio.SBJP_Studies.data.dto.v1.UploadFileResponseDTO;
import com.erudio.SBJP_Studies.service.FileStorageService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/file/v1")
public class FileController implements FileControllerDocs {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    private FileStorageService fileStorageService;

    public FileController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @Override
    public UploadFileResponseDTO uploadFile(MultipartFile file) {
        return null;
    }

    @Override
    public List<UploadFileResponseDTO> uploadMultipleFile(MultipartFile[] files) {
        return List.of();
    }

    @Override
    public ResponseEntity<ResponseEntity> downloadFile(String fileName, HttpServletRequest request) {
        return null;
    }
}

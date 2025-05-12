package com.erudio.SBJP_Studies.controller.docs;

import com.erudio.SBJP_Studies.data.dto.v1.UploadFileResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileControllerDocs {

    @Operation(
            summary = "Upload a file",
            description = "Uploads a single file and returns its details.",
            tags = {"File"},
            responses = {
                    @ApiResponse(
                            description = "File uploaded successfully",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = UploadFileResponseDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    UploadFileResponseDTO uploadFile(MultipartFile file);

    @Operation(
            summary = "Upload multiple files",
            description = "Uploads multiple files and returns their details.",
            tags = {"File"},
            responses = {
                    @ApiResponse(
                            description = "Files uploaded successfully",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = UploadFileResponseDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    List<UploadFileResponseDTO> uploadMultipleFile(MultipartFile[] files);

    @Operation(
            summary = "Download a file",
            description = "Downloads a file by its name.",
            tags = {"File"},
            responses = {
                    @ApiResponse(
                            description = "File downloaded successfully",
                            responseCode = "200",
                            content = @Content
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    ResponseEntity<ResponseEntity> downloadFile(String fileName, HttpServletRequest request);
}

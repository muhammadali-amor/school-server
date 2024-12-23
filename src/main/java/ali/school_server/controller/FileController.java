package ali.school_server.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@RequestMapping("/api/files")
public class FileController {

    @Value("${file.upload-image-dir}")
    private String uploadImageDir;

    @Value("${file.upload-video-dir}")
    private String uploadVideoDir;

    // Rasmlar va videolarni yuklash uchun umumiy metod
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        // Fayl turi bo'yicha saqlash papkasini tanlang
        String fileType = file.getContentType();
        String uploadDir;

        if (fileType != null && fileType.startsWith("image")) {
            uploadDir = uploadImageDir;
        } else if (fileType != null && fileType.startsWith("video")) {
            uploadDir = uploadVideoDir;
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Faqat rasm va video fayllar yuklash mumkin.");
        }

        // Fayl nomini o'zgartirish uchun unikal ID yaratish
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(uploadDir + fileName);

        try {
            // Papkani yaratish
            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // Faylni saqlash
            Files.write(filePath, file.getBytes());
            return ResponseEntity.ok(fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Fayl yuklashda xatolik yuz berdi.");
        }
    }

    @GetMapping("/{type}/{fileName}")
    public ResponseEntity<Resource> getFile(@PathVariable String type, @PathVariable String fileName) {
        String uploadDir;

        // Fayl turi bo'yicha papkani tanlang
        if ("image".equalsIgnoreCase(type)) {
            uploadDir = uploadImageDir;
        } else if ("video".equalsIgnoreCase(type)) {
            uploadDir = uploadVideoDir;
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        try {
            Path filePath = Paths.get(uploadDir).resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                MediaType mediaType = type.equals("image") ? MediaType.IMAGE_JPEG : MediaType.APPLICATION_OCTET_STREAM;
                return ResponseEntity.ok()
                        .contentType(mediaType)
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{type}/{fileName}")
    public ResponseEntity<String> deleteFile(@PathVariable String type, @PathVariable String fileName) {
        String uploadDir;

        // Fayl turi bo'yicha papkani tanlang
        if ("image".equalsIgnoreCase(type)) {
            uploadDir = uploadImageDir;
        } else if ("video".equalsIgnoreCase(type)) {
            uploadDir = uploadVideoDir;
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Faqat rasm va video fayllar o'chirilishi mumkin.");
        }

        try {
            Path filePath = Paths.get(uploadDir).resolve(fileName).normalize();
            File file = filePath.toFile();

            if (file.exists() && file.delete()) {
                return ResponseEntity.ok("Fayl muvaffaqiyatli o'chirildi.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fayl topilmadi yoki o'chirib bo'lmadi.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Faylni o'chirishda xatolik yuz berdi.");
        }
    }


}


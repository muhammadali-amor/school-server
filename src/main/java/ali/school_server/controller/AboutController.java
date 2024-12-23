package ali.school_server.controller;

import ali.school_server.payload.AboutDto;
import ali.school_server.payload.ApiResponse;
import ali.school_server.service.AboutService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/api/about")
public class AboutController {

    private final AboutService aboutService;

    @GetMapping
    public HttpEntity<?> getAbout() {
        List<AboutDto> serviceAbout = aboutService.getAbout();
        return ResponseEntity.ok(serviceAbout);
    }

    @PutMapping()
    public HttpEntity<?> addAbout(@RequestBody AboutDto aboutDto) {
        ApiResponse<?> addAbout = aboutService.addAbout(aboutDto);
        return ResponseEntity.status(addAbout.isSuccess() ? 200 : 400).body(addAbout);
    }
}

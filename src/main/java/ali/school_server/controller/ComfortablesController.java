package ali.school_server.controller;

import ali.school_server.payload.ApiResponse;
import ali.school_server.payload.ComfortableDto;
import ali.school_server.service.ComfortablesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/comfortable")
@CrossOrigin
public class ComfortablesController {

    private final ComfortablesService comfortablesService;

    @GetMapping()
    public HttpEntity<?> getComfortable() {
        List<ComfortableDto> comfortable = comfortablesService.getComfortable();
        return ResponseEntity.ok(comfortable);
    }

    @PostMapping()
    public HttpEntity<?> addComfortable(@RequestBody ComfortableDto comfortablesDto) {
        ApiResponse<?> comfortable = comfortablesService.addComfortable(comfortablesDto);
        return ResponseEntity.status(comfortable.isSuccess() ? 200 : 409).body(comfortable);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteComfortable(@PathVariable int id) {
        ApiResponse<?> comfortable = comfortablesService.deleteComfortable(id);
        return ResponseEntity.status(comfortable.isSuccess() ? 200 : 409).body(comfortable);
    }
}

package ali.school_server.controller;

import ali.school_server.payload.ApiResponse;
import ali.school_server.payload.ComfortablesDto;
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

    @GetMapping("/")
    public HttpEntity<?> getComfortables() {
        List<ComfortablesDto> comfortables = comfortablesService.getComfortables();
        return ResponseEntity.ok(comfortables);
    }

    @PostMapping("/add-comfortable")
    public HttpEntity<?> addComfortable(@RequestBody ComfortablesDto comfortables) {
        ApiResponse<?> comfortable = comfortablesService.addComfortable(comfortables);
        return ResponseEntity.status(comfortable.isSuccess() ? 200 : 409).body(comfortable);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteComfortable(@PathVariable int id) {
        ApiResponse<?> comfortable = comfortablesService.deleteComfortable(id);
        return ResponseEntity.status(comfortable.isSuccess() ? 200 : 409).body(comfortable);
    }
}

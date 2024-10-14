package ali.school_server.controller;

import ali.school_server.payload.ApiResponse;
import ali.school_server.payload.StoriesDto;
import ali.school_server.service.StoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/api/stories")
public class StoriesController {

    private final StoriesService storiesService;

    @GetMapping
    public HttpEntity<?> getStories() {
        List<StoriesDto> stories = storiesService.getStories();
        return ResponseEntity.ok(stories);
    }

    @PostMapping("/add-story")
    public HttpEntity<?> addStory(@RequestBody StoriesDto storiesDto) {
        ApiResponse<?> apiResponse = storiesService.addStories(storiesDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}

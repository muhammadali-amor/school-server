package ali.school_server.controller;

import ali.school_server.payload.ApiResponse;
import ali.school_server.payload.NewsDto;
import ali.school_server.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/api/news")
@RestController
public class NewsController {
    private final NewsService newsService;

    @GetMapping
    public HttpEntity<?> getNews() {
        List<NewsDto> news = newsService.getNews();
        return ResponseEntity.ok(news);
    }

    @PostMapping("/add-news")
    public HttpEntity<?> addNews(@RequestBody NewsDto newsDto) {
        ApiResponse<?> apiResponse = newsService.addNews(newsDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}

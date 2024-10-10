package ali.school_server.service;

import ali.school_server.entity.News;
import ali.school_server.payload.ApiResponse;
import ali.school_server.payload.NewsDto;
import ali.school_server.repository.NewsRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewsService {

    private NewsRepository newsRepository;

    public List<NewsDto> getNews() {
        List<NewsDto> newsDtos = new ArrayList<>();
        for (News news : newsRepository.findAll()) {
            NewsDto newsDto = NewsDto.builder()
                    .title(news.getTitle())
                    .date(news.getDate())
                    .image(news.getPhoto())
                    .description(news.getDescription())
                    .build();
            newsDtos.add(newsDto);
        }
        return newsDtos;
    }

    public ApiResponse<?> addNews(NewsDto newsDto) {
        try {
            News news = News.builder()
                    .title(newsDto.getTitle())
                    .date(newsDto.getDate())
                    .photo(newsDto.getImage())
                    .description(newsDto.getDescription())
                    .build();
            newsRepository.save(news);
            return ApiResponse.builder().message("News added ->").success(false).build();
        } catch (Exception e) {
            System.err.println("error : ->" + e.getMessage());
            return new ApiResponse<>("error : >-" + e.getMessage(), false);
        }
    }

}

package ali.school_server.service;

import ali.school_server.entity.News;
import ali.school_server.payload.ApiResponse;
import ali.school_server.payload.NewsDto;
import ali.school_server.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsService {

    private final NewsRepository newsRepository;

    public List<NewsDto> getNews() {
        List<NewsDto> newsDtos = new ArrayList<>();
        for (News news : newsRepository.findAll()) {
            NewsDto newsDto = NewsDto.builder()
                    .id(news.getId())
                    .name(news.getName())
                    .date(news.getDate())
                    .photoId(news.getPhotoId())
                    .description(news.getDescription())
                    .build();
            newsDtos.add(newsDto);
        }
        return newsDtos;
    }

    public ApiResponse<?> addNews(NewsDto newsDto) {
        try {
            News news = News.builder()
                    .date(newsDto.getDate())
                    .photoId(newsDto.getPhotoId())
                    .description(newsDto.getDescription())
                    .build();
            news.setName(newsDto.getName());
            newsRepository.save(news);
            return ApiResponse.builder().message("News added ->").success(true).build();
        } catch (Exception e) {
            System.err.println("error : ->" + e.getMessage());
            return new ApiResponse<>("error : >-" + e.getMessage(), false);
        }
    }

    public ApiResponse<?> deleteNews(int id) {
        try {
            newsRepository.deleteById(id);
            return ApiResponse.builder().message("News deleted ->").success(true).build();
        }catch (Exception e) {
            System.err.println("error : ->" + e.getMessage());
            return new ApiResponse<>("error : >-" + e.getMessage(), false);
        }
    }

}

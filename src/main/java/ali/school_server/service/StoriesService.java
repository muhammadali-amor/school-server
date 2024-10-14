package ali.school_server.service;

import ali.school_server.entity.Stories;
import ali.school_server.payload.ApiResponse;
import ali.school_server.payload.StoriesDto;
import ali.school_server.repository.StoriesRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class StoriesService {
    private StoriesRepository storiesRepository;

    public List<StoriesDto> getStories() {
        List<StoriesDto> storiesDtos = new ArrayList<>();
        for (Stories story : storiesRepository.findAll()) {
            LocalDateTime now = LocalDateTime.now();
            if (story.getDate().isAfter(now.minusHours(24))) {
                StoriesDto storiesDto = StoriesDto.builder()
                        .id(story.getId())
                        .name(story.getTitle())
                        .video(story.getVideo())
                        .image(story.getPhoto())
                        .build();
                storiesDtos.add(storiesDto);
            }
        }
        return storiesDtos;
    }

    public ApiResponse<?> addStories(StoriesDto storiesDto) {
        try {
            LocalDateTime now = LocalDateTime.now();
            Stories story = Stories.builder()
                    .date(now)
                    .photo(storiesDto.getImage())
                    .video(storiesDto.getVideo())
                    .title(storiesDto.getName())
                    .build();
            storiesRepository.save(story);
            return new ApiResponse<>("Story saved", true);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new ApiResponse<>("error - " + e.getMessage(), false);
        }
    }

}

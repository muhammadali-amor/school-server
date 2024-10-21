package ali.school_server.service;

import ali.school_server.entity.Stories;
import ali.school_server.payload.ApiResponse;
import ali.school_server.payload.StoriesDto;
import ali.school_server.repository.StoriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StoriesService {
    private final StoriesRepository storiesRepository;

    public List<StoriesDto> getStories() {
        List<StoriesDto> storiesDtos = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now(ZoneId.systemDefault());
        for (Stories story : storiesRepository.findAll()) {
            LocalDateTime storyDate = story.getDate();
//            Date date = new Date();
//            System.out.println(date.getDate());
//            System.out.println(date.getYear());
//            System.out.println(story.getDate().toString().substring(8, 10));
//            System.out.println(Integer.parseInt(story.getDate().toString().substring(0, 4)));
//            (date.getDate() == Integer.parseInt(story.getDate().toString().substring(8, 10)) || (Integer.parseInt(story.getDate().toString().substring(8, 10)) + 1) > Integer.parseInt(story.getDate().toString().substring(5, 7))) && (date.getYear() + 1900) == Integer.parseInt(story.getDate().toString().substring(0, 4))
            if (ChronoUnit.HOURS.between(storyDate, now) <= 24) {
                storiesDtos.add(
                        StoriesDto.builder()
                                .id(story.getId())
                                .name(story.getName())
                                .title(story.getTitle())
                                .photoOrVideoId(story.getPhotoOrVideoId())
                                .build()
                );
            }
        }
        return storiesDtos;
    }

    public List<StoriesDto> getStoriesAll() {
        List<StoriesDto> storiesDtos = new ArrayList<>();
        for (Stories story : storiesRepository.findAll()) {
            storiesDtos.add(
                    StoriesDto.builder()
                            .id(story.getId())
                            .name(story.getName())
                            .title(story.getTitle())
                            .photoOrVideoId(story.getPhotoOrVideoId())
                            .build()
            );
        }
        return storiesDtos;
    }

    public ApiResponse<?> addStories(StoriesDto storiesDto) {
        try {
            LocalDateTime now = LocalDateTime.now();
            Stories story = Stories.builder()
                    .date(now)
                    .title(storiesDto.getTitle())
                    .photoOrVideoId(storiesDto.getPhotoOrVideoId())
                    .build();
            story.setName(storiesDto.getName());
            storiesRepository.save(story);
            return new ApiResponse<>("Story saved", true);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new ApiResponse<>("error - " + e.getMessage(), false);
        }
    }

    public ApiResponse<?> deleteStories(Integer id) {
        try {
            storiesRepository.deleteById(id);
            return new ApiResponse<>("Story deleted", true);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new ApiResponse<>("error - " + e.getMessage(), false);
        }
    }
}


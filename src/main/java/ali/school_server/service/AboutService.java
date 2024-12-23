package ali.school_server.service;

import ali.school_server.entity.About;
import ali.school_server.payload.AboutDto;
import ali.school_server.payload.ApiResponse;
import ali.school_server.repository.AboutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AboutService {

    private final AboutRepository aboutRepository;

    public List<AboutDto> getAbout() {
        List<AboutDto> aboutDtos = new ArrayList<>();
        for (About about : aboutRepository.findAll()) {
            AboutDto aboutDto = AboutDto.builder()
                    .id(about.getId())
                    .title(about.getTitle())
                    .name(about.getName())
                    .description(about.getDescription())
                    .photoId(about.getPhotoId())
                    .build();
            aboutDtos.add(aboutDto);
        }
        return aboutDtos;
    }

    public ApiResponse<?> addAbout(AboutDto aboutDto) {
        try {
            About about = aboutRepository.findFirstByOrderById().orElseThrow(() -> new RuntimeException("About record not found!"));
            switch (aboutDto.getIncoming()) {
                case "title" -> about.setTitle(aboutDto.getTitle());
                case "name" -> about.setName(aboutDto.getName());
                case "description" -> about.setDescription(aboutDto.getDescription());
                case "photoId" -> about.setPhotoId(aboutDto.getPhotoId());
                default -> {
                    return new ApiResponse<>("About edited x", false);
                }
            }
            aboutRepository.save(about);
            return new ApiResponse<>("About edited >-", true);
        } catch (Exception e) {
            System.err.println("error : -> " + e.getMessage());
            return new ApiResponse<>("xato : " + e.getMessage(), false);
        }
    }
}

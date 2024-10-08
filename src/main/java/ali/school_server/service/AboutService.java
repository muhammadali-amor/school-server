package ali.school_server.service;

import ali.school_server.entity.About;
import ali.school_server.payload.AboutDto;
import ali.school_server.payload.ApiResponse;
import ali.school_server.repository.AboutRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AboutService {

    private AboutRepository aboutRepository;

    public List<AboutDto> getAbout() {
        List<AboutDto> aboutDtos = new ArrayList<>();
        for (About about : aboutRepository.findAll()) {
            AboutDto aboutDto = AboutDto.builder()
                    .id(about.getId())
                    .title(about.getTitle())
                    .description(about.getDescription())
                    .image(about.getPhoto())
                    .build();
            aboutDtos.add(aboutDto);
        }
        return aboutDtos;
    }

    public ApiResponse<?> addAbout(AboutDto aboutDto) {
        try {
            if (aboutRepository.findAll().isEmpty()){
                About about = About.builder()
                        .title(aboutDto.getTitle())
                        .photo(aboutDto.getImage())
                        .description(aboutDto.getDescription())
                        .build();
                aboutRepository.save(about);
                return new ApiResponse<>("About saved >-", true);
            } else {
                for (About about : aboutRepository.findAll()) {
                    about.setTitle(aboutDto.getTitle());
                    about.setDescription(aboutDto.getDescription());
                    about.setPhoto(aboutDto.getImage());
                    aboutRepository.save(about);
                }
                return new ApiResponse<>("About edited >-", true);
            }
        } catch (Exception e) {
            System.err.println("error : -> " + e.getMessage());
            return new ApiResponse<>("xato : " + e.getMessage(), false);
        }
    }
}

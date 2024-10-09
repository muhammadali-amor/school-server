package ali.school_server.service;

import ali.school_server.entity.Comfortables;
import ali.school_server.payload.ApiResponse;
import ali.school_server.payload.ComfortablesDto;
import ali.school_server.repository.ComfortablesRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ComfortablesService {

    private ComfortablesRepository comfortablesRepository;

    public List<ComfortablesDto> getComfortables() {
        List<ComfortablesDto> comfortablesDtos = new ArrayList<>();
        comfortablesRepository.findAll().forEach(comfortables -> {
            ComfortablesDto build = ComfortablesDto.builder().id(comfortables.getId()).url(comfortables.getUrl()).title(comfortables.getTitle()).image(comfortables.getImage()).build();
            comfortablesDtos.add(build);
        });
        return comfortablesDtos;
    }

    public ApiResponse<?> addComfortable(ComfortablesDto comfortablesDto) {
        try {
            boolean existsed = comfortablesRepository.existsComfortablesByTitle(comfortablesDto.getTitle());
            if (!existsed) {
                Comfortables comfortable = Comfortables.builder()
                        .title(comfortablesDto.getTitle())
                        .image(comfortablesDto.getImage())
                        .url(comfortablesDto.getUrl())
                        .build();
                comfortablesRepository.save(comfortable);
                return ApiResponse.builder().message("Saved comfortable >-").success(true).build();
            } else {
                return ApiResponse.builder().message("Existse comfortable >-").success(false).build();
            }
        } catch (Exception e) {
            System.err.println("error : -> " + e.getMessage());
            return new ApiResponse<>("error : -> " + e.getMessage(), false);
        }
    }

    public ApiResponse<?> deleteComfortable(Integer id) {
        try {
            comfortablesRepository.deleteById(id);
            return ApiResponse.builder().message("Deleted comfortable >-").success(true).build();
        } catch (Exception e) {
            System.err.println("error : -> " + e.getMessage());
            return new ApiResponse<>("error : -> " + e.getMessage(), false);
        }
    }

}

package ali.school_server.service;

import ali.school_server.entity.Comfortable;
import ali.school_server.payload.ApiResponse;
import ali.school_server.payload.ComfortableDto;
import ali.school_server.repository.ComfortableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ComfortablesService {

    private final ComfortableRepository comfortablesRepository;

    public List<ComfortableDto> getComfortable() {
        try {
            List<ComfortableDto> comfortablesDtos = new ArrayList<>();
            List<Comfortable> comfortables = comfortablesRepository.findAll();
            for (Comfortable comfortable : comfortables) {
                ComfortableDto comfortablesDto = ComfortableDto.builder()
                        .id(comfortable.getId())
                        .url(comfortable.getUrl())
                        .name(comfortable.getName())
                        .build();
                comfortablesDtos.add(comfortablesDto);
            }
            return comfortablesDtos;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }


    public ApiResponse<?> addComfortable(ComfortableDto comfortablesDto) {
        try {
            boolean existsComfortableByName = comfortablesRepository.existsComfortableByName(comfortablesDto.getName());
            if (!existsComfortableByName) {
                Comfortable comfortables = Comfortable.builder()
                        .url(comfortablesDto.getUrl())
                        .photoId(comfortablesDto.getPhotoId())
                        .build();
                comfortables.setName(comfortablesDto.getName());
                comfortablesRepository.save(comfortables);
                return new ApiResponse<>("Comfortable saqlandi", true);
            } else {
                return new ApiResponse<>("Comfortable mavjud", false);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new ApiResponse<>("Comfortable saqlashda xatolik", false);
        }
    }

    public ApiResponse<?> deleteComfortable(int id) {
        try {
            comfortablesRepository.deleteById(id);
            return new ApiResponse<>("Comfortable o'chirildi", true);
        } catch (Exception e) {
            return new ApiResponse<>("Comfortable o'chirishda xatolik", false);
        }
    }
}

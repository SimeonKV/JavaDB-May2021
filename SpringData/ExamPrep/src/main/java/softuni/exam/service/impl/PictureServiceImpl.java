package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.PictureSeedDto;
import softuni.exam.models.entity.Picture;
import softuni.exam.repository.PictureRepository;
import softuni.exam.service.CarService;
import softuni.exam.service.PictureService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Service
public class PictureServiceImpl implements PictureService {
    private final static String PICTURE_FILE_PATH  = "src\\main\\resources\\files\\json\\pictures.json";
    private final PictureRepository pictureRepository;
    private final CarService carService;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final Gson gson;

    public PictureServiceImpl(PictureRepository pictureRepository, CarService carService, ModelMapper modelMapper, ValidationUtil validationUtil, Gson gson) {
        this.pictureRepository = pictureRepository;
        this.carService = carService;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.gson = gson;
    }


    @Override
    public boolean areImported() {
        return this.pictureRepository.count() > 0;
    }

    @Override
    public String readPicturesFromFile() throws IOException {
        return Files.readString(Path.of(PICTURE_FILE_PATH));
    }

    @Override
    public String importPictures() throws IOException {
        StringBuilder sb = new StringBuilder();


        PictureSeedDto[] pictureSeedDtos =
                this.gson.fromJson(readPicturesFromFile(),PictureSeedDto[].class);

        Arrays.stream(pictureSeedDtos)
                .filter(pictureSeedDto -> {
                    boolean isValid = this.validationUtil.isValid(pictureSeedDto);

                    if(isValid){
                        sb.append("entity valid").append(System.lineSeparator());
                    }else {
                        sb.append("entity invalid").append(System.lineSeparator());
                    }

                    return isValid;
                })
                .map(pictureSeedDto -> {
                    Picture picture = modelMapper.map(pictureSeedDto, Picture.class);
                    picture.setCar(this.carService.findById(pictureSeedDto.getCar()));
                    return picture;

                }).forEach(picture -> this.pictureRepository.save(picture));

        return sb.toString();
    }
}

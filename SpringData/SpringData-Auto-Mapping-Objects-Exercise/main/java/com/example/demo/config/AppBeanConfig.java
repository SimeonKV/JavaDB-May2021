package com.example.demo.config;

import com.example.demo.model.dto.GameAddDTO;
import com.example.demo.model.entity.Game;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Configuration
public class AppBeanConfig {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();

        Converter<String, LocalDate> stringToDate = c -> LocalDate.parse(c.getSource()
                , DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        modelMapper
                .typeMap(GameAddDTO.class, Game.class)
                .addMappings(mapper ->{
                    mapper.map(GameAddDTO::getThumbnailURL,Game::setImageThumbnail);

                    mapper
                            .using(stringToDate)
                            .map(GameAddDTO::getReleaseDate,Game::setReleasedOn);


                });


        return modelMapper;
    }
}

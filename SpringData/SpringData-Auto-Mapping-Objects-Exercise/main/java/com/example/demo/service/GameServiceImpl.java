package com.example.demo.service;

import com.example.demo.model.dto.GameAddDTO;
import com.example.demo.model.entity.Game;
import com.example.demo.repository.GameRepository;
import com.example.demo.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import java.math.BigDecimal;
import java.util.Set;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public GameServiceImpl(GameRepository gameRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.gameRepository = gameRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public void addGame(GameAddDTO gameAddDTO) {

        Set<ConstraintViolation<GameAddDTO>> violation = this.validationUtil.violation(gameAddDTO);

        if(!violation.isEmpty()){
            violation
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
            return;
        }

        Game game = this.modelMapper.map(gameAddDTO,Game.class);
        this.gameRepository.save(game);

        System.out.println("Added " + game.getTitle());
    }
}

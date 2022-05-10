package com.example.demo;

import com.example.demo.model.dto.GameAddDTO;
import com.example.demo.model.dto.UserLoginDTO;
import com.example.demo.model.dto.UserRegisterDto;
import com.example.demo.service.GameService;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;

@Component
public class CommandLineRunner implements org.springframework.boot.CommandLineRunner {
    private final BufferedReader bufferedReader;
    private final UserService userService;
    private final GameService gameService;

    public CommandLineRunner(UserService userService, GameService gameService) {
        this.userService = userService;
        this.gameService = gameService;
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run(String... args) throws Exception {


        while (true){
            System.out.println("Enter your command");
            String[] commands = bufferedReader.readLine().split("\\|");

            switch (commands[0]){
                case"RegisterUser":
                    userService.registerUser(new UserRegisterDto(commands[1],commands[2]
                            ,commands[3],commands[4]));
                    break;
                case"LoginUser":
                    userService.loginUser(new UserLoginDTO(commands[1],commands[2]));
                    break;
                case "Logout":
                     this.userService.logout();
                    break;
                case "AddGame":
                    this.gameService.addGame(new GameAddDTO(commands[1], new BigDecimal(commands[2]),
                            Double.parseDouble(commands[3]),commands[4],commands[5],commands[6],commands[7]));
                    break;
            }



        }

    }
}

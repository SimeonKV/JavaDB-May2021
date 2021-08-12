package xml.exercise.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import xml.exercise.model.dto.UserSeedDto;
import xml.exercise.model.dto.UserViewRootDto;
import xml.exercise.model.dto.UserWithSoldProductsDto;
import xml.exercise.model.entity.User;
import xml.exercise.repository.UserRepository;
import xml.exercise.service.CategoryService;
import xml.exercise.service.UserService;
import xml.exercise.util.ValidationUtil;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;


    public UserServiceImpl(UserRepository userRepository, CategoryService categoryService, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public void seedUsers(List<UserSeedDto> users) {
        users
                .stream()
                .filter(user -> this.validationUtil.isValid(user))
                .map(userSeedDto -> modelMapper.map(userSeedDto, User.class))
                .forEach(user -> this.userRepository.save(user));


    }

    @Override
    public User getRandomUser() {
        long userId = ThreadLocalRandom
                .current()
                .nextLong(1,this.userRepository.count() + 1);


        return this.userRepository.findById(userId).orElse(null) ;
    }

    @Override
    public long getEntityCount() {
        return this.userRepository.count();
    }

    @Override
    public UserViewRootDto findUsersWithMoreThanOneSoldProduct() {
        UserViewRootDto userViewRootDto = new UserViewRootDto();

        userViewRootDto.setProducts(
                this.userRepository
                .findAllUsersWithMoreThanOneSoldProduct()
                .stream()
                .map(user -> modelMapper.map(user, UserWithSoldProductsDto.class))
                .collect(Collectors.toList())

        );


        return userViewRootDto;
    }


}

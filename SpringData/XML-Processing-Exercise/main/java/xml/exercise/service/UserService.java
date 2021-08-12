package xml.exercise.service;

import xml.exercise.model.dto.UserSeedDto;
import xml.exercise.model.dto.UserViewRootDto;
import xml.exercise.model.entity.User;

import java.util.List;

public interface UserService {
    void seedUsers(List<UserSeedDto> users);

    User getRandomUser();

    long getEntityCount();


    UserViewRootDto findUsersWithMoreThanOneSoldProduct();
}

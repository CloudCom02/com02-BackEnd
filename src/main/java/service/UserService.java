package service;

import dto.UserReq;
import entities.User;
import global.BaseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import repository.UserRepository;

import java.io.IOException;
import java.util.Optional;

import static global.BaseResponseStatus.USERS_EXISTS_EMAIL;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void join(UserReq.UserJoinReq userJoinReq) throws BaseException, IOException {

        Optional<User> userOptional = userRepository.findByEmail(userJoinReq.getEmail());

        if (userOptional.isEmpty()) {
            User user = User.builder()
                    .email(userJoinReq.getEmail())
                    .password(userJoinReq.getPassword())
                    .build();

            userRepository.saveAndFlush(user);
        } else {
            throw new BaseException(USERS_EXISTS_EMAIL);
        }
    }
}

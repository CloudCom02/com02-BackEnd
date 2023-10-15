package com._02server.com02backendproject.service;

import com._02server.com02backendproject.repository.UserRepository;
import com._02server.com02backendproject.dto.UserReq;
import com._02server.com02backendproject.entities.User;
import com._02server.com02backendproject.global.BaseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

import static com._02server.com02backendproject.global.BaseResponseStatus.USERS_EXISTS_EMAIL;

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

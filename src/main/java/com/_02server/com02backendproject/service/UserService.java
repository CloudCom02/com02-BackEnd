package com._02server.com02backendproject.service;

import com._02server.com02backendproject.dto.UserRes;
import com._02server.com02backendproject.repository.CapacityOfUserRepository;
import com._02server.com02backendproject.repository.UserRepository;
import com._02server.com02backendproject.dto.UserReq;
import com._02server.com02backendproject.entities.User;
import com._02server.com02backendproject.global.BaseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Optional;

import static com._02server.com02backendproject.global.BaseResponseStatus.USERS_EXISTS_EMAIL;
import static com._02server.com02backendproject.global.BaseResponseStatus.USERS_NOT_EXISTS;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final CapacityOfUserRepository capacityOfUserRepository;

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

    public UserRes.UserLoginRes login(UserReq.UserLoginReq userLoginReq) throws BaseException, IOException {

        Optional<User> userOptional = userRepository.findByEmail(userLoginReq.getEmail());

        if (userOptional.isEmpty()) {
            throw new BaseException(USERS_NOT_EXISTS);
        } else {
            UserRes.UserLoginRes userLoginRes = new UserRes.UserLoginRes(userOptional.get().getUserId());
            return userLoginRes;
        }
    }

    public UserRes.UserInfoCheckRes info(UserReq.UserInfoCheckReq userInfoCheckReq) throws BaseException, IOException {

        Optional<User> userOptional = userRepository.findByUserId(userInfoCheckReq.getUserIdx());

        if(userOptional.isEmpty()) {
            throw new BaseException(USERS_NOT_EXISTS);
        } else {
            UserRes.UserInfoCheckRes userInfoCheckRes = new UserRes.UserInfoCheckRes(userOptional.get().getEmail());
            return userInfoCheckRes;
        }
    }

    @Transactional
    public void delete(UserReq.UserDeleteReq userDeleteReq) throws BaseException, IOException {
        Optional<User> userOptional = userRepository.findByUserId(userDeleteReq.getUserIdx());

        if(userOptional.isEmpty()){
            throw new BaseException(USERS_NOT_EXISTS);
        } else {
            userRepository.deleteById(userOptional.get().getUserId());
        }
    }
}

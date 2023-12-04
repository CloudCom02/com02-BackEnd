package com._02server.com02backendproject.service;

import com._02server.com02backendproject.dto.UserRes;
import com._02server.com02backendproject.repository.CapacityOfUserRepository;
import com._02server.com02backendproject.repository.UserRepository;
import com._02server.com02backendproject.dto.UserReq;
import com._02server.com02backendproject.entities.User;
import com._02server.com02backendproject.global.BaseException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Optional;
import java.util.Random;

import static com._02server.com02backendproject.global.BaseResponseStatus.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final MailService mailService;
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

    public String sendCodeToEmail(String email, boolean isForJoin) throws BaseException {
        this.checkDuplicatedEmail(email, isForJoin);

        String title = "[아맞다충전!] 이메일 인증 번호 안내";
        String authCode = this.createCode();
        String content = "하단에 안내된 6자리 인증 번호를 입력해주세요.\n" + authCode;

        mailService.sendEmail(email, title, content);
        return authCode;

//        // 이메일 인증 요청 시 인증 번호 Redis에 저장 ( key = "AuthCode " + Email / value = AuthCode )
//        redisService.setValues(AUTH_CODE_PREFIX + toEmail,
//                authCode, Duration.ofMillis(this.authCodeExpirationMillis));
    }

    private void checkDuplicatedEmail(String email, boolean isForJoin) throws BaseException {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (isForJoin) { // 회원가입에 대한 이메일 인증인 경우
            if (userOptional.isPresent()) {
                throw new BaseException(USERS_EXISTS_EMAIL); // 이미 존재함 -> 회원가입 불가능
            }
        } else { // 비밀번호 찾기에 대한 이메일 인증인 경우
            if (userOptional.isEmpty()) {
                throw new BaseException(USERS_NOT_EXISTS); // 가입되지 않은 회원 -> 비밀번호 찾기 불가능
            }
        }
    }

    private String createCode() throws BaseException {
        int length = 6;
        try {
            Random random = SecureRandom.getInstanceStrong();
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < length; i++) {
                builder.append(random.nextInt(10));
            }
            return builder.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new BaseException(ERR_MAKE_CODE);
        }
    }

    public Boolean verifiedCode(String email, String authCode, HttpServletRequest request) throws BaseException {
        String correctCode = request.getSession().getAttribute("code").toString();
        String correctEmail = request.getSession().getAttribute("email").toString();

        if (correctCode.equals(authCode) && correctEmail.equals(email)) {
            return true;
        } else if (!email.equals(correctEmail)) {
            throw new BaseException(INCORRECT_EMAIL);
        } else {
            throw new BaseException(INCORRECT_CODE);
        }

//        String redisAuthCode = redisService.getValues(AUTH_CODE_PREFIX + email);
//        boolean authResult = redisService.checkExistsValue(redisAuthCode) && redisAuthCode.equals(authCode);
//        return EmailVerificationResult.of(authResult);
    }

    public Boolean checkDupEmail(String email) throws BaseException {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            return false;
        }
        return true;
    }

    @Transactional
    public void changePassword(String email, String password) throws BaseException {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            throw new BaseException(USERS_NOT_EXISTS);
        }
        User user = userOptional.get();
        user.setPassword(password);
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

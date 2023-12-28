package com.dev.wanted.domain.application.impl;

import com.dev.wanted.domain.application.UserService;
import com.dev.wanted.domain.dto.request.LoginRequestDto;
import com.dev.wanted.domain.dto.request.UserSignupRequestDto;
import com.dev.wanted.domain.dto.response.UserLoginResponseDto;
import com.dev.wanted.domain.entity.User;
import com.dev.wanted.domain.repository.UserRepository;
import com.dev.wanted.domain.role.entity.Role;
import com.dev.wanted.domain.role.enums.RoleEnum;
import com.dev.wanted.domain.role.repositry.RoleRepository;
import com.dev.wanted.global.exception.user.DeleteUserLogin;
import com.dev.wanted.global.exception.user.InvalidPassword;
import com.dev.wanted.global.exception.user.NotFoundUserAccount;
import com.dev.wanted.global.jwt.util.JwtTokenizer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.dev.wanted.global.jwt.util.JwtTokenizer.REFRESH_TOKEN;
import static com.dev.wanted.global.jwt.util.JwtTokenizer.REFRESH_TOKEN_EXPIRE_COUNT;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final JwtTokenizer jwtTokenizer;

    public UserServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            RoleRepository roleRepository,
            JwtTokenizer jwtTokenizer
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.jwtTokenizer = jwtTokenizer;
    }

    @Override
    @Transactional
    public void signUp(UserSignupRequestDto userSignupRequestDto) {
        User user = User.createUser(userSignupRequestDto, passwordEncoder);
        signupWithRole(user);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public UserLoginResponseDto login(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        User user = userRepository.findByAccount(loginRequestDto.getAccount())
                .orElseThrow(() -> new NotFoundUserAccount(loginRequestDto.getAccount()));

        Optional.of(user.isDeleted())
                .filter(isDeleted -> !isDeleted)
                .orElseThrow(() -> new DeleteUserLogin(loginRequestDto.getAccount()));

        passwordNotMatched(loginRequestDto, user);

        return createToken(response, user);
    }

    private void passwordNotMatched(LoginRequestDto loginRequestDto, User user) {
        Optional.of(!passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword()))
                .filter(passwordNotMatched -> passwordNotMatched)
                .ifPresent(passwordNotMatched -> {
                    throw new InvalidPassword(loginRequestDto.getPassword());
                });
    }


    private UserLoginResponseDto createToken(HttpServletResponse response, User user) {

        List<String> roles = user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toList());

        String accessToken = jwtTokenizer.createAccessToken(
                user.getId(),
                user.getAccount(),
                roles
        );

        String refreshToken = jwtTokenizer.createRefreshToken(
                user.getId(),
                user.getAccount(),
                roles
        );

        addTokenCookie(response, refreshToken);

        return UserLoginResponseDto.of(accessToken);
    }


    private void signupWithRole(User User) {
        Optional<Role> userRole = roleRepository.findByName(RoleEnum.USER.getRoleName());
        userRole.ifPresent(User::changeRole);
    }

    private void addTokenCookie(HttpServletResponse response, String value) {
        Cookie cookie = new Cookie(REFRESH_TOKEN, value);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setMaxAge(Math.toIntExact(REFRESH_TOKEN_EXPIRE_COUNT));
        cookie.setPath("/");
        response.addCookie(cookie);
    }


    public static String getRefreshToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        Optional.ofNullable(cookies)
                .orElseThrow(() -> new IllegalArgumentException("쿠키가 없습니다."));

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(REFRESH_TOKEN)) {
                return cookie.getValue();
            }
        }
        throw new IllegalArgumentException("Refresh Token 은 없습니다.");
    }
}

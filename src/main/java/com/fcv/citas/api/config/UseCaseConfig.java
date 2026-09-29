package com.fcv.citas.api.config;

import com.fcv.citas.api.application.port.in.LoginUseCase;
import com.fcv.citas.api.application.port.in.LogoutUseCase;
import com.fcv.citas.api.application.port.in.RefreshSessionUseCase;
import com.fcv.citas.api.application.port.in.RegisterUserUseCase;
import com.fcv.citas.api.application.port.out.AccessTokenIssuerPort;
import com.fcv.citas.api.application.port.out.PasswordHasherPort;
import com.fcv.citas.api.application.port.out.RefreshTokenHasherPort;
import com.fcv.citas.api.application.port.out.RefreshTokenRepositoryPort;
import com.fcv.citas.api.application.port.out.UserRepositoryPort;
import com.fcv.citas.api.application.service.LoginService;
import com.fcv.citas.api.application.service.LogoutService;
import com.fcv.citas.api.application.service.RefreshSessionService;
import com.fcv.citas.api.application.service.RegisterUserService;
import java.time.Clock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public Clock clock() {
        return Clock.systemUTC();
    }

    @Bean
    public RegisterUserUseCase registerUserUseCase(
            UserRepositoryPort userRepositoryPort, PasswordHasherPort passwordHasherPort) {
        return new RegisterUserService(userRepositoryPort, passwordHasherPort);
    }

    @Bean
    public LoginUseCase loginUseCase(
            UserRepositoryPort userRepositoryPort,
            PasswordHasherPort passwordHasherPort,
            AccessTokenIssuerPort accessTokenIssuerPort,
            RefreshTokenRepositoryPort refreshTokenRepositoryPort,
            RefreshTokenHasherPort refreshTokenHasherPort,
            Clock clock,
            JwtProperties jwtProperties) {
        return new LoginService(
                userRepositoryPort,
                passwordHasherPort,
                accessTokenIssuerPort,
                refreshTokenRepositoryPort,
                refreshTokenHasherPort,
                clock,
                jwtProperties.refreshDays());
    }

    @Bean
    public RefreshSessionUseCase refreshSessionUseCase(
            RefreshTokenRepositoryPort refreshTokenRepositoryPort,
            RefreshTokenHasherPort refreshTokenHasherPort,
            UserRepositoryPort userRepositoryPort,
            AccessTokenIssuerPort accessTokenIssuerPort,
            Clock clock,
            JwtProperties jwtProperties) {
        return new RefreshSessionService(
                refreshTokenRepositoryPort,
                refreshTokenHasherPort,
                userRepositoryPort,
                accessTokenIssuerPort,
                clock,
                jwtProperties.refreshDays());
    }

    @Bean
    public LogoutUseCase logoutUseCase(
            RefreshTokenRepositoryPort refreshTokenRepositoryPort,
            RefreshTokenHasherPort refreshTokenHasherPort,
            Clock clock) {
        return new LogoutService(refreshTokenRepositoryPort, refreshTokenHasherPort, clock);
    }
}

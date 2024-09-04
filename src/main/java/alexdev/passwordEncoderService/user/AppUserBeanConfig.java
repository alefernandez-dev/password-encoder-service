package alexdev.passwordEncoderService.user;

import alexdev.passwordEncoderService.auth.AppPasswordEncoderService;
import alexdev.passwordEncoderService.auth.BCryptPasswordEncoderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppUserBeanConfig {

    @Bean
    AppUserService appUserService(AppUserRepository repository, AppPasswordEncoderService encoderService) {
        return new AppUserService(repository, encoderService);
    }

    @Bean
    AppPasswordEncoderService appPasswordEncoderService(PasswordEncoder encoder) {
        return new BCryptPasswordEncoderService(encoder);
    }

}

package alexdev.passwordEncoderService;

import alexdev.passwordEncoderService.user.AppUser;
import alexdev.passwordEncoderService.user.AppUserRepository;
import alexdev.passwordEncoderService.auth.AppPasswordEncoderService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PasswordEncoderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PasswordEncoderServiceApplication.class, args);
	}

	@Bean
	ApplicationRunner applicationRunner(AppUserRepository repository, AppPasswordEncoderService encoderService) {
		return args -> {
			var alejandro = new AppUser();
			alejandro.generateId();
			alejandro.setUsername("alejandro");
			alejandro.setPassword(encoderService.encode("alejandro"));
			alejandro.setRole(AppUser.AppRole.ADMIN);

			var maria = new AppUser();
			maria.generateId();
			maria.setUsername("maria");
			maria.setPassword(encoderService.encode("maria"));
			maria.setRole(AppUser.AppRole.USER);

			repository.save(alejandro);
			repository.save(maria);
		};
	}

}

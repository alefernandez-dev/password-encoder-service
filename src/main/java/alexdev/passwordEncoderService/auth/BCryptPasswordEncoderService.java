package alexdev.passwordEncoderService.auth;

import org.springframework.security.crypto.password.PasswordEncoder;

public class BCryptPasswordEncoderService implements AppPasswordEncoderService {

    private final PasswordEncoder passwordEncoder;

    public BCryptPasswordEncoderService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String encode(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public boolean verify(String password, String encodedPassword) {
        return passwordEncoder.matches(password, encodedPassword);
    }
}

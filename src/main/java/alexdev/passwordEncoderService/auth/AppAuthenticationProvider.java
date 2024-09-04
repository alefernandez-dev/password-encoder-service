package alexdev.passwordEncoderService.auth;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public class AppAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService detailsService;
    private final AppPasswordEncoderService encoderService;

    public AppAuthenticationProvider(UserDetailsService detailsService, AppPasswordEncoderService encoderService) {
        this.detailsService = detailsService;
        this.encoderService = encoderService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        var username = authentication.getName();
        var password = authentication.getCredentials().toString();

        var userDetails = Optional.of(getUserDetails(username))
                .filter(user -> encoderService.verify(password, user.getPassword()))
                .orElseThrow(() -> new BadCredentialsException("bad credentials"));


        return UsernamePasswordAuthenticationTokenGenerator
                .authenticatedToken(userDetails);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    private UserDetails getUserDetails(String username) {
        return detailsService.loadUserByUsername(username);
    }
}

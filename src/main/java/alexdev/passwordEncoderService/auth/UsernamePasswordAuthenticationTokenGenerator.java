package alexdev.passwordEncoderService.auth;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

public final class UsernamePasswordAuthenticationTokenGenerator {
    private UsernamePasswordAuthenticationTokenGenerator() {
    }

    static UsernamePasswordAuthenticationToken authenticatedToken(UserDetails userDetails) {
        return new UsernamePasswordAuthenticationToken(
                userDetails.getUsername(),
                null,
                userDetails.getAuthorities()
        );
    }
}

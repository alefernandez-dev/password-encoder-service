package alexdev.passwordEncoderService.auth;

import alexdev.passwordEncoderService.user.AppUser;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

public final class UserDetailsMapper {
    private UserDetailsMapper() {
    }

    public static UserDetails toUserDetails(AppUser appUser) {
        return User.builder()
                .username(appUser.getUsername())
                .password(appUser.getPassword())
                .roles(appUser.getRole().name())
                .build();
    }

}

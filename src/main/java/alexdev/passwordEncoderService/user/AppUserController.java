package alexdev.passwordEncoderService.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class AppUserController {

    private final AppUserService userService;

    public AppUserController(AppUserService userService) {
        this.userService = userService;
    }


    @PostMapping
    ResponseEntity<Message> register(@RequestBody AppUser appUser) {
        userService.register(appUser);
        return ResponseEntity.ok(new Message("user registered successfully"));
    }

    @GetMapping
    ResponseEntity<List<AppUserResponse>> list(@RequestParam(required = false, defaultValue = "100") Integer limit) {
        var users = userService.list(limit)
                .stream()
                .map(this::toResponse)
                .toList();
        return ResponseEntity.ok(users);
    }

    private AppUserResponse toResponse(AppUser appUser) {
        return new AppUserResponse(appUser.getId(), appUser.getUsername(), appUser.getRole().name());
    }


    private record Message(String message) implements Serializable {}
    private record AppUserResponse(UUID id, String username, String role) implements Serializable {}

}

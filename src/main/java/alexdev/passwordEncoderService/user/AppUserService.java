package alexdev.passwordEncoderService.user;

import alexdev.passwordEncoderService.auth.AppPasswordEncoderService;

import java.util.List;

public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final AppPasswordEncoderService passwordEncoderService;

    public AppUserService(AppUserRepository appUserRepository,
                          AppPasswordEncoderService passwordEncoderService) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoderService = passwordEncoderService;
    }


    public void register(AppUser appUser) {

        if (appUserRepository.existsByUsername(appUser.getUsername())) {
            throw new UsernameAlreadyExistsException();
        }
        appUser.generateId();
        appUser.setPassword(passwordEncoderService.encode(appUser.getPassword()));
        appUserRepository.save(appUser);
    }



    public List<AppUser> list(int limit) {
        return appUserRepository.listWithLimit(limit);
    }

}

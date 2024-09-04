package alexdev.passwordEncoderService.auth;

public interface AppPasswordEncoderService {

    String encode(String password);

    boolean verify(String password, String encodedPassword);

}

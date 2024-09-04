package alexdev.passwordEncoderService.user;

public class UsernameAlreadyExistsException extends RuntimeException{
    public UsernameAlreadyExistsException() {
        super("username already exists");
    }
}

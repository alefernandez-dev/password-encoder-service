package alexdev.passwordEncoderService.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, UUID> {
    Optional<AppUser> findByUsername(String username);
    @Query(value = "SELECT * FROM app_user LIMIT :limit", nativeQuery = true)
    List<AppUser> listWithLimit(int limit);
    boolean existsByUsername(String username);
}

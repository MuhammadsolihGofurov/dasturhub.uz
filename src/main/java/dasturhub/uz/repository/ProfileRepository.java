package dasturhub.uz.repository;

import dasturhub.uz.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, String> {
    Optional<Profile> findByUsernameAndVisibleTrue(String username);

    boolean existsByUsername(String username);
}

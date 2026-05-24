package dasturhub.uz.services.auth;

import dasturhub.uz.dtos.auth.RegisterDto;
import dasturhub.uz.entity.Profile;
import dasturhub.uz.entity.enums.ProfileRoles;
import dasturhub.uz.repository.ProfileRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements IAuthService{

    private final ProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(ProfileRepository profileRepository, PasswordEncoder passwordEncoder) {
        this.profileRepository = profileRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void registration(RegisterDto registerDto) {
        boolean exists = profileRepository.existsByUsername(registerDto.getUsername());

        if (exists) {
            throw new IllegalArgumentException("Bu foydalanuvchi nomi allaqachon band!");
        }

        // 2. Yangi Profile obyekti yaratamiz va DTO'dan ma'lumotlarni ko'chiramiz
        Profile profile = new Profile();
        profile.setFullName(registerDto.getFullName());
        profile.setUsername(registerDto.getUsername());

        // MUHIM: Parolni BCrypt orqali shifrlab keyin o'rnatamiz
        String encodedPassword = passwordEncoder.encode(registerDto.getPassword());
        profile.setPassword(encodedPassword);

        // 3. Sukut bo'yicha oddiy foydalanuvchi rolini beramiz
        profile.setProfileRole(ProfileRoles.ROLE_USER);

        // 4. Bazaga saqlaymiz
        profileRepository.save(profile);
    }
}

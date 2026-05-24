package dasturhub.uz.services.profile;

import dasturhub.uz.exceptions.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsService {
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}

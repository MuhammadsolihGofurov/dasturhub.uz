package dasturhub.uz.services.auth;

import dasturhub.uz.dtos.auth.RegisterDto;

public interface IAuthService {
    void registration(RegisterDto registerDto);

}

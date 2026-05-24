package dasturhub.uz.dtos.auth;

import lombok.Data;

@Data
public class RegisterDto {
    private String username;
    private String fullName;
    private String password;
}

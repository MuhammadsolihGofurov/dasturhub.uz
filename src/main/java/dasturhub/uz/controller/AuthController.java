package dasturhub.uz.controller;

import dasturhub.uz.dtos.auth.RegisterDto;
import dasturhub.uz.services.auth.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping("/auth")
@Controller
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("registerDto", new RegisterDto());
        return "auth/register";
    }

    @PostMapping("/register")
    public String registerSubmit(@ModelAttribute RegisterDto registerDto, RedirectAttributes redirectAttributes) {
        try {
            authService.registration(registerDto);

            redirectAttributes.addFlashAttribute("successMessage", "Ro'yxatdan muvaffaqiyatli o'tdingiz! Tizimga kiring.");
            return "redirect:/auth/login";

        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/auth/register?error=true";
        }
    }
}

package CRUDWeb.WebAppProject.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


//DTO nebo Data Transfer Objects jsou objekty, které přenášejí data mezi procesy, aby se snížil počet volání metod.
public class UserDTO {

    //Zde vytváříme popis údajů pro uživatele k jejich vyplnění.
    @Email(message = "Vyplňte validní email")
    @NotBlank(message = "Vyplňte uživatelský email")
    @NotNull(message = "Vyplňte uživatelský email")
    private String email;

    @NotBlank(message = "Vyplňte uživatelské heslo")
    @NotNull(message = "Vyplňte uživatelské heslo")
    @Size(min = 6, message = "Heslo musí mít alespoň 6 znaků")
    private String password;

    @NotBlank(message = "Vyplňte uživatelské heslo")
    @NotNull(message = "Vyplňte uživatelské heslo")
    @Size(min = 6, message = "Heslo musí mít alespoň 6 znaků")
    private String passwordConfirmation;


    //Metody k navrácení hodnoty getter a metody pro zápis setter
    //
    //Metoda getEmail()vrací hodnotu email proměnné instance.
    //
    //Metoda setEmail()nastaví hodnotu email proměnné instance na hodnotu zadanou jako parametr.
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }
}
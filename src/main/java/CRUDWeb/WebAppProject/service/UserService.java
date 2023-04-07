package  CRUDWeb.WebAppProject.service;

import CRUDWeb.WebAppProject.dto.UserDTO;
import jakarta.validation.Valid;
import org.springframework.security.core.userdetails.UserDetailsService;

//Rozhraní pro UserServiceImpl
public interface UserService extends UserDetailsService {

    //Metoda, která vytváří nové uživatele
    void create(@Valid UserDTO user, boolean isAdmin);
}

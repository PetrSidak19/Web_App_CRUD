package CRUDWeb.WebAppProject.service;

import CRUDWeb.WebAppProject.dto.UserDTO;
import CRUDWeb.WebAppProject.repository.UserRepository;
import CRUDWeb.WebAppProject.entity.UserEntity;
import CRUDWeb.WebAppProject.exceptions.DuplicateEmailException;
import CRUDWeb.WebAppProject.exceptions.PasswordsDoNotEqualException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    //Autowired poskytuje kontrolu nad tím, kde a jak má být automatické propojení provedeno.
    @Autowired
    private UserRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    //Načtení podrobností o uživateli z datového modelu na základě uživatelského jména a vyvolání výjimky UsernameNotFoundException,
    //pokud v datovém modelu není nalezen žádný uživatel se zadaným uživatelským jménem.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usersRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username, " + username + " not found"));
    }


    //Tento kód je zodpovědný za vytvoření nové uživatelské entity v datovém modelu na základě informací poskytnutých v parametru user a její uložení do databáze
    //s kódováním hesla a zpracováním výjimek pro shodu hesel a problémy s duplicitními emaily.
    @Override
    public void create(UserDTO user, boolean isAdmin) {
        if (!user.getPassword().equals(user.getPasswordConfirmation()))
            throw new PasswordsDoNotEqualException();

        UserEntity userEntity = new UserEntity();

        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        userEntity.setAdmin(isAdmin);

        try {
            usersRepository.save(userEntity);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateEmailException();
        }
    }
}


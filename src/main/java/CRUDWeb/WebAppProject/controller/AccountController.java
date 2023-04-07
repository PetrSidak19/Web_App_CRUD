package CRUDWeb.WebAppProject.controller;


import CRUDWeb.WebAppProject.dto.UserDTO;
import CRUDWeb.WebAppProject.exceptions.DuplicateEmailException;
import CRUDWeb.WebAppProject.exceptions.PasswordsDoNotEqualException;
import CRUDWeb.WebAppProject.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



//Řídí tok dat do objektu modelu a aktualizuje pohled při každé změně
@Controller
//K mapování webových požadavků na konkrétní třídy pro web.
@RequestMapping("account")
public class AccountController {

//Anotace @Autowired poskytuje kontrolu nad tím, kde a jak má být automatické propojení provedeno
    @Autowired
    private UserService userService;

    //Rozhraní anotace GetMapping. Anotace pro mapování požadavků HTTP GET na konkrétní metody obsluhy, která vrací stránku login.
    @GetMapping("/login")
    public String renderLogin() {
        return "login";
    }

    @GetMapping("/register")
    public String renderRegister(@ModelAttribute UserDTO userDTO) {
        // Model je anotace, která váže parametr metody nebo návratovou hodnotu metody k pojmenovanému atributu modelu a poté je vystavuje webovému zobrazení
        return "register";
    }

    //Anotace - písemná informace nějakému dokumentu
    //Anotace pro mapování požadavků HTTP POST na konkrétní metody obsluhy
    @PostMapping("register")
    public String register(
            //Anotace @Valid zajišťuje ověření celého objektu . Důležité je, že provádí validaci celého objektového grafu.
            @Valid @ModelAttribute UserDTO userDTO,
            BindingResult result,
            RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors())
            return renderRegister(userDTO);

        try {
            userService.create(userDTO, false);
        } catch (DuplicateEmailException e) {
            result.rejectValue("email", "error", "Email je již používán.");
            return "register";
        } catch (PasswordsDoNotEqualException e) {
            result.rejectValue("password", "error", "Hesla se nerovnají.");
            result.rejectValue("confirmPassword", "error", "Hesla se nerovnají.");
            return "register";
        }

        redirectAttributes.addFlashAttribute("success", "Uživatel zaregistrován.");
        return "redirect:/account/login";
    }

    //vytvoření vyjímky
    //Příkaz try umožňuje definovat blok kódu, který má být testován na chyby během jeho provádění.

}
package CRUDWeb.WebAppProject.controller;

import CRUDWeb.WebAppProject.entity.EmployeeEntity;
import CRUDWeb.WebAppProject.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

//Balíček Java util obsahuje rámec kolekcí,třídy související s datem a časem, model událostí, internacionalizaci a různé pomocné třídy
import java.util.List;

//Don´t forget write that on web - /employees/PAGE!(Info for me:-))
@Controller
@RequestMapping("/employees")
public class EmployeeController {

    //vytvoření pole employeeService. Vygenerování konstruktoru, který má rozhraní EmployeService.
    private final EmployeeService employeeService;

    //constructor injection - - Podpis konstruktoru je zkompilován a je k dispozici všem, aby jej viděli.
    //
    //Tento kód je konstruktorem třídy EmployeeController. Konstruktor vezme  EmployeeService, který je anotací @Autowired. Používá k automatickému propojení EmployeeService.
    //
    //Celkově je tento kód příkladem toho, jak použít konstruktor k automatickému vkládání závislostí do třídy, což usnadňuje správu a testování.
    public EmployeeController (@Autowired EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    //Nastavená ochrana pro příhlášení uživatele.
    @Secured("ROLE_ADMIN")
    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/info")
    //Objekt model představuje nebo modeluje položku v doméně aplikace
    public String info(Model model) {
        return "app-info";
    }

    //Tento model použijeme v naší šabloně thymeleaf k zobrazení všech dat v tabulce.
    @GetMapping("/home")
    public String listEmployees(Model model) {
        //Vybere všechny zaměstnance z databáze
        List<EmployeeEntity> employeeEntity =employeeService.findAll();

        model.addAttribute("employees", employeeEntity);
        //Vrátí stránku "home"
        return "home";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {

        EmployeeEntity theEmployeeEntity =new EmployeeEntity();

        //vytvoření atributu modelu pro data ze vstupního formuláře
        model.addAttribute("employee", theEmployeeEntity);

        return "model-add";
    }

    @PostMapping("/save")
    //datová vazba pomocí atributu modelu zaměstnanec
    public String saveEmployee(@ModelAttribute("employee") EmployeeEntity employeeEntity){

        //uložení zaměstnance do databáze
        employeeService.save(employeeEntity);

        //Kód je metoda, která zpracovává požadavek od klienta a vrací odpověď přesměrování na zadanou adresu URL.
        return "redirect:/employees/home";
    }

    @GetMapping("/update")
    public String update(@RequestParam("employeeId") int id, Model model) {

        //Za pomoci tohoto kódu získáme zaměstnance z databáze
        EmployeeEntity theEmployeeEntity =employeeService.findById(id);

        //Nastavíme zaměstnance jako atribut modelu pro předvyplnění formuláře
        model.addAttribute("employee", theEmployeeEntity);

        return "model-add";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("employeeId") int id, RedirectAttributes redirectAttributes) {

        //Smazání zaměstnance z databáze
        employeeService.deleteById(id);

        // Vygenerování upozornění při smazání zaměstnance
        redirectAttributes.addFlashAttribute("success", "Zaměstnanec smazán");

        return "redirect:/employees/home";
    }
}

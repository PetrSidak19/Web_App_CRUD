package CRUDWeb.WebAppProject.repository;

import CRUDWeb.WebAppProject.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

//Rozhraní CrudRepository je rozhraní v Spring Data JPA, které poskytuje základní operace CRUD (vytvoření, čtení, aktualizace, mazání) pro entity v databázi.
//
//Long - znamená, že úložiště je určeno pro práci s entitami.
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    //Kód definuje metodu dotazu v rozhraní úložiště Spring Data JPA, která hledá entitu uživatele v datovém modelu na základě emailové adresy.
    Optional<UserEntity> findByEmail(String email);
}

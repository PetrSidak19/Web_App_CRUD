package CRUDWeb.WebAppProject.repository;

import CRUDWeb.WebAppProject.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


//Díky JPA jsou data k rozšíření rozhraní v JpaRepository a proto nepotřebujeme dělat všechny metody CRUD.
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {

    //Tato metoda vygeneruje úložiště JPA, takže můžeme třídit naše data podle Id.
    public List<EmployeeEntity> findAllByOrderByLastNameAsc();

}

//Celkově tento kód ukazuje, jak lze Spring Data JPA použít k definování a interakci s databázovým úložištěm jednoduchým a efektivním způsobem.

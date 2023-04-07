package CRUDWeb.WebAppProject.service;

import CRUDWeb.WebAppProject.entity.EmployeeEntity;
import java.util.List;

//Rozhraní pro metodu CRUD
public interface EmployeeService {

    //Tyto metody představují základní operace potřebné pro správu dat zaměstnanců v databázi.
    //
    //Třídy, které implementují EmployeeService rozhraní, budou poskytovat své vlastní implementace pro tyto metody.
    public List<EmployeeEntity> findAll();

    public EmployeeEntity findById(int theId);

    public void save(EmployeeEntity theEmployeeEntity);

    public void deleteById(int theId);
}

package CRUDWeb.WebAppProject.service;

import CRUDWeb.WebAppProject.repository.EmployeeRepository;
import CRUDWeb.WebAppProject.entity.EmployeeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

//Service - Komponenta služby Spring boot pro manipulaci s databází.
@Service
public class EmployeeServiceImpl implements EmployeeService {

    //vytvoření pole employeeRepository. Vygenerování konstruktoru, který má rozhraní EmployeRepositoy.
    private EmployeeRepository employeeRepository;


    //constructor injection - Podpis konstruktoru je zkompilován a je k dispozici všem, aby jej viděli.
    //
    //Tento kód je konstruktorem třídy EmployeeServiceImpl, která implementuje EmployeeService rozhraní.

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository theEmployeeRepository) {
        employeeRepository = theEmployeeRepository;
    }


    //Tento kód ukazuje, jak použít findAll metodu rozhraní JpaRepository k načtení seznamu entit z databáze a jak vrátit tento seznam do volajícího kódu.
    @Override
    public List<EmployeeEntity> findAll() {

        return employeeRepository.findAllByOrderByLastNameAsc();
    }


    //Tento kód ukazuje, jak použít findById metodu rozhraní JpaRepository k načtení entity z databáze na základě jejího ID a jak zacházet s případem, kdy entita neexistuje.

    @Override
    public EmployeeEntity findById(int theId) {


        Optional<EmployeeEntity> result = employeeRepository.findById(theId);

        EmployeeEntity employeeEntity =null;

        if(result.isPresent()) {

            employeeEntity =result.get();
        }
        else {

            throw new RuntimeException("There is no employee with the ID - "+theId);
        }
        return employeeEntity;
    }


    //Tato metoda vkládá nebo aktualizuje entitu do databáze v závislosti na tom, zda již existuje nebo ne.
    @Override
    public void save(EmployeeEntity theEmployeeEntity) {

        employeeRepository.save(theEmployeeEntity);
    }

    //Metoda deleteById používá k odstranění zaměstnanecké entity se zadaným "theId" z databáze.
    @Override
    public void deleteById(int theId) {

        employeeRepository.deleteById(theId);

    }

}

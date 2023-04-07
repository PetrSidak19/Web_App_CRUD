package CRUDWeb.WebAppProject.entity;

// importovaný balíček Jakarta Persistence je API pro správu perzistence k objektově relačnímu mapování.
import jakarta.persistence.*;

//mapování této třídy entity zaměstnance tabulky z databáze
@Entity
@Table(name="employee")
public class EmployeeEntity {

    // primární klíč této entity (jeden z nástrojů od hibernate) Aplikovaná anotace pro primitivní datové typy naší tabulky
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    //Column se používá k určení mapování sloupce pro pole. Pokud není zadána žádná anotace sloupce, použijí se výchozí hodnoty.
    @Column(name="id")
    private int id;

    @Column(name="first_name")
    private String firstName; //vytvoření řádku pro ukládání jména v databázi
    @Column(name="last_name")
    private String lastName;
    @Column(name="email")
    private String email;
    @Column(name="phone")
    private String phone;

    //vytvoření výchozího konstruktoru
    public EmployeeEntity() {
    }

    // vygenerování konstruktoru za pomoci instance this, kterou zamezíme záměnu mezi atributy třídy a parametry se stejným názvem.
    public EmployeeEntity(int id, String firstName, String lastName, String email, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }

    //Metody k navrácení hodnoty getter a metody pro zápis setter
    //
    //Metoda getFirstName()vrací hodnotu firstName proměnné instance.
    //
    //Metoda setFirstName()nastaví hodnotu firstName proměnné instance na hodnotu zadanou jako parametr.

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    //Metoda toString vrátí řetězec všech atributů do výstupu programu.
    @Override
    public String toString() {
        return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", phone=" + phone + "]";
    }
}

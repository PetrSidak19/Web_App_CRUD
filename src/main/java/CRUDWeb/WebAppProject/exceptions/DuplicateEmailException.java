package CRUDWeb.WebAppProject.exceptions;

public class DuplicateEmailException extends RuntimeException {
}

//Třída RuntimeException je podtřídou třídy Exception, která představuje výjimku, kterou může program zpracovat.
//
// RuntimeException je speciální typ výjimky, která nemusí být deklarována v klauzuli throws metody, což z ní činí pohodlnou volbu pro vlastní výjimky, jako je DuplicateEmailException.

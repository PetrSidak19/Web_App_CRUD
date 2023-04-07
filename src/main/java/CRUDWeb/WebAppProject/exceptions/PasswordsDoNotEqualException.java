package CRUDWeb.WebAppProject.exceptions;

public class PasswordsDoNotEqualException extends RuntimeException {
}

//Třída RuntimeException je podtřídou RuntimeException,takže je pohodlnou volbou pro vlastní výjimky, jako je PasswordsDoNotEqualException.
//
//Rozšířením RuntimeException třídy PasswordsDoNotEqualException je nekontrolovaná výjimka, což znamená, že nemusí být deklarována.

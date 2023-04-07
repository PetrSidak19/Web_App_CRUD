package CRUDWeb.WebAppProject.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


// Anotace Configuration označuje, že třída má metody definice @Bean
@Configuration

//@EnableGlobalMethodSecurity je funkční rozhraní, které potřebujeme k vytvoření naší vrstvy zabezpečení a získání autorizace metody.
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class ApplicationSecurityConfiguration {

    //Tento kód je metoda, která definuje SecurityFilterChain pro framework Spring Security.
    //
    //Celkově tento kód ukazuje, jak použít HttpSecurity objekt ke konfiguraci Spring Security pro aplikaci a jak vytvořit a spravovat SecurityFilterChain objekt pomocí @Beananotace.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //V Javě můžeme pomocí klíčového slova throw vyvolat zaškrtnuté nebo nezaškrtnuté výjimky.
        return http
                .authorizeHttpRequests()
                .anyRequest()
                .permitAll()
                .and()
                .formLogin()
                .loginPage("/account/login")
                .usernameParameter("email")
                .loginProcessingUrl("/account/login")
                .defaultSuccessUrl("/employees/home", true)
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/account/logout")
                .and()
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
//Tím, že definujeme PasswordEncoder tímto způsobem, může jej Spring Security automaticky použít ke kódování a ověřování uživatelských hesel během autorizace.

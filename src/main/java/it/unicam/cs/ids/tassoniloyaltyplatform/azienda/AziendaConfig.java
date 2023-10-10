package it.unicam.cs.ids.tassoniloyaltyplatform.azienda;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AziendaConfig {

    CommandLineRunner commandLineRunner(
            AziendaRepository repository){
        return args -> {
            Azienda noemi =new Azienda(
                    "afaf",
                    "afasf",
                      "9283128L"

            );
            repository.save(noemi);
        };



    }
}

package it.unicam.tassoniloyaltyplatform.security;

import it.unicam.tassoniloyaltyplatform.azienda.Azienda;
import it.unicam.tassoniloyaltyplatform.azienda.AziendaService;
import it.unicam.tassoniloyaltyplatform.dtos.LivelloDTO;
import it.unicam.tassoniloyaltyplatform.dtos.ProgrammaFedeltaDTO;
import it.unicam.tassoniloyaltyplatform.livello.LivelloService;
import it.unicam.tassoniloyaltyplatform.programmaFedelta.*;
import it.unicam.tassoniloyaltyplatform.ruolo.ERole;
import it.unicam.tassoniloyaltyplatform.ruolo.Role;
import it.unicam.tassoniloyaltyplatform.ruolo.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    CommandLineRunner sottoscrizioneCommandLineRunner(RoleRepository repository) {
        return args -> {
            repository.save(new Role(ERole.ROLE_USER));
            repository.save(new Role(ERole.ROLE_ADMIN));
            repository.save(new Role(ERole.ROLE_MODERATOR));
        };
    }

    @Bean
    CommandLineRunner aziendapfCommandLineRunner(AziendaService aziendaService, ProgrammaFedeltaService programmaFedeltaService, LivelloService livelloService) {
        return args -> {
            Azienda LujaCorp = new Azienda("LujaCorp","dipasquale@gmail.com");
            Azienda TassoniSRL=new Azienda("Tassoni SRL","cedrataspa@gmail.com");
            Azienda JoPizzaSPA=new Azienda("JoPizza s.p.a.", "margherita@gmail.com");
            Azienda Multiplex=new Azienda("Cinema Multiplex", "multi@gmail.com");
            Azienda CoujaSRL=new Azienda("Couja SRL", "burnare@gmail.com");
            aziendaService.registraAzienda(LujaCorp);
            aziendaService.registraAzienda(TassoniSRL);
            aziendaService.registraAzienda(JoPizzaSPA);
            aziendaService.registraAzienda(Multiplex);
            aziendaService.registraAzienda(CoujaSRL);

            long id = 1;

            programmaFedeltaService.registraProgrammaFedelta(new ProgrammaFedeltaDTO(TipoProgramma.livelli, id++, "Luja Fedeltà"));
            programmaFedeltaService.registraProgrammaFedelta(new ProgrammaFedeltaDTO(TipoProgramma.livelli, id++, "Cedrata Tassoni 4free"));
            programmaFedeltaService.registraProgrammaFedelta(new ProgrammaFedeltaDTO(TipoProgramma.livelli, id++, "Pizzata assurda"));
            programmaFedeltaService.registraProgrammaFedelta(new ProgrammaFedeltaDTO(TipoProgramma.livelli, id++, "Cinema Multiplex Loyality"));
            programmaFedeltaService.registraProgrammaFedelta(new ProgrammaFedeltaDTO(TipoProgramma.livelli, id++, "Burnare extra"));

            livelloService.aggiungiLivello(new LivelloDTO(new Long(1), "Principiante", 1000));
            livelloService.aggiungiLivello(new LivelloDTO(new Long(1), "Molto figo", 30000));
            livelloService.aggiungiLivello(new LivelloDTO(new Long(2), "Help", 30000));
            livelloService.aggiungiLivello(new LivelloDTO(new Long(3), "Help", 30000));
            livelloService.aggiungiLivello(new LivelloDTO(new Long(4), "Help", 30000));
            livelloService.aggiungiLivello(new LivelloDTO(new Long(5), "Help", 30000));

        };
    }


}

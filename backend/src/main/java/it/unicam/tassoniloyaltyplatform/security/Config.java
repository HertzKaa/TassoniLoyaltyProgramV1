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
            Azienda LorisCorp = new Azienda("LorisCorp","sbaraglia@gmail.com");
            Azienda TassoniSRL=new Azienda("Tassoni SRL","cedrataspa@gmail.com");
            Azienda SanPellegreinoSPA=new Azienda("San Pellegrino s.p.a.", "limonataspa@gmail.com");
            Azienda FarmaciaMilesi=new Azienda("Farmacia Milesi", "milesimail@gmail.com");
            Azienda PizzeriaDaPasquale=new Azienda("Pizzeria da Pasquale", "pasqualemail@gmail.com");
            aziendaService.registraAzienda(LorisCorp);
            aziendaService.registraAzienda(TassoniSRL);
            aziendaService.registraAzienda(SanPellegreinoSPA);
            aziendaService.registraAzienda(FarmaciaMilesi);
            aziendaService.registraAzienda(PizzeriaDaPasquale);

            long id = 1;

            programmaFedeltaService.registraProgrammaFedelta(new ProgrammaFedeltaDTO(TipoProgramma.livelli, id++, "Loris SuperFedeltà"));
            programmaFedeltaService.registraProgrammaFedelta(new ProgrammaFedeltaDTO(TipoProgramma.livelli, id++, "Cedrata Tassoni 4free"));
            programmaFedeltaService.registraProgrammaFedelta(new ProgrammaFedeltaDTO(TipoProgramma.livelli, id++, "Aranciata Superiore"));
            programmaFedeltaService.registraProgrammaFedelta(new ProgrammaFedeltaDTO(TipoProgramma.livelli, id++, "Farmacia Milesi Loyalty"));
            programmaFedeltaService.registraProgrammaFedelta(new ProgrammaFedeltaDTO(TipoProgramma.livelli, id++, "Pasquale Family"));

            livelloService.aggiungiLivello(new LivelloDTO(new Long(1), "Principiante", 1000));
            livelloService.aggiungiLivello(new LivelloDTO(new Long(1), "Molto figo", 30000));
            livelloService.aggiungiLivello(new LivelloDTO(new Long(2), "Help", 30000));
            livelloService.aggiungiLivello(new LivelloDTO(new Long(3), "Help", 30000));
            livelloService.aggiungiLivello(new LivelloDTO(new Long(4), "Help", 30000));
            livelloService.aggiungiLivello(new LivelloDTO(new Long(5), "Help", 30000));

        };
    }


}

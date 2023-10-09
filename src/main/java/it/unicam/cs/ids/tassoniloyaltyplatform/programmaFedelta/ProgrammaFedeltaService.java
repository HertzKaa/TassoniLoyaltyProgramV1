package it.unicam.cs.ids.tassoniloyaltyplatform.programmaFedelta;

import it.unicam.cs.ids.tassoniloyaltyplatform.azienda.Azienda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
public class ProgrammaFedeltaService {
    private final ProgrammaFedeltaRepository programmaFedeltaRepository;
    @Autowired
    public ProgrammaFedeltaService(ProgrammaFedeltaRepository programmaFedeltaRepository) {
        this.programmaFedeltaRepository = programmaFedeltaRepository;
    }
    @GetMapping
    public List<ProgrammaFedelta> getProgrammiFedelta() {
        return programmaFedeltaRepository.findAll();
    }

    public Optional<ProgrammaFedelta> getProgrammaByAziendaAndNome(Azienda azienda, String nome) {
        return programmaFedeltaRepository.findProgrammaFedeltaByAziendaAndNomeProgramma(azienda,nome);
    }
}

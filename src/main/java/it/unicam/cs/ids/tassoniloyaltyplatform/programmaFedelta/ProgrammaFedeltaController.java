package it.unicam.cs.ids.tassoniloyaltyplatform.programmaFedelta;

import it.unicam.cs.ids.tassoniloyaltyplatform.azienda.Azienda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path= "api/programma_fedelta")
public class ProgrammaFedeltaController {
    private final ProgrammaFedeltaService programmaFedeltaService;
    @Autowired
    public ProgrammaFedeltaController(ProgrammaFedeltaService programmaFedeltaService) {
        this.programmaFedeltaService = programmaFedeltaService;
    }
    public List<ProgrammaFedelta> getProgrammiFedelta(){ return programmaFedeltaService.getProgrammiFedelta();
    }
    public Optional<ProgrammaFedelta> getProgrammaByAziendaAndNome(Azienda azienda, String nome){
        return programmaFedeltaService.getProgrammaByAziendaAndNome(azienda, nome);
    }
}

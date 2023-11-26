package it.unicam.cs.ids.tassoniloyaltyplatform.programmaFedelta;

import it.unicam.cs.ids.tassoniloyaltyplatform.azienda.Azienda;
import it.unicam.cs.ids.tassoniloyaltyplatform.dto.ProgrammaFedeltaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProgrammaFactory {
    public ProgrammaFactory() {
    }
    public ProgrammaFedelta crea(Azienda azienda, ProgrammaFedeltaDTO dto) {
        if (dto.getTipo() == TipoProgramma.livelli) {
            return new ProgrammaLivelli(azienda, dto.getNome());
        } else return null;
    }
}
package it.unicam.cs.ids.tassoniloyaltyplatform.programmaFedelta; //Mike

import it.unicam.cs.ids.tassoniloyaltyplatform.azienda.Azienda;
import it.unicam.cs.ids.tassoniloyaltyplatform.dto.programmaFedeltaDTO;
import org.springframework.stereotype.Component;

@Component
public class ProgrammaFactory {
    public ProgrammaFactory() {
    }
    public ProgrammaFedelta crea(Azienda azienda, programmaFedeltaDTO dto) {
        if (dto.getTipo() == TipoProgramma.livelli) {
            return new ProgrammaLivelli(azienda, dto.getNome());
        } else return null;
    }
}
package it.unicam.tassoniloyaltyplatform.programmaFedelta;

import it.unicam.tassoniloyaltyplatform.azienda.Azienda;
import it.unicam.tassoniloyaltyplatform.dtos.ProgrammaFedeltaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProgrammaFactory {

    @Autowired
    public ProgrammaFactory() {
    }
    public ProgrammaFedelta crea(Azienda azienda, ProgrammaFedeltaDTO dto) {
        if (dto.getTipo() == TipoProgramma.livelli) {
            return new ProgrammaLivelli(azienda, dto.getNome());
        } else return null;
    }
}
package it.unicam.cs.ids.tassoniloyaltyplatform.programmaFedelta;

import it.unicam.cs.ids.tassoniloyaltyplatform.azienda.Azienda;
import org.springframework.stereotype.Component;

@Component
public class CreatoreProgrammi {
    public ProgrammaFedelta creaProgrammaFedelta(TipoProgrammaFedelta tipo, Azienda azienda, ProgrammaFedelta programmaFedelta){
       if(tipo==TipoProgrammaFedelta.coalizioni){
           if (!(programmaFedelta instanceof ProgrammaFedeltaCoalizione programmaFedeltaCoalizione)) {
                return null;//da aggiungere, modificando il ProgrammaCoalizione e serve qualcosa per crearlo, forse fare un altra istanza , tipo il Dto di stas
            }

       }
       return null;
    }
} //

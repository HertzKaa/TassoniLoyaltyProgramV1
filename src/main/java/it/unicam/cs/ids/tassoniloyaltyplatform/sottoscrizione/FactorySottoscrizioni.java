package it.unicam.cs.ids.tassoniloyaltyplatform.sottoscrizione;

import it.unicam.cs.ids.tassoniloyaltyplatform.carta.Carta;
import it.unicam.cs.ids.tassoniloyaltyplatform.programmaFedelta.ProgrammaFedelta;
import it.unicam.cs.ids.tassoniloyaltyplatform.programmaFedelta.ProgrammaLivelli;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FactorySottoscrizioni {
    @Autowired
    public FactorySottoscrizioni(){

    }

    public Sottoscrizione creaSottoscrizione(ProgrammaFedelta programmaFedelta, Carta tessera){

        if(programmaFedelta instanceof ProgrammaLivelli programmaLivelli){
            return new SottoscrizioneLivelli(programmaLivelli,tessera);
        }else {
            //altri tipi di iscrizione
            return null;
        }
    }
}

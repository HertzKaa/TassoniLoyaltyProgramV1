package it.unicam.cs.ids.tassoniloyaltyplatform.sottoscrizione;

import it.unicam.cs.ids.tassoniloyaltyplatform.carta.Carta;
import it.unicam.cs.ids.tassoniloyaltyplatform.programmaFedelta.ProgrammaFedelta;
import it.unicam.cs.ids.tassoniloyaltyplatform.programmaFedelta.ProgrammaLivelli;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FactorySottoscrizioni {

    public FactorySottoscrizioni(){

    }



    public static Sottoscrizione creaSottoscrizione(ProgrammaFedelta programmaFedelta, Carta carta){

        if(programmaFedelta instanceof ProgrammaLivelli programmaLivelli){
            return new SottoscrizioneLivelli(programmaLivelli,carta);
        }else {
            //altri tipi di iscrizione
            return null;
        }
    }
}

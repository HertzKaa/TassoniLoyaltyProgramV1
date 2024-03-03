package it.unicam.tassoniloyaltyplatform.iscrizione;

import it.unicam.tassoniloyaltyplatform.carta.Carta;
import it.unicam.tassoniloyaltyplatform.programmaFedelta.ProgrammaFedelta;
import it.unicam.tassoniloyaltyplatform.programmaFedelta.ProgrammaLivelli;
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

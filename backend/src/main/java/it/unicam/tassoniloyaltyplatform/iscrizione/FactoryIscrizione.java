package it.unicam.tassoniloyaltyplatform.iscrizione;

import it.unicam.tassoniloyaltyplatform.programmaFedelta.ProgrammaFedelta;
import it.unicam.tassoniloyaltyplatform.programmaFedelta.ProgrammaLivelli;
import it.unicam.tassoniloyaltyplatform.tessera.Tessera;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FactoryIscrizione {

    @Autowired
    public FactoryIscrizione(){

    }

    public Iscrizione creaIscrizione(ProgrammaFedelta programmaFedelta, Tessera tessera){

        if(programmaFedelta instanceof ProgrammaLivelli programmaLivelli){
            return new IscrizioneLivelli(programmaLivelli,tessera);
        }else {
            //altri tipi di iscrizione
            return null;
        }
    }
}
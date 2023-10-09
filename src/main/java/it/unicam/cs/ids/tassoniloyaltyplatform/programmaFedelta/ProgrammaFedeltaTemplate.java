package it.unicam.cs.ids.tassoniloyaltyplatform.programmaFedelta;



/**
 * Questa classe serve a visualizzare i diversi template utilizzabili in Loyality Program
 */
public class ProgrammaFedeltaTemplate {

    private final TipoProgrammaFedelta tipo;

    private String descrizione;


    // Costruttore che usa solo il tipo
    public ProgrammaFedeltaTemplate(TipoProgrammaFedelta tipo) {
        this.tipo = tipo;
    }

    // Costruttore che usa il tipo e la descrizione
    public ProgrammaFedeltaTemplate(TipoProgrammaFedelta tipo, String descrizione){
        this.tipo=tipo;
        this.descrizione = descrizione;
    }

    public TipoProgrammaFedelta getTipo() {
        return tipo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
}

package it.unicam.tassoniloyaltyplatform.dto; //Mike

import it.unicam.tassoniloyaltyplatform.programmaFedelta.TipoProgramma;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class programmaFedeltaDTO {

    TipoProgramma tipo;
    Long aziendaId;
    String nome;

}
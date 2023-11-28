package it.unicam.cs.ids.tassoniloyaltyplatform.dto; //Mike

import it.unicam.cs.ids.tassoniloyaltyplatform.programmaFedelta.TipoProgramma;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class programmaFedeltaDTO {

    TipoProgramma tipo;
    Long aziendaId;
    String nome;

}
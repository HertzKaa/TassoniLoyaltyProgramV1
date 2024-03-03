package it.unicam.tassoniloyaltyplatform.dtos;

import it.unicam.tassoniloyaltyplatform.programmaFedelta.TipoProgramma;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProgrammaFedeltaDTO {

    TipoProgramma tipo;
    Long aziendaId;
    String nome;

}
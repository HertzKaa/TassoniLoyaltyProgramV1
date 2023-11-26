package it.unicam.cs.ids.tassoniloyaltyplatform.dto;

import it.unicam.cs.ids.tassoniloyaltyplatform.programmaFedelta.TipoProgramma;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProgrammaFedeltaDTO {
    TipoProgramma tipo;
    Long aziendaId;
    String nome;
}
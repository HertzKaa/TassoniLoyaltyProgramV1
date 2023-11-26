package it.unicam.cs.ids.tassoniloyaltyplatform.dto;

import it.unicam.cs.ids.tassoniloyaltyplatform.programmaFedelta.TipoProgrammaFedelta;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProgrammaFedeltaDTO {
    TipoProgrammaFedelta tipo;
    Long aziendaId;
    String nome;
}
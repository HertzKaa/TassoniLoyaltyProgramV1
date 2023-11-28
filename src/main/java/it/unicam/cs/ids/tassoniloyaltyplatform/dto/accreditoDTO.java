package it.unicam.cs.ids.tassoniloyaltyplatform.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class accreditoDTO {

    private Long tesseraId;
    private Long aziendaId;
    private double sommaAcquisto;

}
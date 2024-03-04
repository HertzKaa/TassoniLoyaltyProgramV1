package it.unicam.tassoniloyaltyplatform.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AccreditoDTO {

    private Long tesseraId;
    private Long aziendaId;
    private double sommaAcquisto;

}
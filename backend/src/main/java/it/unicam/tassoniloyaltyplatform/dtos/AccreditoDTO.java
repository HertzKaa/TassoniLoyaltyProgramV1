package it.unicam.tassoniloyaltyplatform.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AccreditoDTO {

    private Long cartaId;
    private Long aziendaId;
    private double sommaAcquisto;

}
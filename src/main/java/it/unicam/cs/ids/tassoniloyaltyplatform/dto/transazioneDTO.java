package it.unicam.cs.ids.tassoniloyaltyplatform.dto; //Mike

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class transazioneDTO {

    private Long cartaId;
    private Long aziendaId;
    private double sommaAcquisto;

}
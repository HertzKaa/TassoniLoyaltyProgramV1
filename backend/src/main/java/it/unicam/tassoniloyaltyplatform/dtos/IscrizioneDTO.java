package it.unicam.tassoniloyaltyplatform.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class IscrizioneDTO {

    Long cartaId;
    Long programmaId;
    Long clienteId;

}
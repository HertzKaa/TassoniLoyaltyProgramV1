package it.unicam.cs.ids.tassoniloyaltyplatform.transazione;

import it.unicam.cs.ids.tassoniloyaltyplatform.dto.transazioneDTO;
import it.unicam.cs.ids.tassoniloyaltyplatform.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/accredito")
public class TransazioneController {

    private final TransazioneService transazioneService;

    @Autowired
    public TransazioneController( TransazioneService transazioneService) {
        this.transazioneService = transazioneService;

    }

    @GetMapping
    public List<Transazione> getTransazioni(){
        return transazioneService.getTransazioni();
    }

    @GetMapping(path = "/{cartaId}")
    public List<Transazione> getTransizioniByTessera(@PathVariable("carta") Long id){
        return getTransazioni().stream()
                .filter(a -> a.getCarta().getCartaId().equals(id))
                .toList();
    }

    @PostMapping(path = "/convalida")
    @ResponseStatus(value = HttpStatus.OK)
    public void convalidaAcquisto(@RequestBody transazioneDTO accreditoDTO) throws ResourceNotFoundException {
        transazioneService.aggiungiTransazione(accreditoDTO.getAziendaId(),
                accreditoDTO.getCartaId(),
                accreditoDTO.getSommaAcquisto());
    }

}

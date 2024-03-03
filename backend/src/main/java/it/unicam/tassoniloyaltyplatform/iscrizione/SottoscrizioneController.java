package it.unicam.tassoniloyaltyplatform.iscrizione;

import it.unicam.tassoniloyaltyplatform.exception.ResourceNotFoundException;
import it.unicam.tassoniloyaltyplatform.premio.Premio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/sottoscrizioni")
public class SottoscrizioneController {
private final SottoscrizioneService sottoscrizioneService;
    @Autowired
    public SottoscrizioneController(SottoscrizioneService sottoscrizioneService) {
        this.sottoscrizioneService = sottoscrizioneService;
    }
    public List<Sottoscrizione> getSottoscrizioni() {
        return sottoscrizioneService.getSottoscrizioni();
    }

    @PostMapping(path = "/{tesseraId}/programma/{programmaId}")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void registraIscrizione(@PathVariable("cartaId") Long cartaId, @PathVariable("programmaId") Long programmaId) throws ResourceNotFoundException {
        sottoscrizioneService.registraSottoscrizione(cartaId, programmaId);
    }

    @DeleteMapping(path = "{sottoscrizioneId}")
    public void deleteSottoscrizione(@PathVariable("sottoscrizioneId") Long sottoscrizioneId) {
        sottoscrizioneService.cancellaSottoscrizione(sottoscrizioneId);
    }

    @PutMapping(path = "/riscattapremio/{premioId}")
    @ResponseStatus(value = HttpStatus.OK, reason = "Premio riscattato.")
    public Premio riscattaPremioLivelli(@PathVariable("premioId") Long premioId, @RequestParam Long iscrizioneId) throws ResourceNotFoundException {
        return sottoscrizioneService.riscattaPremio(premioId, iscrizioneId);
    }

    @GetMapping(path = "/{iscrizioneId}")
    public Sottoscrizione getSottoscrizioneById(@PathVariable("iscrizioneId") Long id) throws Exception {
        return sottoscrizioneService.getSottoscrizioneById(id);
    }

    @GetMapping(path = "/vantaggidisponibili/{iscrizioneId}")
    public List<Premio> visualizzaPremiRiscattabiliLivelli(@PathVariable("iscrizioneId") Long iscrizioneId) throws ResourceNotFoundException {
        return sottoscrizioneService.premiRiscattabiliLivelli(iscrizioneId);
    }
    @GetMapping(path = "/vantaggi/{iscrizioneId}")
    public List<Premio> visualizzaVantaggiProgrammaLivelli(@PathVariable("iscrizioneId") Long iscrizioneId) throws Exception {
        return sottoscrizioneService.visualizzaVantaggiProgrammaLivelli(iscrizioneId);
    }

}

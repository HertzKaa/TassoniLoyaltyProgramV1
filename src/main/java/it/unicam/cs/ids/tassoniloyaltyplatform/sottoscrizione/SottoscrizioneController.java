package it.unicam.cs.ids.tassoniloyaltyplatform.sottoscrizione;

import it.unicam.cs.ids.tassoniloyaltyplatform.dto.sottoscrizioneDTO;
import it.unicam.cs.ids.tassoniloyaltyplatform.exception.ResourceAlreadyExistsException;
import it.unicam.cs.ids.tassoniloyaltyplatform.exception.ResourceNotFoundException;
import it.unicam.cs.ids.tassoniloyaltyplatform.premio.Premio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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

    @PostMapping
    public ResponseEntity<Object> registraNuovaSottoscrizione(@RequestBody @Validated sottoscrizioneDTO dto) {
        try {
            Sottoscrizione newSub = sottoscrizioneService.addNewSottoscrizione(dto.getClienteId(), dto.getProgrammaId());
            return ResponseEntity.status(HttpStatus.CREATED).body(newSub);
        } catch (ResourceNotFoundException | ResourceAlreadyExistsException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping(path = "{sottoscrizioneId}")
    public void deleteSottoscrizione(@PathVariable("sottoscrizioneId") Long sottoscrizioneId) {
        sottoscrizioneService.deleteSottoscrizione(sottoscrizioneId);
    }

    @GetMapping(path = "/{sottoscrizioneId}/premi/{premioId}/riscatto")
    public ResponseEntity<Sottoscrizione> riscattaPremio(@PathVariable("sottoscrizioneId") Long sottoscrizioneId, @PathVariable("premioId") Long premioId){
        try{
            Sottoscrizione updatedSub = sottoscrizioneService.riscattaPremio(sottoscrizioneId,premioId);
            return new ResponseEntity<>(updatedSub, HttpStatus.OK);
        } catch(RuntimeException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
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

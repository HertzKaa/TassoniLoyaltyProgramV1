package it.unicam.cs.ids.tassoniloyaltyplatform.sottoscrizione;

import it.unicam.cs.ids.tassoniloyaltyplatform.dto.SottoscrizioneDTO;
import it.unicam.cs.ids.tassoniloyaltyplatform.exception.ResourceAlreadyExistsException;
import it.unicam.cs.ids.tassoniloyaltyplatform.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/sottoscrizioni")
public class SottoscrizioneController {
private SottoscrizioneService sottoscrizioneService;

    public SottoscrizioneController(SottoscrizioneService sottoscrizioneService) {
        this.sottoscrizioneService = sottoscrizioneService;
    }
    @DeleteMapping(path = "{sottoscrizioneId}")
    public void deleteSottoscrizione(@PathVariable("sottoscrizioneId") Long sottoscrizioneId) {
        sottoscrizioneService.deleteSottoscrizione(sottoscrizioneId);
    }

    @PostMapping
    public ResponseEntity<Object> registraNuovaSottoscrizione(@RequestBody @Validated SottoscrizioneDTO dto) {
        try {
            Sottoscrizione newSub = sottoscrizioneService.addNewSottoscrizione(dto.getCartaId(), dto.getProgrammaId());
            return ResponseEntity.status(HttpStatus.CREATED).body(newSub);
        } catch (ResourceNotFoundException | ResourceAlreadyExistsException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
    @GetMapping
    public List<Sottoscrizione> getSottoscrizioni() {
        return sottoscrizioneService.getSottoscrizioni();
    }


}

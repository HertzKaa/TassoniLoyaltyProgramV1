package it.unicam.tassoniloyaltyplatform.livello;

import it.unicam.tassoniloyaltyplatform.dtos.LivelloDTO;
import it.unicam.tassoniloyaltyplatform.eccezioni.RecordAlreadyExistsException;
import it.unicam.tassoniloyaltyplatform.eccezioni.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/livello")
public class LivelloController {

    private final LivelloService livelloService;

    @Autowired
    public LivelloController(LivelloService livelloService) {
        this.livelloService = livelloService;
    }

    @GetMapping
    public List<Livello> getAllLivelli() {
        return livelloService.getAllLivelli();
    }

    @GetMapping(path = "/{livelloId}")
    public Livello getLivelloById(@PathVariable("livelloId") Long id) throws RecordNotFoundException {
        return livelloService.findLivelloByID(id);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED,
            reason = "Livello creato correttamente.")
    public void aggiungiLivello(@RequestBody LivelloDTO dto) throws RecordNotFoundException, RecordAlreadyExistsException {
        this.livelloService.aggiungiLivello(dto);
    }

    @PatchMapping(path = "/{livelloId}")
    @ResponseStatus(value = HttpStatus.CREATED,
            reason = "Livello creato correttamente.")
    public void modificaLivello(@PathVariable("livelloId") Long id,
                                @RequestParam String nome,
                                @RequestParam Integer expNextLevel)
            throws RecordNotFoundException, RecordAlreadyExistsException {
        this.livelloService.modificaLivello(id, nome, expNextLevel);
    }
}
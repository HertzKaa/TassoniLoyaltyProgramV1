package it.unicam.cs.ids.tassoniloyaltyplatform.azienda; //Mike

import it.unicam.cs.ids.tassoniloyaltyplatform.exception.ResourceNotFoundException;
import it.unicam.cs.ids.tassoniloyaltyplatform.exception.ResourceAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/azienda")
public class AziendaController {

    private final AziendaService aziendaService;

    @Autowired
    public AziendaController(AziendaService aziendaService) {
        this.aziendaService = aziendaService;
    }

    @GetMapping
    public List<Azienda> getAllAziende() {
        return aziendaService.getAziende();
    }

    @GetMapping(path = "/{aziendaId}")
    public Azienda findAziendaById(@PathVariable("aziendaId") Long id) throws ResourceNotFoundException{
        return aziendaService.findAziendaById(id);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED, reason = "Nuova azienda registrata correttamente.")
    public void registraAzienda(@RequestBody Azienda azienda) throws ResourceAlreadyExistsException {
        aziendaService.registraAzienda(azienda);
    }

    @PutMapping(path = "{id_azienda}")
    @ResponseStatus(value = HttpStatus.OK, reason = "Dati azienda modificati correttamente.")
    public void modificaAzienda(
            @PathVariable("id_azienda") Long id,
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String indirizzo) throws Exception{
        aziendaService.modificaAzienda(id, nome, indirizzo);
    }

    @DeleteMapping(path = "{id_azienda}")
    @ResponseStatus(value = HttpStatus.OK, reason = "Azienda eliminata.")
    public void cancellaAzienda(@PathVariable("id_azienda") Long id) throws ResourceNotFoundException {
        aziendaService.cancellaAzienda(id);
    }

}
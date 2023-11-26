package it.unicam.cs.ids.tassoniloyaltyplatform.azienda;

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
    public Azienda findAziendaById(@PathVariable("aziendaId") Long id) throws RecordNotFoundException{
        return aziendaService.findAziendaById(id);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED, reason = "Nuova azienda registrata correttamente.")
    public void registraAzienda(@RequestBody Azienda azienda) throws RecordAlreadyExistsException {
        aziendaService.registraAzienda(azienda);
    }

    @PutMapping(path = "{azienda_id}")
    @ResponseStatus(value = HttpStatus.OK, reason = "Dati azienda modificati correttamente.")
    public void modificaAzienda(
            @PathVariable("azienda_id") Long id,
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String indirizzo,
            @RequestParam(required = false) String pIva) throws Exception{
        aziendaService.modificaAzienda(id, nome, indirizzo, pIva);
    }

    @DeleteMapping(path = "{azienda_id}")
    @ResponseStatus(value = HttpStatus.OK, reason = "Azienda eliminata.")
    public void cancellaAzienda(@PathVariable("azienda_id") Long id) throws RecordNotFoundException {
        aziendaService.cancellaAzienda(id);
    }

}

package it.unicam.tassoniloyaltyplatform.premio;

import it.unicam.tassoniloyaltyplatform.dto.premioLivelloDTO;
import it.unicam.tassoniloyaltyplatform.exception.ResourceAlreadyExistsException;
import it.unicam.tassoniloyaltyplatform.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/premio")
public class PremioController {

    private final PremioService premioService;


    @Autowired
    public PremioController(PremioService premioService) {
        this.premioService = premioService;
    }
    @GetMapping
    public List<Premio> getAllPremi(){
        return this.premioService.getAllPremi();
    }
    @GetMapping(path = "/{id_premio}")
    public Premio getPremioById(@PathVariable Long id) throws ResourceNotFoundException {
        return this.premioService.findPremioById(id);
    }
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED, reason = "Creazione del premio avvenuta.")
    public void aggiungiPremioLivello(@RequestBody premioLivelloDTO premioDTO) throws ResourceNotFoundException, ResourceAlreadyExistsException {
        this.premioService.aggiungiPremioLivello(premioDTO);
    }
}

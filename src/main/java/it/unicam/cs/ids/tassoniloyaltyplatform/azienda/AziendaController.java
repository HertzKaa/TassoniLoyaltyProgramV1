package it.unicam.cs.ids.tassoniloyaltyplatform.azienda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/azienda")
public class AziendaController {
    private final AziendaService aziendaService;
    @Autowired
    public AziendaController(AziendaService aziendaService) {
        this.aziendaService = aziendaService;
    }

    @GetMapping
    public List<Azienda> getAziende(){
        return aziendaService.getAziende();
    }
    @PostMapping
    public void aggiungiAzienda(@RequestBody Azienda azienda){
        aziendaService.aggiungiAzienda(azienda);
    }
    @DeleteMapping(path= "{aziendaId}")
    public void rimuoviAzienda(@PathVariable("aziendaId")Long aziendaId){
        aziendaService.rimuoviAzienda(aziendaId);
    }
    public void updateAzienda(
            @PathVariable("aziendaId") Long aziendaId,
            @RequestParam(required = false) String nomeAzienda,
            @RequestParam(required = false) String indirizzoAzienda,
            @RequestParam(required = false) Long contattoAzienda){
        aziendaService.updateAzienda(aziendaId,nomeAzienda,indirizzoAzienda,contattoAzienda);
    }
}

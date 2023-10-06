package it.unicam.cs.ids.tassoniloyaltyplatform.azienda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

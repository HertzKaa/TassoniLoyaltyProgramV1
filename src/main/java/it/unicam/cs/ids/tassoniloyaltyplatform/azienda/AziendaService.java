package it.unicam.cs.ids.tassoniloyaltyplatform.azienda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
@Service
public class AziendaService {
private final AziendaRepository aziendaRepository;
    @Autowired
    public AziendaService(AziendaRepository aziendaRepository) {
        this.aziendaRepository = aziendaRepository;
    }

    /**
     *
     * @return ritorna una lista presa dal database di Aziende
     */
    @GetMapping
    public List<Azienda> getAziende(){
        return aziendaRepository.findAll();
    }
}

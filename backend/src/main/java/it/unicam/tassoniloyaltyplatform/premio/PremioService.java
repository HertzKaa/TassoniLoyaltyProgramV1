package it.unicam.tassoniloyaltyplatform.premio;

import it.unicam.tassoniloyaltyplatform.dtos.PremioLivelloDTO;
import it.unicam.tassoniloyaltyplatform.eccezioni.RecordAlreadyExistsException;
import it.unicam.tassoniloyaltyplatform.eccezioni.RecordNotFoundException;

import it.unicam.tassoniloyaltyplatform.livello.Livello;
import it.unicam.tassoniloyaltyplatform.livello.LivelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Service
public class PremioService {

    private final PremioRepository premioRepository;
    private final LivelloService livelloService;

    @Autowired
    public PremioService(PremioRepository premioRepository, LivelloService livelloService) {
        this.premioRepository = premioRepository;
        this.livelloService = livelloService;
    }

    @GetMapping
    public List<Premio> getAllPremi() {
        return this.premioRepository.findAll();
    }

    @GetMapping
    public Premio findPremioByID(Long id) throws RecordNotFoundException{
        Optional<Premio> premio = premioRepository.findById(id);
        if(premio.isPresent()) return premio.get();
        else throw new RecordNotFoundException();
    }

    @PostMapping
    public void aggiungiPremioLivello(PremioLivelloDTO dto) throws RecordNotFoundException, RecordAlreadyExistsException {
        Livello livello = this.livelloService.findLivelloByID(dto.getLivelloId());
        for (Premio p: livello.getCatalogoPremi()) {
            if(p.getNome().equals(dto.getNome())) {
                throw new RecordAlreadyExistsException();
            }
        }
        Premio premio = new Premio(livello, dto.getNome(), dto.getDescrizione());
        this.livelloService.aggiungiPremio(livello, premio);
    }
}

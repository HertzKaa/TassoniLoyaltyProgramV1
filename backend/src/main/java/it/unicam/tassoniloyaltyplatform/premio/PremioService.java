package it.unicam.tassoniloyaltyplatform.premio;

import it.unicam.tassoniloyaltyplatform.dto.premioLivelloDTO;
import it.unicam.tassoniloyaltyplatform.exception.ResourceAlreadyExistsException;
import it.unicam.tassoniloyaltyplatform.exception.ResourceNotFoundException;
import it.unicam.tassoniloyaltyplatform.livello.Livello;
import it.unicam.tassoniloyaltyplatform.livello.LivelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

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
    public Premio findPremioById(Long id)throws ResourceNotFoundException {
        Optional<Premio> premio = premioRepository.findById(id);
        if(premio.isPresent()) return  premio.get();
        else  throw new ResourceNotFoundException();
    }
    @GetMapping
    public List<Premio> getAllPremi() {
        return this.premioRepository.findAll();
    }
    @GetMapping
    public void aggiungiPremioLivello(premioLivelloDTO premioDTO)throws ResourceNotFoundException, ResourceAlreadyExistsException {
        Livello livello = this.livelloService.findLivelloByID(premioDTO.getLivelloId());
        for (Premio p: livello.getCatalogoPremi()){
            if(p.getNome().equals(premioDTO.getNome())){
                throw new ResourceAlreadyExistsException();
            }

        }
        Premio premio = new Premio(livello, premioDTO.getNome(), premioDTO.getDescrizione());
        this.livelloService.aggiungiPremio(livello,premio);
    }
}

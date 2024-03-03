package it.unicam.cs.ids.tassoniloyaltyplatform.azienda; //Mike

import it.unicam.cs.ids.tassoniloyaltyplatform.exception.ResourceAlreadyExistsException;
import it.unicam.cs.ids.tassoniloyaltyplatform.exception.ResourceNotFoundException;
import it.unicam.cs.ids.tassoniloyaltyplatform.programmaFedelta.ProgrammaFedelta;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Service
public class AziendaService {

    private final AziendaRepository aziendaRepository;

    @Autowired
    public AziendaService(AziendaRepository aziendaRepository) {
        this.aziendaRepository = aziendaRepository;
    }

    @GetMapping
    public Azienda findAziendaById(Long aziendaId) throws ResourceNotFoundException{
        Optional<Azienda> azienda = aziendaRepository.findById(aziendaId);
        if(azienda.isPresent()) return azienda.get();
        else throw new ResourceNotFoundException();
    }

    @GetMapping
    public List<Azienda> getAziende() {
        return aziendaRepository.findAll();
    }

    @PostMapping
    public void registraAzienda(Azienda newAzienda) throws ResourceAlreadyExistsException {
        Optional<Azienda> aziendaOptional = aziendaRepository
                .findAziendaByIndirizzo(newAzienda.getIndirizzo());
        if(aziendaOptional.isPresent()) {
            throw new ResourceAlreadyExistsException();
        }
        aziendaRepository.save(newAzienda);
    }

    public void aggiungiProgrammaAlCatalogo(Azienda azienda, ProgrammaFedelta programmaFedelta) {
        azienda.getProgrammiFedelta().add(programmaFedelta);
        aziendaRepository.save(azienda);
    }

    @Transactional
    public void modificaAzienda(Long aziendaId, String nome, String indirizzo) throws ResourceAlreadyExistsException{
        Azienda azienda = aziendaRepository.getReferenceById(aziendaId);

        if (indirizzo != null && !indirizzo.isEmpty()) {
            Optional<Azienda> aziendaOptional = aziendaRepository.findAziendaByIndirizzo(indirizzo);
            if(aziendaOptional.isPresent()) {
                throw new ResourceAlreadyExistsException();
            } else azienda.setIndirizzo(indirizzo);
        }

        if (nome != null && !nome.isEmpty()) {
            azienda.setNome(nome);
        }

    }

    @DeleteMapping
    public void cancellaAzienda(Long aziendaId) throws ResourceNotFoundException{
        boolean exists = aziendaRepository.existsById(aziendaId);
        if(!exists) {
            throw new ResourceNotFoundException();
        }
        aziendaRepository.deleteById(aziendaId);
    }

}
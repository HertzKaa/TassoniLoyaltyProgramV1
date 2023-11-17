package it.unicam.cs.ids.tassoniloyaltyplatform.azienda;

import it.unicam.cs.ids.tassoniloyaltyplatform.exception.RecordAlreadyExistsException;
import it.unicam.cs.ids.tassoniloyaltyplatform.exception.RecordNotFoundException;
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

    /**
     *
     * @return ritorna una lista presa dal database di Aziende
     */
    @GetMapping
    public Azienda findAziendaById(Long id) throws RecordNotFoundException{
        Optional<Azienda> azienda = aziendaRepository.findById(id);
        if(azienda.isPresent()) return azienda.get();
        else throw new RecordNotFoundException();
    }

    @GetMapping
    public List<Azienda> getAziende() {
        return aziendaRepository.findAll();
    }

    @PostMapping
    public void registraAzienda(Azienda newAzienda) throws RecordAlreadyExistsException {
        Optional<Azienda> aziendaOptional = aziendaRepository
                .findAziendaBypIva(newAzienda.getpIva());
        if(aziendaOptional.isPresent()) {
            throw new RecordAlreadyExistsException();
        }
        aziendaRepository.save(newAzienda);
    }

    /* public void aggiungiProgrammaAlCatalogo(Azienda azienda, ProgrammaFedelta programmaFedelta) {
        azienda.getProgrammiFedelta().add(programmaFedelta);
        aziendaRepository.save(azienda);
    } */

    @Transactional
    public void modificaAzienda(Long id, String nome, String pIva) throws RecordAlreadyExistsException{
        Azienda azienda = aziendaRepository.getReferenceById(id);

        if (pIva != null && !pIva.isEmpty()) {
            Optional<Azienda> aziendaOptional = aziendaRepository.findAziendaBypIva(pIva);
            if(aziendaOptional.isPresent()) {
                throw new RecordAlreadyExistsException();
            } else azienda.setpIva(pIva);
        }

        if (nome != null && !nome.isEmpty()) {
            azienda.setNome(nome);
        }

    }

    @DeleteMapping
    public void cancellaAzienda(Long id) throws RecordNotFoundException{
        boolean exists = aziendaRepository.existsById(id);
        if(!exists) {
            throw new RecordNotFoundException();
        }
        aziendaRepository.deleteById(id);
    }

}

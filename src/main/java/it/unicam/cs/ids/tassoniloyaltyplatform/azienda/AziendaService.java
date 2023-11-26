package it.unicam.cs.ids.tassoniloyaltyplatform.azienda; //Stas

import it.unicam.cs.ids.tassoniloyaltyplatform.exception.ResourceAlreadyExistsException;
import it.unicam.cs.ids.tassoniloyaltyplatform.exception.ResourceNotFoundException;
//import it.unicam.cs.ids.tassoniloyaltyplatform.programmaFedelta.ProgrammaFedelta;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AziendaService {
    private final AziendaRepository aziendaRepository;

    @Autowired
    public AziendaService(AziendaRepository aziendaRepository) {
        this.aziendaRepository = aziendaRepository;
    }

    /**
     * Restituisce tutte le aziende presenti nel database
     *
     * @return una List<Azienda> contenente tutte le aziende presenti nel database
     */
    public List<Azienda> getAziende() {
        return aziendaRepository.findAll();
    }

    /**
     * Aggiunge una nuova azienda al database
     *
     * @param azienda azienda che si vuole aggiungere nel database
     */
    public Azienda addNewAzienda(Azienda azienda) throws ResourceAlreadyExistsException {
        Optional<Azienda> aziendaOptional = aziendaRepository.findByNomeAndIndirizzo(azienda.getNome(), azienda.getIndirizzo());

        if (aziendaOptional.isPresent()) {
            throw new ResourceAlreadyExistsException();
        }
        return aziendaRepository.save(azienda);
    }

    @Transactional
    public void addProgrammaToAzienda(Long aziendaId, programmaFedelta ProgrammaFedelta) throws ResourceNotFoundException {
        Optional<Azienda> aziendaOptional = aziendaRepository.findById(aziendaId);
        if (aziendaOptional.isEmpty()) {
            throw new ResourceNotFoundException();
        }

        boolean exists = aziendaOptional.get().getProgrammiFedelta().stream()
                .anyMatch(pf -> pf.getNomeProgramma().equals(programmaFedelta.getNomeProgramma()));
        if (exists) {
            throw new ResourceAlreadyExistsException("Un programma fedeltà con il nome" +
                    programmaFedelta.getNomeProgramma() + " appartiene già all'azienda");
        }

        if (!Objects.equals(programmaFedelta.getAzienda().getAziendaId(), aziendaId)) {
            throw new IllegalArgumentException("Il programma fedeltà non appartiene a questa azienda, ma a " +
                    programmaFedelta.getAzienda().toString());
        }

        //questa riga serve, altrimenti transactional non funziona
        Azienda azienda = aziendaOptional.get();
        azienda.getProgrammiFedelta().add(ProgrammaFedelta);
    }

    /**
     * Elimina un'azienda con lo specifico aziendaId dal database
     *
     * @param aziendaId id dell'azienda che si vuole eliminare dal database
     */
    public void deleteAzienda(Long aziendaId) throws ResourceNotFoundException {
        boolean exists = aziendaRepository.existsById(aziendaId);

        if (!exists) {
            throw new ResourceNotFoundException();
        } else {
            aziendaRepository.deleteById(aziendaId);
        }
    }

    public Optional<Azienda> getAziendaById(long aziendaId) {
        return aziendaRepository.findById(aziendaId);
    }

    private Azienda retrieveAzienda(Long aziendaId) throws ResourceNotFoundException {
        Optional<Azienda> aziendaOptional = aziendaRepository.findById(aziendaId);
        if (aziendaOptional.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        return aziendaOptional.get();
    }

    /* public List<ProgrammaFedelta> getProgrammiAzienda(Long aziendaId) {
        Azienda azienda = retrieveAzienda(aziendaId);
        return azienda.getProgrammiFedelta();
    } */

    public void deleteAllAziende(){
        aziendaRepository.deleteAll();
    }
}
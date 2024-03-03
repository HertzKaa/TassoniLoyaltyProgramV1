package it.unicam.tassoniloyaltyplatform.programmaFedelta; //mike

import it.unicam.tassoniloyaltyplatform.azienda.Azienda;
import it.unicam.tassoniloyaltyplatform.azienda.AziendaService;
import it.unicam.tassoniloyaltyplatform.dto.programmaFedeltaDTO;
import it.unicam.tassoniloyaltyplatform.exception.ResourceAlreadyExistsException;
import it.unicam.tassoniloyaltyplatform.exception.ResourceNotFoundException;
//import it.unicam.cs.ids.tassoniloyaltyplatform.iscrizione.Iscrizione;
//import it.unicam.cs.ids.tassoniloyaltyplatform.livello.Livello;
import it.unicam.tassoniloyaltyplatform.livello.Livello;
import it.unicam.tassoniloyaltyplatform.sottoscrizione.Sottoscrizione;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Service
public class ProgrammaFedeltaService {

    private final ProgrammaFactory programmaFactory;
    private final ProgrammaFedeltaRepository programmaRepository;
    private final AziendaService aziendaService;

    @Autowired
    public ProgrammaFedeltaService(ProgrammaFactory programmaFactory,
                                   ProgrammaFedeltaRepository programmaRepository,
                                   AziendaService aziendaService) {
        this.programmaFactory = programmaFactory;
        this.programmaRepository = programmaRepository;
        this.aziendaService = aziendaService;
    }

    @GetMapping
    public List<ProgrammaFedelta> getAllProgrammiFedelta() {
        return programmaRepository.findAll();
    }

    @GetMapping
    public ProgrammaFedelta findProgrammaByID(Long id) throws ResourceNotFoundException {
        Optional<ProgrammaFedelta> programma = programmaRepository.findById(id);
        if(programma.isPresent()) return programma.get();
        else throw new ResourceNotFoundException();
    }

    @GetMapping
    public List<ProgrammaFedelta> findProgrammiByNome(String nome) {
        return this.programmaRepository.findProgrammaFedeltaByNome(nome);
    }


    @PostMapping
    public void registraProgrammaFedelta(@RequestBody programmaFedeltaDTO dto) throws ResourceNotFoundException, ResourceAlreadyExistsException {
        Azienda azienda = aziendaService.findAziendaById(dto.getAziendaId());
        for (ProgrammaFedelta p: azienda.getProgrammiFedelta()) {
            if(p.getNome().equals(dto.getNome()))
                throw new ResourceAlreadyExistsException();
        }
        ProgrammaFedelta nuovoProgramma = programmaFactory.crea(azienda, dto);
        aziendaService.aggiungiProgrammaAlCatalogo(azienda, nuovoProgramma);
        System.out.print(nuovoProgramma);
    }

    @Transactional
    public void modificaProgramma(Long id, String nome, Integer ratioExpEuro) throws ResourceNotFoundException, ResourceAlreadyExistsException{
        ProgrammaFedelta programma =this.findProgrammaByID(id);
        //faccio qui le modifiche generali del pf
        if(nome != null && nome.length() > 0){
            for (ProgrammaFedelta p: programma.getAzienda().getProgrammiFedelta()) {
                if(p.getNome().equals(nome)) throw new ResourceAlreadyExistsException();
            }
            programma.setNome(nome);
        }
        //chiamo metodi specifici per modificare parametri presenti solo in alcuni tipi di programma
        if(programma instanceof ProgrammaLivelli programmaLivelli){
            this.modificaProgrammaLivelli(programmaLivelli, ratioExpEuro);
        }
        //gestisco con altri if programmi di altro tipo
        this.programmaRepository.save(programma);
    }

    @Transactional
    public void modificaProgrammaLivelli(ProgrammaLivelli programma, Integer ratioExpEuro) throws ResourceNotFoundException{
        if(ratioExpEuro != null && ratioExpEuro > 0){
            programma.setRapportoExpEuro(ratioExpEuro);
        }
    }

    @DeleteMapping
    public void cancellaProgrammaFedelta(Long id) throws ResourceNotFoundException{
        ProgrammaFedelta programma = this.findProgrammaByID(id);
        this.programmaRepository.deleteById(id);
    }

    public void rimuoviIscrizione(Sottoscrizione iscrizione) {
        iscrizione.getProgramma().getIscrizioni().remove(iscrizione);
        programmaRepository.save(iscrizione.getProgramma());
    }
     public void aggiungiLivello(ProgrammaLivelli programma, Livello livello) throws ResourceAlreadyExistsException {
        List<Livello> livelliProgramma = programma.getLivelli();
        if(!livelliProgramma.isEmpty()) {
            livelliProgramma.get(livelliProgramma.size() - 1).notUltimo();
        }
        programma.getLivelli().add(livello);
        programmaRepository.save(programma);
    }
}
package it.unicam.cs.ids.tassoniloyaltyplatform.sottoscrizione;

import it.unicam.cs.ids.tassoniloyaltyplatform.carta.Carta;
import it.unicam.cs.ids.tassoniloyaltyplatform.carta.CartaService;
import it.unicam.cs.ids.tassoniloyaltyplatform.cliente.ClienteService;
import it.unicam.cs.ids.tassoniloyaltyplatform.exception.ResourceNotFoundException;
import it.unicam.cs.ids.tassoniloyaltyplatform.premio.Premio;
import it.unicam.cs.ids.tassoniloyaltyplatform.programmaFedelta.ProgrammaFedelta;
import it.unicam.cs.ids.tassoniloyaltyplatform.programmaFedelta.ProgrammaFedeltaService;
import it.unicam.cs.ids.tassoniloyaltyplatform.programmaFedelta.ProgrammaLivelli;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SottoscrizioneService {
    private final SottoscrizioneRepository sottoscrizioneRepository;
    private final ClienteService clienteService;
    private final ProgrammaFedeltaService programmaFedeltaService;
    private final CartaService cartaService;
    @Autowired
    public SottoscrizioneService(SottoscrizioneRepository sottoscrizioneRepository, ClienteService clienteService, ProgrammaFedeltaService programmaFedeltaService, CartaService cartaService) {
        this.sottoscrizioneRepository = sottoscrizioneRepository;
        this.clienteService = clienteService;
        this.programmaFedeltaService = programmaFedeltaService;
        this.cartaService = cartaService;
    }

    public void deleteSottoscrizione(Long sottoscrizioneId) {
    }

    public Sottoscrizione addNewSottoscrizione(Long cartaId, Long programmaId) {

    }
    @GetMapping
    public List<Sottoscrizione> getSottoscrizioni() {
      return sottoscrizioneRepository.findAll();
    }
    public Sottoscrizione getSottoscrizioneByID(Long id) throws ResourceNotFoundException{
        Optional<Sottoscrizione> sottoscrizione = sottoscrizioneRepository.findById(id);
        if(sottoscrizione.isPresent()) return sottoscrizione.get();
        else throw new ResourceNotFoundException();
    }

    @PostMapping
    public void registraIscrizione(Long cartaId, Long programmaId) throws ResourceNotFoundException {
        Carta carta = cartaService.getCartaById(cartaId);
        ProgrammaFedelta programma = programmaFedeltaService.findProgrammaByID(programmaId);
        Sottoscrizione newSottoscrizione = FactorySottoscrizioni.creaSottoscrizione(programma,carta);
        programma.getSottoscrizioni().add(newSottoscrizione);
        carta.getSottoscrizioni().add(newSottoscrizione);
        sottoscrizioneRepository.save(newSottoscrizione);
    }


    @PutMapping
    @ResponseStatus(value = HttpStatus.OK, reason = "Iscrizione aggiornata")
    public void aggiornaIscrizione(Long idAzienda, Long cartaId, double spesa) throws ResourceNotFoundException{

        List<Sottoscrizione> daAggiornare = cartaService.getCartaById(idTessera).getSottoscrizioni().stream()
                .filter(i -> i.getProgramma().getAzienda().getAziendaId().equals(idAzienda))
                .toList();
        daAggiornare.stream().
                forEach(i -> this.aggiungiProgresso(i,spesa));
        sottoscrizioneRepository.saveAll(daAggiornare);
    }
    public void aggiungiProgresso(Sottoscrizione iscrizione, double spesa){
        if(iscrizione instanceof SottoscrizioneLivelli iscrizioneLivelli) {
            aggiungiEsperienzaLivello(iscrizioneLivelli, spesa);
        }
        //if(iscrizione instanceof altroTipoDiIscrizione) doSomething()
    }
    private void aggiungiEsperienzaLivello(SottoscrizioneLivelli iscrizione,  double spesa) {
        if(!iscrizione.getLivelloCorrente().isUltimoLivello()) {
            iscrizione.setProgressoLivello(iscrizione.getProgressoLivello()
                    + spesa * ((ProgrammaLivelli) iscrizione.getProgramma()).getRapportoExpEuro());
            this.controllaLevelUp(iscrizione);
        }
    }
    private void controllaLevelUp(SottoscrizioneLivelli sottoscrizione) {

        if (sottoscrizione.getProgressoLivello() >= sottoscrizione.getLivelloCorrente().getExpLevelUp()) {
            sottoscrizione.setLivelloCorrente(((ProgrammaLivelli) sottoscrizione.getProgramma()).getLivelli()
                    .get(((ProgrammaLivelli) sottoscrizione.getProgramma()).getLivelli().indexOf(sottoscrizione.getLivelloCorrente()) + 1));
            if(!sottoscrizione.getLivelloCorrente().isUltimoLivello()) {
                sottoscrizione.setProgressoLivello(sottoscrizione.getProgressoLivello() - sottoscrizione.getLivelloCorrente().getExpLevelUp());
                this.controllaLevelUp(sottoscrizione);
            } else sottoscrizione.setProgressoLivello(0);
            sottoscrizioneRepository.save(sottoscrizione);
        }
    }

    public List<Premio> visualizzaVantaggiProgrammaLivelli(Long idIscrizione) throws ResourceNotFoundException {
        return ((ProgrammaLivelli) getSottoscrizioneByID(idIscrizione).getProgramma()).getLivelli().stream()
                .flatMap(l->l.getCatalogoPremi().stream())
                .collect(Collectors.toList());
    }
}

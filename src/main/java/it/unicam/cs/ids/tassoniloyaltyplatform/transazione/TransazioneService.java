package it.unicam.cs.ids.tassoniloyaltyplatform.transazione;


import it.unicam.cs.ids.tassoniloyaltyplatform.azienda.AziendaService;
import it.unicam.cs.ids.tassoniloyaltyplatform.carta.CartaService;
import it.unicam.cs.ids.tassoniloyaltyplatform.sottoscrizione.SottoscrizioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.List;

@Service
public class TransazioneService {

    private final TransazioneRepository transazioneRepository;
    private final SottoscrizioneService sottoscrizioneService;
    private final AziendaService aziendaService;
    private final CartaService cartaService;


    @Autowired
    public TransazioneService(
            TransazioneRepository transazioneRepository, SottoscrizioneService sottoscrizioneService, AziendaService aziendaService, CartaService cartaService) {
        this.transazioneRepository = transazioneRepository;
        this.sottoscrizioneService = sottoscrizioneService;

        this.aziendaService = aziendaService;

        this.cartaService = cartaService;
    }

    public List<Transazione> getAccrediti() {
        return transazioneRepository.findAll();
    }


    @PostMapping
    public void aggiungiAccredito(Long aziendaId, Long tesseraId, double spesa) throws RecordNotFoundException {
        Transazione nuovoAccredito =
                new Transazione(this.cartaService.getCartaById(tesseraId),
                        this.aziendaService.findAziendaById(aziendaId),
                        new Date(),spesa);
        this.transazioneRepository.save(nuovoAccredito);
        this.sottoscrizioneService.aggiornaIscrizione(aziendaId, tesseraId, spesa);
    }
}

package it.unicam.cs.ids.tassoniloyaltyplatform.sottoscrizione;

import it.unicam.cs.ids.tassoniloyaltyplatform.cliente.ClienteService;
import it.unicam.cs.ids.tassoniloyaltyplatform.programmaFedelta.ProgrammaFedeltaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SottoscrizioneService {
    private final SottoscrizioneRepository sottoscrizioneRepository;
    private final ClienteService clienteService;
    private final ProgrammaFedeltaService programmaFedeltaService;
    @Autowired
    public SottoscrizioneService(SottoscrizioneRepository sottoscrizioneRepository, ClienteService clienteService, ProgrammaFedeltaService programmaFedeltaService) {
        this.sottoscrizioneRepository = sottoscrizioneRepository;
        this.clienteService = clienteService;
        this.programmaFedeltaService = programmaFedeltaService;
    }

    public void deleteSottoscrizione(Long sottoscrizioneId) {
    }

    public Sottoscrizione addNewSottoscrizione(Long cartaId, Long programmaId) {

    }

    public List<Sottoscrizione> getSottoscrizioni() {
    }
}

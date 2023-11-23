package it.unicam.cs.ids.tassoniloyaltyplatform.sottoscrizione;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SottoscrizioneService {
    private final SottoscrizioneRepository sottoscrizioneRepository;
    @Autowired
    public SottoscrizioneService(SottoscrizioneRepository sottoscrizioneRepository) {
        this.sottoscrizioneRepository = sottoscrizioneRepository;
    }
}

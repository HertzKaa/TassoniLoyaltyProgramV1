package it.unicam.cs.ids.tassoniloyaltyplatform.sottoscrizione;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/sottoscrizioni")
public class SottoscrizioneController {
private SottoscrizioneService sottoscrizioneService;

    public SottoscrizioneController(SottoscrizioneService sottoscrizioneService) {
        this.sottoscrizioneService = sottoscrizioneService;
    }
}

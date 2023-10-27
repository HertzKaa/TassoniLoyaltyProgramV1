package it.unicam.cs.ids.tassoniloyaltyplatform.azienda;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.swing.text.html.Option;
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
    public List<Azienda> getAziende(){
        return aziendaRepository.findAll();
    }

    public void aggiungiAzienda(Azienda azienda) {
        Optional<Azienda> aziendaOptional= aziendaRepository
                .findAziendaByNomeAzienda(azienda.getNome());
        if(aziendaOptional.isPresent()){
               throw new IllegalStateException("azienda presente");
        }
        aziendaRepository.save(azienda);
    }

    public void rimuoviAzienda(Long aziendaId) {
        boolean exist= aziendaRepository.existsById(aziendaId);
         if(!exist) {
            throw new IllegalStateException(
                    "azienda con id"+ aziendaId + "non esiste");

        }
        aziendaRepository.deleteById(aziendaId);
    }

    @Transactional
    public void updateAzienda(
            Long aziendaId, String nomeAzienda, String indirizzoAzienda, Long contattoAzienda) {

       //TO DO
    }
}

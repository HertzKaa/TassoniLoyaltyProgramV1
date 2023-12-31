package it.unicam.cs.ids.tassoniloyaltyplatform.azienda; //Stas

import it.unicam.cs.ids.tassoniloyaltyplatform.azienda.Azienda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AziendaRepository extends JpaRepository<Azienda, Long> {

    /**
     * Metodo equivalente a una query che cerca un'azienda che abbia un particolare nome e indirizzo
     *
     * @param nome      nome dell'azienda che si desidera cercare
     * @param indirizzo indirizzo dell'azienda che si desidera cercare
     * @return una Optional<Azienda> corrispondente all'azienda con il desiderato nome e indirizzo
     */
    Optional<Azienda> findAziendaByNome(String nome);
    Optional<Azienda> findAziendaByIndirizzo(String indirizzo);
}
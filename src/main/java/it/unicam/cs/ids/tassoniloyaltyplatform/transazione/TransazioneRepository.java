package it.unicam.cs.ids.tassoniloyaltyplatform.transazione;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransazioneRepository extends JpaRepository<Transazione,Long> {

}

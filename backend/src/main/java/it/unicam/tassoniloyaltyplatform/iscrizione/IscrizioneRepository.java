package it.unicam.tassoniloyaltyplatform.iscrizione;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IscrizioneRepository extends JpaRepository<Iscrizione, Long> {
}

package it.unicam.cs.ids.tassoniloyaltyplatform.azienda;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AziendaRepository
        extends JpaRepository<Azienda,Long> {


}

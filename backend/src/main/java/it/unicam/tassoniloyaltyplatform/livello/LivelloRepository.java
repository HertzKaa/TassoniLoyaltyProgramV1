package it.unicam.tassoniloyaltyplatform.livello;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivelloRepository extends JpaRepository<Livello, Long> {
}
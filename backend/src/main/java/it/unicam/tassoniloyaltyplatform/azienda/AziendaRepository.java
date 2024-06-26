package it.unicam.tassoniloyaltyplatform.azienda; //Stas

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AziendaRepository extends JpaRepository<Azienda,Long> {

    Optional<Azienda> findAziendaByNome(String nome);

    Optional<Azienda> findAziendaByEmail(String email);
}
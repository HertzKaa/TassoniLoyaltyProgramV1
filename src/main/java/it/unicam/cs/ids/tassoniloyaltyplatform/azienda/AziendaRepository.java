package it.unicam.cs.ids.tassoniloyaltyplatform.azienda;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AziendaRepository
        extends JpaRepository<Azienda,String> {
 //    @Query("SELECT s FROM Azienda s WHERE s.nome=?1")
 //    giusto come esempio, da vedere
     Optional<Azienda> findAziendaByNomeAzienda(String nome);
}

package it.unicam.cs.ids.tassoniloyaltyplatform.carta;

import it.unicam.cs.ids.tassoniloyaltyplatform.azienda.Azienda;
import it.unicam.cs.ids.tassoniloyaltyplatform.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartaRepository extends JpaRepository<Carta,Long>{


    @Override
    Optional<Carta> findCartaByCliente(Cliente cliente);


}

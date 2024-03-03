package it.unicam.tassoniloyaltyplatform.tessera;


import it.unicam.tassoniloyaltyplatform.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TesseraRepository extends JpaRepository<Tessera,Long> {

    Optional<Tessera> findByTitolareTessera(Cliente cliente);
}
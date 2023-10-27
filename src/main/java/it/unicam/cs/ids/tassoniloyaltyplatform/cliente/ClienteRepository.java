package it.unicam.cs.ids.tassoniloyaltyplatform.cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long>{


    Optional<Cliente> findClienteByNomeAndIndirizzo(String nome,String indirizzo);
}

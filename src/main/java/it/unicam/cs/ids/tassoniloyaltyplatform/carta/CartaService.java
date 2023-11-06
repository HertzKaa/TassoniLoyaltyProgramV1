package it.unicam.cs.ids.tassoniloyaltyplatform.carta;

import it.unicam.cs.ids.tassoniloyaltyplatform.cliente.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Service
public class CartaService {
    private final CartaRepository cartaRepository;
    @Autowired
    public CartaService(CartaRepository cartaRepository) {
        this.cartaRepository = cartaRepository;
    }
    @GetMapping
    public Optional<Carta> getCartaByCliente(Cliente cliente) {
        return cartaRepository.findCartaByCliente(cliente);
    }
}

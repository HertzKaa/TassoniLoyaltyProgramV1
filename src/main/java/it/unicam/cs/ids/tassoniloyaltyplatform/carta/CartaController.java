package it.unicam.cs.ids.tassoniloyaltyplatform.carta;

import it.unicam.cs.ids.tassoniloyaltyplatform.cliente.Cliente;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(path = "api/carta")
public class CartaController {
    private final CartaService cartaService;

    public CartaController(CartaService cartaService) {
        this.cartaService = cartaService;
    }
    @GetMapping
    public Optional<Carta> getCartaByCliente(Cliente cliente) {
        return cartaService.getCartaByCliente(cliente);
    }
}

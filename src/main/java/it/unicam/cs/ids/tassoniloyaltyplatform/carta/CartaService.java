package it.unicam.cs.ids.tassoniloyaltyplatform.carta;

import org.springframework.stereotype.Service;

@Service
public class CartaService {
    private final CartaRepository cartaRepository;

    public CartaService(CartaRepository cartaRepository) {
        this.cartaRepository = cartaRepository;
    }
}

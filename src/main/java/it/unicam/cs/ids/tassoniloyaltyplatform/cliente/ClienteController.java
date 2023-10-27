package it.unicam.cs.ids.tassoniloyaltyplatform.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(path = "api/cliente")
public class ClienteController {
    private final ClienteService clienteService;
    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }
    @GetMapping
    public Optional<Cliente> getClienteByNomeAndIndirizzo(String nome, String indirizzo){
        return clienteService.getClienteByNomeAndIndirizzo(nome,indirizzo);
    }
}

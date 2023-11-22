package it.unicam.cs.ids.tassoniloyaltyplatform.cliente;

import it.unicam.cs.ids.tassoniloyaltyplatform.carta.Carta;
import it.unicam.cs.ids.tassoniloyaltyplatform.exception.ResourceAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    @GetMapping
    public List<Cliente> getClienti() {
        return clienteService.getClienti();
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> registraNuovoCliente(@RequestBody Cliente cliente) {
        try {
            Cliente newCliente = clienteService.addNewCliente(cliente);
            return new ResponseEntity<>(newCliente, HttpStatus.CREATED);
        } catch (ResourceAlreadyExistsException e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping(path = "{clienteId}")
    public void updateClienteEmail(@PathVariable("clienteId") Long clienteId, String email) {
        clienteService.updateClienteEmail(clienteId, email);
    }

    @DeleteMapping(path = "{clienteId}")
    public void deleteCliente(@PathVariable("clienteId") Long clienteId) {
        clienteService.eliminaCliente(clienteId);
    }
}

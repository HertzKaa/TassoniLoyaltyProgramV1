package it.unicam.cs.ids.tassoniloyaltyplatform.cliente;

import it.unicam.cs.ids.tassoniloyaltyplatform.carta.Carta;
import it.unicam.cs.ids.tassoniloyaltyplatform.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;
    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }
    @GetMapping
    public Optional<Cliente> getClienteByNomeAndIndirizzo(String nome, String indirizzo) {
        if(nome!=null||indirizzo!=null){
            return clienteRepository.findClienteByNomeAndIndirizzo(nome,indirizzo);
        }
        return null;
    }

    public List<Cliente> getClienti() {
        return clienteRepository.findAll();
    }
    public Cliente addNewCliente(Cliente cliente) throws IllegalStateException {
        Optional<Cliente> clientetByEmailOptional = clienteRepository.findClienteByEmail(cliente.getEmail());
        if (clientetByEmailOptional.isPresent()) {
            throw new IllegalStateException("Email gia' in uso");
        }

        Optional<Cliente> clienteByNumCellulareOptional = clienteRepository.findClienteByNumCellulare(cliente.getNumCellulare());
        if (clienteByNumCellulareOptional.isPresent()) {
            throw new IllegalStateException("Numero di cellulare gia' in uso");
        }

        if (cliente.getCarta() == null) {
            cliente.setCarta(new Carta(cliente));
        }
        return clienteRepository.save(cliente);
    }

    public void updateClienteEmail(Long clienteId, String email) {
    }

    public void eliminaCliente(Long clienteId) {
        boolean exists = clienteRepository.existsById(clienteId);

        if (!exists) {
            throw new ResourceNotFoundException("cliente con id " + clienteId + " non esiste!");
        }
        clienteRepository.deleteById(clienteId);
    }
}

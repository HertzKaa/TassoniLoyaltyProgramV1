package it.unicam.cs.ids.tassoniloyaltyplatform.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;
    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Optional<Cliente> getClienteByNomeAndIndirizzo(String nome, String indirizzo) {
        if(nome!=null||indirizzo!=null){
            return clienteRepository.findClienteByNomeAndIndirizzo(nome,indirizzo);
        }
        return null;
    }
}

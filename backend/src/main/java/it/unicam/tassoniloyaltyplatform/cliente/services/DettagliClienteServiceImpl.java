package it.unicam.tassoniloyaltyplatform.cliente.services; //Interfaccia che funziona con spring security

import it.unicam.tassoniloyaltyplatform.eccezioni.RecordNotFoundException;
import it.unicam.tassoniloyaltyplatform.cliente.Cliente;
import it.unicam.tassoniloyaltyplatform.cliente.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class DettagliClienteServiceImpl implements UserDetailsService {
    @Autowired
    ClienteRepository clienteRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Cliente cliente = clienteRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return DettagliClienteImpl.build(cliente);
    }

    public Cliente findClienteById(long id) throws RecordNotFoundException {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isPresent()) {
            return cliente.get();
        } else throw new RecordNotFoundException();
    }


}

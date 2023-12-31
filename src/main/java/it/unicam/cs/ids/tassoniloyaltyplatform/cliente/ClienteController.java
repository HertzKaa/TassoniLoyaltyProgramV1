package it.unicam.cs.ids.tassoniloyaltyplatform.cliente; //Stas

import it.unicam.cs.ids.tassoniloyaltyplatform.exception.ResourceAlreadyExistsException;
import it.unicam.cs.ids.tassoniloyaltyplatform.exception.ResourceNotFoundException;
import it.unicam.cs.ids.tassoniloyaltyplatform.sottoscrizione.Sottoscrizione;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Classe che esegue le chiamate CRUD dell'entità cliente
 */
@RestController
@RequestMapping(path = "/api/clienti")
public class ClienteController {
    public final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    /**
     * Esegue una chiamata GET che restituisce tutti i clienti presenti nel database
     *
     * @return una List<Cliente> che contiene tutti i clienti nel database
     */
    @GetMapping
    public List<Cliente> getClienti() {
        return clienteService.getClienti();
    }

    @GetMapping(path = "/sottoscrizioni/{clienteId}")
    public List<Sottoscrizione> getSottoscrizioniCliente(@PathVariable("clienteId") Long clienteId) throws ResourceNotFoundException {
        return clienteService.getSottoscrizioniCliente(clienteId);
    }

    /**
     * Esegue una chiamata POST che salva un nuovo cliente nel database
     *
     * @param cliente il cliente che verrà inserito nel database
     *                <p>
     *                Esempio di body per la richiesta:
     *                {
     *                "nome" : "Pinco",
     *                "cognome" : "Pallino",
     *                "numCellulare" : "12345678910",
     *                "email" : "pinco.pallino@gmail.com",
     *                "indirizzo" : "Via Pallini 42",
     *                "dataDiNascita" : "2000-01-01",
     *                "codiceFiscale" : "PLLPPT0045V42F"
     *                }
     */
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> registraNuovoCliente(@RequestBody Cliente cliente) {
        Cliente newCliente = clienteService.addNewCliente(cliente);
        return new ResponseEntity<>(newCliente, HttpStatus.CREATED);
    }

    /**
     * Esegue una chiamata DELETE che elimina un cliente con lo specifico id dal database
     *
     * @param clienteId l'id del cliente che si desidera eliminare
     */
    @DeleteMapping(path = "{clienteId}")
    public void deleteCliente(@PathVariable("clienteId") Long clienteId) throws ResourceNotFoundException {
        clienteService.deleteCliente(clienteId);
    }

    /**
     * Esegue una chiamata PUT che modifica l'email un cliente con lo specifico id
     *
     * @param clienteId l'id del cliente che deve essere modificato
     * @param email     la nuova email da inserire
     */
    @PutMapping(path = "{clienteId}")
    public void updateClienteEmail(@PathVariable("clienteId") Long clienteId, String email) throws ResourceNotFoundException {
        clienteService.updateClienteEmail(clienteId, email);
    }
}
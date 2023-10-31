package it.unicam.cs.ids.tassoniloyaltyplatform.sottoscrizione;

import it.unicam.cs.ids.tassoniloyaltyplatform.azienda.Azienda;
import it.unicam.cs.ids.tassoniloyaltyplatform.cliente.Cliente;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Sottoscrizione {

    @Id
    @GeneratedValue
    private Long id;
    //private Cliente cliente;
    //private Azienda azienda;

    /**   Costruttore senza Id
     * @param cliente cliente che attua la sottoscrizione
     * @param azienda azienda su cui il cliente è sottoscritto
     */
    public Sottoscrizione(Cliente cliente, Azienda azienda) {
        this.cliente = cliente;
        this.azienda = azienda;
    }

    /**  Costruttore con Id
     * @param cliente cliente che attua la sottoscrizione
     * @param azienda azienda su cui il cliente è sottoscritto
     * @param id id della sottoscrizione
     */
    public Sottoscrizione(Cliente cliente, Azienda azienda, Long id) {
        this.cliente = cliente;
        this.azienda = azienda;
        this.id=id;
    }

    public Sottoscrizione() {

    }

    public Cliente getCliente() {
        return cliente;
    }

}

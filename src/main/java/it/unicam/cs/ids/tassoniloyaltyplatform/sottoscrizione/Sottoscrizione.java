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

    public Sottoscrizione(Cliente cliente, Azienda azienda) {
        this.cliente = cliente;
        this.azienda = azienda;
    }

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

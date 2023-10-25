package it.unicam.cs.ids.tassoniloyaltyplatform.sottoscrizione;

import it.unicam.cs.ids.tassoniloyaltyplatform.cliente.Cliente;

public class Sottoscrizione {

    private Cliente cliente;

    public Sottoscrizione(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

}

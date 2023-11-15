package it.unicam.cs.ids.tassoniloyaltyplatform.sottoscrizione;

import com.fasterxml.jackson.annotation.JsonBackReference;
import it.unicam.cs.ids.tassoniloyaltyplatform.azienda.Azienda;
import it.unicam.cs.ids.tassoniloyaltyplatform.cliente.Cliente;
import jakarta.persistence.*;

@Entity(name = "Sottoscrizione")
@Table(name = "Sottoscrizione")
public class Sottoscrizione {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "cliente_id", referencedColumnName = "cliente_id")
    private Cliente cliente;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "azienda_id", referencedColumnName = "azienda_id")
    private Azienda azienda;

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

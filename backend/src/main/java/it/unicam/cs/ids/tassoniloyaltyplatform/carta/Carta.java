package it.unicam.cs.ids.tassoniloyaltyplatform.carta; //Stas

import com.fasterxml.jackson.annotation.JsonBackReference;
import it.unicam.cs.ids.tassoniloyaltyplatform.cliente.Cliente;
import it.unicam.cs.ids.tassoniloyaltyplatform.sottoscrizione.Sottoscrizione;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@Entity(name = "Carta")
@Table(name = "carta")
public class Carta {
    @Id
    @Column(name = "carta_id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cartaId;
    @OneToOne
    @JsonBackReference
    @JoinColumn(name = "cliente_id", referencedColumnName = "cliente_id")
    private Cliente clienteId;
    @OneToMany(mappedBy = "carta", cascade = CascadeType.ALL)
    private final List<Sottoscrizione> sottoscrizioni;
    public Carta(Long cartaId, Cliente cliente) {
        this.cartaId = cartaId;
        this.clienteId = clienteId;
        this.sottoscrizioni= new ArrayList<>();
    }

    public Carta(Cliente cliente) {

        this.clienteId = clienteId;
        this.sottoscrizioni= new ArrayList<>();
    }

    public Carta() {
        this.sottoscrizioni= new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carta carta = (Carta) o;
        return Objects.equals(getCartaId(), carta.getCartaId()) && Objects.equals(getClienteId(), carta.getClienteId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCartaId(), getClienteId());
    }
}
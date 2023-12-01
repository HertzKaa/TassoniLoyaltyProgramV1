package it.unicam.cs.ids.tassoniloyaltyplatform.carta; //Stas

import com.fasterxml.jackson.annotation.JsonBackReference;
import it.unicam.cs.ids.tassoniloyaltyplatform.cliente.Cliente;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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

    public Carta(Long cartaId, Cliente cliente) {
        this.cartaId = cartaId;
        this.clienteId = clienteId;
    }

    public Carta(Cliente cliente) {
        this.clienteId = clienteId;
    }

    public Carta() {
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
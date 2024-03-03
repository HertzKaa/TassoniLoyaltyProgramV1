package it.unicam.tassoniloyaltyplatform.tessera;

import it.unicam.tassoniloyaltyplatform.iscrizione.Iscrizione;
import jakarta.persistence.*;
import lombok.*;
import it.unicam.tassoniloyaltyplatform.cliente.Cliente;
import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@Entity @Table(name = "tessera")
public class Tessera {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "id_tessera",
            updatable = false
    )
    private Long tesseraId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "cliente_id",
            referencedColumnName = "cliente_id"
    )
    private Cliente titolareTessera;

    @OneToMany(mappedBy = "tessera", cascade = CascadeType.ALL)
    private final List<Iscrizione> iscrizioni;

    public Tessera() {
        this.iscrizioni = new ArrayList<>();
    }

    public Tessera(Cliente cliente){
        this.titolareTessera = cliente;
        this.iscrizioni = new ArrayList<>();
    }

}
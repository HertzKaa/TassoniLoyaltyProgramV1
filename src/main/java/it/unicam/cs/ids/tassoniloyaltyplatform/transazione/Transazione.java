package it.unicam.cs.ids.tassoniloyaltyplatform.transazione; //Stas

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.unicam.cs.ids.tassoniloyaltyplatform.azienda.Azienda;
import it.unicam.cs.ids.tassoniloyaltyplatform.carta.Carta;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor(force = true)
@Entity(name = "Accredito")
@Table(name = "accredito")
public class Transazione {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(
            name = "id_accredito",
            updatable = false
    )
    private Long idAccredito;

    @Column(
            name = "data",
            nullable = false

    )
    private final Date data;

    @ManyToOne @JsonIgnore
    @JoinColumn(
            name = "id_tessera",
            referencedColumnName = "id_tessera",
            nullable = false,
            updatable = false)
    private final Carta tessera;

    @ManyToOne @JsonIgnore
    @JoinColumn(
            name = "id_azienda",
            referencedColumnName = "id_azienda",
            nullable = false,
            updatable = false
    )
    private final Azienda azienda;

    private final double spesaAcquisto;

    public Transazione( Carta tessera, Azienda azienda, Date data, double spesaAcquisto) {
        this.tessera=tessera;
        this.azienda=azienda;
        this.data=data;
        this.spesaAcquisto=spesaAcquisto;
    }
}

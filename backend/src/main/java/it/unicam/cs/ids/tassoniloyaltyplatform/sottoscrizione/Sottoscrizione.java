package it.unicam.cs.ids.tassoniloyaltyplatform.sottoscrizione;

import com.fasterxml.jackson.annotation.JsonBackReference;
import it.unicam.cs.ids.tassoniloyaltyplatform.azienda.Azienda;
import it.unicam.cs.ids.tassoniloyaltyplatform.carta.Carta;
import it.unicam.cs.ids.tassoniloyaltyplatform.cliente.Cliente;
import it.unicam.cs.ids.tassoniloyaltyplatform.programmaFedelta.ProgrammaFedelta;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@ToString
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_tracker", discriminatorType = DiscriminatorType.STRING)
@Entity(name = "Sottoscrizione")
@Table(name = "sottoscrizione")
public class Sottoscrizione {

    @Id
    @GeneratedValue
    @Column(name = "sottoscrizione_id",
            nullable = false,
            updatable = false)
    private Long id;

    @ManyToOne()
    @JsonBackReference
    @JoinColumn(name = "carta_id", referencedColumnName = "carta_id")
    private Carta carta;

    @ManyToOne()
    @JsonBackReference
    @JoinColumn(name = "azienda_id", referencedColumnName = "azienda_id")
    private Azienda azienda;

    @ManyToOne()
    @JoinColumn(name = "programma_id", referencedColumnName = "programma_id")
    private ProgrammaFedelta programma;



    /**   Costruttore senza Id
     * @param carta cliente che attua la sottoscrizione
     * @param programmaFedelta  programma su cui il cliente è sottoscritto
     */
    public Sottoscrizione(Carta carta, ProgrammaFedelta programmaFedelta) {
        this.carta = carta;
        this.programma=programmaFedelta;
    }

    /**  Costruttore con Id
     * @param carta cliente che attua la sottoscrizione
     * @param programmaFedelta programma su cui il cliente è sottoscritto
     * @param id id della sottoscrizione
     */
    public Sottoscrizione(Carta carta, ProgrammaFedelta programmaFedelta, Long id) {
        this.carta=carta;
        this.programma=programmaFedelta;
        this.id=id;
    }

    /**
     * Costruttore di default del generico tracker di programmi fedeltà
     */
    public Sottoscrizione() {
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Sottoscrizione that = (Sottoscrizione) o;
        return id != null && Objects.equals(id, that.id);
    }
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

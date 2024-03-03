package it.unicam.tassoniloyaltyplatform.iscrizione;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.unicam.tassoniloyaltyplatform.programmaFedelta.ProgrammaFedelta;
import it.unicam.tassoniloyaltyplatform.tessera.Tessera;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity(name = "Iscrizione")
@Table(name = "iscrizione")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_iscrizione")
public class Iscrizione {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(
            name = "id_iscrizione",
            updatable = false
    )
    private Long iscrizioneId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(
            name = "id_tessera",
            nullable = false,
            updatable = false
    )
    private Tessera tessera;


    @ManyToOne
    @JoinColumn(
            name = "id_programma",
            nullable = false,
            updatable = false
    )
    private ProgrammaFedelta programma;

    /**
     * Costruttore di default
     */
    public Iscrizione() {
    }

    public Iscrizione(ProgrammaFedelta programma, Tessera tessera) {
        this.tessera = tessera;
        this.programma = programma;
    }
}
package it.unicam.cs.ids.tassoniloyaltyplatform.programmaFedelta; //Mike

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.unicam.cs.ids.tassoniloyaltyplatform.azienda.Azienda;
//import it.unicam.cs.ids.tassoniloyaltyplatform.iscrizione.Iscrizione;
import it.unicam.cs.ids.tassoniloyaltyplatform.sottoscrizione.Sottoscrizione;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_programma")
@Entity(name = "ProgrammaFedelta")
@Table(name = "programma_fedelta")
public abstract class ProgrammaFedelta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "id_programma"
    )
    private Long programmaId;

    @ManyToOne
    @JoinColumn(
            name = "id_azienda",
            referencedColumnName = "id_azienda",
            updatable = false
    )
    private Azienda azienda;

    @JsonIgnore
    @OneToMany(mappedBy = "programma", cascade = CascadeType.ALL)
    private List<Sottoscrizione> sottoscrizioni;

    @Column(
            name = "nome",
            nullable = false,
            columnDefinition = "VARCHAR(40)"
    )
    private String nome;


    /**
     * Costruttore di default
     */
    public ProgrammaFedelta() {
        sottoscrizioni = new ArrayList<>();
    }

    /**
     * Costruttore senza id, il quale verrà generato dal DB
     *
     * @param azienda
     * @param nome
     */
    public ProgrammaFedelta(Azienda azienda, String nome) {

        sottoscrizioni = new ArrayList<>();
        this.azienda = azienda;
        this.nome = nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
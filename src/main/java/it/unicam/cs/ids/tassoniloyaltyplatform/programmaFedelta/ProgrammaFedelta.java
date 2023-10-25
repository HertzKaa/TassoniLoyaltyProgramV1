package it.unicam.cs.ids.tassoniloyaltyplatform.programmaFedelta;

import it.unicam.cs.ids.tassoniloyaltyplatform.azienda.Azienda;
import it.unicam.cs.ids.tassoniloyaltyplatform.cliente.Cliente;
import it.unicam.cs.ids.tassoniloyaltyplatform.sottoscrizione.Sottoscrizione;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ToString
@Table(name = "programma_fedelta")
@Entity(name = "programma_fedelta")
public class ProgrammaFedelta {
    @Id
    @Column(name="programma_fedelta_id", nullable=false, updatable= false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long programmaFedeltaId;

    private int tipo;//Non so se serve

    @Column(name= "nome", nullable = false, columnDefinition = "TEXT")
    private String nomeProgramma;

    //private Azienda azienda;

    @Column(name= "numero_clienti", nullable = false, columnDefinition = "NUM")
    private int numClienti;

    //private List<Sottoscrizione> sottoscrizioni;

    public ProgrammaFedelta(Long programmaFedeltaId, int tipo, String nomeProgramma, Azienda azienda, int numClienti, List<Sottoscrizione> sottoscrizioni) {
        this.programmaFedeltaId = programmaFedeltaId;
        this.tipo = tipo;
        this.nomeProgramma = nomeProgramma;
        //this.azienda = azienda;
        this.numClienti = numClienti;
        sottoscrizioni= new ArrayList<>();
    }

    public ProgrammaFedelta(int tipo, String nomeProgramma, Azienda azienda, int numClienti) {
        this.tipo = tipo;
        this.nomeProgramma = nomeProgramma;
        //this.azienda = azienda;
        this.numClienti = numClienti;
        //sottoscrizioni= new ArrayList<>();
    }

    public ProgrammaFedelta() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProgrammaFedelta that = (ProgrammaFedelta) o;

        // Verifica che i programmi abbiano lo stesso AziendaId
        return programmaFedeltaId != null ? programmaFedeltaId.equals(that.programmaFedeltaId) : that.programmaFedeltaId == null;
    }
    @Override
    public int hashCode() {
        return Objects.hash(programmaFedeltaId);

    }
}

package it.unicam.cs.ids.tassoniloyaltyplatform.programmaFedelta; //Mike

import it.unicam.cs.ids.tassoniloyaltyplatform.azienda.Azienda;
import it.unicam.cs.ids.tassoniloyaltyplatform.livello.Livello;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@DiscriminatorValue("livelli")
public class ProgrammaLivelli extends ProgrammaFedelta {

    @Column(
            name = "rapporto_Exp/Euro",
            nullable = false
    )
    private Integer rapportoExpEuro;


    @OneToMany(mappedBy = "programma", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Livello> livelli;


    public ProgrammaLivelli() {
        this.livelli = new ArrayList<>();
    }

    public ProgrammaLivelli(Azienda azienda, String nome) {
        super(azienda, nome);
        this.rapportoExpEuro = 100; //valore di default
        this.livelli = new ArrayList<>();
    }


    public void setRapportoExpEuro(Integer rapportoExpEuro) {
        this.rapportoExpEuro = rapportoExpEuro;
    }
}
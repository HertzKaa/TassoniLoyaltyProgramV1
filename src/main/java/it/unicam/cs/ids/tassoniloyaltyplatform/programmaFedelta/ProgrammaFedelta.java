package it.unicam.cs.ids.tassoniloyaltyplatform.programmaFedelta;

import it.unicam.cs.ids.tassoniloyaltyplatform.azienda.Azienda;

import java.util.Objects;

public class ProgrammaFedelta {
    private Long programmaFedeltaId;
    private int tipo;//Non so se serve
    private String nomeProgramma;
    private Azienda azienda;

    public ProgrammaFedelta(Long programmaFedeltaId, int tipo, String nomeProgramma, Azienda azienda) {
        this.programmaFedeltaId = programmaFedeltaId;
        this.tipo = tipo;
        this.nomeProgramma = nomeProgramma;
        this.azienda = azienda;
    }

    public ProgrammaFedelta(int tipo, String nomeProgramma, Azienda azienda) {
        this.tipo = tipo;
        this.nomeProgramma = nomeProgramma;
        this.azienda = azienda;
    }

    public Long getProgrammaFedeltaId() {
        return programmaFedeltaId;
    }

    public int getTipo() {
        return tipo;
    }

    public String getNomeProgramma() {
        return nomeProgramma;
    }

    public Azienda getAzienda() {
        return azienda;
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

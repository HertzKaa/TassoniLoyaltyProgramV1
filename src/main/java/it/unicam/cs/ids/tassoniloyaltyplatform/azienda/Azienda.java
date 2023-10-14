
    }

    /**
     * Costruttore senza id, perchè esso viene generato automaticamente.
     *
     * @param nome      nome dell'azienda
     * @param indirizzo indirizzo dell'azienda
     * @param pIva      partita iva dell'azienda
     */
    public Azienda(String nome, String indirizzo, String pIva) {
        this.nome = nome;
        this.indirizzo = indirizzo;
        this.pIva = pIva;
        // this.programmiFedelta = new ArrayList<>();
        // this.transazioni = new ArrayList<>();
    }

    public Long getAziendaId() {
        return aziendaId;
    }

    public void setAziendaId(Long aziendaId) {
        this.aziendaId = aziendaId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getpIva() {
        return pIva;
    }

    public void setpIva(String pIva) {
        this.pIva = pIva;
    }

    /* public List<ProgrammaFedelta> getProgrammiFedelta() {
        return programmiFedelta;
    }

    public void setProgrammiFedelta(List<ProgrammaFedelta> programmiFedelta) {
        this.programmiFedelta = programmiFedelta;
    }

    public List<Transazione> getTransazioni() {
        return transazioni;
    }

    public void setTransazioni(List<Transazione> transazioni) {
        this.transazioni = transazioni;
    }

     */
}
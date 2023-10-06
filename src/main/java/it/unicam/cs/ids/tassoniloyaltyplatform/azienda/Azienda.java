package it.unicam.cs.ids.tassoniloyaltyplatform.azienda;

public class Azienda {
    //test
    private String indirizzoAzienda;
    private Long aziendaId;
    private String nomeAzienda;
    private Long contattoAzienda;

    //per fare i programma fedelta dell'azienda e le transizioni si possono usare i
    /** costruttore con ID
     * @param indirizzoAzienda
     * @param aziendaId
     * @param nomeAzienda
     * @param contattoAzienda
     */
    public Azienda(String indirizzoAzienda, Long aziendaId, String nomeAzienda, Long contattoAzienda) {
        this.indirizzoAzienda = indirizzoAzienda;
        this.aziendaId = aziendaId;
        this.nomeAzienda = nomeAzienda;
        this.contattoAzienda = contattoAzienda;
    }

    /** costruttore senza ID perchè già fornito da springboot
     * @param indirizzoAzienda
     * @param nomeAzienda
     * @param contattoAzienda
     */
    public Azienda(String indirizzoAzienda, String nomeAzienda, Long contattoAzienda) {
        this.indirizzoAzienda = indirizzoAzienda;
        this.nomeAzienda = nomeAzienda;
        this.contattoAzienda = contattoAzienda;
    }

    public String getIndirizzoAzienda() {
        return indirizzoAzienda;
    }

    public void setIndirizzoAzienda(String indirizzoAzienda) {
        this.indirizzoAzienda = indirizzoAzienda;
    }

    public Long getAziendaId() {
        return aziendaId;
    }

    public void setAziendaId(Long aziendaId) {
        this.aziendaId = aziendaId;
    }

    public String getNomeAzienda() {
        return nomeAzienda;
    }

    public void setNomeAzienda(String nomeAzienda) {
        this.nomeAzienda = nomeAzienda;
    }

    public Long getContattoAzienda() {
        return contattoAzienda;
    }

    public void setContattoAzienda(Long contattoAzienda) {
        this.contattoAzienda = contattoAzienda;
    }
}

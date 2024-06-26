import React, { Component } from "react";
import axios from "axios";
import "./home.css"
import authService from "../services/auth.service";
import authHeader from "../services/auth-header";

// homepage di un'applicazione in cui gli utenti possono visualizzare e iscriversi a programmi fedeltà.
class Home extends Component {
    constructor(props) {
        super(props);

        this.state = {
            programmiFedelta: [], //Array vuoto
            isIscritto: false // boolean
        };
    }

    // Viene effettuata una richiesta GET al backend per ottenere i programmi fedeltà disponibili
    componentDidMount() {
        axios.get('/api/programmaFedelta')
            .then(response => {
                this.setState({ programmiFedelta: response.data });
            })
            .catch(error => {
                console.error('Errore nel recupero dei programmi fedeltà:', error);
            });
    }

    render() {
        const { programmiFedelta } = this.state;

        return (
            <div className="container">
                <h2 className="text-center mb-2 pb-2">Programmi Fedeltà</h2>
                <hr style={{border: "1px solid #000"}} />
                <div className="programmi-container">
                    {programmiFedelta.map(programma => (
                        <div key={programma.programmaId} className="programma">
                            <div className="programma-nome">
                                {programma.nome}
                            </div>
                            <div className="programma-iscrizione">
                                <button
                                    className="button-iscrizione"
                                    onClick={() => this.handleIscriviti(programma.programmaId)}
                                    disabled={programma.isIscritto}
                                >
                                    {programma.isIscritto ? 'Iscritto' : 'Iscriviti'}
                                </button>
                            </div>
                        </div>
                    ))}
                </div>
            </div>
        );
    }

    handleIscriviti(programmaId) {
        const currentUser = authService.getCurrentUser();

        if (currentUser) {

            //  Viene chiamato quando l'utente clicca sul pulsante "Iscriviti".
            axios.post(`/api/iscrizione/${currentUser.id}/programma/${programmaId}`, null, { headers: authHeader() })
                .then(response => {
                    // Aggiorna lo stato solo del programma che è stato iscritto
                    this.setState(prevState => ({
                        programmiFedelta: prevState.programmiFedelta.map(programma => {
                            if (programma.programmaId === programmaId) {
                                return {
                                    ...programma,
                                    isIscritto: true
                                };
                            }
                            return programma;
                        })
                    }));
                })
                .catch(
                    error => console.error('Errore durante l\'iscrizione:', error));
        } else {
            alert("Devi essere loggato per iscriverti a un programma fedeltà.");
        }
    }
}

export default Home

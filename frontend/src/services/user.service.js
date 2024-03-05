//Questo è un modulo JavaScript che definisce un servizio chiamato UserService per effettuare chiamate HTTP al backend
import axios from 'axios';
import authHeader from './auth-header';

// Viene definita una costante API_URL che contiene l'URL di base per tutte le chiamate API nell'applicazione.
const API_URL = '/api';

class UserService {
    //Questo metodo fa una richiesta GET a API_URL + '/user' per ottenere il "board" degli utenti.
    getUserBoard() {
        return axios.get(API_URL + '/user', { headers: authHeader() });
    }

    // Questo metodo fa una richiesta GET a API_URL + /tessera/${tesseraId}/iscrizioni'per ottenere le iscrizioni associate a un determinato cliente
    getIscrizioniByCliente(tesseraId) {
        return axios.get(API_URL + `/tessera/${tesseraId}/iscrizioni`, { headers: authHeader() })
    }
}

// Questo rende il servizio disponibile per essere utilizzato in altri file del codice React.
export default new UserService();
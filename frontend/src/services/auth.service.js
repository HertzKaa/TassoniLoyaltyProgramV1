//Services sono le chiamate al backend
//fornisce funzionalità per gestire l'autenticazione degli utenti
import axios from "axios";


const API_URL = '/api';

// Questo metodo rimuove l'oggetto "user" dal localStorage del browser, quindi deautentica l'utente corrente.
class AuthService {
    login(username, password) {
        return axios
            .post(API_URL + "/login", {
                username,
                password
            })
            .then(response => { //Se la richiesta ha successo e il backend restituisce un token di accesso,
                                                    // questo token viene memorizzato nel localStorage del browser per mantenerlo autenticato durante la sessione.
                if (response.data.accessToken) {
                    localStorage.setItem("user", JSON.stringify(response.data)); //Stringifica il token per poterlo conservare/lavorare
                }

                return response.data;
            });
    }

    // Questo metodo rimuove l'oggetto "user" dal localStorage del browser
    logout() {
        localStorage.removeItem("user");
    }

    // Questo metodo invia una richiesta POST al backend per registrare un nuovo utente.
    register(username, email, password) {
        return axios.post(API_URL + "/register", {
            username,
            email,
            password
        });
    }

    // Questo metodo restituisce l'oggetto "user" memorizzato nel localStorage del browser.
    getCurrentUser() {
        return JSON.parse(localStorage.getItem('user'));
    }
}

export default new AuthService();
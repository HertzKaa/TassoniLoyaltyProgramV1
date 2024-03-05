// Questa funzione è progettata per generare l'header di autorizzazione necessario per le richieste HTTP
export default function authHeader() {
    // Recupera l'utente memorizzato nel localStorage dell'applicazione
    const user = JSON.parse(localStorage.getItem('user'));

    // Verifica se l'utente è valido (ovvero se esiste e se ha un accessToken)
    if (user && user.accessToken) {
        // Restituisce un oggetto con un header di autorizzazione contenente il token JWT, seguendo lo schema di autenticazione Bearer.
        return { Authorization: 'Bearer ' + user.accessToken };
    } else {
        // Restituisce un oggetto vuoto
        return {};
    }
}
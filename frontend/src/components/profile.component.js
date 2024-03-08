import React, { Component } from "react";
import { Navigate } from "react-router-dom";
import AuthService from "../services/auth.service";
import "./profilo.css"

export default class ProfileComponent extends Component {
    constructor(props) {
        super(props);

        this.state = {
            redirect: null,
            userReady: false,
            currentUser: { username: "" }
        };
    }

    componentDidMount() {
        // Viene recuperato l'utente corrente utilizzando il servizio di autenticazione 
        const currentUser = AuthService.getCurrentUser();

        // Definito il reindirizzamento
        if (!currentUser) this.setState({ redirect: "/home" });
        this.setState({ currentUser: currentUser, userReady: true })
    }

    render() {
        if (this.state.redirect) {
            // Se l'utente non è autenticato e viene eseguito un reindirizzamento
            return <Navigate to={this.state.redirect} />
        }

    //se l'utente è pronto, vengono visualizzate le informazioni sul profilo utente, inclusi il nome utente,
    // l'ID utente, l'email e i ruoli assegnati. Se l'utente non è ancora pronto (ad esempio, mentre si attende il caricamento dei dati dell'utente), viene visualizzato null.
        const { currentUser } = this.state;

        return (
            <div className="container">
                {(this.state.userReady) ?
                    <div>
                        <div className="text-center">
                            <h3 className="profile-title">
                                <strong>{currentUser.username}</strong>
                            </h3>
                        </div>
                        <div className="row">
                            <div className="col-md-6 offset-md-3">
                                <div className="profile-info">
                                    <p>
                                        <strong className="profile-label">ID Utente:</strong>{" "}
                                        {currentUser.id}
                                    </p>
                                    <p>
                                        <strong className="profile-label">Email:</strong>{" "}
                                        {currentUser.email}
                                    </p>
                                    <strong className="profile-label">Ruoli:</strong>
                                    <ul>
                                        {currentUser.roles &&
                                            currentUser.roles.map((role, index) => <li key={index}>{role}</li>)}
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div> : null}
            </div>
        );
    }
}

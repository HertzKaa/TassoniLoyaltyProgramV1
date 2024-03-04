import React, { Component } from "react";
import {Routes, Route, Link, Navigate} from "react-router-dom"; //moduli sono utilizzati per gestire il routing dell'applicazione
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";

// Importati i vari moduli
import AuthService from "./services/auth.service";
import Login from "./components/login.component";
import Register from "./components/register.component";
import Home from "./components/home.component";
import Profile from "./components/profile.component";
import BoardUser from "./components/listaIscrizioni.component";

//controlla se un utente è già autenticato.
function LoginComponent() {
  if (AuthService.getCurrentUser()) {
    return <Navigate to="/profile" />;
  }
  return <Login />;
}

//controlla se un utente è già registrato.
function RegisterComponent() {
  if (AuthService.getCurrentUser()) {
    return <Navigate to="/profile" />;
  }
  return <Register />;
}


class App extends Component {
  // Viene inizializzato lo stato iniziale della componente App.
  constructor(props) {
    super(props);
    //Il metodo this.logOut viene legato per garantire che l'oggetto this
    // si riferisca correttamente all'istanza della classe App.
    this.logOut = this.logOut.bind(this);

    this.state = {
      currentUser: undefined, // Setta lo stato iniziale dell'utente
    };
  }

  componentDidMount() {
    //Verifica se c'è un utente attualmente autenticato
    const user = AuthService.getCurrentUser();

    //Assegna i flag all'utente
    if (user) {
      this.setState({
        currentUser: user,
        showModeratorBoard: user.roles.includes("ROLE_MODERATOR"),
        showAdminBoard: user.roles.includes("ROLE_ADMIN"),
      });
    }
  }

  // Disconnette un utente
  logOut() {
    AuthService.logout();
    this.setState({
      showModeratorBoard: false,
      showAdminBoard: false,
      currentUser: undefined,
    });
  }

  //JSX che rappresenta la struttura dell'interfaccia utente.
  render() {
    const { currentUser } = this.state;

    return (
        <div>
          <nav className="navbar navbar-expand navbar-dark bg-dark">
            <Link to={"/home"} className="navbar-brand">
              LoyaltyPlatform
            </Link>
            <div className="navbar-nav mr-auto">
              <li className="nav-item">
                <Link to={"/home"} className="nav-link">
                  Programmi
                </Link>
              </li>

              {currentUser && (
                  <li className="nav-item">
                    <Link to={"/user"} className="nav-link">
                      Iscrizioni
                    </Link>
                  </li>
              )}
            </div>

            {currentUser ? (
                <div className="navbar-nav ml-auto">
                  <li className="nav-item">
                    <Link to={"/profile"} className="nav-link">
                      {currentUser.username}
                    </Link>
                  </li>
                  <li className="nav-item">
                    <a href="/login" className="nav-link" onClick={this.logOut}>
                      Esci
                    </a>
                  </li>
                </div>
            ) : (
                <div className="navbar-nav ml-auto">
                  <li className="nav-item">
                    <Link to={"/login"} className="nav-link">
                      Login
                    </Link>
                  </li>

                  <li className="nav-item">
                    <Link to={"/register"} className="nav-link">
                      Registrati
                    </Link>
                  </li>
                </div>
            )}
          </nav>

          <div className="container mt-3">
            <Routes>
              <Route path="/" element={<Home />} />
              <Route path="/home" element={<Home />} />
              <Route path="/login" element={<Login />} />
              <Route path="/register" element={<Register />} />
              <Route path="/profile" element={<Profile />} />
              <Route path="/user" element={<BoardUser />} />
            </Routes>
          </div>
        </div>
    );
  }
}

export default App;
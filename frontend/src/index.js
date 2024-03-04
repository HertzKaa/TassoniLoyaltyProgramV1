
import React from "react";
import { createRoot } from "react-dom/client"; //viene utilizzato per inizializzare e gestire un'istanza React
import { BrowserRouter } from "react-router-dom"; //fornisce un wrapper per l'applicazione, semplifica routing

import App from "./App"; //Viene importato App.js

const container = document.getElementById("root"); //Questo è l'elemento HTML in cui l'applicazione React verrà montata
const root = createRoot(container); //viene creato un root React all'interno del container

root.render( //Viene renderizzata l'App
    <BrowserRouter>
        <App />
    </BrowserRouter>
);
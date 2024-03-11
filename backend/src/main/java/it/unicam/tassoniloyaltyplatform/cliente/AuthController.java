package it.unicam.tassoniloyaltyplatform.cliente;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import it.unicam.tassoniloyaltyplatform.eccezioni.RecordNotFoundException;
import it.unicam.tassoniloyaltyplatform.security.payload.request.LoginRequest;
import it.unicam.tassoniloyaltyplatform.security.payload.request.SignupRequest;
import it.unicam.tassoniloyaltyplatform.security.payload.response.JwtResponse;
import it.unicam.tassoniloyaltyplatform.security.payload.response.MessageResponse;
import it.unicam.tassoniloyaltyplatform.ruolo.ERole;
import it.unicam.tassoniloyaltyplatform.ruolo.Role;
import it.unicam.tassoniloyaltyplatform.ruolo.RoleRepository;
import it.unicam.tassoniloyaltyplatform.security.jwt.JwtUtils;
import it.unicam.tassoniloyaltyplatform.cliente.services.DettagliClienteImpl;
import it.unicam.tassoniloyaltyplatform.tessera.TesseraService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//gestisce le operazioni relative all'autenticazione e registrazione degli utenti
//@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TesseraService tesseraService;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    // gestisce la richiesta di autenticazione
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) throws RecordNotFoundException {
        // Autenticazione dell'utente
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        // indica a Spring Security chi è l'utente autenticato e quali sono i suoi diritti di accesso durante il corso della richiesta corrente
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // Generazione del token JWT
        String jwt = jwtUtils.generateJwtToken(authentication);

        DettagliClienteImpl userDetails = (DettagliClienteImpl) authentication.getPrincipal();
        // estrazione ruoli dell'utente
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        // return della risposta alla richiesta di autenticazione, con token JWT
        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles,
                this.tesseraService.findTesseraByClienteId(userDetails.getId())));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) throws RecordNotFoundException {
        // Verifica se lo username è già in uso
        if (clienteRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Username già preso!"));
        }
        // Verifica se l'email è già in uso
        if (clienteRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Email già in uso!"));
        }

        Cliente cliente = new Cliente(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();
        // Vengono gestiti i ruoli dell'utente e se non è specificato nella richiesta gli 
        //viene assegnato un ruolo di default 
        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Ruolo non trovato"));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Ruolo non trovato"));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Ruolo non trovato"));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Ruolo non trovato"));
                        roles.add(userRole);
                }
            });
        }

        cliente.setRoles(roles);
        clienteRepository.save(cliente);
        tesseraService.aggiungiTessera(cliente.getId());

        return ResponseEntity.ok(new MessageResponse("Utente registrato correttamente!"));
    }
}

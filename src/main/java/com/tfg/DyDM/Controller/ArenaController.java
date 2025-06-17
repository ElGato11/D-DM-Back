package com.tfg.DyDM.Controller;

import com.tfg.DyDM.jwt.JwtService;
import com.tfg.DyDM.entity.Personaje;
import com.tfg.DyDM.model.Sala;
import com.tfg.DyDM.entity.Usuario;
import com.tfg.DyDM.service.ArenaService;
import com.tfg.DyDM.service.PersonajeService;
import com.tfg.DyDM.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/logged/arena")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ArenaController {

    private final ArenaService arenaService;
    private final JwtService jwtService;
    private final UsuarioService usuarioService;
    private final PersonajeService personajeService;

    @GetMapping("/salas")
    public List<Sala> listarSalas() {
        return arenaService.listarSalas();
    }

    @PostMapping("/crear")
    public Sala crearSala(@RequestBody Map<String, String> body, HttpServletRequest request) {
        String nombre = body.get("nombre");
        Long userId = jwtService.extractUserIdFromRequest(request);
        Usuario host = usuarioService.getById(userId);
        return arenaService.crearSala(nombre, host);
    }

    @PostMapping("/unirse/{id}")
    public Sala unirseSala(@PathVariable Long id, HttpServletRequest request) {
        Long userId = jwtService.extractUserIdFromRequest(request);
        Usuario jugador = usuarioService.getById(userId);
        return arenaService.unirseSala(id, jugador);
    }

    @PostMapping("/salir/{id}")
    public void salirSala(@PathVariable Long id, HttpServletRequest request) {
        Long userId = jwtService.extractUserIdFromRequest(request);
        arenaService.salirSala(id, userId);
    }

    @GetMapping("/{id}")
    public Sala getSala(@PathVariable Long id) {
        return arenaService.obtenerSala(id);
    }

    @PostMapping("/{id}/asignar-personaje")
    public Sala asignarPersonaje(@PathVariable Long id, @RequestBody Map<String, Long> body, HttpServletRequest request) {
        Long personajeId = body.get("personajeId");
        Long userId = jwtService.extractUserIdFromRequest(request);
        Personaje personaje = personajeService.getById(personajeId);
        return arenaService.asignarPersonaje(id, userId, personaje);
    }

    @PostMapping("/{id}/atacar")
    public Sala atacar(@PathVariable Long id, @RequestBody Map<String,String> body) {
        Sala sala = arenaService.obtenerSala(id);
        String atacante = body.get("atacante"); // "host" o "jugador2"
        if ("host".equals(atacante)) {
            sala.setHpJugador2(sala.getHpJugador2() - 1);
            if (sala.getHpJugador2() <= 0) {
                personajeService.borrar(sala.getPersonajeJugador2().getIdPersonaje());
                // forzar salida de sala...
            } else {
                sala.setTurnoActual("jugador2");
            }
        } else {
            sala.setHpHost(sala.getHpHost() - 1);
            if (sala.getHpHost() <= 0) {
                personajeService.borrar(sala.getPersonajeHost().getIdPersonaje());
            } else {
                sala.setTurnoActual("host");
            }
        }
        return sala;
    }

    @PostMapping("/{id}/iniciar-combate")
    public Sala iniciarCombate(@PathVariable Long id) {
        Sala sala = arenaService.obtenerSala(id);

        // Calculamos HP como la mitad de la constitución redondeada hacia arriba
        sala.setHpHost((int) Math.ceil(sala.getPersonajeHost().getConstitucion() / 2.0));
        sala.setHpJugador2((int) Math.ceil(sala.getPersonajeJugador2().getConstitucion() / 2.0));

        // Determinar quién ataca primero según la destreza
        sala.setTurnoActual(
                sala.getPersonajeHost().getDestreza() >= sala.getPersonajeJugador2().getDestreza()
                        ? "host" : "jugador2"
        );

        return sala;
    }
}

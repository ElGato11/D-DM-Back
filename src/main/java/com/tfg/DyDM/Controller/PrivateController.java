package com.tfg.DyDM.Controller;

import com.tfg.DyDM.dto.UsuarioDto;
import com.tfg.DyDM.model.*;
import com.tfg.DyDM.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/private")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200/"})
public class PrivateController {

    @Autowired
    private final UsuarioService usuarioService;
    @Autowired
    private ClaseService claseService;
    @Autowired
    private ConjuroService conjuroService;
    @Autowired
    private ObjetoService objetoService;
    @Autowired
    private PersonajeService personajeService;
    @Autowired
    private RazaService razaService;
    @Autowired
    private VentajaService ventajaService;

    //USUARIOS
    @PostMapping("/usuario/crear/admin")
    public ResponseEntity<Usuario> crearUsuario(@RequestBody UsuarioDto usuario) {
        Usuario creado = usuarioService.createUsuarioAdmin(usuario);
        return ResponseEntity.ok(creado);

    }

    @GetMapping("/usuario")
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.obtenerTodos());
    }

    @GetMapping("/usuario/buscar/{id}")
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.obtenerPorId(id));
    }

    @PostMapping("/usuario/actualizar/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @RequestBody UsuarioDto usuarioDto) {
        return ResponseEntity.ok(usuarioService.actualizarUsuario(id, usuarioDto));
    }

    @PostMapping("/usuario/borrar/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    //CLASES
    @PostMapping("/clase/crear")
    public ResponseEntity<Clase> createClase(@RequestBody Clase clase) {
        Clase nuevaClase = claseService.createClase(clase);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaClase);
    }

    @PostMapping("/clase/actualizar/{id}")
    public ResponseEntity<Clase> updateClase(@PathVariable Long id, @RequestBody Clase claseDetails) {
        try {
            Clase updatedClase = claseService.updateClase(id, claseDetails);
            return ResponseEntity.ok(updatedClase);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/clase/borrar/{id}")
    public ResponseEntity<Void> deleteClase(@PathVariable Long id) {
        try {
            claseService.deleteClase(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    //CONJUROS
    @PostMapping("/conjuro/crear")
    public ResponseEntity<Conjuro> createConjuro(@RequestBody Conjuro conjuro) {
        try {
            Conjuro nuevo = conjuroService.save(conjuro);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PostMapping("/conjuro/actualizar/{id}")
    public ResponseEntity<Conjuro> updateConjuro(@PathVariable Long id, @RequestBody Conjuro conjuro) {
        try {
            Conjuro actualizado = conjuroService.update(id, conjuro);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("conjuro/borrar/{id}")
    public ResponseEntity<Void> deleteConjuro(@PathVariable Long id) {
        try {
            conjuroService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // OBJETOS
    @PostMapping("objeto/crear")
    public ResponseEntity<Objeto> createObjeto(@RequestBody Objeto objeto) {
        try {
            Objeto nuevo = objetoService.save(objeto);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PostMapping("/objeto/actualizar/{id}")
    public ResponseEntity<Objeto> updateObjeto(@PathVariable int id, @RequestBody Objeto objeto) {
        try {
            Objeto actualizado = objetoService.update(id, objeto);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/objeto/borrar/{id}")
    public ResponseEntity<Void> deleteObjeto(@PathVariable int id) {
        try {
            objetoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    //PERSONAJE
    @GetMapping("/personaje")
    public List<Personaje> getAllPersonajes() {
        return personajeService.findAll();
    }

    @PostMapping("/personaje/crear")
    public ResponseEntity<Personaje> createPersonaje(@RequestBody Personaje personaje) {
        try {
            Personaje nuevo = personajeService.save(personaje);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    @PostMapping("/personaje/actualizar/{id}")
    public ResponseEntity<Personaje> updatePersonaje(@PathVariable int id, @RequestBody Personaje personaje) {
        try {
            Personaje actualizado = personajeService.update(id, personaje);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/personaje/borrar/{id}")
    public ResponseEntity<Void> deletePersonaje(@PathVariable int id) {
        try {
            personajeService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    //RAZA
    @PostMapping("/raza/crear")
    public ResponseEntity<Raza> createRaza(@RequestBody Raza raza) {
        try {
            Raza nuevo = razaService.save(raza);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    @PostMapping("/raza/actualizar/{id}")
    public ResponseEntity<Raza> updateRaza(@PathVariable Long id, @RequestBody Raza raza) {
        try {
            Raza actualizado = razaService.update(id, raza);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/raza/borrar/{id}")
    public ResponseEntity<Void> deleteRaza(@PathVariable Long id) {
        try {
            razaService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    //VENTAJAS
    @GetMapping("/ventaja")
    public List<Ventaja> getAllVentajas() {
        return ventajaService.findAll();
    }
    @GetMapping("/ventaja/buscar/{id}")
    public ResponseEntity<Ventaja> getVentajaById(@PathVariable int id) {
        return ventajaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/ventaja/crear")
    public ResponseEntity<Ventaja> createVentaja(@RequestBody Ventaja ventaja) {
        try {
            Ventaja nuevo = ventajaService.save(ventaja);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    @PostMapping("ventaja/actualizar/{id}")
    public ResponseEntity<Ventaja> updateVentaja(@PathVariable int id, @RequestBody Ventaja ventaja) {
        try {
            Ventaja actualizado = ventajaService.update(id, ventaja);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("ventaja/borrar/{id}")
    public ResponseEntity<Void> deleteVentaja(@PathVariable int id) {
        try {
            ventajaService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}


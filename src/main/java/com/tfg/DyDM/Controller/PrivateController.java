package com.tfg.DyDM.Controller;

import com.tfg.DyDM.dto.UsuarioDto;
import com.tfg.DyDM.entity.*;
import com.tfg.DyDM.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    @PostMapping("/usuario/cambiar-clave/{id}")
    public ResponseEntity<Void> cambiarClave(@PathVariable Long id, @RequestBody String nuevaClave) {
        usuarioService.cambiarClave(id, nuevaClave);
        return ResponseEntity.ok().build();
    }

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
    public ResponseEntity<Clase> createClase(
            @RequestPart("clase") Clase clase,
            @RequestPart(value = "imagen", required = false) MultipartFile imagen
    ) {
        if (imagen != null && !imagen.isEmpty()) {
            try {
                clase.setImagenClase(imagen.getBytes());
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
        Clase nuevaClase = claseService.createClase(clase);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaClase);
    }

    @PostMapping(value = "/clase/actualizar/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Clase> updateClase(@PathVariable Long id,
                                             @RequestPart("clase") Clase claseDetails,
                                             @RequestPart(value = "imagen", required = false) MultipartFile imagen) {
        try {
            if (imagen != null && !imagen.isEmpty()) {
                claseDetails.setImagenClase(imagen.getBytes());
            }
            Clase updatedClase = claseService.updateClase(id, claseDetails);
            return ResponseEntity.ok(updatedClase);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
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

    //RAZA
    @PostMapping(value = "/raza/crear", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Raza> createRaza(
            @RequestPart("raza") Raza raza,
            @RequestPart(value = "imagen", required = false) MultipartFile imagen) {
        try {
            if (imagen != null && !imagen.isEmpty()) {
                raza.setImagenRaza(imagen.getBytes());
            }
            Raza nuevo = razaService.save(raza);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PostMapping(value = "/raza/actualizar/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Raza> updateRaza(
            @PathVariable Long id,
            @RequestPart("raza") Raza raza,
            @RequestPart(value = "imagen", required = false) MultipartFile imagen) {
        try {
            if (imagen != null && !imagen.isEmpty()) {
                raza.setImagenRaza(imagen.getBytes());
            }
            Raza actualizado = razaService.update(id, raza);
            return ResponseEntity.ok(actualizado);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
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


package com.tfg.DyDM.Controller;


import com.tfg.DyDM.dto.UsuarioDto;
import com.tfg.DyDM.entity.*;
import com.tfg.DyDM.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200/"})
public class PublicController {
    @Autowired
    private ClaseService claseService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private ConjuroService conjuroService;
    @Autowired
    private ObjetoService objetoService;
    @Autowired
    private RazaService razaService;
    @Autowired
    private VentajaService ventajaService;

    //USUARIO
    @PostMapping("/usuario/admin")
    public ResponseEntity<String> createAdminIfNotExists() {
        try {
            // Comprueba si ya existe un usuario llamado "admin"
            if (usuarioService.existsByNombre("admin")) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("El usuario admin ya existe.");
            }

            UsuarioDto admin = new UsuarioDto();
            admin.setNombre("admin");
            admin.setClave("1234");

            usuarioService.createUsuarioAdmin(admin);
            return ResponseEntity.ok("Usuario admin creado correctamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear admin.");
        }
    }
    @PostMapping("/usuario/crear")
    public ResponseEntity<Usuario> crearUsuario(@RequestBody UsuarioDto usuario){
        Usuario creado = usuarioService.createUsuario(usuario);
        return ResponseEntity.ok(creado);

    }

    //CLASE
    @GetMapping("/clase")
    public List<Clase> getAllClases() {
        return claseService.getAllClases();
    }
    @GetMapping("/clase/buscar/{id}")
    public ResponseEntity<Clase> getClaseById(@PathVariable Long id) {
        return claseService.getClaseById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //CONJURO
    @GetMapping("/conjuro")
    public List<Conjuro> getAllConjuros() {
        return conjuroService.findAll();
    }
    @GetMapping("/conjuro/{id}")
    public ResponseEntity<Conjuro> getConjuroById(@PathVariable Long id) {
        return conjuroService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //OBJETO
    @GetMapping("/objeto")
    public List<Objeto> getAllObjetos() {
        return objetoService.findAll();
    }
    @GetMapping("/objeto/{id}")
    public ResponseEntity<Objeto> getObjetoById(@PathVariable int id) {
        return objetoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //RAZA
    @GetMapping("/raza")
    public List<Raza> getAllRazas() {
        return razaService.findAll();
    }
    @GetMapping("/raza/{id}")
    public ResponseEntity<Raza> getRazaById(@PathVariable Long id) {
        return razaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //VENTAJA
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
}

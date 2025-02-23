package net.ausiasmarch.wejeta.api;

import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import net.ausiasmarch.wejeta.entity.User;
import net.ausiasmarch.wejeta.service.UsuarioService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    /**
     * Obtiene un usuario por su email.
     *
     * @param email El email del usuario.
     * @return ResponseEntity con el usuario encontrado o un error 404 si no existe.
     */
    @GetMapping("/byemail/{email}")
    public ResponseEntity<User> getUsuarioByEmail(@PathVariable String email) {
        return ResponseEntity.ok(usuarioService.getByEmail(email));
    }

    /**
     * Obtiene un usuario por su ID.
     *
     * @param id El ID del usuario.
     * @return ResponseEntity con el usuario encontrado o un error 404 si no existe.
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getUsuarioById(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.get(id));
    }

    /**
     * Crea un nuevo usuario o inicia sesión si ya existe.
     *
     * @param usuario El usuario a crear o verificar.
     * @return ResponseEntity con el usuario y un indicador de si ya existía.
     */
    @PostMapping("/new")
    public ResponseEntity<?> createOrLoginUsuario(@RequestBody User usuario) {
        // Verificar si el usuario ya existe por email o sub (Google ID)
        boolean existsByEmail = usuarioService.existsByEmail(usuario.getEmail());
        boolean existsBySub = usuarioService.existsBySub(usuario.getSub());

        if (existsByEmail || existsBySub) {
            // Obtener el usuario existente
            User existingUser = existsByEmail
                    ? usuarioService.getByEmail(usuario.getEmail())
                    : usuarioService.getBySub(usuario.getSub());

            // Construir la respuesta indicando que el usuario ya existía
            HashMap<String, Object> response = new HashMap<>();
            response.put("existingUser", true);
            response.put("user", existingUser);
            return ResponseEntity.ok(response);
        }

        try {
            // Crear un nuevo usuario
            User savedUsuario = usuarioService.create(usuario);

            // Construir la respuesta indicando que el usuario fue creado
            HashMap<String, Object> response = new HashMap<>();
            response.put("existingUser", false);
            response.put("user", savedUsuario);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            // Manejar errores internos del servidor
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"message\": \"Error al guardar el usuario\"}");
        }
    }
}
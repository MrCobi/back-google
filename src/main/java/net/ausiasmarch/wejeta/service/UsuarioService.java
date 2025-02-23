package net.ausiasmarch.wejeta.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import net.ausiasmarch.wejeta.entity.User;
import net.ausiasmarch.wejeta.exception.ResourceNotFoundException;
import net.ausiasmarch.wejeta.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    /**
     * Obtiene un usuario por su email.
     *
     * @param email El email del usuario.
     * @return El usuario encontrado.
     * @throws ResourceNotFoundException Si el usuario no existe.
     */
    public User getByEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("El usuario con email " + email + " no existe"));
    }

    /**
     * Obtiene un usuario por su Google ID (sub).
     *
     * @param sub El Google ID del usuario.
     * @return El usuario encontrado.
     * @throws ResourceNotFoundException Si el usuario no existe.
     */
    public User getBySub(String sub) {
        return usuarioRepository.findBySub(sub)
                .orElseThrow(() -> new ResourceNotFoundException("El usuario con Google ID " + sub + " no existe"));
    }

    /**
     * Obtiene un usuario por su ID.
     *
     * @param id El ID del usuario.
     * @return El usuario encontrado.
     * @throws EntityNotFoundException Si el usuario no existe.
     */
    public User get(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con ID: " + id));
    }

    /**
     * Crea un nuevo usuario.
     *
     * @param user El usuario a crear.
     * @return El usuario creado.
     * @throws ResourceNotFoundException Si el usuario ya existe por email o Google
     *                                   ID.
     */
    public User create(User user) {
        // Verificar si el usuario ya existe por email o Google ID
        if (usuarioRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new ResourceNotFoundException("El usuario con email " + user.getEmail() + " ya existe");
        }
        if (usuarioRepository.findBySub(user.getSub()).isPresent()) {
            throw new ResourceNotFoundException("El usuario con Google ID " + user.getSub() + " ya existe");
        }

        // Guardar el nuevo usuario
        return usuarioRepository.save(user);
    }

    /**
     * Verifica si un usuario existe por su email.
     *
     * @param email El email del usuario.
     * @return true si el usuario existe, false en caso contrario.
     */
    public boolean existsByEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    /**
     * Verifica si un usuario existe por su Google ID (sub).
     *
     * @param sub El Google ID del usuario.
     * @return true si el usuario existe, false en caso contrario.
     */
    public boolean existsBySub(String sub) {
        return usuarioRepository.existsBySub(sub);
    }
}
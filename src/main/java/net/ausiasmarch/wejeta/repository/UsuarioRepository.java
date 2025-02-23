package net.ausiasmarch.wejeta.repository;

import net.ausiasmarch.wejeta.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<User, Long> {

    /**
     * Busca un usuario por su email.
     *
     * @param email El email del usuario.
     * @return Un Optional con el usuario encontrado.
     */
    Optional<User> findByEmail(String email);

    /**
     * Busca un usuario por su Google ID (sub).
     *
     * @param sub El Google ID del usuario.
     * @return Un Optional con el usuario encontrado.
     */
    Optional<User> findBySub(String sub);

    /**
     * Verifica si un usuario existe por su email.
     *
     * @param email El email del usuario.
     * @return true si el usuario existe, false en caso contrario.
     */
    boolean existsByEmail(String email);

    /**
     * Verifica si un usuario existe por su Google ID (sub).
     *
     * @param sub El Google ID del usuario.
     * @return true si el usuario existe, false en caso contrario.
     */
    boolean existsBySub(String sub);
}
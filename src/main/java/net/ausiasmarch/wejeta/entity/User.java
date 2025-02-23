package net.ausiasmarch.wejeta.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identificador único del usuario

    @Column(nullable = false, unique = true)
    private String sub; // Google ID del usuario

    @Column(nullable = false)
    private String name; // Nombre completo del usuario

    @Column(nullable = false, unique = true)
    private String email; // Email del usuario

    @Column(nullable = false)
    private boolean emailVerified; // Indica si el email está verificado

    private String picture; // URL de la imagen de perfil del usuario

    private String givenName; // Nombre de pila del usuario

    private String familyName; // Apellido del usuario
}
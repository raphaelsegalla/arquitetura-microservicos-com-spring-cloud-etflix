package com.raphaelsegalla.auth.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "permission")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Permission implements GrantedAuthority, Serializable {

    private static final long serialVersionUID = 1242863488671032943L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description", nullable = false)
    private String description;

    @Override
    public String getAuthority() {
        return this.description;
    }
}

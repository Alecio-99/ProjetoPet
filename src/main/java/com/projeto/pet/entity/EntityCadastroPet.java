package com.projeto.pet.entity;

import com.projeto.pet.enuns.Status;
import com.projeto.pet.enuns.TipoPlano;
import com.projeto.pet.enuns.UserRoles;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.*;

//Entidade responsavel pelo cadastro do consumidor do serviço.

@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
@Data
@Table(name = "cadastro_pet")
public class EntityCadastroPet implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "email")
    private String email;

    @Column(name = "cnpj")
    private String cnpj;

    @Embedded
    private Entedeco entedeco;

    @ElementCollection(targetClass = TipoPlano.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "pet_plano_contratados", joinColumns = @JoinColumn(name = "pet_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "plano", nullable = false)
    private Set<TipoPlano> planoContratados = new HashSet<>();

    @Enumerated(EnumType.STRING)
    Status status;

    @CreationTimestamp
    private LocalDate planoInicio;

    private LocalDate planoFim;


    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private UserRoles role;


    public EntityCadastroPet(String email, String password, UserRoles role, Status status) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.status = status;
        this.name = email; // Usando email como nome inicialmente
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if(this.role == UserRoles.ADMIN) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        if(this.planoContratados != null){
            for (TipoPlano plano : this.planoContratados){
                authorities.add(new SimpleGrantedAuthority("PLANO_" + plano.name()));
            }
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        EntityCadastroPet that = (EntityCadastroPet) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

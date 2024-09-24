package com.tmtbackend.model;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "user")
@EntityListeners(AuditingEntityListener.class)
public class User implements UserDetails, Principal {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "user_seq_gen")
    @TableGenerator(
            name = "user_seq_gen",
            table = "user_seq",               // Name of the sequence table
            pkColumnName = "seq_name",        // Name of the primary key column
            valueColumnName = "next_val",     // Column to store the next sequence value
            pkColumnValue = "user_seq",       // Value for this sequence in the pkColumnName
            allocationSize = 1
    )
    private Integer id;

    private String fullname;

    @Column(unique = true)
    private String email;

    private String password;

    private long mobileNo;

    private String nationality;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id",nullable = false,referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id",nullable = false,referencedColumnName = "id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"user_id","role_id"})
    )
    private List<Role> roles;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDate createdDate;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDate lastModifiedDate;

    private boolean isEmailVerified;

    private String otp;
    private LocalDateTime otpExpiry;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<RegisterTrip> trips;

    @Override
    public String getName() {
        return fullname;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles
                .stream()
                .map(r -> new SimpleGrantedAuthority(r.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }
}

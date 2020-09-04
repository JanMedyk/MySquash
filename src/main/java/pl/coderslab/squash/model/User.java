package pl.coderslab.squash.model;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
//@AllArgsConstructor
//@NoArgsConstructor
@Table(name = "User")
@ToString
@EqualsAndHashCode(of = "id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @NotEmpty
    @Email

    private String mail;
@Size(min = 3, max = 20)
    private String name;
    @Size(min = 3, max = 20)
    @Column
    private String userName;
    @Size(min = 3, max = 20)
    private String lastName;
    private int old;
    @NotEmpty
//    @Length(min = 3, max = 20)
//    @Column(length = 200)

//    @Size(min = 3, max = 20)
    private String password;

    @ManyToMany
    private List<Sport> sports = new ArrayList<>();
    @ManyToMany
    private List<MatchHistory> matchHistories = new ArrayList<>();
    @ManyToMany
    private List<Inventory> inventories = new ArrayList<>();
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
    @Column
   private boolean enabled;
    public User()
    {
        super();
        this.enabled=false;

    }

}

package pl.coderslab.squash.model;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
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
    private LocalDate dateOfBirth;


    private int old;
    @NotEmpty

    private String password;
    @ManyToOne
    private City city;

    @ManyToMany(cascade =CascadeType.PERSIST)
    private List<Sport> sports = new ArrayList<>();
//    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
//    private List<MatchHistory> matchHistories = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
    @Column
    private boolean enabled;

    public User(@NotEmpty @Email String mail, @Size(min = 3, max = 20) String name, @Size(min = 3, max = 20) String userName, @Size(min = 3, max = 20) String lastName, String city, int old, @NotEmpty String password, List<Sport> sports) {
        this.mail = mail;
        this.name = name;
        this.userName = userName;
        this.lastName = lastName;
//        this.dateOfBirth = dateOfBirth;

        this.old = old;
        this.password = password;
        this.sports = sports;



        this.enabled = true;
    }

    public User() {
        super();
        this.enabled = false;
        this.old = 0;

    }


}

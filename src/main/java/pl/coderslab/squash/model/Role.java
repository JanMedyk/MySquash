package pl.coderslab.squash.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Role")
@ToString
@EqualsAndHashCode(of = "id")
public class Role {
    @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int  id;
    private String name;

}

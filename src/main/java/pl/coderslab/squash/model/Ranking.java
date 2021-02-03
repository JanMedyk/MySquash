package pl.coderslab.squash.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "Ranking")
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
public class Ranking {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long pkt;
    @OneToOne
    private User user;

}

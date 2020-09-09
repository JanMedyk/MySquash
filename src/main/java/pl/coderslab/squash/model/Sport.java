package pl.coderslab.squash.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
//@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Sport")
@ToString
@EqualsAndHashCode(of = "id")

public class Sport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
private levelEnum level;

    public Sport(String name,levelEnum levelEnum) {
        this.name=name;
        this.level=levelEnum;

    }
}

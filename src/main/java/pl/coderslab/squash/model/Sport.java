package pl.coderslab.squash.model;

import lombok.*;
import pl.coderslab.squash.model.enums.SportEnum;

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
    @Enumerated(value = EnumType.STRING)

    private SportEnum name;
    @ManyToOne(cascade =CascadeType.PERSIST)
    private Level levels;
//    private LevelEnum level;
    @OneToOne
    private Image image;


    public Sport(SportEnum name, Level levels) {
        this.name=name;
        this.levels=levels;

    }
}

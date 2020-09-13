package pl.coderslab.squash.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

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


    public Sport(SportEnum name, Level levels) {
        this.name=name;
        this.levels=levels;

    }
}

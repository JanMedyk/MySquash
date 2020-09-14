package pl.coderslab.squash.model;

import lombok.*;
import pl.coderslab.squash.model.enums.LevelEnum;

import javax.persistence.*;

@Entity
@Getter
@Setter
//@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Level")
@ToString
@EqualsAndHashCode(of = "id")
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(value = EnumType.STRING)
    private LevelEnum name;

    public Level(LevelEnum levelEnum) {
        this.name=levelEnum;
    }
}

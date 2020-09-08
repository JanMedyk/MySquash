package pl.coderslab.squash.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "MatchHistory")
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
public class MatchHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dateMatch;
    @ManyToMany(mappedBy = "matchHistories")
    private List<User> user=new ArrayList<>();
    private Integer pktZawodnika1;
    private Integer pktZawodnika2;


}

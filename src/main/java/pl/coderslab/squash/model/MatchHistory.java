package pl.coderslab.squash.model;

import lombok.*;
import org.apache.tomcat.jni.Local;
import org.springframework.lang.Nullable;
import pl.coderslab.squash.model.enums.SportEnum;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    //    @ManyToOne
    private SportEnum sport;

    private LocalDateTime dateMatchTotal;
    private LocalDateTime dateMatchTotalEnd;
    @Transient

    private String dateMatch;
    @Transient
    private LocalTime timeMatch;
    @ManyToOne
    private Club club;


    @ManyToOne
    private User userZakladajacy;
    @ManyToOne
    private User userPrzyjmujacy;
    @OneToMany(cascade =CascadeType.ALL)
    private List<Sets> sets =new ArrayList<>();


@ManyToOne
    private User UserWinner;
    @Nullable
    private Boolean accepted;
    private Boolean completed;
    @PrePersist
        public void PrePersist() {
        LocalDate aa = LocalDate.parse(dateMatch);
        dateMatchTotal = LocalDateTime.of(aa, timeMatch);
        this.accepted = null;
        dateMatchTotalEnd=dateMatchTotal.plusHours(1);



    }

//    @PreUpdate
//    public void PreUpdate() {
//        LocalDate aa = LocalDate.parse(dateMatch);
//        dateMatchTotal = LocalDateTime.of(aa, timeMatch);
//    }


    public MatchHistory(LocalDateTime localDateTime) {
    }
}

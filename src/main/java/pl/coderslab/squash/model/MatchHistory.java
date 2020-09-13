package pl.coderslab.squash.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

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

    private Integer pktUserZakladajacy;
    private Integer pktUserPrzyjmujacy;
    private Integer iloscSetowZakladajacy;
    private Integer iloscSetowyjmujacy;

    private Long idWinner;
    private Boolean accepted;

    @PrePersist
    public void PrePersist() {
        LocalDate aa = LocalDate.parse(dateMatch);
        dateMatchTotal = LocalDateTime.of(aa, timeMatch);
        this.accepted=false;



    }

    @PreUpdate
    public void PreUpdate() {
        LocalDate aa = LocalDate.parse(dateMatch);
        dateMatchTotal = LocalDateTime.of(aa, timeMatch);
    }


    public MatchHistory(LocalDateTime localDateTime) {
    }
}

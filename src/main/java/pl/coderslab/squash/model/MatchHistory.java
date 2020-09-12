package pl.coderslab.squash.model;

import lombok.*;

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
    @ManyToOne
    private Sport sport;

    private LocalDateTime dateMatchTotal;
    @Transient
    private LocalDate dateMatch;
    @Transient
    private LocalTime timeMatch;


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
        dateMatchTotal = LocalDateTime.of(dateMatch, timeMatch);


    }

    @PreUpdate
    public void PreUpdate() {
        dateMatchTotal = LocalDateTime.of(dateMatch, timeMatch);
    }


    public MatchHistory(LocalDateTime localDateTime) {
    }
}

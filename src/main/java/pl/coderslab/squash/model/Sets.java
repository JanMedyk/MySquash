package pl.coderslab.squash.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "sets")
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")

public class Sets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Integer pktZakladajacy;
    private Integer pktPrzyjmujacy;
    @OneToOne
    private User userWygrany;
    @OneToOne
    private User userPrzegrany;
    private Boolean WynikZaakceptowany;

    public Sets(Integer pktZakladajacy, Integer pktPrzyjmujacy, User userWygrany) {
        this.pktPrzyjmujacy=pktPrzyjmujacy;
        this.pktZakladajacy=pktZakladajacy;
        this.userWygrany=userWygrany;
        this.WynikZaakceptowany=false;

    }
}

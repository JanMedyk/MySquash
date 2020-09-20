package pl.coderslab.squash.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
//@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Image")
@ToString
@EqualsAndHashCode(of = "id")
public class Image {
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;

    @Column(name = "image_name")
    private String imageName;

    @Column(name = "image_type")
    private String imageType;

    @Lob
    @Column(name = "image")
    private byte[] image;



}

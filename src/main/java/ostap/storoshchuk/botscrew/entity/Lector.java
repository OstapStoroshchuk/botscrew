package ostap.storoshchuk.botscrew.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "lector")

public class Lector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lector_id")
    private Long id;

    @Column(name = "lector_first_name")
    private String firstName;

    @Column(name = "lector_last_name")
    private String lastName;

    @Enumerated(EnumType.STRING)
    private LectorsType lectorsType;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },mappedBy = "lectors")
    private List<Department> departments = new ArrayList<>();

    private Double salary = 0d;

    @Override
    public String toString() {
        return "Lector{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", works as a " + lectorsType +
                ", salary: " + salary +
                //", in departments=" + departments +
                '}';
    }
}

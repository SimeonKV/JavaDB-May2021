package hospitalDB.entites;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "visitations")
public class Visitation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private LocalDateTime time;
    @Column(columnDefinition = "TEXT(500)")
    private String comments;
    @OneToOne(cascade = CascadeType.ALL)
    private Patient patient;
    @OneToOne(cascade = CascadeType.ALL)
    private Diagnose diagnose;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Medicament> medicaments = new HashSet<>();

    public Visitation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void completeVisitation(Patient patient,Diagnose diagnose,Medicament... medicaments ){
        this.patient = patient;
        this.diagnose = diagnose;
        this.medicaments.addAll(Set.of(medicaments));

    }
}

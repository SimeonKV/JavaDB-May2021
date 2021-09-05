package hospitalDB.entites;

import javax.persistence.*;

@Entity
@Table(name = "diagnoses")
public class Diagnose {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,length = 20)
    private String name;
    @Column(columnDefinition = "TEXT(500)")
    private String comment;

    public Diagnose() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

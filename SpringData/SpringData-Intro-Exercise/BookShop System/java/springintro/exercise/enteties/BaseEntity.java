package springintro.exercise.enteties;

import javax.persistence.*;

@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    protected BaseEntity(){};

    protected Long getId() {
        return id;
    }
}

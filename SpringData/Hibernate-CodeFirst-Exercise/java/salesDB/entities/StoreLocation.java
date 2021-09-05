package salesDB.entities;

import javax.persistence.*;

@Entity
@Table(name = "store_locations")
public class StoreLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "location_name",nullable = false)
    private String locationName;

    public StoreLocation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
}

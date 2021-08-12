package xml.exercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xml.exercise.model.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
}

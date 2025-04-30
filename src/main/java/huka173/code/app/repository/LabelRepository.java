package huka173.code.app.repository;

import huka173.code.app.model.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LabelRepository extends JpaRepository<Label, Long> {
    Optional<Label> findByName(String name);
    boolean existsById(Long id);
}

package portfolio.mo.Repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import portfolio.mo.Entity.hys;

public interface Rhys extends JpaRepository<hys, Integer> {
    Optional<hys> findByNombre(String nombre);
    public boolean existsByNombre(String nombre);    
}

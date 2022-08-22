package portfolio.mo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import portfolio.mo.Entity.Persona;

@Repository
public interface IPersonaRepository extends JpaRepository<Persona,Long> {
}
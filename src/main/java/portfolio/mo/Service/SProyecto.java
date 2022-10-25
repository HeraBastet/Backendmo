package portfolio.mo.Service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import portfolio.mo.Entity.Proyecto;
import portfolio.mo.Repository.RProyecto;

@Service
@Transactional
public class SProyecto {
     @Autowired
     RProyecto rProyecto;
     
     public List<Proyecto> list() {
         return rProyecto.findAll();
     }
     
     public Optional<Proyecto> getOne(int id) {
         return rProyecto.findById(id);
     }
     
     public Optional<Proyecto> getByNombreE(String nombreE) {
         return rProyecto.findByNombreE(nombreE);
     }
     
     public void save(Proyecto expe) {
         rProyecto.save(expe);
     }
     
     public void delete(int id) {
         rProyecto.deleteById(id);
     }
     
     public boolean existsById(int id) {
         return rProyecto.existsById(id);
     }
     
     public boolean existsByNombreE(String nombreE) {
         return rProyecto.existsByNombreE(nombreE);
     }
}

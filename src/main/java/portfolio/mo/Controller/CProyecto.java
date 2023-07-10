package portfolio.mo.Controller;

import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import portfolio.mo.Dto.dtoProyecto;
import portfolio.mo.Entity.Proyecto;
import portfolio.mo.Security.Controller.Mensaje;
import portfolio.mo.Service.SProyecto;

@RestController
@RequestMapping("/proyecto")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}, allowedHeaders = "*")
public class CProyecto {
    @Autowired
    SProyecto sProyecto;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Proyecto>> list() {
        List<Proyecto> list = sProyecto.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Proyecto> getById(@PathVariable("id") int id) {
        if(!sProyecto.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Proyecto experiencia = sProyecto.getOne(id).get();
        return new ResponseEntity(experiencia, HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!sProyecto.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        sProyecto.delete(id);
        return new ResponseEntity(new Mensaje("Proyecto eliminado"), HttpStatus.OK);
    }

    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoProyecto dtoproyecto) {      
        if(StringUtils.isBlank(dtoproyecto.getNombreE()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(sProyecto.existsByNombreE(dtoproyecto.getNombreE()))
            return new ResponseEntity(new Mensaje("Ese proyecto existe"), HttpStatus.BAD_REQUEST);
        
        Proyecto experiencia = new Proyecto(dtoproyecto.getNombreE(), dtoproyecto.getDescripcionE());
        sProyecto.save(experiencia);
        
        return new ResponseEntity(new Mensaje("Proyecto agregada"), HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoProyecto dtoproyecto) {
        if(!sProyecto.existsById(id))
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        if(sProyecto.existsByNombreE(dtoproyecto.getNombreE()) && sProyecto.getByNombreE(dtoproyecto.getNombreE()).get().getId() != id)
            return new ResponseEntity(new Mensaje("Ese proyecto ya existe"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(dtoproyecto.getNombreE()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        
        Proyecto proyecto = sProyecto.getOne(id).get();
        proyecto.setNombreE(dtoproyecto.getNombreE());
        proyecto.setDescripcionE((dtoproyecto.getDescripcionE()));
        
        sProyecto.save(proyecto);
        return new ResponseEntity(new Mensaje("Proyecto actualizado"), HttpStatus.OK);             
    }
}
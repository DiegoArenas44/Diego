/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Aprendiz
 */
@Entity
@Table(name = "persona")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Persona.findAll", query = "SELECT p FROM Persona p"),
    @NamedQuery(name = "Persona.findByIdPERSONA", query = "SELECT p FROM Persona p WHERE p.idPERSONA = :idPERSONA"),
    @NamedQuery(name = "Persona.findByNombre", query = "SELECT p FROM Persona p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Persona.findByApellido", query = "SELECT p FROM Persona p WHERE p.apellido = :apellido"),
    @NamedQuery(name = "Persona.findByTelefonoFijo", query = "SELECT p FROM Persona p WHERE p.telefonoFijo = :telefonoFijo"),
    @NamedQuery(name = "Persona.findByFechaNaciemiento", query = "SELECT p FROM Persona p WHERE p.fechaNaciemiento = :fechaNaciemiento"),
    @NamedQuery(name = "Persona.findByCedula", query = "SELECT p FROM Persona p WHERE p.cedula = :cedula")})
public class Persona implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idPERSONA")
    private Integer idPERSONA;
    @Size(max = 50)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 50)
    @Column(name = "apellido")
    private String apellido;
    @Size(max = 20)
    @Column(name = "telefono_fijo")
    private String telefonoFijo;
    @Size(max = 20)
    @Column(name = "fecha_naciemiento")
    private String fechaNaciemiento;
    @Size(max = 20)
    @Column(name = "cedula")
    private String cedula;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "persona", fetch = FetchType.LAZY)
    private List<Linea> lineaList;

    public Persona() {
    }

    public Persona(Integer idPERSONA) {
        this.idPERSONA = idPERSONA;
    }

    public Integer getIdPERSONA() {
        return idPERSONA;
    }

    public void setIdPERSONA(Integer idPERSONA) {
        this.idPERSONA = idPERSONA;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefonoFijo() {
        return telefonoFijo;
    }

    public void setTelefonoFijo(String telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }

    public String getFechaNaciemiento() {
        return fechaNaciemiento;
    }

    public void setFechaNaciemiento(String fechaNaciemiento) {
        this.fechaNaciemiento = fechaNaciemiento;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    @XmlTransient
    public List<Linea> getLineaList() {
        return lineaList;
    }

    public void setLineaList(List<Linea> lineaList) {
        this.lineaList = lineaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPERSONA != null ? idPERSONA.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Persona)) {
            return false;
        }
        Persona other = (Persona) object;
        if ((this.idPERSONA == null && other.idPERSONA != null) || (this.idPERSONA != null && !this.idPERSONA.equals(other.idPERSONA))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "conexion.Persona[ idPERSONA=" + idPERSONA + " ]";
    }
    
}

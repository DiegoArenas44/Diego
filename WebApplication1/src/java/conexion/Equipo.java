/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Aprendiz
 */
@Entity
@Table(name = "equipo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Equipo.findAll", query = "SELECT e FROM Equipo e"),
    @NamedQuery(name = "Equipo.findByIdEQUIPO", query = "SELECT e FROM Equipo e WHERE e.equipoPK.idEQUIPO = :idEQUIPO"),
    @NamedQuery(name = "Equipo.findByLINEAidLINEA", query = "SELECT e FROM Equipo e WHERE e.equipoPK.lINEAidLINEA = :lINEAidLINEA"),
    @NamedQuery(name = "Equipo.findByMarca", query = "SELECT e FROM Equipo e WHERE e.marca = :marca"),
    @NamedQuery(name = "Equipo.findByDescripcion", query = "SELECT e FROM Equipo e WHERE e.descripcion = :descripcion"),
    @NamedQuery(name = "Equipo.findByEstadoEquipo", query = "SELECT e FROM Equipo e WHERE e.estadoEquipo = :estadoEquipo")})
public class Equipo implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EquipoPK equipoPK;
    @Size(max = 45)
    @Column(name = "marca")
    private String marca;
    @Size(max = 45)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 45)
    @Column(name = "estado_equipo")
    private String estadoEquipo;
    @JoinColumn(name = "LINEA_idLINEA", referencedColumnName = "idLINEA", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Linea linea;

    public Equipo() {
    }

    public Equipo(EquipoPK equipoPK) {
        this.equipoPK = equipoPK;
    }

    public Equipo(int idEQUIPO, int lINEAidLINEA) {
        this.equipoPK = new EquipoPK(idEQUIPO, lINEAidLINEA);
    }

    public EquipoPK getEquipoPK() {
        return equipoPK;
    }

    public void setEquipoPK(EquipoPK equipoPK) {
        this.equipoPK = equipoPK;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstadoEquipo() {
        return estadoEquipo;
    }

    public void setEstadoEquipo(String estadoEquipo) {
        this.estadoEquipo = estadoEquipo;
    }

    public Linea getLinea() {
        return linea;
    }

    public void setLinea(Linea linea) {
        this.linea = linea;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (equipoPK != null ? equipoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Equipo)) {
            return false;
        }
        Equipo other = (Equipo) object;
        if ((this.equipoPK == null && other.equipoPK != null) || (this.equipoPK != null && !this.equipoPK.equals(other.equipoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "conexion.Equipo[ equipoPK=" + equipoPK + " ]";
    }
    
}

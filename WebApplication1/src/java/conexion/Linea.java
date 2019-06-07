/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Aprendiz
 */
@Entity
@Table(name = "linea")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Linea.findAll", query = "SELECT l FROM Linea l"),
    @NamedQuery(name = "Linea.findByIdLINEA", query = "SELECT l FROM Linea l WHERE l.lineaPK.idLINEA = :idLINEA"),
    @NamedQuery(name = "Linea.findByPERSONAidPERSONA", query = "SELECT l FROM Linea l WHERE l.lineaPK.pERSONAidPERSONA = :pERSONAidPERSONA"),
    @NamedQuery(name = "Linea.findByEstadoLinea", query = "SELECT l FROM Linea l WHERE l.estadoLinea = :estadoLinea")})
public class Linea implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LineaPK lineaPK;
    @Size(max = 45)
    @Column(name = "estado_linea")
    private String estadoLinea;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "linea", fetch = FetchType.LAZY)
    private List<Factura> facturaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "linea", fetch = FetchType.LAZY)
    private List<Equipo> equipoList;
    @JoinColumn(name = "PERSONA_idPERSONA", referencedColumnName = "idPERSONA", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Persona persona;

    public Linea() {
    }

    public Linea(LineaPK lineaPK) {
        this.lineaPK = lineaPK;
    }

    public Linea(int idLINEA, int pERSONAidPERSONA) {
        this.lineaPK = new LineaPK(idLINEA, pERSONAidPERSONA);
    }

    public LineaPK getLineaPK() {
        return lineaPK;
    }

    public void setLineaPK(LineaPK lineaPK) {
        this.lineaPK = lineaPK;
    }

    public String getEstadoLinea() {
        return estadoLinea;
    }

    public void setEstadoLinea(String estadoLinea) {
        this.estadoLinea = estadoLinea;
    }

    @XmlTransient
    public List<Factura> getFacturaList() {
        return facturaList;
    }

    public void setFacturaList(List<Factura> facturaList) {
        this.facturaList = facturaList;
    }

    @XmlTransient
    public List<Equipo> getEquipoList() {
        return equipoList;
    }

    public void setEquipoList(List<Equipo> equipoList) {
        this.equipoList = equipoList;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lineaPK != null ? lineaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Linea)) {
            return false;
        }
        Linea other = (Linea) object;
        if ((this.lineaPK == null && other.lineaPK != null) || (this.lineaPK != null && !this.lineaPK.equals(other.lineaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "conexion.Linea[ lineaPK=" + lineaPK + " ]";
    }
    
}

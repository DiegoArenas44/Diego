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
@Table(name = "factura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Factura.findAll", query = "SELECT f FROM Factura f"),
    @NamedQuery(name = "Factura.findByIdFACTURA", query = "SELECT f FROM Factura f WHERE f.facturaPK.idFACTURA = :idFACTURA"),
    @NamedQuery(name = "Factura.findByLINEAidLINEA", query = "SELECT f FROM Factura f WHERE f.facturaPK.lINEAidLINEA = :lINEAidLINEA"),
    @NamedQuery(name = "Factura.findByFechaEmision", query = "SELECT f FROM Factura f WHERE f.fechaEmision = :fechaEmision"),
    @NamedQuery(name = "Factura.findByValor", query = "SELECT f FROM Factura f WHERE f.valor = :valor")})
public class Factura implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected FacturaPK facturaPK;
    @Size(max = 45)
    @Column(name = "fecha_emision")
    private String fechaEmision;
    @Size(max = 45)
    @Column(name = "valor")
    private String valor;
    @JoinColumn(name = "LINEA_idLINEA", referencedColumnName = "idLINEA", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Linea linea;

    public Factura() {
    }

    public Factura(FacturaPK facturaPK) {
        this.facturaPK = facturaPK;
    }

    public Factura(int idFACTURA, int lINEAidLINEA) {
        this.facturaPK = new FacturaPK(idFACTURA, lINEAidLINEA);
    }

    public FacturaPK getFacturaPK() {
        return facturaPK;
    }

    public void setFacturaPK(FacturaPK facturaPK) {
        this.facturaPK = facturaPK;
    }

    public String getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
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
        hash += (facturaPK != null ? facturaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Factura)) {
            return false;
        }
        Factura other = (Factura) object;
        if ((this.facturaPK == null && other.facturaPK != null) || (this.facturaPK != null && !this.facturaPK.equals(other.facturaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "conexion.Factura[ facturaPK=" + facturaPK + " ]";
    }
    
}

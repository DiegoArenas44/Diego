/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Aprendiz
 */
@Embeddable
public class FacturaPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "idFACTURA")
    private int idFACTURA;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LINEA_idLINEA")
    private int lINEAidLINEA;

    public FacturaPK() {
    }

    public FacturaPK(int idFACTURA, int lINEAidLINEA) {
        this.idFACTURA = idFACTURA;
        this.lINEAidLINEA = lINEAidLINEA;
    }

    public int getIdFACTURA() {
        return idFACTURA;
    }

    public void setIdFACTURA(int idFACTURA) {
        this.idFACTURA = idFACTURA;
    }

    public int getLINEAidLINEA() {
        return lINEAidLINEA;
    }

    public void setLINEAidLINEA(int lINEAidLINEA) {
        this.lINEAidLINEA = lINEAidLINEA;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idFACTURA;
        hash += (int) lINEAidLINEA;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FacturaPK)) {
            return false;
        }
        FacturaPK other = (FacturaPK) object;
        if (this.idFACTURA != other.idFACTURA) {
            return false;
        }
        if (this.lINEAidLINEA != other.lINEAidLINEA) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "conexion.FacturaPK[ idFACTURA=" + idFACTURA + ", lINEAidLINEA=" + lINEAidLINEA + " ]";
    }
    
}

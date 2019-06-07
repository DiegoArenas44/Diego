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
public class EquipoPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "idEQUIPO")
    private int idEQUIPO;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LINEA_idLINEA")
    private int lINEAidLINEA;

    public EquipoPK() {
    }

    public EquipoPK(int idEQUIPO, int lINEAidLINEA) {
        this.idEQUIPO = idEQUIPO;
        this.lINEAidLINEA = lINEAidLINEA;
    }

    public int getIdEQUIPO() {
        return idEQUIPO;
    }

    public void setIdEQUIPO(int idEQUIPO) {
        this.idEQUIPO = idEQUIPO;
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
        hash += (int) idEQUIPO;
        hash += (int) lINEAidLINEA;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EquipoPK)) {
            return false;
        }
        EquipoPK other = (EquipoPK) object;
        if (this.idEQUIPO != other.idEQUIPO) {
            return false;
        }
        if (this.lINEAidLINEA != other.lINEAidLINEA) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "conexion.EquipoPK[ idEQUIPO=" + idEQUIPO + ", lINEAidLINEA=" + lINEAidLINEA + " ]";
    }
    
}

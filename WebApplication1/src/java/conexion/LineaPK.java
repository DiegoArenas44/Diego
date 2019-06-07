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
public class LineaPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "idLINEA")
    private int idLINEA;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PERSONA_idPERSONA")
    private int pERSONAidPERSONA;

    public LineaPK() {
    }

    public LineaPK(int idLINEA, int pERSONAidPERSONA) {
        this.idLINEA = idLINEA;
        this.pERSONAidPERSONA = pERSONAidPERSONA;
    }

    public int getIdLINEA() {
        return idLINEA;
    }

    public void setIdLINEA(int idLINEA) {
        this.idLINEA = idLINEA;
    }

    public int getPERSONAidPERSONA() {
        return pERSONAidPERSONA;
    }

    public void setPERSONAidPERSONA(int pERSONAidPERSONA) {
        this.pERSONAidPERSONA = pERSONAidPERSONA;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idLINEA;
        hash += (int) pERSONAidPERSONA;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LineaPK)) {
            return false;
        }
        LineaPK other = (LineaPK) object;
        if (this.idLINEA != other.idLINEA) {
            return false;
        }
        if (this.pERSONAidPERSONA != other.pERSONAidPERSONA) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "conexion.LineaPK[ idLINEA=" + idLINEA + ", pERSONAidPERSONA=" + pERSONAidPERSONA + " ]";
    }
    
}

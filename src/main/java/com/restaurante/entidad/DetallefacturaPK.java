/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.entidad;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author ingru
 */
@Embeddable
public class DetallefacturaPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDDETALLEFCTURA")
    private long iddetallefctura;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDFACTURA")
    private long idfactura;

    public DetallefacturaPK() {
    }

    public DetallefacturaPK(long iddetallefctura, long idfactura) {
        this.iddetallefctura = iddetallefctura;
        this.idfactura = idfactura;
    }

    public long getIddetallefctura() {
        return iddetallefctura;
    }

    public void setIddetallefctura(long iddetallefctura) {
        this.iddetallefctura = iddetallefctura;
    }

    public long getIdfactura() {
        return idfactura;
    }

    public void setIdfactura(long idfactura) {
        this.idfactura = idfactura;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) iddetallefctura;
        hash += (int) idfactura;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetallefacturaPK)) {
            return false;
        }
        DetallefacturaPK other = (DetallefacturaPK) object;
        if (this.iddetallefctura != other.iddetallefctura) {
            return false;
        }
        if (this.idfactura != other.idfactura) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.restaurante.entidad.DetallefacturaPK[ iddetallefctura=" + iddetallefctura + ", idfactura=" + idfactura + " ]";
    }
    
}

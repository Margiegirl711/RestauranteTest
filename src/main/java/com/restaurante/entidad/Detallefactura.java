/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.entidad;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ingru
 */
@Entity
@Table(name = "DETALLEFACTURA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detallefactura.findAll", query = "SELECT d FROM Detallefactura d"),
    @NamedQuery(name = "Detallefactura.findByIddetallefctura", query = "SELECT d FROM Detallefactura d WHERE d.detallefacturaPK.iddetallefctura = :iddetallefctura"),
    @NamedQuery(name = "Detallefactura.findByIdfactura", query = "SELECT d FROM Detallefactura d WHERE d.detallefacturaPK.idfactura = :idfactura"),
    @NamedQuery(name = "Detallefactura.findByPlato", query = "SELECT d FROM Detallefactura d WHERE d.plato = :plato"),
    @NamedQuery(name = "Detallefactura.findByImporte", query = "SELECT d FROM Detallefactura d WHERE d.importe = :importe")})
public class Detallefactura implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetallefacturaPK detallefacturaPK;
    @Size(max = 200)
    @Column(name = "PLATO")
    private String plato;
    @Column(name = "IMPORTE")
    private BigInteger importe;
    @JoinColumn(name = "IDCOCINERO", referencedColumnName = "IDCOCINERO")
    @ManyToOne
    private Cocinero idcocinero;
    @JoinColumn(name = "IDFACTURA", referencedColumnName = "IDFACTURA", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Factura factura;

    public Detallefactura() {
    }

    public Detallefactura(DetallefacturaPK detallefacturaPK) {
        this.detallefacturaPK = detallefacturaPK;
    }

    public Detallefactura(long iddetallefctura, long idfactura) {
        this.detallefacturaPK = new DetallefacturaPK(iddetallefctura, idfactura);
    }

    public DetallefacturaPK getDetallefacturaPK() {
        return detallefacturaPK;
    }

    public void setDetallefacturaPK(DetallefacturaPK detallefacturaPK) {
        this.detallefacturaPK = detallefacturaPK;
    }

    public String getPlato() {
        return plato;
    }

    public void setPlato(String plato) {
        this.plato = plato;
    }

    public BigInteger getImporte() {
        return importe;
    }

    public void setImporte(BigInteger importe) {
        this.importe = importe;
    }

    public Cocinero getIdcocinero() {
        return idcocinero;
    }

    public void setIdcocinero(Cocinero idcocinero) {
        this.idcocinero = idcocinero;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detallefacturaPK != null ? detallefacturaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detallefactura)) {
            return false;
        }
        Detallefactura other = (Detallefactura) object;
        if ((this.detallefacturaPK == null && other.detallefacturaPK != null) || (this.detallefacturaPK != null && !this.detallefacturaPK.equals(other.detallefacturaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.restaurante.entidad.Detallefactura[ detallefacturaPK=" + detallefacturaPK + " ]";
    }
    
}

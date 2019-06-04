/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.entidad;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
 * @author ingru
 */
@Entity
@Table(name = "MESA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mesa.findAll", query = "SELECT m FROM Mesa m"),
    @NamedQuery(name = "Mesa.findByIdmesa", query = "SELECT m FROM Mesa m WHERE m.idmesa = :idmesa"),
    @NamedQuery(name = "Mesa.findByNummaxcomensales", query = "SELECT m FROM Mesa m WHERE m.nummaxcomensales = :nummaxcomensales"),
    @NamedQuery(name = "Mesa.findByUbicacion", query = "SELECT m FROM Mesa m WHERE m.ubicacion = :ubicacion")})
public class Mesa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDMESA")
    private Long idmesa;
    @Column(name = "NUMMAXCOMENSALES")
    private BigInteger nummaxcomensales;
    @Size(max = 200)
    @Column(name = "UBICACION")
    private String ubicacion;
    @OneToMany(mappedBy = "idmesa")
    private Collection<Factura> facturaCollection;

    public Mesa() {
    }

    public Mesa(Long idmesa) {
        this.idmesa = idmesa;
    }

    public Long getIdmesa() {
        return idmesa;
    }

    public void setIdmesa(Long idmesa) {
        this.idmesa = idmesa;
    }

    public BigInteger getNummaxcomensales() {
        return nummaxcomensales;
    }

    public void setNummaxcomensales(BigInteger nummaxcomensales) {
        this.nummaxcomensales = nummaxcomensales;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    @XmlTransient
    public Collection<Factura> getFacturaCollection() {
        return facturaCollection;
    }

    public void setFacturaCollection(Collection<Factura> facturaCollection) {
        this.facturaCollection = facturaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmesa != null ? idmesa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mesa)) {
            return false;
        }
        Mesa other = (Mesa) object;
        if ((this.idmesa == null && other.idmesa != null) || (this.idmesa != null && !this.idmesa.equals(other.idmesa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.getUbicacion()+"[" + idmesa + "]"+this.getNummaxcomensales();
    }
    
}

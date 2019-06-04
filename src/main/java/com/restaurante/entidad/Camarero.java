/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.entidad;

import java.io.Serializable;
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
@Table(name = "CAMARERO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Camarero.findAll", query = "SELECT c FROM Camarero c"),
    @NamedQuery(name = "Camarero.findByIdcamarero", query = "SELECT c FROM Camarero c WHERE c.idcamarero = :idcamarero"),
    @NamedQuery(name = "Camarero.findByNombre", query = "SELECT c FROM Camarero c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Camarero.findByApellido1", query = "SELECT c FROM Camarero c WHERE c.apellido1 = :apellido1"),
    @NamedQuery(name = "Camarero.findByApellido2", query = "SELECT c FROM Camarero c WHERE c.apellido2 = :apellido2")})
public class Camarero implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDCAMARERO")
    private Long idcamarero;
    @Size(max = 200)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 200)
    @Column(name = "APELLIDO1")
    private String apellido1;
    @Size(max = 200)
    @Column(name = "APELLIDO2")
    private String apellido2;
    @OneToMany(mappedBy = "idcamarero")
    private Collection<Factura> facturaCollection;

    public Camarero() {
    }

    public Camarero(Long idcamarero) {
        this.idcamarero = idcamarero;
    }

    public Long getIdcamarero() {
        return idcamarero;
    }

    public void setIdcamarero(Long idcamarero) {
        this.idcamarero = idcamarero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
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
        hash += (idcamarero != null ? idcamarero.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Camarero)) {
            return false;
        }
        Camarero other = (Camarero) object;
        if ((this.idcamarero == null && other.idcamarero != null) || (this.idcamarero != null && !this.idcamarero.equals(other.idcamarero))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.nombre+" "+this.apellido1+"[ idcamarero=" + idcamarero + " ]";
    }
    
}

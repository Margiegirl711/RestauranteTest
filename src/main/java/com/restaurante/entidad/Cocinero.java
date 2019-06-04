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
@Table(name = "COCINERO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cocinero.findAll", query = "SELECT c FROM Cocinero c"),
    @NamedQuery(name = "Cocinero.findByIdcocinero", query = "SELECT c FROM Cocinero c WHERE c.idcocinero = :idcocinero"),
    @NamedQuery(name = "Cocinero.findByNombre", query = "SELECT c FROM Cocinero c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Cocinero.findByApellido1", query = "SELECT c FROM Cocinero c WHERE c.apellido1 = :apellido1"),
    @NamedQuery(name = "Cocinero.findByColumn2", query = "SELECT c FROM Cocinero c WHERE c.column2 = :column2")})
public class Cocinero implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDCOCINERO")
    private Long idcocinero;
    @Size(max = 200)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 200)
    @Column(name = "APELLIDO1")
    private String apellido1;
    @Size(max = 200)
    @Column(name = "COLUMN2")
    private String column2;
    @OneToMany(mappedBy = "idcocinero")
    private Collection<Detallefactura> detallefacturaCollection;

    public Cocinero() {
    }

    public Cocinero(Long idcocinero) {
        this.idcocinero = idcocinero;
    }

    public Long getIdcocinero() {
        return idcocinero;
    }

    public void setIdcocinero(Long idcocinero) {
        this.idcocinero = idcocinero;
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

    public String getColumn2() {
        return column2;
    }

    public void setColumn2(String column2) {
        this.column2 = column2;
    }

    @XmlTransient
    public Collection<Detallefactura> getDetallefacturaCollection() {
        return detallefacturaCollection;
    }

    public void setDetallefacturaCollection(Collection<Detallefactura> detallefacturaCollection) {
        this.detallefacturaCollection = detallefacturaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcocinero != null ? idcocinero.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cocinero)) {
            return false;
        }
        Cocinero other = (Cocinero) object;
        if ((this.idcocinero == null && other.idcocinero != null) || (this.idcocinero != null && !this.idcocinero.equals(other.idcocinero))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.nombre+" "+this.apellido1+"[" + idcocinero + "]";
    }
    
}

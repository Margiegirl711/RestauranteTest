/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.entidad;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "CAMARERO_FACTURA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CamareroFactura.findAll", query = "SELECT c FROM CamareroFactura c"),
    @NamedQuery(name = "CamareroFactura.findById", query = "SELECT c FROM CamareroFactura c WHERE c.id = :id"),
    @NamedQuery(name = "CamareroFactura.findByContador", query = "SELECT c FROM CamareroFactura c WHERE c.contador = :contador"),
    @NamedQuery(name = "CamareroFactura.findByNombre", query = "SELECT c FROM CamareroFactura c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CamareroFactura.findByApellido", query = "SELECT c FROM CamareroFactura c WHERE c.apellido = :apellido"),
    @NamedQuery(name = "CamareroFactura.findByTotal", query = "SELECT c FROM CamareroFactura c WHERE c.total = :total"),
    @NamedQuery(name = "CamareroFactura.findByMes", query = "SELECT c FROM CamareroFactura c WHERE c.mes = :mes")})
public class CamareroFactura implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "ID")
    @Id
    private Long id;
    @Column(name = "CONTADOR")
    private BigInteger contador;
    @Size(max = 200)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 200)
    @Column(name = "APELLIDO")
    private String apellido;
    @Column(name = "TOTAL")
    private BigInteger total;
    @Size(max = 10)
    @Column(name = "MES")
    private String mes;

    public CamareroFactura() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigInteger getContador() {
        return contador;
    }

    public void setContador(BigInteger contador) {
        this.contador = contador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public BigInteger getTotal() {
        return total;
    }

    public void setTotal(BigInteger total) {
        this.total = total;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }
    
}

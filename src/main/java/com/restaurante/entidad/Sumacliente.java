/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.entidad;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ingru
 */
@Entity
@Table(name = "SUMACLIENTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sumacliente.findAll", query = "SELECT s FROM Sumacliente s"),
    @NamedQuery(name = "Sumacliente.findByIdcliente", query = "SELECT s FROM Sumacliente s WHERE s.idcliente = :idcliente"),
    @NamedQuery(name = "Sumacliente.findByNombre", query = "SELECT s FROM Sumacliente s WHERE s.nombre = :nombre"),
    @NamedQuery(name = "Sumacliente.findByApellido1", query = "SELECT s FROM Sumacliente s WHERE s.apellido1 = :apellido1"),
    @NamedQuery(name = "Sumacliente.findBySuma", query = "SELECT s FROM Sumacliente s WHERE s.suma = :suma")})
public class Sumacliente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDCLIENTE")
    @Id
    private long idcliente;
    @Size(max = 200)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 200)
    @Column(name = "APELLIDO1")
    private String apellido1;
    @Column(name = "SUMA")
    private BigInteger suma;

    public Sumacliente() {
    }

    public long getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(long idcliente) {
        this.idcliente = idcliente;
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

    public BigInteger getSuma() {
        return suma;
    }

    public void setSuma(BigInteger suma) {
        this.suma = suma;
    }
    
}

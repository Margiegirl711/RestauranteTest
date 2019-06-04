/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurante.beans;

import com.restaurante.entidad.Sumacliente;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ingru
 */
@Stateless
public class SumaclienteFacade extends AbstractFacade<Sumacliente> {
    @PersistenceContext(unitName = "com.restaurante_Restaurante_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SumaclienteFacade() {
        super(Sumacliente.class);
    }
    
}

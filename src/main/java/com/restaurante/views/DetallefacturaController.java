package com.restaurante.views;

import com.restaurante.entidad.Detallefactura;
import com.restaurante.views.util.JsfUtil;
import com.restaurante.views.util.PaginationHelper;
import com.restaurante.beans.DetallefacturaFacade;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

@Named("detallefacturaController")
@SessionScoped
public class DetallefacturaController implements Serializable {

    private Detallefactura current;
    private DataModel items = null;
    @EJB
    private com.restaurante.beans.DetallefacturaFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public DetallefacturaController() {
    }

    public Detallefactura getSelected() {
        if (current == null) {
            current = new Detallefactura();
            current.setDetallefacturaPK(new com.restaurante.entidad.DetallefacturaPK());
            selectedItemIndex = -1;
        }
        return current;
    }

    private DetallefacturaFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (Detallefactura) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Detallefactura();
        current.setDetallefacturaPK(new com.restaurante.entidad.DetallefacturaPK());
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            current.getDetallefacturaPK().setIdfactura(current.getFactura().getIdfactura());
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("DetallefacturaCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Detallefactura) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            current.getDetallefacturaPK().setIdfactura(current.getFactura().getIdfactura());
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("DetallefacturaUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Detallefactura) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("DetallefacturaDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public Detallefactura getDetallefactura(com.restaurante.entidad.DetallefacturaPK id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Detallefactura.class)
    public static class DetallefacturaControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DetallefacturaController controller = (DetallefacturaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "detallefacturaController");
            return controller.getDetallefactura(getKey(value));
        }

        com.restaurante.entidad.DetallefacturaPK getKey(String value) {
            com.restaurante.entidad.DetallefacturaPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new com.restaurante.entidad.DetallefacturaPK();
            key.setIddetallefctura(Long.parseLong(values[0]));
            key.setIdfactura(Long.parseLong(values[1]));
            return key;
        }

        String getStringKey(com.restaurante.entidad.DetallefacturaPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getIddetallefctura());
            sb.append(SEPARATOR);
            sb.append(value.getIdfactura());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Detallefactura) {
                Detallefactura o = (Detallefactura) object;
                return getStringKey(o.getDetallefacturaPK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Detallefactura.class.getName());
            }
        }

    }

}

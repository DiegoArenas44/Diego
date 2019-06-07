package conexion;

import conexion.util.JsfUtil;
import conexion.util.PaginationHelper;
import facade.LineaFacade;

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

@Named("lineaController")
@SessionScoped
public class LineaController implements Serializable {

    private Linea current;
    private DataModel items = null;
    @EJB
    private facade.LineaFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public LineaController() {
    }

    public Linea getSelected() {
        if (current == null) {
            current = new Linea();
            current.setLineaPK(new conexion.LineaPK());
            selectedItemIndex = -1;
        }
        return current;
    }

    private LineaFacade getFacade() {
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
        current = (Linea) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Linea();
        current.setLineaPK(new conexion.LineaPK());
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            current.getLineaPK().setPERSONAidPERSONA(current.getPersona().getIdPERSONA());
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("LineaCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Linea) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            current.getLineaPK().setPERSONAidPERSONA(current.getPersona().getIdPERSONA());
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("LineaUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Linea) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("LineaDeleted"));
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

    public Linea getLinea(conexion.LineaPK id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Linea.class)
    public static class LineaControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            LineaController controller = (LineaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "lineaController");
            return controller.getLinea(getKey(value));
        }

        conexion.LineaPK getKey(String value) {
            conexion.LineaPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new conexion.LineaPK();
            key.setIdLINEA(Integer.parseInt(values[0]));
            key.setPERSONAidPERSONA(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(conexion.LineaPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getIdLINEA());
            sb.append(SEPARATOR);
            sb.append(value.getPERSONAidPERSONA());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Linea) {
                Linea o = (Linea) object;
                return getStringKey(o.getLineaPK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Linea.class.getName());
            }
        }

    }

}

package conexion;

import conexion.util.JsfUtil;
import conexion.util.PaginationHelper;
import facade.EquipoFacade;

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

@Named("equipoController")
@SessionScoped
public class EquipoController implements Serializable {

    private Equipo current;
    private DataModel items = null;
    @EJB
    private facade.EquipoFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public EquipoController() {
    }

    public Equipo getSelected() {
        if (current == null) {
            current = new Equipo();
            current.setEquipoPK(new conexion.EquipoPK());
            selectedItemIndex = -1;
        }
        return current;
    }

    private EquipoFacade getFacade() {
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
        current = (Equipo) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Equipo();
        current.setEquipoPK(new conexion.EquipoPK());
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            current.getEquipoPK().setLINEAidLINEA(current.getLinea().getLineaPK().getIdLINEA());
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("EquipoCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Equipo) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            current.getEquipoPK().setLINEAidLINEA(current.getLinea().getLineaPK().getIdLINEA());
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("EquipoUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Equipo) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("EquipoDeleted"));
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

    public Equipo getEquipo(conexion.EquipoPK id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Equipo.class)
    public static class EquipoControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EquipoController controller = (EquipoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "equipoController");
            return controller.getEquipo(getKey(value));
        }

        conexion.EquipoPK getKey(String value) {
            conexion.EquipoPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new conexion.EquipoPK();
            key.setIdEQUIPO(Integer.parseInt(values[0]));
            key.setLINEAidLINEA(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(conexion.EquipoPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getIdEQUIPO());
            sb.append(SEPARATOR);
            sb.append(value.getLINEAidLINEA());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Equipo) {
                Equipo o = (Equipo) object;
                return getStringKey(o.getEquipoPK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Equipo.class.getName());
            }
        }

    }

}

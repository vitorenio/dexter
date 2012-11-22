package br.furb.extbuilder.ui.component;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;


public class Panel extends Component implements IPropertySource{

    private List<Panel> children;
    private Panel parent;
    
    private String height = "";
    private String width = "";
    
    private IPropertyDescriptor[] properties = {
    		new TextPropertyDescriptor( "id", "Id"),
    		new TextPropertyDescriptor("name", "Name"),
    		new TextPropertyDescriptor("height", "Height"),
    		new TextPropertyDescriptor("width", "Width"),
    };
    
    public Panel(){
        children = new ArrayList<Panel>();
    }
    public List<Panel> getChildren() {
        return children;
    }
    
    public void addChild(Panel child) {
        child.setParent(this);
        children.add(child);
    }

    
    public Panel getParent() {
        return parent;
    }

    
    public void setParent(Panel parent) {
        this.parent = parent;
    }
    


    @Override
    public Object getEditableValue() {
        return this;
    }
    @Override
    public IPropertyDescriptor[] getPropertyDescriptors() {        
        return properties;
    }
    @Override
    public Object getPropertyValue(Object id) {
        if ("id".equals(id)){
            return getId();
        }else if("name".equals(id)){
        	return getName();
        }else if("height".equals(id)){
        	return height;
        }else if("width".equals(id)){
        	return width;
        }
        return "-vazio-";
    }
    @Override
    public boolean isPropertySet(Object id) {
        return true;
    }
    @Override
    public void resetPropertyValue(Object id) {
        setName("");      
        setId("");
        this.height = "";
        this.width = "";
        
    }
    @Override
    public void setPropertyValue(Object id, Object value) {
    	if ("id".equals(id)){
    		 setId((String) value);
        }else if("name".equals(id)){
        	 setName((String) value);
        }else if("height".equals(id)){
        	this.height = (String) value;
        }else if("width".equals(id)){
        	this.width = (String) value;
        }

    }

}

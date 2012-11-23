package br.furb.extbuilder.ui.component;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

import br.furb.extbuilder.core.ext.ExtPanel;


public class Panel extends ExtPanel implements IPropertySource{

    private List<Panel> children;
    private Panel parent;
    
    private IPropertyDescriptor[] properties = {
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
    	if("name".equals(id)){
        	return getName();
        }else if("height".equals(id)){
        	return getHeight();
        }else if("width".equals(id)){
        	return getWidth();
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
        setHeight("");
        this.setWidth("");
        
    }
    @Override
    public void setPropertyValue(Object id, Object value) {
    	if("name".equals(id)){
        	 setName((String) value);
        }else if("height".equals(id)){
        	setHeight((String) value);
        }else if("width".equals(id)){
        	setWidth((String) value);
        }

    }

}

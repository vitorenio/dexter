package br.furb.extbuilder.ui.component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;

import com.google.gson.annotations.SerializedName;

import br.furb.extbuilder.core.Conversible;


public abstract class Component  implements IPropertySource{
    
	@SerializedName("items")
    private List<Component> children;
    private Component parent;
	
    private String name = "";

    public Component(){
    	children = new ArrayList<Component>();
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

	public <T extends Component> void setParent(T component) {
		this.parent = component;
		
	}
    public List<Component> getChildren() {
        return children;
    }
    
    public void addChild(Component child) {
        child.setParent(this);
        children.add(child);
    }

    
    public Component getParent() {
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
    public abstract IPropertyDescriptor[] getPropertyDescriptors();
    @Override
    public abstract Object getPropertyValue(Object id) ;
    @Override
    public boolean isPropertySet(Object id) {
        return true;
    }
    @Override
    public abstract void resetPropertyValue(Object id);
    @Override
    public abstract void setPropertyValue(Object id, Object value);
} 

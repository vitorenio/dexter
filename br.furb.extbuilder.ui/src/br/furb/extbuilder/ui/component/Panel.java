package br.furb.extbuilder.ui.component;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

import br.furb.extbuilder.core.ext.ExtPanel;


public class Panel extends Component{

    private String height = "";
    private String width = "";
    private String title = "";
	private String renderTo;

	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}


	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getRenderTo() {
		return renderTo;
	}
	public void setRenderTo(String renderTo) {
		this.renderTo = renderTo;
	}
	
    @Override
    public Object getEditableValue() {
        return this;
    }
    @Override
    public IPropertyDescriptor[] getPropertyDescriptors() {        
        return new IPropertyDescriptor[]{
        		new TextPropertyDescriptor("name", "Name"),
        		new TextPropertyDescriptor("height", "Height"),
        		new TextPropertyDescriptor("width", "Width"),
        };
    }
    @Override
    public Object getPropertyValue(Object id) {
    	if("name".equals(id)){
        	return getName();
        }else if("height".equals(id)){
        	return getHeight();
        }else if("width".equals(id)){
        	return getWidth();
        }else if ("title".equals(id)){
        	return getTitle();
        }else if ("renderTo".equals(id)){
        	return getRenderTo();
        }
        return "-vazio-";
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
        }else if ("title".equals(id)){
        	setTitle((String) value);
        }else if ("renderTo".equals(id)){
        	setRenderTo((String) value);
        }

    }
	
}

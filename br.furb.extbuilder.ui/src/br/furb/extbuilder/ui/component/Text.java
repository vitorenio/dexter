package br.furb.extbuilder.ui.component;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

public class Text extends Component {
	
	private String xtype = "textfield";
	private String fieldLabel;
	private boolean allowBlank;
	
	public String getXtype() {
		return xtype;
	}
	public void setXtype(String xtype) {
		this.xtype = xtype;
	}
	public String getFieldLabel() {
		return fieldLabel;
	}
	public void setFieldLabel(String fieldLabel) {
		this.fieldLabel = fieldLabel;
	}
	public boolean isAllowBlank() {
		return allowBlank;
	}
	public void setAllowBlank(boolean allowBlank) {
		this.allowBlank = allowBlank;
	}
	
    @Override
    public Object getEditableValue() {
        return this;
    }
    @Override
    public IPropertyDescriptor[] getPropertyDescriptors() {        
        return new IPropertyDescriptor[]{
        		new TextPropertyDescriptor("name", "Name"),
        		new TextPropertyDescriptor("xtype", "xtype"),
        		new TextPropertyDescriptor("fieldLabel", "fieldLabel"),
        		new TextPropertyDescriptor("allowBlank", "allowBlank"),
        };
    }
    @Override
    public Object getPropertyValue(Object id) {
    	if("name".equals(id)){
        	return getName();
        }else if("xtype".equals(id)){
        	return getXtype();
        }else if("fieldLabel".equals(id)){
        	return getFieldLabel();
        }else if ("allowBlank".equals(id)){
        	return isAllowBlank();
        }
        return "-vazio-";
    }

    @Override
    public void resetPropertyValue(Object id) {
        setName("");      
        setXtype("");
        setFieldLabel("");
        setAllowBlank(false);
        
    }
    @Override
    public void setPropertyValue(Object id, Object value) {
    	if("name".equals(id)){
        	 setName((String) value);
        }else if("xtype".equals(id)){
        	setXtype((String) value);
        }else if("fieldLabel".equals(id)){
        	setFieldLabel((String) value);
        }else if ("allowBlank".equals(id)){
        	setAllowBlank((Boolean) value);
        }else{
        	throw new IllegalArgumentException("Propriedade " + id + " nao é suportada pelo Text.");
        }

    }
}

package br.furb.extbuilder.ui.component;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

public class Button extends Component{

	private String text;
	private String handler;
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getHandler() {
		return handler;
	}
	public void setHandler(String handler) {
		this.handler = handler;
	}
	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		return new IPropertyDescriptor[]{
        		new TextPropertyDescriptor("name", "Name"),
        		new TextPropertyDescriptor("text", "Text"),
        		new TextPropertyDescriptor("handler", "handler"),
        };
	}
	@Override
	public Object getPropertyValue(Object id) {
    	if("name".equals(id)){
        	return getName();
        }else if("text".equals(id)){
        	return getText();
        }else if("handler".equals(id)){
        	return getHandler();
        }
        return "-vazio-";	}
	@Override
	public void resetPropertyValue(Object id) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setPropertyValue(Object id, Object value) {
		if("name".equals(id)){
       	 setName((String) value);
       }else if("text".equals(id)){
       	setText((String) value);
       }else if("handler".equals(id)){
       	setHandler((String) value);
       }
	}
}

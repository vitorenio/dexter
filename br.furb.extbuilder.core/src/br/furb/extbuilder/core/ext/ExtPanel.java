package br.furb.extbuilder.core.ext;

public class ExtPanel extends ExtComponent{

    private String height = "";
    private String width = "";
    
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
	
	@Override
	public String convert() {
		return "Ext.newButton();";
	}
	
}

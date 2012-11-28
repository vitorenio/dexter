package br.furb.extbuilder.ui.component;

import org.eclipse.ui.views.properties.IPropertyDescriptor;

public class Column extends Component{
	private String text;
	private int flex;
	private String dataIndex;
	private String xtype;
	private String format;
	private String align;
	private String cls;
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getFlex() {
		return flex;
	}
	public void setFlex(int flex) {
		this.flex = flex;
	}
	public String getDataIndex() {
		return dataIndex;
	}
	public void setDataIndex(String dataIndex) {
		this.dataIndex = dataIndex;
	}
	public String getXtype() {
		return xtype;
	}
	public void setXtype(String xtype) {
		this.xtype = xtype;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getAlign() {
		return align;
	}
	public void setAlign(String align) {
		this.align = align;
	}
	public String getCls() {
		return cls;
	}
	public void setCls(String cls) {
		this.cls = cls;
	}
	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object getPropertyValue(Object id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void resetPropertyValue(Object id) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setPropertyValue(Object id, Object value) {
		// TODO Auto-generated method stub
		
	}
	
	
/**
        text: 'File',
        flex: 50,
        dataIndex: 'name'

        xtype: 'datecolumn',
        format: 'm-d h:i a',
        align: 'right',
        cls: 'listview-filesize'
*/
}

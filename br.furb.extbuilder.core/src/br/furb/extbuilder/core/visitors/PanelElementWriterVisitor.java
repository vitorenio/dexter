package br.furb.extbuilder.core.visitors;

public class PanelElementWriterVisitor implements PanelElementVisitor {

	@Override
	public void visit(PanelElement panel) {
		System.out.println("var panel = Ext.create();");
	}

}

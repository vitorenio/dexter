package br.furb.extbuilder.core.ext;

import br.furb.extbuilder.core.Conversible;

public abstract class ExtComponent implements Conversible {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

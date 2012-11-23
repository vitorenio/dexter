package br.furb.extbuilder.ui.component;

import java.util.HashMap;
import java.util.Map;

import br.furb.extbuilder.core.Conversible;


public abstract class Component<T extends Conversible> {
    
	/**
	 * converte o componente e sua arvore em um objeto JS ExtJS
	 */
    protected T componentConverter;
	
    private String id = "";
    private String name = "";

    public Component(){
        //propertyMap = new HashMap<T, String>();
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
	public String convert() {
		return componentConverter.convert();
	}

    
} 

package br.furb.extbuilder.ui.component;

import java.util.HashMap;
import java.util.Map;


public abstract class Component {
    
//    private Map<T, String> propertyMap;

    
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
    
    
} 

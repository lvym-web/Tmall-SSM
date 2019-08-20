package com.lvym.beans;

import java.util.List;

public class Category {
    private Integer id;

    private String name;

    private List<Product> products;
    
    private List<Property> properties;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public List<Property> getProperties() {
		return properties;
	}

	public void setProperties(List<Property> properties) {
		this.properties = properties;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", products="
				+ products + ", properties=" + properties + "]";
	}
    
    
}
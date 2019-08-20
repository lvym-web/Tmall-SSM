package com.lvym.beans;


public class Orderitem {
    private Integer id;

    private Integer pid;

    private Integer oid;

    private Integer uid;

    private Integer number;
    
    private Integer mark;
    
    private Double total;
    
    private Product product;
    
    private Order orders;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Order getOrders() {
		return orders;
	}

	public void setOrders(Order orders) {
		this.orders = orders;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Integer getMark() {
		return mark;
	}

	public void setMark(Integer mark) {
		this.mark = mark;
	}

	@Override
	public String toString() {
		return "Orderitem [id=" + id + ", pid=" + pid + ", oid=" + oid
				+ ", uid=" + uid + ", number=" + number + ", mark=" + mark
				+ ", total=" + total + ", product=" + product + ", orders="
				+ orders + "]";
	}

	

	

}
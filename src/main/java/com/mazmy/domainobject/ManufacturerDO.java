package com.mazmy.domainobject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(
	name="manufacturer",
	uniqueConstraints = @UniqueConstraint(name = "uc_manufacturer", columnNames = {"manufacturer"})
)
public class ManufacturerDO extends BasicDo{
	
	@Column(nullable = false)
    @NotNull(message = "manufacturer can not be null!")
    private String manufacturer;

	protected ManufacturerDO(){
	}

	public ManufacturerDO(String manufacturer) {
		super();
		this.manufacturer = manufacturer;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	
}

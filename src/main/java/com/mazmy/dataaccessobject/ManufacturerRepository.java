package com.mazmy.dataaccessobject;

import org.springframework.data.repository.CrudRepository;

import com.mazmy.domainobject.ManufacturerDO;

/**
 * @author azmym
 * Database Access Object for Manufacturer table.
 */
public interface ManufacturerRepository extends CrudRepository<ManufacturerDO, Long>{

	ManufacturerDO  findByManufacturer (String manufacturer);
}

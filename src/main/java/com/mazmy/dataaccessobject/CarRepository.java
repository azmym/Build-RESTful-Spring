package com.mazmy.dataaccessobject;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mazmy.domainobject.CarDO;

/**
 * @author azmym
 * Database Access Object for car table.
 */
public interface CarRepository extends CrudRepository<CarDO, Long>{

	List<CarDO> findAllByEngineType(String engineType);
	
	List<CarDO> findAllByConvertible(boolean convertible);
	
	List<CarDO> findByManufacturerManufacturerLikeIgnoreCase(String manufacturer);
}

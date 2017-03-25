package com.mazmy.service.car;

import java.util.List;

import com.mazmy.domainobject.CarDO;
import com.mazmy.exception.ConstraintsViolationException;
import com.mazmy.exception.EntityNotFoundException;

public interface CarService {
	
	CarDO find (Long carId) throws EntityNotFoundException;

	CarDO create(CarDO carDO) throws ConstraintsViolationException;
	
	void delete(Long carId) throws EntityNotFoundException;
	
	List<CarDO> findByConvertible(boolean convertible);
	
	List<CarDO> findByManufacturer(String manufacturer);
	
	List<CarDO> findByEngineType(String engineType);
	
}

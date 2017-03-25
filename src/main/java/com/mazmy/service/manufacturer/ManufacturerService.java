package com.mazmy.service.manufacturer;

import com.mazmy.domainobject.ManufacturerDO;
import com.mazmy.exception.ConstraintsViolationException;
import com.mazmy.exception.EntityNotFoundException;

public interface ManufacturerService {

	ManufacturerDO find (Long manufacturerId) throws EntityNotFoundException;
	
	ManufacturerDO findManufacturerName (String manufacturerName) throws EntityNotFoundException;
	
	ManufacturerDO create(ManufacturerDO manufacturerDO) throws ConstraintsViolationException;
}

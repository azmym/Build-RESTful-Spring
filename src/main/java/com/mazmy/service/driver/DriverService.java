package com.mazmy.service.driver;

import java.util.List;

import com.mazmy.domainobject.CarDO;
import com.mazmy.domainobject.DriverDO;
import com.mazmy.domainvalue.OnlineStatus;
import com.mazmy.exception.CarAlreadyInUseException;
import com.mazmy.exception.ConstraintsViolationException;
import com.mazmy.exception.DriverOfflineException;
import com.mazmy.exception.EntityNotFoundException;

public interface DriverService
{

    DriverDO find(Long driverId) throws EntityNotFoundException;

    DriverDO create(DriverDO driverDO) throws ConstraintsViolationException;

    void delete(Long driverId) throws EntityNotFoundException;

    void updateLocation(long driverId, double longitude, double latitude) throws EntityNotFoundException;

    List<DriverDO> find(OnlineStatus onlineStatus);

	void selectCar(long driverId, CarDO carDO) throws EntityNotFoundException, DriverOfflineException, CarAlreadyInUseException;

	void deSelectCar(long driverId) throws EntityNotFoundException;
	
	DriverDO findByUsername (String username);
	
	public List<DriverDO> filter(CarDO carDO);

}

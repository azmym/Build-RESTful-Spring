package com.mazmy.service.driver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mazmy.SpringBaseTest;
import com.mazmy.domainobject.CarDO;
import com.mazmy.domainobject.DriverDO;
import com.mazmy.exception.CarAlreadyInUseException;
import com.mazmy.exception.DriverOfflineException;
import com.mazmy.exception.EntityNotFoundException;
import com.mazmy.service.car.CarService;

public class DriverServiceTest extends SpringBaseTest{

	@Autowired
	private DriverService driverService;
	@Autowired
	private CarService carService;
	
	@Test
	public void testFindByUsername() {
		String driverName = "driver06";
		DriverDO findByUsername = driverService.findByUsername(driverName);
		assertNotNull(findByUsername);
		driverName = "test";
		findByUsername = driverService.findByUsername(driverName);
		assertNull(findByUsername);
	}
	
	@Test
	public void testSelectCar() throws EntityNotFoundException, DriverOfflineException, CarAlreadyInUseException {
		CarDO carDO = carService.find(1L);
		long driverId = 6;
		driverService.selectCar(driverId, carDO);
		DriverDO find = driverService.find(driverId);
		assertNotNull(find.getCar());
	}
	
	@Test(expected = DriverOfflineException.class)
	public void testSelectCar1() throws EntityNotFoundException, DriverOfflineException, CarAlreadyInUseException {
		CarDO carDO = carService.find(1L);
		long driverId = 1;
		driverService.selectCar(driverId, carDO);
	}
	
	@Test(expected = EntityNotFoundException.class)
	public void testSelectCar2() throws EntityNotFoundException, DriverOfflineException, CarAlreadyInUseException {
		CarDO carDO = carService.find(1L);
		long driverId = 0;
		driverService.selectCar(driverId, carDO);
	}
	
	@Test(expected = CarAlreadyInUseException.class)
	public void testSelectCar3() throws EntityNotFoundException, DriverOfflineException, CarAlreadyInUseException {
		CarDO carDO = carService.find(1L);
		long driverId = 4;
		driverService.selectCar(driverId, carDO);
		driverService.selectCar(5, carDO);
	}

	@Test
	public void testDeSelectCar () throws EntityNotFoundException{
		long driverId = 6;
		driverService.deSelectCar(driverId);
		DriverDO find = driverService.find(driverId);
		assertNull(find.getCar());
	}
	
	@Test
	public void testFilter() throws EntityNotFoundException, DriverOfflineException, CarAlreadyInUseException{
		CarDO carDO = carService.find(2L);
		long driverId = 4;
		driverService.selectCar(driverId, carDO);
		List<DriverDO> filter = driverService.filter(carDO);
		assertEquals(Integer.valueOf(1), Integer.valueOf(filter.size()));
	}
}

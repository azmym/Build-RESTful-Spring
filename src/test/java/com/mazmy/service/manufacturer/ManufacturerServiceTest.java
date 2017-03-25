package com.mazmy.service.manufacturer;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mazmy.SpringBaseTest;
import com.mazmy.domainobject.ManufacturerDO;
import com.mazmy.exception.ConstraintsViolationException;
import com.mazmy.exception.EntityNotFoundException;

public class ManufacturerServiceTest extends SpringBaseTest{

	@Autowired
	private ManufacturerService manufacturerService;
	
	@Test
	public void testFind() throws EntityNotFoundException {
		long carId = 1;
		ManufacturerDO find = manufacturerService.find(carId);
		assertNotNull(find);
	}
	
	@Test(expected = EntityNotFoundException.class)
	public void testFindExc() throws EntityNotFoundException {
		long carId = 15;
		manufacturerService.find(carId);
	}

	@Test
	public void testFindCarChecked() throws EntityNotFoundException {
		long carId = 1;
		ManufacturerDO find = manufacturerService.find(carId);
		assertNotNull(find);
	}
	
	@Test(expected = EntityNotFoundException.class)
	public void testFindCarCheckedExc() throws EntityNotFoundException {
		long carId = 15;
		manufacturerService.find(carId);
	}
	
	@Test
	public void testFindManufacturerName() throws EntityNotFoundException{
		String manufacturerContain = "Mercedes-Benz";
		ManufacturerDO findByManufacturer = manufacturerService.findManufacturerName(manufacturerContain);
		assertNotNull(findByManufacturer);
	}
	
	@Test(expected = EntityNotFoundException.class)
	public void testFindManufacturerNameEx() throws EntityNotFoundException{
		String manufacturerContain = "Ford";
		manufacturerService.findManufacturerName(manufacturerContain);
	}
	
	@Test
	public void testFindManufacturerChecked() throws EntityNotFoundException{
		String manufacturerContain = "Mercedes-Benz";
		ManufacturerDO findByManufacturer = manufacturerService.findManufacturerName(manufacturerContain);
		assertNotNull(findByManufacturer);
	}
	
	@Test(expected = EntityNotFoundException.class)
	public void testFindManufacturerCheckedEx() throws EntityNotFoundException{
		String manufacturerContain = "Ford";
		manufacturerService.findManufacturerName(manufacturerContain);
	}
	
	@Test(expected = ConstraintsViolationException.class)
	public void testCreateEx() throws ConstraintsViolationException, EntityNotFoundException{
		ManufacturerDO manufacturerDO = manufacturerService.find(1L);
		manufacturerDO.setManufacturer("BMW");
		manufacturerService.create(manufacturerDO);
	}
}

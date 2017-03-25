package com.mazmy.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mazmy.controller.mapper.CarMapper;
import com.mazmy.datatransferobject.CarDTO;
import com.mazmy.domainobject.CarDO;
import com.mazmy.domainobject.ManufacturerDO;
import com.mazmy.domainvalue.ConvertibleValue;
import com.mazmy.domainvalue.EngineType;
import com.mazmy.exception.ConstraintsViolationException;
import com.mazmy.exception.EntityNotFoundException;
import com.mazmy.service.car.CarService;
import com.mazmy.service.manufacturer.ManufacturerService;

/**
 * 
 * @author azmym All operations with a car will be routed by this controller
 */
@RestController
@RequestMapping("v1/cars")
public class CarController {

	private final CarService carService;
	private final ManufacturerService manufacturerService;

	@Autowired
	public CarController(final CarService carService, final ManufacturerService manufacturerService) {
		this.carService = carService;
		this.manufacturerService = manufacturerService;
	}

	@GetMapping("/{carId}")
	public CarDTO getCarById(@Valid @PathVariable long carId) throws EntityNotFoundException {
		return CarMapper.makeCarDTO(carService.find(carId));
	}

	@GetMapping
	public List<CarDTO> findByEngineType(@Valid @RequestParam EngineType engineType)
			throws ConstraintsViolationException, EntityNotFoundException {
		return CarMapper.makeDriverDTOList(carService.findByEngineType(engineType.type()));
	}

	@GetMapping("/convertible/{convertible}")
	public List<CarDTO> findByConvertible(@Valid @PathVariable ConvertibleValue convertible) throws EntityNotFoundException {
		return CarMapper.makeDriverDTOList(carService.findByConvertible(convertible.value()));
	}

	@GetMapping("/manufacturer/{manufacturer}")
	public List<CarDTO> findByManufacturer(@Valid @PathVariable String manufacturer) throws EntityNotFoundException {
		return CarMapper.makeDriverDTOList(carService.findByManufacturer(manufacturer));
	}

	/**
	 * create new CarDo If the Manufacturer wasn't placed, will be set with null
	 * If Manufacturer Name is placed , will check if exist in DB or not if it's
	 * not exist in DB will create new one, and if exist will use it
	 * 
	 * @param carDTO
	 * @return CarDTO
	 * @throws ConstraintsViolationException
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CarDTO createCar(@Valid @RequestBody CarDTO carDTO) throws ConstraintsViolationException {
		CarDO makeCarDO = CarMapper.makeCarDO(carDTO);
		ManufacturerDO manufacturer = makeCarDO.getManufacturer();

		if (null != manufacturer) {
			try {
				manufacturer = manufacturerService.findManufacturerName(manufacturer.getManufacturer());
			} catch (EntityNotFoundException e1) {
				manufacturer = manufacturerService.create(new ManufacturerDO(manufacturer.getManufacturer()));
			}
		}
		makeCarDO.setManufacturer(manufacturer);
		return CarMapper.makeCarDTO(carService.create(makeCarDO));
	}

	@DeleteMapping("/{carId}")
	public void deleteCar(@Valid @PathVariable long carId) throws EntityNotFoundException {
		carService.delete(carId);
	}

}

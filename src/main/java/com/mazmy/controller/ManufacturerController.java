package com.mazmy.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mazmy.controller.mapper.ManufacturerMapper;
import com.mazmy.datatransferobject.ManufacturerDTO;
import com.mazmy.domainobject.ManufacturerDO;
import com.mazmy.exception.ConstraintsViolationException;
import com.mazmy.exception.EntityNotFoundException;
import com.mazmy.service.manufacturer.ManufacturerService;

/**
 * 
 * @author azmym 
 * All operations with a manufacturers will be routed by this controller
 */
@RestController
@RequestMapping("v1/manufacturers")
public class ManufacturerController {

	private final ManufacturerService manufacturerService;
	
	@Autowired
	public ManufacturerController(final ManufacturerService manufacturerService) {
		this.manufacturerService = manufacturerService;
	}
	
	/**
	 * find  Manufacturer By id
	 * @param manufacturerId
	 * @return ManufacturerDTO
	 * @throws EntityNotFoundException
	 */
	@GetMapping("/{manufacturerId}")
	public ManufacturerDTO getManufacturerById(@Valid @PathVariable long manufacturerId) throws EntityNotFoundException {
		return ManufacturerMapper.makeManufacturerDTO(manufacturerService.find(manufacturerId));
	}
	
	/**
	 * find  Manufacturer by name
	 * @param manufacturerName
	 * @return ManufacturerDTO
	 * @throws EntityNotFoundException
	 */
	@GetMapping
	public ManufacturerDTO getManufacturerByName(@Valid @RequestParam String manufacturerName) throws EntityNotFoundException {
		return ManufacturerMapper.makeManufacturerDTO(manufacturerService.findManufacturerName(manufacturerName));
	}
	
	/**
	 * create new CarDo 
	 * @param manufacturerDTO
	 * @return manufacturerDTO
	 * @throws ConstraintsViolationException
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ManufacturerDTO createManufacturer(@Valid @RequestBody ManufacturerDTO manufacturerDTO) throws ConstraintsViolationException {
		ManufacturerDO makeManufacturerDO = ManufacturerMapper.makeManufacturerDO(manufacturerDTO);
		return ManufacturerMapper.makeManufacturerDTO(manufacturerService.create(makeManufacturerDO));
	}
}

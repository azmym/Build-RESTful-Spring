package com.mazmy.service.car;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mazmy.dataaccessobject.CarRepository;
import com.mazmy.domainobject.CarDO;
import com.mazmy.exception.ConstraintsViolationException;
import com.mazmy.exception.EntityNotFoundException;

/**
 * @author azmym
 * Service to encapsulate the link between DAO and controller and to have business logic for some car specific thinks
 */
@Service("CarService")
public class DefaultCarService implements CarService{

	private static org.slf4j.Logger LOG = LoggerFactory.getLogger(DefaultCarService.class);
	
	private final CarRepository carRepository;
	
	@Autowired
	public DefaultCarService (final CarRepository carRepository){
		this.carRepository = carRepository;
	}
	
	/**
	 * Select car by id
	 * 
	 * @param carId
	 * @return found car
     * @throws EntityNotFoundException if no car with the given id was found.
	 */
	@Override
	public CarDO find(Long carId) throws EntityNotFoundException {
		return findCarChecked(carId);
	}

	private CarDO findCarChecked(Long carId) throws EntityNotFoundException{
		CarDO carDO = carRepository.findOne(carId);
		if (null == carDO) {
			throw new EntityNotFoundException("Could not find car with id: " + carId);
		}
		return carDO;
	}

	/**
     * Creates a new Car.
     *
     * @param carDO
     * @return
     * @throws ConstraintsViolationException if a car already exists with the given licensePlate, ... .
     */
	@Override
	public CarDO create(CarDO carDO) throws ConstraintsViolationException {
		CarDO car = null;
		try{
			car = carRepository.save(carDO);
		}catch (DataIntegrityViolationException e) {
			LOG.warn("Some constraints are thrown due to car creation",e);
			throw new ConstraintsViolationException(e);
		}
		return car;
	}

	/**
     * Deletes an existing car by id.
     *
     * @param driverId
     * @throws EntityNotFoundException if no car with the given id was found.
     */
	@Override
	@Transactional
	public void delete(Long carId) throws EntityNotFoundException {
		CarDO carDO = findCarChecked(carId);
		carRepository.delete(carDO);
	}

	/**
	 * Find all cars by engine type
	 * @param engineType
	 */
	@Override
	public List<CarDO> findByEngineType(String engineType) {
		return carRepository.findAllByEngineType(engineType);
	}

	/**
	 * Find all cars by engine convertible
	 * @param convertible
	 */
	@Override
	public List<CarDO> findByConvertible(boolean convertible) {
		return carRepository.findAllByConvertible(convertible);
	}

	/**
	 * Find all cars by manufacturer name
	 * @param manufacturer
	 */
	@Override
	public List<CarDO> findByManufacturer(String manufacturer) {
		return carRepository.findByManufacturerManufacturerLikeIgnoreCase("%"+manufacturer+"%");
	}

}

package com.mazmy.service.driver;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mazmy.dataaccessobject.DriverRepository;
import com.mazmy.domainobject.CarDO;
import com.mazmy.domainobject.DriverDO;
import com.mazmy.domainvalue.GeoCoordinate;
import com.mazmy.domainvalue.OnlineStatus;
import com.mazmy.exception.CarAlreadyInUseException;
import com.mazmy.exception.ConstraintsViolationException;
import com.mazmy.exception.DriverOfflineException;
import com.mazmy.exception.EntityNotFoundException;

/**
 * Service to encapsulate the link between DAO and controller and to have business logic for some driver specific thinks.
 * <p/>
 */
@Service
public class DefaultDriverService implements DriverService
{

    private static org.slf4j.Logger LOG = LoggerFactory.getLogger(DefaultDriverService.class);

    private final DriverRepository driverRepository;

    private final EntityManager entityManager;
    
    @Autowired
    public DefaultDriverService(final DriverRepository driverRepository, final EntityManager entityManager)
    {
        this.driverRepository = driverRepository;
        this.entityManager = entityManager;
    }


    /**
     * Selects a driver by id.
     *
     * @param driverId
     * @return found driver
     * @throws EntityNotFoundException if no driver with the given id was found.
     */
    @Override
    public DriverDO find(Long driverId) throws EntityNotFoundException
    {
        return findDriverChecked(driverId);
    }


    /**
     * Creates a new driver.
     *
     * @param driverDO
     * @return
     * @throws ConstraintsViolationException if a driver already exists with the given username, ... .
     */
    @Override
    public DriverDO create(DriverDO driverDO) throws ConstraintsViolationException
    {
        DriverDO driver = null;
        try
        {
            driver = driverRepository.save(driverDO);
        }
        catch (DataIntegrityViolationException e)
        {
            LOG.warn("Some constraints are thrown due to driver creation", e);
            throw new ConstraintsViolationException(e.getMessage());
        }
        return driver;
    }


    /**
     * Deletes an existing driver by id.
     *
     * @param driverId
     * @throws EntityNotFoundException if no driver with the given id was found.
     */
    @Override
    @Transactional
    public void delete(Long driverId) throws EntityNotFoundException
    {
        DriverDO driverDO = findDriverChecked(driverId);
        driverDO.setDeleted(true);
    }


    /**
     * Update the location for a driver.
     *
     * @param driverId
     * @param longitude
     * @param latitude
     * @throws EntityNotFoundException
     */
    @Override
    @Transactional
    public void updateLocation(long driverId, double longitude, double latitude) throws EntityNotFoundException
    {
        DriverDO driverDO = findDriverChecked(driverId);
        driverDO.setCoordinate(new GeoCoordinate(latitude, longitude));
    }


    /**
     * Find all drivers by online state.
     *
     * @param onlineStatus
     */
    @Override
    public List<DriverDO> find(OnlineStatus onlineStatus)
    {
        return driverRepository.findByOnlineStatus(onlineStatus);
    }


    private DriverDO findDriverChecked(Long driverId) throws EntityNotFoundException
    {
        DriverDO driverDO = driverRepository.findOne(driverId);
        if (driverDO == null)
        {
            throw new EntityNotFoundException("Could not find entity with id: " + driverId);
        }
        return driverDO;
    }


    /**
     * Assign car to driver
     * 
     * @param driverId
     * @param carDO
     * @throws EntityNotFoundException 
     * @throws DriverOfflineException 
     * @throws CarAlreadyInUseException 
     */
	@Override
	@Transactional
	public void selectCar(long driverId, CarDO carDO) throws EntityNotFoundException, DriverOfflineException, CarAlreadyInUseException {
		DriverDO driverDO = findDriverChecked(driverId);
		// check if there is the driver who will pick up the car is offline or not
		List<OnlineStatus> collect = Stream.of(OnlineStatus.values())
				.filter(os -> os.equals(driverDO.getOnlineStatus()))
				.collect(Collectors.toList());
		if (collect.size()==1 && collect.get(0)==OnlineStatus.OFFLINE)
			throw new DriverOfflineException("The selected driver with id: "+driverId+" in offline status.");
		//check if the car is used or not
		long count = driverRepository.findByCarId(carDO.getId()).stream()
					.filter(d -> d.getCar().getId() == carDO.getId())
					.count();
		if (count != 0) throw new CarAlreadyInUseException("the selected car with id: "+carDO.getId()+" already in use.");
		driverDO.setCar(carDO);
	}


	/**
	 *  Non-allocation car to driver
	 * 
	 * @param driverId
	 * @throws EntityNotFoundException
	 */
	@Override
	@Transactional
	public void deSelectCar(long driverId) throws EntityNotFoundException {
		DriverDO driverDO = findDriverChecked(driverId);
		driverDO.setCar(null);
	}

	@Override
	public DriverDO findByUsername(String username) {
		return driverRepository.findByUsername(username);
	}
	
	 /**
     * there are several ways to implement filter pattern like 
     *  - hibernate Criteria
     *  - Spring Data Criteria
     *  - Java Stream
     */
	@Transactional
	@Override
	public List<DriverDO> filter(CarDO carDO){
		Session session = entityManager.unwrap(Session.class);
		Criteria createCriteria = session.createCriteria(DriverDO.class,"driver")
				.createAlias("driver.car","car");
		if (null != carDO.getEngineType())
			createCriteria.add(Restrictions.eq("car.engineType", carDO.getEngineType()));
		if (null != carDO.getConvertible())
			createCriteria.add(Restrictions.eq("car.convertible", carDO.getConvertible()));
		if (null != carDO.getLicensePlate()) 
			createCriteria.add(Restrictions.like("car.licensePlate", carDO.getLicensePlate()));
		
		return createCriteria.list();
	}
}

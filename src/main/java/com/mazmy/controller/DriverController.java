package com.mazmy.controller;

import com.mazmy.controller.mapper.DriverMapper;
import com.mazmy.datatransferobject.DriverDTO;
import com.mazmy.domainobject.CarDO;
import com.mazmy.domainobject.DriverDO;
import com.mazmy.domainvalue.ConvertibleValue;
import com.mazmy.domainvalue.EngineType;
import com.mazmy.domainvalue.OnlineStatus;
import com.mazmy.exception.CarAlreadyInUseException;
import com.mazmy.exception.ConstraintsViolationException;
import com.mazmy.exception.DriverOfflineException;
import com.mazmy.exception.EntityNotFoundException;
import com.mazmy.service.car.CarService;
import com.mazmy.service.driver.DriverService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * All operations with a driver will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("v1/drivers")
public class DriverController
{

    private final DriverService driverService;
    private final CarService carService;

    @Autowired
    public DriverController(final DriverService driverService, final CarService carService)
    {
        this.driverService = driverService;
        this.carService = carService;
    }


    @GetMapping("/{driverId}")
    public DriverDTO getDriver(@Valid @PathVariable long driverId) throws EntityNotFoundException
    {
        return DriverMapper.makeDriverDTO(driverService.find(driverId));
    }

    /**
     * @param driverDTO
     * @return
     * @throws ConstraintsViolationException
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DriverDTO createDriver(@Valid @RequestBody DriverDTO driverDTO) throws ConstraintsViolationException
    {
        DriverDO driverDO = DriverMapper.makeDriverDO(driverDTO);
        return DriverMapper.makeDriverDTO(driverService.create(driverDO));
    }


    @DeleteMapping("/{driverId}")
    public void deleteDriver(@Valid @PathVariable long driverId) throws EntityNotFoundException
    {
        driverService.delete(driverId);
    }


    @PutMapping("/{driverId}")
    public void updateLocation(
        @Valid @PathVariable long driverId, @RequestParam double longitude, @RequestParam double latitude)
        throws ConstraintsViolationException, EntityNotFoundException
    {
        driverService.updateLocation(driverId, longitude, latitude);
    }


    @GetMapping
    public List<DriverDTO> findDrivers(@RequestParam OnlineStatus onlineStatus)
        throws ConstraintsViolationException, EntityNotFoundException
    {
        return DriverMapper.makeDriverDTOList(driverService.find(onlineStatus));
    }
    
    /**
     * Enable drivers to select a car they are driving with
     * 
     * @param driverId
     * @param carId
     * @throws EntityNotFoundException
     * @throws DriverOfflineException 
     * @throws CarAlreadyInUseException 
     */
	@PutMapping("/selectcar")
	public void selectCar(@Valid @RequestParam long driverId, @RequestParam long carId)
			throws EntityNotFoundException, DriverOfflineException, CarAlreadyInUseException {
		CarDO carDO = carService.find(carId);
		driverService.selectCar(driverId, carDO);
	}
    
    /**
     * Enable drivers to deselect a car
     * 
     * @param driverId
     * @throws EntityNotFoundException 
     */
    @PutMapping("/deselectcar")
    public void deSelectCar(@Valid  @RequestParam long driverId) throws EntityNotFoundException{
    	driverService.deSelectCar(driverId);
    }
    
   
    @GetMapping("/filter")
    public List<DriverDTO> filter( @RequestParam(required = false) EngineType engineType,  @RequestParam(required = false) ConvertibleValue convertible, @RequestParam(required = false) String licensePlate){
    	String  engine = (null == engineType) ? null : engineType.type();
    	Boolean convertibleState = (null == convertible) ? null : convertible.value();
    	CarDO carDO = new CarDO(licensePlate, 0, convertibleState, 0, engine, null);
		return DriverMapper.makeDriverDTOList(driverService.filter(carDO));
    }
}

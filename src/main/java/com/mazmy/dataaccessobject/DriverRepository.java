package com.mazmy.dataaccessobject;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mazmy.domainobject.DriverDO;
import com.mazmy.domainvalue.OnlineStatus;

/**
 * Database Access Object for driver table.
 * <p/>
 */
public interface DriverRepository extends CrudRepository<DriverDO, Long>
{

    List<DriverDO> findByOnlineStatus(OnlineStatus onlineStatus);
    
    DriverDO findByUsername(String username);
    
    // if we are using  micro service, should change this implementation 
    List<DriverDO> findByCarId(Long carId);

}

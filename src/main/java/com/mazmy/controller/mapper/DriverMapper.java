package com.mazmy.controller.mapper;

import com.mazmy.datatransferobject.DriverDTO;
import com.mazmy.domainobject.CarDO;
import com.mazmy.domainobject.DriverDO;
import com.mazmy.domainvalue.GeoCoordinate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class DriverMapper {
	public static DriverDO makeDriverDO(DriverDTO driverDTO) {
		return new DriverDO(driverDTO.getUsername(), driverDTO.getPassword(), null);
	}

	public static DriverDTO makeDriverDTO(DriverDO driverDO) {
		DriverDTO.DriverDTOBuilder driverDTOBuilder = DriverDTO.newBuilder()
				.setId(driverDO.getId())
				.setPassword(driverDO.getPassword())
				.setUsername(driverDO.getUsername());

		GeoCoordinate coordinate = driverDO.getCoordinate();
		if (coordinate != null) {
			driverDTOBuilder.setCoordinate(coordinate);
		}
		
		CarDO carDO = driverDO.getCar();
		if (carDO != null) {
			driverDTOBuilder.setCarDTO(CarMapper.makeCarDTO(carDO));
		}

		return driverDTOBuilder.createDriverDTO();
	}

    public static List<DriverDTO> makeDriverDTOList(Collection<DriverDO> drivers)
    {
        return drivers.stream()
            .map(DriverMapper::makeDriverDTO)
            .collect(Collectors.toList());
    }
}

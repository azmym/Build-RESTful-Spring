package com.mazmy.controller.mapper;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.mazmy.SpringBaseTest;
import com.mazmy.datatransferobject.DriverDTO;
import com.mazmy.domainobject.DriverDO;

public class DriverMapperTest extends SpringBaseTest{

	@Test
	public void testMakeDriverDO() {
		DriverDTO.DriverDTOBuilder driverDTOBuilder= DriverDTO.newBuilder();
		driverDTOBuilder.setUsername("test").setPassword("test");
		DriverDTO driverDTO = driverDTOBuilder.createDriverDTO();
		DriverDO makeDriverDO = DriverMapper.makeDriverDO(driverDTO);
		assertEquals(driverDTO.getUsername(), makeDriverDO.getUsername());
		assertEquals(driverDTO.getPassword(), makeDriverDO.getPassword());
		
	}

	@Test
	public void testMakeDriverDTO() {
		DriverDO driverDO = new DriverDO("test", "test", null);
		DriverDTO makeDriverDTO = DriverMapper.makeDriverDTO(driverDO);
		assertEquals(driverDO.getUsername(), makeDriverDTO.getUsername());
		assertEquals(driverDO.getPassword(), makeDriverDTO.getPassword());
	}
	
	@Test
	public void testMakeDriverDTOList (){
		DriverDO driverDO = new DriverDO("test", "test", null);
		DriverDO driverDO1 = new DriverDO("test1", "test1", null);
		DriverDO driverDO2 = new DriverDO("test2", "test2", null);
		DriverDO driverDO3 = new DriverDO("test3", "test3", null);
		ArrayList<DriverDO> arrayList = new ArrayList<DriverDO>();
		arrayList.add(driverDO);
		arrayList.add(driverDO1);
		arrayList.add(driverDO2);
		arrayList.add(driverDO3);
		List<DriverDTO> makeDriverDTOList = DriverMapper.makeDriverDTOList(arrayList);
		assertEquals(arrayList.size(), makeDriverDTOList.size());
	}
}

package com.mazmy.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.mazmy.SpringBaseTest;

/**
 * unit testing for DriverController for methods selectCar, deSelectCar and filter
 * @author azmym
 *
 */
public class DriverControllerTest extends SpringBaseTest{

	@Test
	public void testSelectCarWithoutSecurity() throws Exception {
		mockMvc.perform(put("/v1/drivers/selectcar")
				.param("driverId", "1")
				.param("carId", "1"))
				.andExpect(status().isUnauthorized())
				.andDo(MockMvcResultHandlers.print());		
	}
	
	@Test
	public void testSelectCarWithSecurity() throws Exception {
		mockMvc.perform(put("/v1/drivers/selectcar").with(authentication(auth))
				.param("driverId", "4")
				.param("carId", "3"))
				.andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print());
				
	}
	
	@Test
	public void testSelectCarWithSecurityWithOfflineDriver() throws Exception {
		mockMvc.perform(put("/v1/drivers/selectcar").with(authentication(auth))
				.param("driverId", "1")
				.param("carId", "1"))
				.andExpect(status().isBadRequest())
				.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void testSelectCarWithSecurityWithUsedCar() throws Exception {
		mockMvc.perform(put("/v1/drivers/selectcar").with(authentication(auth))
				.param("driverId", "5")
				.param("carId", "1"))
				.andExpect(status().isBadRequest())
				.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void testDeSelectCarWithoutSecurity() throws Exception {
		mockMvc.perform(put("/v1/drivers/deselectcar")
				.param("driverId", "1"))
				.andExpect(status().isUnauthorized())
				.andDo(MockMvcResultHandlers.print());	
	}
	
	@Test
	public void testDeSelectCarWithSecurity() throws Exception {
		mockMvc.perform(put("/v1/drivers/deselectcar").with(authentication(auth))
				.param("driverId", "4"))
				.andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print());	
	}
	
	@Test
	public void testFilterWithoutSecurity() throws Exception {
		mockMvc.perform(get("/v1/drivers/filter"))
				.andExpect(status().isUnauthorized())
				.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void testFilterWithSecurity() throws Exception {
		mockMvc.perform(get("/v1/drivers/filter")
				.with(authentication(auth))
				.param("licensePlate", "WW GG 544"))
				.andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}
}

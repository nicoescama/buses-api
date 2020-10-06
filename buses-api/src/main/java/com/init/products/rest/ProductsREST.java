package com.init.products.rest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.init.products.dao.BusesDAO;
import com.init.products.dao.DevicesDAO;
import com.init.products.entities.Bus;
import com.init.products.entities.Device;

@RestController
@RequestMapping("/")
public class ProductsREST {
	
	@Autowired
	private DevicesDAO devicesDAO;
	
	@Autowired
	private BusesDAO busesDAO;
	
	//READING
	@RequestMapping(value="devices", method=RequestMethod.GET)
	public ResponseEntity<List<Device>> getDevices(){
		List<Device> devices = devicesDAO.findAll();
		return ResponseEntity.ok(devices);				
	}
	
	@RequestMapping(value="buses", method=RequestMethod.GET)
	public ResponseEntity<List<Bus>> getBuses(){
		List<Bus> buses = busesDAO.findAll();
		return ResponseEntity.ok(buses);				
	}
	
	
	@RequestMapping(value="devices/{deviceId}", method=RequestMethod.GET)
	public ResponseEntity<Device> getDeviceById(@PathVariable("deviceId") Long deviceId){
		Optional<Device> deviceOpt = devicesDAO.findById(deviceId);
		if(!deviceOpt.isPresent()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(deviceOpt.get());				
	}
	
	@RequestMapping(value="buses/{busId}", method=RequestMethod.GET)
	public ResponseEntity<Bus> getBusById(@PathVariable("busId") Long busId){
		Optional<Bus> busOpt = busesDAO.findById(busId);
		if(!busOpt.isPresent()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(busOpt.get());				
	}
	
	@RequestMapping(value="buses/{busId}/devices", method=RequestMethod.GET)
	public ResponseEntity<List<Device>> getDevicesOfBusById(@PathVariable("busId") Long busId){
		Optional<Bus> busOpt = busesDAO.findById(busId);	
		if(!busOpt.isPresent()) {
			return ResponseEntity.noContent().build();
		}
		List<Device> devices = devicesDAO.findAll();
		List<Device> devicesFound = devices.stream().filter(div -> div.getBusId() == busId).collect(Collectors.toList());
		return ResponseEntity.ok(devicesFound);				
	}
	
	//CREATION
	@RequestMapping(value="devices", method=RequestMethod.POST)
	public ResponseEntity<Device> agregarDevice(@RequestBody Device dev){
		Device newDev = devicesDAO.save(dev);
		return ResponseEntity.ok(newDev);
	}
	
	@RequestMapping(value="buses", method=RequestMethod.POST)
	public ResponseEntity<Bus> agregarBus(@RequestBody Bus busN){
		Bus newBus = busesDAO.save(busN);
		return ResponseEntity.ok(newBus);
	}
	
	//DELATION
	@RequestMapping(value="devices/{deviceId}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteDevice(@PathVariable("deviceId") Long deviceId){
		devicesDAO.deleteById(deviceId);		
		return ResponseEntity.ok(null);
	}
	
	//Devices attached to bus are also deleted
	@RequestMapping(value="buses/{busId}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteBus(@PathVariable("busId") Long busId){
		List<Device> devices = devicesDAO.findAll();
		devices.stream().filter(div -> div.getBusId() == busId)
				.forEach(div -> devicesDAO.deleteById(div.getId()));
		busesDAO.deleteById(busId);		
		return ResponseEntity.ok(null);
	}
	
	//MODIFICATION (it needs to be  to allow partial modification)
	@RequestMapping(value="devices/{deviceId}", method=RequestMethod.PUT)
	public ResponseEntity<Void> updateDevice(@RequestBody Device dev){
		Optional<Device> deviceOpt = devicesDAO.findById(dev.getId());
		if(!deviceOpt.isPresent()) {
			return ResponseEntity.noContent().build();
		}
		devicesDAO.save(dev);
		return ResponseEntity.ok(null);	
	}
	
	@RequestMapping(value="buses/{busId}", method=RequestMethod.PUT)
	public ResponseEntity<Void> updateBus(@RequestBody Bus busU){
		Optional<Bus> busOpt = busesDAO.findById(busU.getId());
		if(!busOpt.isPresent()) {
			return ResponseEntity.noContent().build();
		}
		busesDAO.save(busU);
		return ResponseEntity.ok(null);	
	}


}

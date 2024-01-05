package com.mdp.petmed.Service;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"api/service"})
public class ServiceController {
    private final ServiceService serviceService;

    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @PostMapping({"/create"})
    public ServiceDTO create(@RequestBody ServiceDTO serviceDTO) {
        return new ServiceDTO(serviceService.insert(serviceDTO));
    }

    @PutMapping("/update/{id}")
    public ServiceDTO update(@PathVariable("id") Long id, @RequestBody ServiceDTO serviceDTO){
        return new ServiceDTO(serviceService.update(serviceDTO));
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id){
        serviceService.delete(id);
    }

    @GetMapping("/get/{id}")
    public ServiceDTO get(@PathVariable("id") Long id){
        return new ServiceDTO(serviceService.getById(id));
    }

    @GetMapping("/getAll")
    public List<ServiceDTO> getAll(@RequestParam("page") int page,
                                   @RequestParam("size") int size) {    
        return serviceService.getAllServicePaged(page, size).stream().map(
                ServiceDTO::new
        ).toList();        
    }
}

package com.mdp.petmed.Service;

import org.springframework.data.domain.PageRequest;

import java.nio.charset.StandardCharsets;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class ServiceService {
    private final ServiceRepository serviceRepository;

    public ServiceService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Transactional
    public ServiceModel insert(String name, Double price, String photo) {
        ServiceModel service = new ServiceModel(name, price, photo.getBytes());
        return serviceRepository.save(service);
    }

    @Transactional
    public ServiceModel update(ServiceDTO serviceDTO){
        final ServiceModel service = findService(serviceDTO.getId());
        service.setName(serviceDTO.getName());
        service.setPrice(serviceDTO.getPrice());
        service.setPhoto(serviceDTO.getPhoto().getBytes(StandardCharsets.UTF_8));
        return serviceRepository.save(service);
    }

    @Transactional
    public ServiceModel delete(Long id){
        final ServiceModel curService = findService(id);
        serviceRepository.delete(curService);
        return curService;
    }

    @Transactional
    public ServiceModel findService(Long id){
        return serviceRepository.getReferenceById(id);
    }

    @Transactional
    public Page<ServiceModel> getAllServicePaged(int page, int size){
        return serviceRepository.findAll(PageRequest.of(page - 1, size));
    }
}

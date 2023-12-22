package com.mdp.petmed.Service;

import org.springframework.data.domain.PageRequest;

import java.nio.charset.StandardCharsets;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServiceService {
    private final ServiceRepository serviceRepository;

    public ServiceService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Transactional
    public ServiceModel insert(ServiceDTO serviceDTO) {
        ServiceModel service = new ServiceModel(serviceDTO.getName(), serviceDTO.getPrice(), serviceDTO.getPhoto());//.getBytes());
        return serviceRepository.save(service);
    }

    @Transactional
    public ServiceModel update(ServiceDTO serviceDTO){
        final ServiceModel service = getById(serviceDTO.getId());
        service.setName(serviceDTO.getName());
        service.setPrice(serviceDTO.getPrice());
        service.setPhoto(serviceDTO.getPhoto());//.getBytes(StandardCharsets.UTF_8));
        return serviceRepository.save(service);
    }

    @Transactional
    public void delete(Long id) throws Exception{
        try{
            final ServiceModel curService = getById(id);
            serviceRepository.delete(curService);
        }catch(Exception ex){
            throw new Exception("Ошибка при удалении сервиса с id " + id);
        }
        
    }

    @Transactional
    public ServiceModel getById(Long id){
        return serviceRepository.getReferenceById(id);
    }

    @Transactional
    public Page<ServiceModel> getAllServicePaged(int page, int size){
        return serviceRepository.findAll(PageRequest.of(page - 1, size));
    }
}

package com.example.service.serviceImpl;

import com.example.entity.Address;
import com.example.entity.Univercity;
import com.example.payload.ApiResponse;
import com.example.payload.UnivercityDto;
import com.example.repository.AddressRepository;
import com.example.repository.UnivercityRepository;
import com.example.service.UnivercityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UnivercityServiceImpl implements UnivercityService {
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    UnivercityRepository univercityRepository;

    @Override
    public Univercity getByUniercityId(Integer id) {
        Optional<Univercity> univercity = univercityRepository.findById(id);
        return univercity.orElse(null);
    }

    @Override
    public ApiResponse editUniversity(Integer id, UnivercityDto univercityDto) {
        Optional<Univercity> univercityOptional = univercityRepository.findById(id);
        if (univercityOptional.isPresent()){
            Univercity univercity = univercityOptional.get();
            univercity.setName(univercityDto.getName());
            univercity.setOpenYear(univercityDto.getOpenYear());

            Address address = univercity.getAddress();
            address.setCity(univercityDto.getCity());
            address.setDistrict(univercityDto.getDistrict());
            address.setStreet(univercityDto.getStreet());

            addressRepository.save(address);

            univercityRepository.save(univercity);
            return new ApiResponse("edit univercity",true);
        }
        return new ApiResponse("not found univercity",false);
    }

    @Override
    public ApiResponse deleteUnivercity(Integer id) {
        try {
        univercityRepository.deleteById(id);
            return new ApiResponse("univercity delete",true);
        }
        catch (Exception e){
        return new ApiResponse("Error",false) ;
        }
    }

    @Override
    public List<Univercity> getAllUnivercity() {
        return univercityRepository.findAll();
    }

    @Override
    public ApiResponse addUniversity(UnivercityDto univercityDto) {
        boolean exists = univercityRepository.existsByName(univercityDto.getName());
        if (exists){
            return new ApiResponse("univercity already exists",false);
        }
        Address address = new Address();
        address.setCity(univercityDto.getCity());
        address.setDistrict(univercityDto.getDistrict());
        address.setStreet(univercityDto.getStreet());

        Address savedAddress = addressRepository.save(address);

        Univercity univercity=new Univercity();
        univercity.setName(univercityDto.getName());
        univercity.setOpenYear(univercityDto.getOpenYear());
        univercity.setAddress(savedAddress);

        univercityRepository.save(univercity);

        return new ApiResponse("add univercity succes",true);
    }
}

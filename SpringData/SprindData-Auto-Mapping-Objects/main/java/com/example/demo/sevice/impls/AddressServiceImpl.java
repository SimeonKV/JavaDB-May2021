package com.example.demo.sevice.impls;

import com.example.demo.dao.AddressRepository;
import com.example.demo.entity.Address;
import com.example.demo.exceptions.NonExistingEntityException;
import com.example.demo.sevice.AddressService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public List<Address> getAllAddresses() {
        return this.addressRepository.findAll();
    }

    @Override
    public Address getAddressById(Long id) {
        return this.addressRepository.findById(id).orElseThrow(() ->
            new NonExistingEntityException(String.format("Address with ID = %s does not exist",id))
        );
    }

    @Override
    @Transactional
    public Address addAddress(Address address) {
        address.setId(null);
        return this.addressRepository.save(address);
    }

    @Override
    @Transactional
    public Address updateAddress(Address address) {
        getAddressById(address.getId());
        return this.addressRepository.save(address);
    }

    @Override
    @Transactional
    public Address deleteAddress(Long id) {
       Address removed = getAddressById(id);
       this.addressRepository.deleteById(id);

        return removed;
    }

    @Override
    public long getAddressCount() {
        return this.addressRepository.count();
    }
}

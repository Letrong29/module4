package com.codegym.service;

import com.codegym.model.contract.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IContractService {

    Page<Contract> findAll(Pageable pageable);

    Contract findById(int id);

    void save(Contract contract);

    void remove(int id);

    Page<Contract> findAllByEndDateGreaterThan(String now, Pageable pageable);
}

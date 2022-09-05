package com.codegym.service.impl.contract;

import com.codegym.model.contract.Contract;
import com.codegym.repository.contract.IContractRepository;
import com.codegym.service.IContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ContractService implements IContractService {

    @Autowired
    private IContractRepository iContractRepository;

    @Override
    public Page<Contract> findAll(Pageable pageable) {
        return this.iContractRepository.findAll(pageable);
    }
}
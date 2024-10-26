package it.vittoriomigliore.agriculturaldashboard.core.service;

import it.vittoriomigliore.agriculturaldashboard.core.entity.Cost;
import it.vittoriomigliore.agriculturaldashboard.core.repository.CostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CostService {
    CostRepository costRepository;

    @Autowired
    public CostService(CostRepository costRepository) {
        this.costRepository = costRepository;
    }

    public Cost saveCost(Cost cost) {
        return costRepository.save(cost);
    }
}

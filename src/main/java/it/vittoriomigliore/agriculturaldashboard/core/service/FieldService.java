package it.vittoriomigliore.agriculturaldashboard.core.service;

import it.vittoriomigliore.agriculturaldashboard.core.entity.Company;
import it.vittoriomigliore.agriculturaldashboard.core.entity.Crop;
import it.vittoriomigliore.agriculturaldashboard.core.entity.Field;
import it.vittoriomigliore.agriculturaldashboard.core.repository.FieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FieldService {

    CompanyService companyService;

    FieldRepository fieldRepository;

    @Autowired
    public FieldService(CompanyService companyService, FieldRepository fieldRepository) {
        this.companyService = companyService;
        this.fieldRepository = fieldRepository;
    }

    public List<Field> getAllFields() {
        Company company = companyService.getSystemCompany();
        return fieldRepository.findAllByCompany(company);
    }

    public List<Field> getAllFieldsByCrop(Crop crop) {
        Company company = companyService.getSystemCompany();
        return fieldRepository.findAllByCompanyAndCrop(company, crop);
    }
}

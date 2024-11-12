package it.vittoriomigliore.agriculturaldashboard.core.controller;

import it.vittoriomigliore.agriculturaldashboard.core.entity.Company;
import it.vittoriomigliore.agriculturaldashboard.core.entity.Field;
import it.vittoriomigliore.agriculturaldashboard.core.service.CompanyService;
import it.vittoriomigliore.agriculturaldashboard.core.service.FieldService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class HomeController {

    private final CompanyService companyService;
    private final FieldService fieldService;

    public HomeController(CompanyService companyService, FieldService fieldService) {
        this.companyService = companyService;
        this.fieldService = fieldService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @ModelAttribute
    public void addFields(Model model) {
        Company company = companyService.getSystemCompany();
        model.addAttribute("companyName", company.getName());
        List<Field> fields = fieldService.getAllFields();
        model.addAttribute("fields", fields);
        model.addAttribute("fieldIdList", fields.stream().map(Field::getId).toList());
    }
}

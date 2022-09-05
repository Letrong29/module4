package com.codegym.controller;

import com.codegym.model.customer.Customer;
import com.codegym.service.ICustomerService;
import com.codegym.service.ICustomerTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private ICustomerService iCustomerService;

    @Autowired
    private ICustomerTypeService iCustomerTypeService;

    @GetMapping(value = {"/home",""})
    public String goCustomerList(@PageableDefault(size = 3) Pageable pageable,
                                 @RequestParam Optional<String> keySearch,
                                 Model model){

        String keyVal = keySearch.orElse("");

        model.addAttribute("customers",
                this.iCustomerService.findAllByNameContaining(keyVal, pageable));

        model.addAttribute("keySearch", keyVal);

        return "customer-list";
    }

    @GetMapping("/create")
    public String goCreateForm(Model model){

        model.addAttribute("customer", new Customer());

        model.addAttribute("customerTypeList",
                this.iCustomerTypeService.findAll());

        return "customer-create";
    }

    @PostMapping("/save")
    public String saveCustomer(@ModelAttribute Customer customer,
                               RedirectAttributes redirectAttributes){

        this.iCustomerService.save(customer);

        redirectAttributes.addFlashAttribute("message",
                "successfully added new");

        return "redirect:/customer/home";
    }

    @GetMapping("/edit")
    public String goEditForm(@RequestParam int id,
                             Model model){

        model.addAttribute("customer",
                this.iCustomerService.findById(id));

        model.addAttribute("customerTypeList",
                this.iCustomerTypeService.findAll());

        return "customer-edit";
    }

    @PostMapping("/update")
    public String updateCustomer(@ModelAttribute Customer customer,
                               RedirectAttributes redirectAttributes){

        this.iCustomerService.save(customer);

        redirectAttributes.addFlashAttribute("message",
                "successfully update");

        return "redirect:/customer/home";
    }

    @PostMapping("/delete")
    public String deleteCustomer(@RequestParam int customerId){

        this.iCustomerService.deleteById(customerId);

        return "redirect:/customer/home";
    }

}
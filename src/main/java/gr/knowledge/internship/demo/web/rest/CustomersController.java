package gr.knowledge.internship.demo.web.rest;

import gr.knowledge.internship.demo.domain.Customers;
import gr.knowledge.internship.demo.service.CustomersService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/knowledge")
public class CustomersController {

    private final CustomersService customersService;

    public CustomersController(CustomersService customersService) {
            this.customersService = customersService;
        }


    /* GetAll */
    @GetMapping("/Customers")
    public List<Customers> getAllCustomers() {
            return customersService.findAllCustomers();
        }


    /* GetById */
    @GetMapping("/getCustomersById/{id}")
    public Optional<Customers> getCustomersById(@PathVariable(value = "id") Long id) {
        return customersService.getCustomersById(id);
    }


    /* POST (createNew) */
    @PostMapping("/createNewCustomer")
    public Customers saveCustomers(@RequestBody Customers customers) {
        return customersService.saveCustomers(customers);
    }


    /* PUT (update) */
    @PutMapping("/updateCustomers")
    public Customers updateCustomers(@RequestBody Customers customers) {
        return customersService.saveCustomers(customers);
    }


    /* DELETE ById */
    @DeleteMapping("/deleteCustomerById/{id}")
    void deleteCustomerById(@PathVariable Long id) {
        customersService.deleteCustomerById(id);
    }


}


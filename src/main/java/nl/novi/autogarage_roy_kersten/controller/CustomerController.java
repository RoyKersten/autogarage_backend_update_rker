package nl.novi.autogarage_roy_kersten.controller;

import nl.novi.autogarage_roy_kersten.model.Customer;
import nl.novi.autogarage_roy_kersten.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

/**
 * The CustomerController class ensures that HTTP Requests en Responses are handled and processed further to the CustomerService interface.
**/

@CrossOrigin
@RestController
@RequestMapping(value = "/customers")
public class CustomerController {

    //Attributes
    private CustomerService customerService;

    @Autowired
    //Constructors
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    //Methods

    //Create a new Customer
    @PostMapping(value = "")
    public ResponseEntity<Object> addCustomer(@RequestBody Customer customer) {
        long newId = customerService.addCustomer(customer);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idCustomer}")
                .buildAndExpand(newId).toUri();
        return ResponseEntity.created(location).body(location);
    }

    //Get all Customers
    @GetMapping(value = "")
    public ResponseEntity<Object> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    //Get customer by idCustomer
    @GetMapping("/{idCustomer}")
    public ResponseEntity<Object> getCustomerById(@PathVariable("idCustomer") long idCustomer) {
        Customer customer = customerService.getCustomerById(idCustomer);
        return ResponseEntity.ok(customer);
    }

    //Delete Customer by idCustomer
    @DeleteMapping("/{idCustomer}")
    public ResponseEntity<Object> deleteCustomerById(@PathVariable("idCustomer") long idCustomer) {
        customerService.deleteCustomerById(idCustomer);
        return ResponseEntity.ok("customer successfully deleted, please note that connected car(s) also have been deleted!");
    }

    //Update customer by idCustomer
    @PutMapping("/{idCustomer}")
    public ResponseEntity<Object> updateCustomerById(@PathVariable("idCustomer") long idCustomer, @RequestBody Customer updateCustomer) {
        customerService.updateCustomerById(idCustomer, updateCustomer);
        return ResponseEntity.ok("update customer successfully");
    }
}



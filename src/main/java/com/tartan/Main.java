package com.tartan;

import ch.qos.logback.core.model.INamedModel;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping
public class Main {
    private final CustomerRepository customerRepository; // for api response (get request)
    public Main(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args); // this has to be done inorder to be a spring-boot application
    }
    @GetMapping("api/v1/customers") // get request
    public List<Customer> getCustomers(){
        return customerRepository.findAll(); // for api response (get request)
    }

    // for api (post request)
    record NewCustomerRequest(
            String name,
            String email,
            Integer age
    ){}
    @PostMapping
    // RequestBody used to convert the JSON object to class object
    public void addCustomer(@RequestBody NewCustomerRequest request){
        Customer customer = new Customer();
        customer.setName(request.name());
        customer.setEmail(request.email());
//        customer.setAge(request.age());
        customerRepository.save(customer);
    }

    // for api (delete request)
    @DeleteMapping("{customerId}") // {} used for dynamic customerId
    public void deleteCustomer(@PathVariable("customerId") Integer id){
        customerRepository.deleteById(id);
    }

    // for an api response
    @GetMapping("/greet")  // specify path
    public GreetResponse greet(){
        GreetResponse response = new GreetResponse(
                "Hello",
                List.of("Java", "Golang", "Javascript"),
                new Person("Alex", 28, 30_000)
        );
        return response;
    }

    record Person(String name,int age, double savings){

    }
    record GreetResponse(
            String greet,
            List<String>favProgrammingLanguages,
            Person person
    ){}
}

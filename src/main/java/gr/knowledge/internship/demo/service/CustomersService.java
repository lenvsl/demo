package gr.knowledge.internship.demo.service;

import gr.knowledge.internship.demo.domain.Customers;
import gr.knowledge.internship.demo.domain.OrderDetails;
import gr.knowledge.internship.demo.repository.CustomersRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomersService {


    /* sundesi me repository */
    private static CustomersRepository customersRepository;

    /*to arxikopoiw gia na trexei*/
    public CustomersService(CustomersRepository customersRepository) { this.customersRepository = customersRepository; }


    /* getAll method */
    @Transactional(readOnly = true)
    public List<Customers> findAllCustomers() {
        return customersRepository.findAll();
    }


    /* getById method */
    @Transactional(readOnly = true)
    public Optional<Customers> getCustomersById(Long id){
            return customersRepository.findById(id);
    }


    /* SAVE method */
    @Transactional
    public Customers saveCustomers(Customers customers){
            return customersRepository.save(customers);
    }


    /* deleteById method */
    @Transactional
    public void deleteCustomerById(Long id) {
            customersRepository.deleteById(id);
    }



    /* askisi konnoy a   (24.3.2013) */
    public Customers getById(Long costumerId){
        Customers result;  //arxikopoihsh
        Optional<Customers> details = customersRepository.findById(costumerId);
        if(details.isPresent()){
            result = details.get();
        } else {
            throw new RuntimeException("not found");
        }
        return result;

    }

}




package gr.knowledge.internship.demo.repository;

    import gr.knowledge.internship.demo.domain.Customers;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.stereotype.Repository;

@Repository
public interface CustomersRepository extends JpaRepository<Customers, Long>{
}
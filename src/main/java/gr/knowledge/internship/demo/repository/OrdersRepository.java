package gr.knowledge.internship.demo.repository;

    import gr.knowledge.internship.demo.domain.OrderDetails;
    import gr.knowledge.internship.demo.domain.Orders;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.data.jpa.repository.Query;
    import org.springframework.data.repository.query.Param;
    import org.springframework.stereotype.Repository;

    import java.time.LocalDate;
    import java.util.Date;
    import java.util.List;
    import java.util.Optional;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {


    /* 5 */
    @Query("SELECT SUM(o.orderTotal), o.orderTotal FROM Orders o WHERE o.orderDate BETWEEN :startDate AND :endDate")
    Long getTotalRevenue(@Param("startDate") Date startDate, @Param("endDate") Date endDate);



}






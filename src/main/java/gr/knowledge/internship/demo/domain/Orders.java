package gr.knowledge.internship.demo.domain;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "order_id")
    private Long orderId;
    @Column (name = "customer_id")
    private Long customerId;
    @Column (name = "order_date")
    private Date orderDate;
    @Column (name = "order_total")
    private Long orderTotal;

    public Orders() {
}

    public Orders(Long orderId, Long customerId, Date orderDate, Long orderTotal) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.orderTotal = orderTotal;
    }
}
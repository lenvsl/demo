package gr.knowledge.internship.demo.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "customers")
public class Customers {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "customer_id")
    private Long customerId;
    @Column (name = "customer_name")
    private String customerName;
    @Column (name = "customer_email")
    private String customerEmail;

    public Customers() {
    }

    public Customers(Long customerId, String customerName, String customerEmail) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
    }
}
package gr.knowledge.internship.demo.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_details")
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "order_id")
    private Long orderId;

    @Column (name = "product_id")
    private Long productId;

    //@ManyToOne(optional = false, fetch = FetchType.LAZY)
    //private Products product;

    @Column (name = "quantity")
    private Long quantity;
    @Column (name = "price")
    private Long price;

}


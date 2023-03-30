package gr.knowledge.internship.demo.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "product_id")
    private Long productId;
    @Column (name = "product_name")
    private String productName;
    @Column (name = "product_description")
    private String productDescription;
    @Column (name = "product_price")
    private Long productPrice;
    @Column (name = "product_category")
    private String productCategory;
    @Column (name = "product_quantity")
    private Long productQuantity;
    public Products(List<Products> allProducts) {
    }


}



package gr.knowledge.internship.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor   //bazei constructor mono toy me args
@NoArgsConstructor    //bazei constructor mono toy xwris args
public class CustomerOrder {
    private Long productId;
    private Long quantity;
    private Date orderDate;
    private Long customerId;



}
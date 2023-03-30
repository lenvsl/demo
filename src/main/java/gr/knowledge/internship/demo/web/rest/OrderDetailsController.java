package gr.knowledge.internship.demo.web.rest;

import gr.knowledge.internship.demo.domain.CustomerOrder;
import gr.knowledge.internship.demo.domain.OrderDetails;
import gr.knowledge.internship.demo.domain.Products;
import gr.knowledge.internship.demo.service.OrderDetailsService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/knowledge")
public class OrderDetailsController {

    private final OrderDetailsService orderDetailsService;

    public OrderDetailsController(OrderDetailsService orderDetailsService) {
        this.orderDetailsService = orderDetailsService;
    }


    /* GetAll */
    @GetMapping("/orderDetails")
    public List<OrderDetails> getAllOrderDetails() {
        return orderDetailsService.findAllOrderDetails();
    }


    /* GetById */
    @GetMapping("/getOrderDetailsById/{id}")
    Optional<OrderDetails> getOrderDetailsById(@PathVariable(value = "id") Long id) {
        return orderDetailsService.getOrderDetailsById(id);
    }


    /* POST (createNew) */
    @PostMapping("/createNewOrderDetail")
    public OrderDetails saveOrderDetails(@RequestBody OrderDetails orderDetails) {
        return orderDetailsService.saveOrderDetails(orderDetails);
    }


    /* PUT (update) */
    @PutMapping("/updateOrderDetails")
    public OrderDetails updateOrderDetails(@RequestBody OrderDetails orderDetails) {
        return orderDetailsService.saveOrderDetails(orderDetails);
    }


    /* DELETE ById */
    @DeleteMapping("/deleteOrderDetailById/{id}")
    void deleteOrderDetailById(@PathVariable Long id) {
        orderDetailsService.deleteOrderDetailById(id);
    }

/*
    @PatchMapping("/orderDetails/{productName}/{quantity}")
    public OrderDetails updateAfterProductSold(
            @PathVariable("productName") String productName,
            @PathVariable("quantity") Long quantity){
        return orderDetailsService.updateForProduct(productName, quantity);
    }*/





    /* 6 */

    @GetMapping("/top_selling_products_of_the_week")
    public Map<Long, List<Long>> getProductsByDates(@RequestParam("startDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate,
                                                        @RequestParam("endDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate) {
        return orderDetailsService.getProductsByDates(startDate,endDate);
    }



}








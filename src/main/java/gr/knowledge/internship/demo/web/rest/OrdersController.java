package gr.knowledge.internship.demo.web.rest;

import gr.knowledge.internship.demo.domain.CustomerOrder;
import gr.knowledge.internship.demo.domain.OrderDetails;
import gr.knowledge.internship.demo.domain.Orders;
import gr.knowledge.internship.demo.domain.Products;
import gr.knowledge.internship.demo.service.OrderDetailsService;
import gr.knowledge.internship.demo.service.OrdersService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/knowledge")
public class OrdersController {

    private final OrdersService ordersService;
    private OrderDetailsService orderDetailsService;

    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }


    /* GetAll */
    @GetMapping("/order")
    public List<Orders> getAllOrders() {
        return ordersService.findAllOrders();
    }


    /* GetById */
    @GetMapping("/getOrderById/{id}")
    public Optional<Orders> getOrderById(@PathVariable(value = "id") Long id) {
        return ordersService.getOrderById(id);
    }


    /* POST (createNew) */
    @PostMapping("/createNewOrder")
    public Orders saveOrders(@RequestBody Orders orders) {
        return ordersService.saveOrders(orders);
    }


    /* PUT (update) */
    @PutMapping("/updateOrder")
    public Orders updateOrders(@RequestBody Orders orders) {
        return ordersService.saveOrders(orders);
    }


    /* DELETE ById */
    @DeleteMapping("/deleteOrderById/{id}")
    void deleteOrderById(@PathVariable Long id) {
        ordersService.deleteOrderById(id);
    }


    /* 5 */
    @GetMapping("/revenue")
    public Long getTotalRevenue(@RequestParam("startDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate,
                                @RequestParam("endDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate) {
        Long revenue = ordersService.getTotalRevenue(startDate, endDate);

        return revenue;
    }



    /* 4 */
    @PutMapping("/OrdDet")
    public Orders updateOrderDetails(@RequestBody CustomerOrder customerOrder) {
         return ordersService.createOrderForCustomer(customerOrder);
    }


}

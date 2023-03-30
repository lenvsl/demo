package gr.knowledge.internship.demo.service;

import gr.knowledge.internship.demo.domain.OrderDetails;
import gr.knowledge.internship.demo.domain.Orders;
import gr.knowledge.internship.demo.domain.Products;
import gr.knowledge.internship.demo.repository.OrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderDetailsService {


    /* sundesi me repository */
    private OrderDetailsRepository orderDetailsRepository;
    @Autowired
    private ProductsService productsService;
    private OrdersService ordersService;


    /* to arxikopoiw gia na trexei */
    public OrderDetailsService(OrderDetailsRepository orderDetailsRepository) {
        this.orderDetailsRepository = orderDetailsRepository;
       }




    /* getAll method */
    @Transactional(readOnly = true)
    public List<OrderDetails> findAllOrderDetails() {
        return orderDetailsRepository.findAll();
    }


    /* getById method */
    @Transactional(readOnly = true)
    public Optional<OrderDetails> getOrderDetailsById(Long id){
            return orderDetailsRepository.findById(id);
        }


    /* SAVE method */
    @Transactional
    public OrderDetails saveOrderDetails(OrderDetails orderDetails){
    return orderDetailsRepository.save(orderDetails);
    }


    /* deleteById method */
    @Transactional
    public void deleteOrderDetailById(Long id) {
        orderDetailsRepository.deleteById(id);
    }




    /* a) elegxo an yparxei to proion sto orderDetails */

    private OrderDetails getDetailsByProduct (Products products) {
        OrderDetails result;  //arxikopoihsh xreiazetai gt ti tha ginei an den ftasei pote sto optional;
        Optional<OrderDetails> details = orderDetailsRepository.getOrderDetailsById(products.getProductId());

        if (details.isPresent()) {
            result = details.get();
        } else {
            throw new RuntimeException("not found");
        }
        return result;
    }


    /* b) elegxo an yparxei h posothta poy epithimei */
    /* askisi konnoy γ   (24.3.2013) */

    private Boolean checkOrderDetails (OrderDetails details, Long quantity) {
        boolean result = false;

        if(details.getQuantity() != null && details.getQuantity() >= quantity){
            result = true;
        }

        return result;
    }



    Boolean checkOrderQuantity(Long productQuantity, Long inputQuantity) {
        boolean result = true;
        if(productQuantity <= inputQuantity){
            result = false;
        }
        return result;
    }



       /* 4 */

    @Transactional
    public OrderDetails updateForProduct(String productName, Long quantity){

            OrderDetails result;
            Products product = productsService.getProductByName(productName);
            OrderDetails details = getDetailsByProduct(product);

        if(ordersService.sygkrisiQuantity(product.getProductQuantity(),quantity)){
            details.setQuantity(details.getQuantity() - quantity);
            result = orderDetailsRepository.save(details);
        } else {
            throw new RuntimeException("sale did not happen");
        }
        return result;
    }



    /* askisi konnoy ε   (24.3.2013) */

    public void createDetailsByOrder(Products product, Orders order, Long quantity){
        OrderDetails result = new OrderDetails();
        result.setProductId(product.getProductId());
        result.setOrderId(order.getOrderId());
        result.setQuantity(quantity);
        result.setPrice(product.getProductPrice());

        orderDetailsRepository.save(result);
    }



    /* 6 */
/*
    public Map<Long,List<Long>> getProductsByDates(Date startDate, Date endDate) {

        Map<Long, List<Long>> ordersMap = new HashMap<>();
        List<OrderDetails> orderDetails = orderDetailsRepository.getProductsOfTheWeek2(startDate,endDate);

        for (OrderDetails detail : orderDetails) {

            if (ordersMap.containsKey(detail.getProductId())) {
                List<Long> ordersIdList = ordersMap.get(detail.getProductId());
                ordersIdList.add(detail.getOrderId());
                ordersMap.put(detail.getProductId(), ordersIdList);
            } else {

                List<Long> ordersList = new ArrayList<>();
                ordersList.add(detail.getOrderId());
                ordersMap.put(detail.getProductId(), ordersList);
            }
        }
        return ordersMap;
    }
*/
/* postman: http://localhost:8080/knowledge/top_selling_products_of_the_week?startDate=2023-03-20&endDate=2023-03-28 */

    public Map<Long,List<Long>> getProductsByDates(Date startDate, Date endDate) {

        Map<Long, Integer> productSalesCount = new HashMap<>();
        Map<Long, List<Long>> ordersMap = new HashMap<>();
        List<OrderDetails> orderDetails = orderDetailsRepository.getProductsOfTheWeek2(startDate,endDate);

        for (OrderDetails detail : orderDetails) {
            Long productId = detail.getProductId();
            if (ordersMap.containsKey(productId)) {
                List<Long> ordersIdList = ordersMap.get(productId);
                ordersIdList.add(detail.getOrderId());
                ordersMap.put(productId, ordersIdList);
            } else {

                List<Long> ordersList = new ArrayList<>();
                ordersList.add(detail.getOrderId());
                ordersMap.put(productId, ordersList);
            }
            //count the number of times each product was ordered
            if (productSalesCount.containsKey(productId)) {
                productSalesCount.put(productId, productSalesCount.get(productId) + 1);
            } else {
                productSalesCount.put(productId, 1);
            }
        }

    //Sort the products based on their sales count
            List<Long> sortedProductIds = productSalesCount.entrySet().stream()
                    .sorted(Map.Entry.<Long, Integer>comparingByValue().reversed())
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());

    //Return the sorted product IDs along with their order IDs
            Map<Long, List<Long>> sortedOrdersMap = new LinkedHashMap<>();
            for (Long productId : sortedProductIds) {
                sortedOrdersMap.put(productId, ordersMap.get(productId));
            }
            return sortedOrdersMap;
    }



    // Optional  <OrderDetails> orderDetails = orderDetailsRepository.getByQuantity(quantity);
    // OrderDetails quant = orderDetailsRepository.


    // @Transactional
   // public Long allOrderDetails() {
   // List<OrderDetails> orderDetails = OrderDetailsRepository.findAll();
   //     return OrderDetailsRepository.getAllPrice(Long price);
   // }
}

/*
    @Transactional
    public Long allProducts() {
        List<Products> products = productsRepository.findAll();
        Long result = 0L;
        for (Products product: products){
            if (product.getProductQuantity() != null) {
                result = result + product.getProductQuantity();
            }
        }
        return result;
    }
*/

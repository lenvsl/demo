package gr.knowledge.internship.demo.repository;

        import gr.knowledge.internship.demo.domain.Products;
        import org.springframework.data.domain.Sort;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.data.jpa.repository.Query;
        import org.springframework.data.repository.query.Param;
        import org.springframework.stereotype.Repository;
        import org.springframework.web.bind.annotation.GetMapping;

        import java.util.List;
        import java.util.Optional;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Long> {

        //List<Products> findByProductCategoryAndProductQuantity(String productCategory, int productQuantity);

        /* Query with 2 params */
        @Query("SELECT pr FROM Products pr WHERE (:category IS NULL OR pr.productCategory = :category) AND pr.productQuantity = :quantity")
        List<Products> getProductsByProductCategory(@Param("category") Optional<String> category, @Param("quantity") long quantity);


        /* query get product by name */
        @Query("SELECT pr FROM Products pr WHERE (pr.productName = :name)")
        Optional<Products> getProductsByName(@Param("name") String name);


        /* 6 */
      //  SELECT

        /* 3.Write a query to retrieve all products in a specific category and sort them by price in descending order. */
        //@Query("SELECT p FROM Products p WHERE p.productCategory = ?1 ORDER BY p.productPrice DESC")
        //List<Products> findAllByCategoryOrderByPriceDesc(String category);
        List<Products> findByProductCategory(String productCategory, Sort sort);


        //@Override
        //boolean existsById(Long aLong);
}
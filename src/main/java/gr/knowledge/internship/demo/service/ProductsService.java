package gr.knowledge.internship.demo.service;

import gr.knowledge.internship.demo.domain.Customers;
import gr.knowledge.internship.demo.domain.OrderDetails;
import gr.knowledge.internship.demo.domain.Products;
import gr.knowledge.internship.demo.repository.ProductsRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductsService {


    /* sundesi me repository */
    private ProductsRepository productsRepository;


    /* to arxikopoiw gia na trexei */
    public ProductsService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }


    /* getAll method */
    @Transactional(readOnly = true)
    public List<Products> findAllProducts() {
        return productsRepository.findAll();
    }


    /* getById method */
    /*
    @Transactional(readOnly = true)
    public Optional<Products> getProductById(Long id) {
        return productsRepository.findById(id); //.orElseThrow(() -> new EntityNotFoundException("Product not found with ID: " + id));
    }
    */

    @Transactional(readOnly = true)
    public boolean getProductById(Long id) {
        return productsRepository.existsById(id);
    }


    /* SAVE method */
    @Transactional
    public Products saveProducts(Products products) {
        return productsRepository.save(products);
    }


    /* deleteById method */
    @Transactional
    public void deleteProductById(Long id) {
        productsRepository.deleteById(id);
    }


    /* Exception */
    public Products checkIfEntityExists(Long id) {
        return productsRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product with id " + id + " doesn't exist."));
    }


    /* query with 2 params */
    //public List<Products> getProductsByCategoryAndQuantity(String productCategory, int productQuantity) {
    //    return productsRepository.findByProductCategoryAndProductQuantity(productCategory, productQuantity);
    //}

    public List<Products> getProductsByProductCategory(Optional<String> category, long productQuantity) {
        return productsRepository.getProductsByProductCategory(category, productQuantity);
    }


    /* palio Patch */
    /* public Products partiallyUpdateProduct(Long productId,Products partialProduct) {
        Products existingProduct = productsRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product with id " + productId + " doesn't exist."));

        if (partialProduct.getProductId() != null) {
            existingProduct.setProductId(partialProduct.getProductId());
        }

        if (partialProduct.getProductName() != null) {
            existingProduct.setProductName(partialProduct.getProductName());
        }

        if (partialProduct.getProductDescription() != null) {
            existingProduct.setProductDescription(partialProduct.getProductDescription());
        }

        if (partialProduct.getProductCategory() != null) {
            existingProduct.setProductCategory(partialProduct.getProductCategory());
        }

        if (partialProduct.getProductPrice() != null) {
            existingProduct.setProductPrice(partialProduct.getProductPrice());
        }

        if (partialProduct.getProductQuantity() != null) {
            existingProduct.setProductQuantity(partialProduct.getProductQuantity());
        }

        return productsRepository.save(existingProduct);
    }*/


    /* Patch */
    public Products partiallyUpdateProduct(Long productId, Products partialProduct) {
        Optional<Products> existingProduct = productsRepository.findById(productId);
        if (!existingProduct.isPresent()) {
            throw new RuntimeException("Not found");
        }

        if (partialProduct.getProductId() != null) {
            existingProduct.get().setProductId(partialProduct.getProductId());
        }

        if (partialProduct.getProductName() != null) {
            existingProduct.get().setProductName(partialProduct.getProductName());
        }

        if (partialProduct.getProductDescription() != null) {
            existingProduct.get().setProductDescription(partialProduct.getProductDescription());
        }

        if (partialProduct.getProductCategory() != null) {
            existingProduct.get().setProductCategory(partialProduct.getProductCategory());
        }

        if (partialProduct.getProductPrice() != null) {
            existingProduct.get().setProductPrice(partialProduct.getProductPrice());
        }

        if (partialProduct.getProductQuantity() != null) {
            existingProduct.get().setProductQuantity(partialProduct.getProductQuantity());
        }

        return productsRepository.save(existingProduct.get());

    }


    /* Μεθοδος που μετραει απο την λιστα των προιοντων την τιμη του quantity */
    @Transactional
    public Long allProducts() {
        List<Products> products = productsRepository.findAll();
        Long result = 0L;
        for (Products product : products) {
            if (product.getProductQuantity() != null) {
                result = result + product.getProductQuantity();
            }
        }
        return result;
    }


    /* 3.Write a query to retrieve all products in a specific category and sort them by price in descending order. */
    //public List<Products> getAllProductsInCategorySortedByPriceDesc(String productCategory) {
    //    return productsRepository.findAllByCategoryOrderByPriceDesc(productCategory);
    //}

    public List<Products> getAllProductsInCategorySortedByPriceDesc(String productCategory) {
        Sort sortByProductPriceDesc = Sort.by("productPrice").descending();
        return productsRepository.findByProductCategory(productCategory, sortByProductPriceDesc);
    }

    public List<Products> getByProductCategory(String productCategory) {
        List<Products> result = new ArrayList<>();
        List<Products> products = productsRepository.findAll();
        for (Products product : products) {
            if (product.getProductCategory() != null && product.getProductCategory().equals(productCategory)) {
                result.add(product);
            }
        }
        return result;
    }

    /*5.Write a query to retrieve the total revenue generated by the store in the last month.*/



    /* 4 */

    /* elegxo an yparxei to proion p thelw genika sto products */
    public Products getProductByName(String productName) {
        Products result ;
        Optional<Products> product = productsRepository.getProductsByName(productName);
        if (product.isPresent()) {
            result = product.get();
        } else {
            throw new RuntimeException("not found");
        }
        return result;
    }


    /* askisi konnoy b    (24.3.2013) */
    public Products getById(Long productId){
        Products result;  //arxikopoihsh
        Optional<Products> details = productsRepository.findById(productId);
        if(details.isPresent()){
            result = details.get();
        } else {
            throw new RuntimeException("not found");
        }
        return result;

    }



    /* askisi konnoy στ    (24.3.2013) */
    public void updateProductQuantity(Products products, Long quantity){

        products.setProductQuantity(products.getProductQuantity()-quantity);
        productsRepository.save(products);

    }
}
package com.learnjava.completablefuture;

import com.learnjava.domain.Product;
import com.learnjava.service.InventoryService;
import com.learnjava.service.ProductInfoService;
import com.learnjava.service.ReviewService;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProductServiceUsingCompletablaFutureTest {

    private ProductInfoService pis = new ProductInfoService();
    private ReviewService rs = new ReviewService();
    private InventoryService is = new InventoryService();

    ProductServiceUsingCompletablaFuture pscf = new
            ProductServiceUsingCompletablaFuture(pis, rs, is);

    @Test
    void retrieveProductDetails() {
        //given
        String productId = "ABC123";

        //when
        Product product = pscf.retrieveProductDetails(productId);

        //then
        assertNotNull(product);
        assertTrue(product.getProductInfo().getProductOptions().size() > 0);
        assertNotNull(product.getReview());
    }

    @Test
    void retrieveProductDetails_approach2() {
        //given
        String productId = "ABC123";

        //when
        CompletableFuture<Product> productFuture = pscf.retrieveProductDetails_approach2(productId);
        Product product = productFuture.join();
        //then
        assertNotNull(product);
        assertTrue(product.getProductInfo().getProductOptions().size() > 0);
        assertNotNull(product.getReview());
    }

    @Test
    void retrieveProductDetails_approach2_test2() {
        //given
        String productId = "ABC123";

        //when
        CompletableFuture<Product> productFuture = pscf.retrieveProductDetails_approach2(productId);

        //then
        productFuture.thenAccept((product -> {
            assertNotNull(product);
            assertTrue(product.getProductInfo().getProductOptions().size() > 0);
            assertNotNull(product.getReview());
        }));


    }

    @Test
    void retrieveProductDetailsWithInventory() {
        //given
        String productId = "ABC123";

        //when
        Product product = pscf.retrieveProductDetailsWithInventory(productId);

        //then
        assertNotNull(product);
        assertTrue(product.getProductInfo().getProductOptions().size() > 0);
        product.getProductInfo().getProductOptions()
                .forEach(productOption -> {
                    assertNotNull(productOption.getInventory());
                });
        assertNotNull(product.getReview());
    }
}
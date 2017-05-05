/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;


import com.iti.model.entites.Product;
import com.iti.springecommerce.service.ProductService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Mrawi
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("ServiceConfig.xml");
         ProductService productRef = (ProductService) context.getBean("productService");
         Product p = productRef.getProduct(2);
         p.setProductName("updated name");
        
//         ProductImage img = new ProductImage();
//         img.setUrl("new image test2");
        System.out.println("the result is:" +   productRef.deleteCategory(2));
    }
}

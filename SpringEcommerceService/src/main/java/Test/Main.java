/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import com.iti.model.entity.ProductImage;
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
         ProductImage img = new ProductImage();
         img.setUrl("new image");
        System.out.println("the result is:" +  productRef.addImageToProduct(2, img));
    }
}

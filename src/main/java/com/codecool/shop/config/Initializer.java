package com.codecool.shop.config;

import com.codecool.shop.dao.GenreDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.PublisherDao;
import com.codecool.shop.dao.implementation.GenreDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.PublisherDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.Genre;
import com.codecool.shop.model.Publisher;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        GenreDao productCategoryDataStore = GenreDaoMem.getInstance();
        PublisherDao supplierDataStore = PublisherDaoMem.getInstance();

        //setting up a new supplier
        Publisher amazon = new Publisher("Amazon", "Digital content and services");
        supplierDataStore.add(amazon);
        Publisher lenovo = new Publisher("Lenovo", "Computers");
        supplierDataStore.add(lenovo);

        //setting up a new product category
        Genre tablet = new Genre("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(tablet);

        //setting up products and printing it
        productDataStore.add(new Product("Amazon Fire", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon));
        productDataStore.add(new Product("Lenovo IdeaPad Miix 700", 479, "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo));
        productDataStore.add(new Product("Amazon Fire HD 8", 89, "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon));
        productDataStore.add(new Product("The diving bell and the butterfly", 49.9f, "USD", "", tablet, amazon));
        productDataStore.add(new Product("The troop", 49.9f, "USD", "", tablet, amazon));
        productDataStore.add(new Product("Player piano", 49.9f, "USD", "", tablet, amazon));
        productDataStore.add(new Product("Homeland", 49.9f, "USD", "", tablet, amazon));
        productDataStore.add(new Product("A scanner darkly", 49.9f, "USD", "", tablet, amazon));
    }

}

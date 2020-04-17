package com.codecool.shop.controller;

import com.codecool.shop.dao.DataSourceException;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.CartDaoJdbc;
import com.codecool.shop.dao.implementation.ProductDaoJdbc;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.model.CartItem;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDataStoreDB = ProductDaoJdbc.getInstance();
        CartDaoJdbc cartDataStoreDB = CartDaoJdbc.getInstance();
        List<CartItem> allCartItem = new ArrayList<>();
        int itemsInCart = 0;

        try {
            allCartItem = cartDataStoreDB.getAll();
        } catch (DataSourceException e) {
            System.err.println("Unable to reach all cart entries.");
            System.exit(1);
        }

        try {
            itemsInCart = cartDataStoreDB.itemsInCart();
        } catch (DataSourceException e) {
            System.err.println("Unable to reach the number of cart entries.");
            System.exit(1);
        }

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("products", productDataStoreDB.getAll());
        context.setVariable("cartItems", allCartItem);
        context.setVariable("cartItemCounter", itemsInCart);
        // // Alternative setting of the template context
        // Map<String, Object> params = new HashMap<>();
        // params.put("category", productCategoryDataStore.find(1));
        // params.put("products", productDataStore.getBy(productCategoryDataStore.find(1)));
        // context.setVariables(params);
        engine.process("product/index.html", context, resp.getWriter());
    }

}

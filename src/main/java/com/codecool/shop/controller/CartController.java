package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductDaoJdbc;
import com.sun.tools.sjavac.server.RequestHandler;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.slf4j.Logger;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.LoggerFactory;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.stream.Collectors;

import com.google.gson.Gson;

@WebServlet(urlPatterns = {"/shoppingCart"})
public class CartController extends HttpServlet {
    public static final Logger logger = LoggerFactory.getLogger(CartController.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        BasicConfigurator.configure();
        System.out.println(logger.isTraceEnabled());
        logger.trace("Tracing");
        logger.debug("debugging");
        logger.info("this is info");
        logger.warn("this is WARNING");
        logger.error("this is ERROR");
        String cartJson = req.getReader().lines().collect(Collectors.joining());
        //System.out.println(cartJson);
        String param = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        RequestIdContainer requestIdContainer = new Gson().fromJson(param, RequestIdContainer.class);
        int id = Integer.parseInt(requestIdContainer.getId());
        String author = requestIdContainer.getAuthor();
        String title = requestIdContainer.getTitle();
        String price = requestIdContainer.getPrice();
        //System.out.println(id + author + title + price);
        String protocol = req.getProtocol();
        //System.out.println(req.getHeaderNames());
        //System.out.println(req.getContentType());

        //System.out.println(protocol);
        //ProductDao productDataStoreDB = ProductDaoJdbc.getInstance();
        //System.out.println(resp.toString());

        Gson gson = new Gson();
        //System.out.println(cartJson);

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        //context.setVariable("products", productDataStoreDB.getAll());

        // // Alternative setting of the template context
        // Map<String, Object> params = new HashMap<>();
        // params.put("category", productCategoryDataStore.find(1));
        // params.put("products", productDataStore.getBy(productCategoryDataStore.find(1)));
        // context.setVariables(params);
        //engine.process("product/index.html", context, resp.getWriter());
    }
}


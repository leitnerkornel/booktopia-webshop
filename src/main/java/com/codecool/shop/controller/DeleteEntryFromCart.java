package com.codecool.shop.controller;

import com.codecool.shop.dao.DataSourceException;
import com.codecool.shop.dao.implementation.CartDaoJdbc;
import com.codecool.shop.json.CartDataContainer;
import com.codecool.shop.json.CartEntryContainer;
import com.codecool.shop.model.CartItem;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;


import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.DataSourceException;
import com.codecool.shop.dao.implementation.CartDaoJdbc;
import com.codecool.shop.json.CartDataContainer;
import com.codecool.shop.model.CartItem;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;


@WebServlet(urlPatterns = {"/deleteOneEntryFromShoppingCart"})
public class DeleteEntryFromCart extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        CartEntryContainer cartEntryContainer = new Gson().fromJson(param, CartEntryContainer.class);
        int cartID = cartEntryContainer.getIdAsNumber();

        System.out.println(cartID);

        CartDaoJdbc cartDataStoreDB = CartDaoJdbc.getInstance();

        try {
            cartDataStoreDB.removeItemById(cartID);
        } catch (DataSourceException e) {
            System.err.println("Couldn't delete entry with this cart ID: " + cartID);
            System.exit(1);
        }

        String responseJSON = new Gson().toJson("Was success from deleteEntry");

        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(responseJSON);
        out.flush();

        /*TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());*/
        //context.setVariable("products", productDataStoreDB.getAll());

        // // Alternative setting of the template context
        // Map<String, Object> params = new HashMap<>();
        // params.put("category", productCategoryDataStore.find(1));
        // params.put("products", productDataStore.getBy(productCategoryDataStore.find(1)));
        // context.setVariables(params);
        //engine.process("product/index.html", context, resp.getWriter());
    }
}








package com.codecool.shop.controller;

import com.codecool.shop.dao.DataSourceException;
import com.codecool.shop.dao.implementation.CartDaoJdbc;
import com.codecool.shop.json.CartEntryContainer;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/getCartIdByBookId"})
public class GetCartIdByBookId extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        CartEntryContainer cartEntryContainer = new Gson().fromJson(param, CartEntryContainer.class);
        int bookId = cartEntryContainer.getIdAsNumber();

        System.out.println(bookId);

        CartDaoJdbc cartDataStoreDB = CartDaoJdbc.getInstance();

        String cartIdFromDB = "";
        try {
            cartIdFromDB = Integer.toString(cartDataStoreDB.getCartIdByBookId(bookId));
        } catch (DataSourceException e) {
            System.err.println("Couldn't retrieve newly added cart entry's id.");
            System.exit(1);
        }

        String responseJSON = new Gson().toJson(cartIdFromDB);

        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(responseJSON);
        out.flush();
    }
}

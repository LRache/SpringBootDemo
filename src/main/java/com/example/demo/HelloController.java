package com.example.demo;

import com.example.demo.response.Response;
import com.example.demo.response.ResponseData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.sql.Statement;

@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

    @GetMapping("/tripplan")
    public Response tripplan() {
        if (Globals.sqlConnection == null) {
            return new Response(1, "");
        }
        try {
            Statement stmt = Globals.sqlConnection.createStatement();
        } catch (SQLException e) {

        }
        return "";
    }
}

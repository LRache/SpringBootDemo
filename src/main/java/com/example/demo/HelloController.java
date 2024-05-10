package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

    @GetMapping("/tripplan")
    public ResponseEntity<Object> tripplan() {
        if (Globals.sqlConnection == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("server error");
        }
        try {
            Statement stmt = Globals.sqlConnection.createStatement();
            List<TripPlan> plans = new LinkedList<>();

        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("server error");
        }
        return ResponseEntity.status(HttpStatus.OK).body("success");
    }
}

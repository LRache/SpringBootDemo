package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class TripPlanController {
    @CrossOrigin(origins = "*")
    @GetMapping("/tripplan")
    public ResponseEntity<Object> getTripPlan() {
        if (Globals.sqlConnection == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("server error");
        }
        try {
            Statement stmt = Globals.sqlConnection.createStatement();
            List<TripPlan> plans = new LinkedList<>();
            ResultSet result = stmt.executeQuery("select * from tripplan");
            while (result.next()) {
                TripPlan plan = new TripPlan();
                plan.id = result.getInt("id");
                plan.des = result.getString("des");
                plan.city = result.getString("city");
                plan.date = result.getString("datetime");
                plan.status = result.getInt("status");
                plan.note = result.getString("note");
                plans.add(plan);
            }
            return ResponseEntity.status(HttpStatus.OK).body(plans);
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("[]");
        }
    }

    @PostMapping("/tripplan")
    public ResponseEntity<String> addTripPlan(@RequestBody TripPlan plan) {
        if (Globals.sqlConnection == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("server error");
        }
        try {
            PreparedStatement stmt = Globals.sqlConnection.prepareStatement(
                    "insert into tripplan(id, des, city, datetime, status, note) values(?, ?, ?, ?, ?, ?)"
            );
            stmt.setInt(1, plan.id);
            stmt.setString(2, plan.des);
            stmt.setString(3, plan.city);
            stmt.setString(4, plan.date);
            stmt.setInt(5, plan.status);
            stmt.setString(6, plan.note);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("server error");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("");
    }

    @PutMapping("/tripplan")
    public ResponseEntity<String> updateTripPlan(@RequestBody TripPlan plan) {
        if (Globals.sqlConnection == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("server error");
        }
        try {
            PreparedStatement stmt = Globals.sqlConnection.prepareStatement(
                    "update tripplan set des = ?, city = ?, datetime = ?, status = ?, note = ? where id = ?"
            );
            stmt.setString(1, plan.des);
            stmt.setString(2, plan.city);
            stmt.setString(3, plan.date);
            stmt.setInt(4, plan.status);
            stmt.setString(5, plan.note);
            stmt.setInt(6, plan.id);
            stmt.executeUpdate();

            return ResponseEntity.status(HttpStatus.OK).body("");
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("server error");
        }
    }
}

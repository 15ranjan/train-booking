package com.example.trainbooking.controller;

import com.example.trainbooking.model.*;
import com.example.trainbooking.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping("/purchase")
    public ResponseEntity<Receipt> purchaseTicket(@RequestBody TicketRequest ticketRequest) {
        Receipt receipt =  ticketService.purchaseTicket(ticketRequest);
        return ResponseEntity.ok(receipt);
    }

    @PostMapping("/testing")
    public void testing(){
        System.out.println("api working");
    }

    @GetMapping("/seats")
    public ResponseEntity<List<SeatInfo>> getUsersBySection(String section){
        List<SeatInfo> usersInSection = ticketService.getUsersBySection(section);
        return ResponseEntity.ok(usersInSection);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<Receipt> removeUserByEmail(@RequestParam String email){
        Receipt removedReceipt = ticketService.removeUser(email);

        if(removedReceipt != null){
            return ResponseEntity.ok(removedReceipt);
        }
        return null;
    }
    @PutMapping("/modify")
    public ResponseEntity<Receipt> modifyUserSeat(@RequestParam String email, String newSeat){
        Receipt updatedReceipt = ticketService.modifyUserSeat(email, newSeat);
        if(updatedReceipt != null){
            return ResponseEntity.ok(updatedReceipt);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}

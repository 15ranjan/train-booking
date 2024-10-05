package com.example.trainbooking.services;

import com.example.trainbooking.model.*;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class TicketService {

    //private List<User> userList = new ArrayList<>();
    private List<String> sectionASeats = new ArrayList<>();
    private List<String> sectionBSeats = new ArrayList<>();
    private List<Receipt>  receiptList = new ArrayList<>();

    public TicketService(){
        for(int i = 1; i <= 2; i++){
            sectionASeats.add("A" + i);
            sectionBSeats.add("B" + i);
        }
    }
    public Receipt modifyUserSeat(String email, String newSeat){
        //1. Fetch User's Ticket
        Receipt userReceipt = null;

        for(Receipt receipt : receiptList){
            if(receipt.getUser().getEmail().equalsIgnoreCase(email)){
                userReceipt = receipt;
                break;
            }
        }
        if (userReceipt == null) {
            throw new RuntimeException("User not found.");
        }
        //2. Validate requested seat
        if(!sectionASeats.contains(newSeat) && !sectionBSeats.contains(newSeat)){
            throw new RuntimeException("Seat is unavailable or does not exist.");
        }
        // 3. Release old seat
        String oldSeat = userReceipt.getSeat();
        if(oldSeat.startsWith("A")){
            sectionASeats.add(oldSeat);
        }else if(oldSeat.startsWith("B")){
            sectionBSeats.add(oldSeat);
        }
        //4. Assign new Seat
        if(newSeat.startsWith("A")){
            sectionASeats.remove(newSeat);
        }else if(newSeat.startsWith("B")){
            sectionBSeats.remove(newSeat);
        }
        userReceipt.setSeat(newSeat);
        return userReceipt;
    }

    public Receipt removeUser(String email){
        Receipt removedReceipt = null;
        for(Receipt receipt : receiptList){
            if(receipt.getUser().getEmail().equalsIgnoreCase(email)){
                removedReceipt = receipt;
                break;
            }
        }
        if(removedReceipt != null){
            String seat = removedReceipt.getSeat();;
            receiptList.remove(removedReceipt);

            if(seat.startsWith("A")){
                sectionASeats.add(seat);
            }else if(seat.startsWith("B")){
                sectionBSeats.add(seat);
            }
            return removedReceipt;
        }
        return null;

    }
    public Receipt purchaseTicket(TicketRequest ticketRequest){
        String seat = assignSeat();
        Receipt receipt = new Receipt(ticketRequest.getFrom(),ticketRequest.getTo(),
                                       ticketRequest.getUser(), 20.0, seat);
        receiptList.add(receipt);
        return receipt;
    }
    public String assignSeat(){
        if(!sectionASeats.isEmpty()){
            return sectionASeats.remove(0);
        }else if(!sectionBSeats.isEmpty()){
            return sectionBSeats.remove(0);
        }else{
            throw new RuntimeException("No Seats available!");
        }
    }
    public List<SeatInfo> getUsersBySection(String section){
        List<SeatInfo> seatInfoList = new ArrayList<>();
        for(Receipt receipt : receiptList){
            User user = receipt.getUser();
            String seat = receipt.getSeat();
            if(section.equalsIgnoreCase("A")){
                if(seat.startsWith("A")){
                    seatInfoList.add(new SeatInfo(user.getFirstName(), user.getLastName(), user.getEmail(), seat));
                }
            }
            if(section.equalsIgnoreCase("B")){
                if(seat.startsWith("B")){
                    seatInfoList.add(new SeatInfo(user.getFirstName(), user.getLastName(), user.getEmail(), seat));
                }
            }
        }
        return seatInfoList;
    }


}

package com.example.trainbooking.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class TicketRequest {
    private String from;
    private String to;
    private User user;

}

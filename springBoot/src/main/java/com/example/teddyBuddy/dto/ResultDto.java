package com.example.teddyBuddy.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultDto<T> {
    private String msg;
    private boolean success;
    private T detail;
}
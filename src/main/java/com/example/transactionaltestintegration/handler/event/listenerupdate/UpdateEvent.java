package com.example.transactionaltestintegration.handler.event.listenerupdate;

import com.example.transactionaltestintegration.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UpdateEvent {
    private String content;
    private Comment comment;
}

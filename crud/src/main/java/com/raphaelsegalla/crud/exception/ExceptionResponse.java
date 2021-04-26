package com.raphaelsegalla.crud.exception;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse implements Serializable {

    private static final long serialVersionUID = 7671861392381445936L;

    private Date timestamp;
    private String message;
    private String details;

}

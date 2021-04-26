package com.raphaelsegalla.auth.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserDto implements Serializable {

    private static final long serialVersionUID = -1861259971513308184L;

    private String username;
    private String password;
}

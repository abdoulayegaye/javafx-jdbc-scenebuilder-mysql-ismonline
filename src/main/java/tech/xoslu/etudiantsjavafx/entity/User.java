package tech.xoslu.etudiantsjavafx.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private int id;
    private String name;
    private String username;
    private String password;
}

package tech.xoslu.etudiantsjavafx.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Etudiant {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private double moyenne;

    public Etudiant(String nom, String prenom, String email, String telephone, double moyenne) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.moyenne = moyenne;
    }
}

package tech.xoslu.etudiantsjavafx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import tech.xoslu.etudiantsjavafx.entity.User;
import tech.xoslu.etudiantsjavafx.service.LoginServiceImpl;
import tech.xoslu.etudiantsjavafx.tools.Notification;
import tech.xoslu.etudiantsjavafx.tools.Outils;

import java.io.IOException;

public class LoginController {

    private LoginServiceImpl loginDb = new LoginServiceImpl();

    @FXML
    private PasswordField passwordPfd;

    @FXML
    private TextField usernameTfd;

    @FXML
    void login(ActionEvent event) throws IOException {
        String username = usernameTfd.getText().trim();
        String password = passwordPfd.getText().trim();
        if (username.isEmpty() || password.isEmpty()) {
            Notification.NotifError("Erreur", "Tous les champs sont requis !");
        } else {
            User user = loginDb.seConnecter(username, password);
            if (user != null) {
                Notification.NotifSuccess("Succés", "Connexion réussie !");
                Outils.load(event, "Gestion des étudiants", "/pages/etudiants.fxml");
            } else {
                Notification.NotifError("Erreur", "Username et/ou Password incorrects !");
            }
        }
    }

}

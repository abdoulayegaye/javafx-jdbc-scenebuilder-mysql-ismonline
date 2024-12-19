package tech.xoslu.etudiantsjavafx.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import tech.xoslu.etudiantsjavafx.entity.Etudiant;
import tech.xoslu.etudiantsjavafx.service.EtudiantServiceImpl;
import tech.xoslu.etudiantsjavafx.tools.Notification;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class EtudiantController implements Initializable {

    int id;

    private EtudiantServiceImpl dao = new EtudiantServiceImpl();

    @FXML
    private TableColumn<Etudiant, String> emailCol;

    @FXML
    private Button enregistrerBtn;

    @FXML
    private TableView<Etudiant> etudiantsTbv;

    @FXML
    private TableColumn<Etudiant, Double> moyenneCol;

    @FXML
    private TextField moyenneTfd;

    @FXML
    private TableColumn<Etudiant, String> nomCol;

    @FXML
    private TextField nomTfd;

    @FXML
    private TableColumn<Etudiant, String> prenomCol;

    @FXML
    private TextField prenomTfd;

    @FXML
    private TableColumn<Etudiant, String> telephoneCol;

    @FXML
    private TextField telephoneTfd;

    @FXML
    void clearAction(ActionEvent event) {
        viderChamps();
        enregistrerBtn.setDisable(false);
    }

    @FXML
    void deleteAction(ActionEvent event) {
        int ok = dao.delete(id);
        if (ok == 1){
            Notification.NotifSuccess("Succés", "Etudiant supprimé");
            loadTable();
            viderChamps();
            enregistrerBtn.setDisable(false);
        }
        else
            Notification.NotifError("Erreur", "Erreur de suppression");
    }

    @FXML
    void getData(MouseEvent event) {
        Etudiant e = etudiantsTbv.getSelectionModel().getSelectedItem();
        id = e.getId();
        nomTfd.setText(e.getNom());
        prenomTfd.setText(e.getPrenom());
        telephoneTfd.setText(e.getTelephone());
        moyenneTfd.setText(String.valueOf(e.getMoyenne()));
        enregistrerBtn.setDisable(true);
    }

    @FXML
    void saveAction(ActionEvent event) {
        String nom = nomTfd.getText();
        String prenom = prenomTfd.getText();
        String email = prenom.charAt(0)+nom+"@xoslu.tech";
        String telephone = telephoneTfd.getText();
        double moyenne = Double.parseDouble(moyenneTfd.getText());
        if (!dao.emailExisting(email)){
            Etudiant etudiant = new Etudiant(nom, prenom, email.toLowerCase(), telephone, moyenne);
            int ok = dao.create(etudiant);
            if (ok == 1){
                Notification.NotifSuccess("Succés", "Etudiant créé");
                loadTable();
                viderChamps();
            }
            else
                Notification.NotifError("Erreur", "Erreur de création");
        }else
            Notification.NotifError("Erreur", "Email déja existant");
    }

    @FXML
    void updateAction(ActionEvent event) {
        int idEtudiant = id;
        String nom = nomTfd.getText();
        String prenom = prenomTfd.getText();
        String email = prenom.charAt(0)+nom+"@xoslu.tech";
        String telephone = telephoneTfd.getText();
        double moyenne = Double.parseDouble(moyenneTfd.getText());
        if (!dao.emailExisting(email)){
            Etudiant etudiant = new Etudiant(idEtudiant, nom, prenom, email.toLowerCase(), telephone, moyenne);
            int ok = dao.update(etudiant);
            if (ok == 1){
                Notification.NotifSuccess("Succés", "Etudiant modifié");
                loadTable();
                viderChamps();
                enregistrerBtn.setDisable(false);
            }
            else
                Notification.NotifError("Erreur", "Erreur de modification");
        }else
            Notification.NotifError("Erreur", "Email existant");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadTable();
    }

    public ObservableList<Etudiant> getEtudiants() {
        ObservableList<Etudiant> etudiants = FXCollections.observableArrayList();
        List<Etudiant> etudiantsList = dao.getEtudiants();
        etudiants.addAll(etudiantsList);
        return etudiants;
    }

    public void loadTable() {
        etudiantsTbv.setItems(getEtudiants());
        prenomCol.setCellValueFactory(new PropertyValueFactory<Etudiant, String>("prenom"));
        nomCol.setCellValueFactory(new PropertyValueFactory<Etudiant, String>("nom"));
        emailCol.setCellValueFactory(new PropertyValueFactory<Etudiant, String>("email"));
        telephoneCol.setCellValueFactory(new PropertyValueFactory<Etudiant, String>("telephone"));
        moyenneCol.setCellValueFactory(new PropertyValueFactory<Etudiant, Double>("moyenne"));
    }

    public void viderChamps(){
        nomTfd.clear();
        prenomTfd.clear();
        telephoneTfd.setText("");
        moyenneTfd.setText("");
    }
}

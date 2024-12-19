package tech.xoslu.etudiantsjavafx.service;

import tech.xoslu.etudiantsjavafx.db.DbConnection;
import tech.xoslu.etudiantsjavafx.entity.Etudiant;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EtudiantServiceImpl {
    private DbConnection db = new DbConnection();
    private ResultSet rs;
    private int ok;

    public int create(Etudiant et) {
        String sql = "INSERT INTO etudiant VALUES(NULL,?,?,?,?,?)";
        try {
            db.initPrepar(sql);
            db.getPstm().setString(1, et.getNom());
            db.getPstm().setString(2, et.getPrenom());
            db.getPstm().setString(3, et.getEmail());
            db.getPstm().setString(4, et.getTelephone());
            db.getPstm().setDouble(5, et.getMoyenne());
            ok = db.executeMaj();
            db.closeConnection();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return ok;
    }

    public List<Etudiant> getEtudiants() {
        String sql = "SELECT * FROM etudiant";
        List<Etudiant> etudiants = new ArrayList<Etudiant>();
        try {
            db.initPrepar(sql);
            rs = db.getPstm().executeQuery();
            while(rs.next()) {
                Etudiant et = new Etudiant();
                et.setId(rs.getInt("id"));
                et.setNom(rs.getString("nom"));
                et.setPrenom(rs.getString("prenom"));
                et.setEmail(rs.getString("email"));
                et.setTelephone(rs.getString("telephone"));
                et.setMoyenne(rs.getDouble("moyenne"));
                etudiants.add(et);
            }
            db.closeConnection();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return etudiants;
    }

    public Etudiant getEtudiantById(int id) {
        String sql = "SELECT * FROM etudiant WHERE id = ?";
        Etudiant et = null;
        try {
            db.initPrepar(sql);
            db.getPstm().setInt(1, id);
            rs = db.getPstm().executeQuery();
            if(rs.next()) {
                et = new Etudiant();
                et.setId(rs.getInt("id"));
                et.setNom(rs.getString("nom"));
                et.setPrenom(rs.getString("prenom"));
                et.setEmail(rs.getString("email"));
                et.setTelephone(rs.getString("telephone"));
                et.setMoyenne(rs.getDouble("moyenne"));
            }
            db.closeConnection();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return et;
    }

    public int update(Etudiant et) {
        String sql = "UPDATE etudiant SET nom=?,prenom=?,email=?,telephone=?,moyenne=? WHERE id=?";
        try {
            db.initPrepar(sql);
            db.getPstm().setString(1, et.getNom());
            db.getPstm().setString(2, et.getPrenom());
            db.getPstm().setString(3, et.getEmail());
            db.getPstm().setString(4, et.getTelephone());
            db.getPstm().setDouble(5, et.getMoyenne());
            db.getPstm().setInt(6, et.getId());
            ok = db.executeMaj();
            db.closeConnection();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return ok;
    }

    public int delete(int id) {
        String sql = "DELETE FROM etudiant WHERE id=?";
        try {
            db.initPrepar(sql);
            db.getPstm().setInt(1, id);
            ok = db.executeMaj();
            db.closeConnection();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return ok;
    }

    public boolean emailExisting(String email) {
        boolean ok = false;
        String sql = "SELECT * FROM etudiant WHERE email = ?";
        try {
            db.initPrepar(sql);
            db.getPstm().setString(1, email);
            rs = db.executeSelect();
            if(rs.next()) {
                ok = true;
            }
            db.closeConnection();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return ok;
    }

}

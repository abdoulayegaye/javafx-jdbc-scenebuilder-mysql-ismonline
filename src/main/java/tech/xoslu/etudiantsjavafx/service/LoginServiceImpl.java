package tech.xoslu.etudiantsjavafx.service;

import tech.xoslu.etudiantsjavafx.db.DbConnection;
import tech.xoslu.etudiantsjavafx.entity.User;

import java.sql.ResultSet;

public class LoginServiceImpl {
    private DbConnection db = new DbConnection();

    public User seConnecter(String username, String password){
        User user = null;
        String sql = "SELECT * FROM utilisateur WHERE username=? AND password=?";
        try{
            db.initPrepar(sql);
            db.getPstm().setString(1, username);
            db.getPstm().setString(2, password);
            ResultSet rs = db.executeSelect();
            if (rs.next()){
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }
}

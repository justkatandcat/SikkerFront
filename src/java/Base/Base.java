package Base;

import static java.lang.System.out;
import java.sql.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author RubenHH
 */
public class Base {
    Connection c=null;
    Statement s=null;
    PreparedStatement p=null;
    ResultSet r=null;
    
    public void conexion() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            c=DriverManager.getConnection("jdbc:mysql://localhost/Sikker","root","n0m3l0");
            s=c.createStatement();
        }
        catch(SQLException error){
            out.println(error);
        }
    }
    
    public String verifica(String usu, String pass) {
        String msjc=null;
        try{
            String call="call sp_log(?,?);";
            String msj=null;
            p=c.prepareStatement(call);
            p.setString(1, usu);
            p.setString(2, pass);
            r=p.executeQuery();
            while(r.next()){
                msj=r.getString(1);
            }
            if(msj.equals("!=existe")){
                msjc="!=existe";
            }
            else if(msj.equals("!=contra")){
                msjc="!=contra";
            }
            else if(msj.equals("true")){
                msjc="true";
            }
        }catch(SQLException error){
            out.println(error);
        }
        return msjc;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servletHTML;

import Base.Base;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author RubenHH
 */
//@WebServlet(name = "ServletLog", urlPatterns = {"/ServletLog"})
public class ServletLog extends HttpServlet{
    
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException{
        Base b= new Base();
        String nombre=req.getParameter("email");
        String key=req.getParameter("key");
        try {
            b.conexion();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ServletLog.class.getName()).log(Level.SEVERE, null, ex);
        }
        String msj=b.verifica(nombre, key);
        res.setContentType("text/html");
        PrintWriter out = new PrintWriter(res.getOutputStream());
        out.println("<html>");
        if(msj.equals("!=existe")){
            out.println("<script>alert('Usuario inexistente');</script>");
            out.println("<script>window.top.location='index.html'</script>");
        }
        else if(msj.equals("!=contra")){
            out.println("<script>alert('Contraseña incorrecta');</script>");
            out.println("<script>window.top.location='index.html'</script>");
        }
        else if(msj.equals("true")){
            out.println("<script>alert('Contraseña Correcta');</script>");
            out.println("<script>window.top.location='index.html'</script>");
        }
        out.println("</body></html>");
        out.close();
    }
}

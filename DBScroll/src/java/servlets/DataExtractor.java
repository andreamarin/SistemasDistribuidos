/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author andreamarin y luis felipe landa
 */
public class DataExtractor extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            response.setContentType("text/html;charset=UTF-8");
            
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/SecondDatabase","root","root");
            Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            
            ResultSet rs = st.executeQuery("SELECT * FROM CUSTOMERS");
            Object[][] data = ResultSetToArray(rs);
            
            HttpSession session = request.getSession();
            session.setAttribute("data", data);
            session.setAttribute("index", 0);
            response.sendRedirect("scroll.jsp");
            
            con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(DataExtractor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataExtractor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    private Object[][] ResultSetToArray(ResultSet rs){
        Object data[][]  = null;
        try{
           rs.last();
           ResultSetMetaData rsmd = rs.getMetaData();
           int numCols = rsmd.getColumnCount();
           int numRows = rs.getRow();
           data = new Object[numRows][numCols];
           
           int j = 0;
           rs.beforeFirst();;
           while(rs.next()){
               for (int i = 0; i < numCols; i++) {
                   data[j][i] = rs.getObject(i+1);
               }
               j++;
           }
        }catch(Exception e){
            System.out.println(e);
        }
        
        return data;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

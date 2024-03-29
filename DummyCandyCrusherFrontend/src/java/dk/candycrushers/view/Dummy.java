/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.candycrushers.view;

import dk.candycrushers.control.BankManager;
import dk.candycrushers.dto.AccountDetail;
import dk.candycrushers.dto.CustomerDetail;
import dk.candycrushers.dto.CustomerSummary;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Thomas
 */
@WebServlet(name = "Dummy", urlPatterns = {"/Dummy"})
public class Dummy extends HttpServlet {

    @EJB
    private BankManager manager;

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Dummy</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Greeting: " + manager.sayHello("Dummy") + "</h1>");
            out.println("<p>Test at hente alle kunder<p>");
            for (CustomerSummary customer : manager.getCustomers()) {
                out.println("<p>" + customer.getFirstName() + " " + customer.getLastName() + "</p>");
            }
            out.println("<p>Test at hente en kunde<p>");
            out.println("<hr>");
            out.println("<p>"+manager.getCustomer(1).getFirstName()+"</p>");
//            CustomerDetail addCustomer = manager.addCustomer("Mads", "Larsen", "xxx@cph.dk","k", 2);
            
            CustomerDetail updateCustomer = manager.updateCustomer(2, "Hans", "Madsen", "hans.madsen@cph.dk");
//            
            out.println("<p>" + manager.getAccount(1).getAccountType()+ " saldo: " 
                    + manager.getAccount(1).getBalance() + "<p>");
            
//            AccountDetail account = manager.addAccount("Lån", 1000, 1);
            
//            out.println("<p>" + account.getAccountType() + " oprettet </p>");
            
            manager.transactionToAnOtherAccount(2, 1, 5000);
            
            out.println("<p>" + manager.getAccount(1).getAccountType()+ " saldo: " 
                    + manager.getAccount(1).getBalance() + "<p>");
            
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
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

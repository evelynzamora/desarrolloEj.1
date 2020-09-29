package controller;

import Modell.Productos;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Evelyn
 */
public class sr_Producto extends HttpServlet {
    Productos producto;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, @NotNull HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet sr_Producto</title>");            
            out.println("</head>");
            out.println("<body>");
            
            producto = new Productos(Integer.valueOf(request.getParameter("txt_id")),Integer.valueOf(request.getParameter("drop_marca")),Integer.valueOf(request.getParameter("txt_existencia")), Float.parseFloat(request.getParameter("txt_precio_costo")), Float.parseFloat(request.getParameter("txt_precio_venta")), request.getParameter("txt_producto"), request.getParameter("txt_descripcion"));
            if("agregar".equals(request.getParameter("btn_agregar"))){
                if(producto.agregar() > 0){
                    response.sendRedirect("index.jsp");
                }else{
                    out.println("<h1>Error.............</h1>");
                    out.println("<a href-'index.jsp'>Regresar...</a>");
                }
            } 
            
            if("modificar".equals(request.getParameter("btn_modificar"))){
                if(producto.modificar() > 0){
                    response.sendRedirect("index.jsp");
                }else{
                    out.println("<h1>Error.............</h1>");
                    out.println("<a href-'index.jsp'>Regresar...</a>");
                }
            }   
            
            if("eliminar".equals(request.getParameter("btn_eliminar"))){
                if(producto.elimsinar() > 0){
                    response.sendRedirect("index.jsp");
                }else{
                    out.println("<h1>Error.............</h1>");
                    out.println("<a href-'index.jsp'>Regresar...</a>");
                }
            }   
            
            out.println("</body>");
            out.println("</html>");
        }
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

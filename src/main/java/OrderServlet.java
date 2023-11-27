import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MainMenu;
import model.Orders;

@WebServlet("/")
public class OrderServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

  
                
        String url = request.getServletPath();

        // show the response from the console log from jetty server
        System.out.println("url: " + url);

        switch (url) {
            case "/index.html":
                System.out.println("page: HOME");
                goHome(request, response);
                break;

            case "/add":
                System.out.println("page: ADD");
                goOrder(request, response);
                break;

            case "/add/submit":
                System.out.println("page: ADD/SUBMIT");
                goAddOrder(request, response);
                break;

            case "/pending":
                System.out.println("page: PENDING");
                goPending(request, response);
                break;

            case "/pending/serve":
                System.out.println("page: PENDING/SERVE");
                goServePending(request, response);
                break;

            case "/served":
                System.out.println("page: SERVED");
                goServed(request, response);
                break;

            case "/served/print":
                System.out.println("page: SERVED/PRINT");
                goPrintServed(request, response);
                break;


            default:        
                break;
        }        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }


    private void goHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }

    private void goOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/order.jsp");
        dispatcher.forward(request, response);
    }

    private void goAddOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MainMenu mainMenu = new MainMenu();

        String name = request.getParameter("name");
        String order = request.getParameter("order");
        int quantity = Integer.parseInt(request.getParameter("qty"));  
        
        
        try {
            int ID = mainMenu.addOrder(name, order, quantity);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/info.jsp");
            request.setAttribute("type", "addSuccess");
            request.setAttribute("id", ID);

            Orders orders = mainMenu.selectOrder(ID);
            request.setAttribute("name", orders.getName());
            request.setAttribute("order", orders.getOrder());
            request.setAttribute("price", orders.getPrice());
            request.setAttribute("qty", orders.getQuantity());
            request.setAttribute("total", orders.getTotal());
            request.setAttribute("isPending", orders.isPending());
            request.setAttribute("dateTime", orders.getDateTime());

            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();


        }
    }

    private void goPending(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pending.jsp");
        dispatcher.forward(request, response);
    }

    private void goServePending(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        
        MainMenu mainMenu = new MainMenu();

        try {
            mainMenu.setPendingStatus(id, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pending.jsp");
        dispatcher.forward(request, response);

    }

    private void goServed(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/served.jsp");
        dispatcher.forward(request, response);
    }

    private void goPrintServed(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MainMenu mainMenu = new MainMenu();

        int id = Integer.parseInt(request.getParameter("id"));

        try {
            Orders orders = mainMenu.selectOrder(id);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/info.jsp");
            request.setAttribute("type", "printInfo");
            request.setAttribute("id", orders.getId());
            request.setAttribute("name", orders.getName());
            request.setAttribute("order", orders.getOrder());
            request.setAttribute("price", orders.getPrice());
            request.setAttribute("qty", orders.getQuantity());
            request.setAttribute("total", orders.getTotal());
            request.setAttribute("isPending", orders.isPending());
            request.setAttribute("dateTime", orders.getDateTime());

            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

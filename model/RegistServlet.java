package model;
 
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import model.DAO;//链接数据库
import model.DbUtil;//增加数据库增加查询用户的功能的类
import model.User;//user:用于从数据库中读出用户名和密码，同时也可以从前端界面中获得用户名和密码的类。
@WebServlet("/Regist")
public class RegistServlet extends HttpServlet{
 
    @Override
    protected void doGet(HttpServletRequest req,
    		HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
 
    @Override
    protected void doPost(HttpServletRequest req,
    		HttpServletResponse resp) throws ServletException, IOException {
    
    
        /**
         * 接收前台传来的值 账号和密码
         */
    	
    	//获取注册用户名
        String usersname=req.getParameter("usersname");
        //获取注册用户密码
        String password=req.getParameter("password");
        
        DbUtil db= new DbUtil();
        User user=new User(usersname,password);
        DAO dao=new DAO();
        try {
            //数据库链接
            Connection con=db.getCon();//获得数据库的方法
            
            if(dao.regist(con,user)) {
                resp.sendRedirect("login.jsp");
            }else {
                resp.sendRedirect("regist.jsp");
       
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    }
}

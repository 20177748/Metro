package model;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
 
 
 
public class DbUtil {//增加数据库增加查询用户的功能的类
    /**
     * login(Connection con,User user) 登录验证
     * (Connection con,User user)注册功能
  
     */
    public User login(Connection con,User user) throws Exception{//user:用于从数据库中读出用户名和密码，同时也可以从前端界面中获得用户名和密码的类。
        User resultUser=null;
        String sql="select * from users where usersname=? and password=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, user.getUsersname());
        pstmt.setString(2, user.getPassword());
        ResultSet rs=pstmt.executeQuery();
        if(rs.next()){
            resultUser=new User();
            resultUser.setUsersname(rs.getString("usersname"));
            resultUser.setPassword(rs.getString("password"));
        }
        return resultUser;
    }
    
    //注册功能
    public boolean regist(Connection con,User user) throws Exception{
    	 User resultUser=null;//注册检测
         String sql1="select * from users where usersname=? and password=?";
         PreparedStatement pstmt1=con.prepareStatement(sql1);
         pstmt1.setString(1, user.getUsersname());
         pstmt1.setString(2, user.getPassword());
         ResultSet rs=pstmt1.executeQuery();
         if(rs.next()){
             resultUser=new User();
             resultUser.setUsersname(rs.getString("usersname"));
             resultUser.setPassword(rs.getString("password"));
         }
    	boolean flag=false;//是否注册成功
        PreparedStatement pstmt = null;
        String sql="INSERT INTO user(usersame,password)VALUES(?,?)";
        
        pstmt = con.prepareStatement(sql);
        pstmt.setString(1, user.getUsersname());
        pstmt.setString(2, user.getPassword());
        if (pstmt.executeUpdate() > 0) {
            flag = true;
        }
        return flag;
    }
 
}


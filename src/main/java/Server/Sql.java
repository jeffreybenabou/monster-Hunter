package Server;


import java.sql.*;
import java.util.ArrayList;


public class Sql {

    private static Connection connect;


    public static void delete_statement(){

        String sqldelete = "delete from student where h between ? and ? and name = ?";

        try {
            PreparedStatement pst = connect.prepareStatement(sqldelete);

            pst.setString(1, "180");
            pst.setString(2, "190");
            pst.setString(3, "effi");

            pst.execute();


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void update_statement(){

        String sqlupdate = "UPDATE student SET name=?  WHERE id =? ";

        try {
            PreparedStatement pst = connect.prepareStatement(sqlupdate);

            pst.setString(1, "effi");
            pst.setString(2, "3344");
            //	pst.setString(3, "66127762");

            pst.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void register(String password,String user){



        String sqlInsert = "INSERT INTO user (password, iduser) VALUES ('"+password+"','"+user+"')";

        try {
            PreparedStatement pst = connect.prepareStatement(sqlInsert);
            pst.execute();



        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static ArrayList<String> addAllOnlineUsers(String user) {
        try {
            ArrayList<String> arrayList = new ArrayList();
            PreparedStatement statement = connect.prepareStatement("SELECT * FROM user");

            ResultSet result = statement.executeQuery();

            while (result.next()) {

                if (!result.getString(1).equals(user)&&result.getString(3).equals("true")) {
                    arrayList.add(result.getString(1));
                }


            }
            return arrayList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void setTheUserStatistics(String user){
        PreparedStatement statement = null;


        try {
            statement = connect.prepareStatement("INSERT INTO statistics (id,win, lose, total ) VALUES ('"+user+"'"+",0,0,0)");

            System.out.println(statement);

             statement.executeUpdate();







        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public static ArrayList<Integer> getTheStatisticsdata(String userName){
        PreparedStatement statement = null;

        ArrayList<Integer> list=new ArrayList();

        try {
            statement = connect.prepareStatement("SELECT * FROM statistics");


            ResultSet statistics = statement.executeQuery();


            while (statistics.next())
            {
                if(statistics.getString(1).equals(userName))
                {
                    list.add(Integer.parseInt(statistics.getString(2)));
                    list.add(Integer.parseInt(statistics.getString(3)));
                    list.add(Integer.parseInt(statistics.getString(4)));

                }

            }
            System.out.println(list);

        } catch (SQLException e) {
            e.printStackTrace();
        }
return list;

    }

    public  static void setTheUserOnline(String online,String userid)
    {


        String sqlInsert =  "UPDATE user SET online = '"+online+"'  WHERE iduser = '"+userid+"'";

        try {
            PreparedStatement pst = connect.prepareStatement(sqlInsert);
            pst.execute();



        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static boolean checkIfPasswordIsTrue(String user,String password){
        try {

            PreparedStatement userStatment = connect.prepareStatement("SELECT iduser FROM user");

            ResultSet result = userStatment.executeQuery();

            PreparedStatement passwordStatment = connect.prepareStatement("SELECT password FROM user");

            ResultSet resultPassword = passwordStatment.executeQuery();

            while(result.next()&&resultPassword.next())
            {

                if(result.getString(1).equals(user)&&resultPassword.getString(1).equals(password))
                    return true;


            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean checkIfUserExist(String user)
        {

        try {
            PreparedStatement statement = connect.prepareStatement("SELECT iduser FROM user");

            ResultSet result = statement.executeQuery();

            while(result.next())
            {

                if(result.getString(1).equals(user))
                    return true;


            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
return false;
    }

    public static void connection()
    {

        try {


            Class.forName("com.mysql.jdbc.Driver").newInstance();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    public static void ConectingToSQL ()
    {

        connection();

        String host = "jdbc:mysql://127.0.0.1:3306/starcraft?useSSL=false";
        String username = "root";
        String password = "";


        try {
            connect =  DriverManager.getConnection(host, username, password);

        } catch (SQLException e) {
            System.out.println( e.getCause().getMessage());


        }



    }


    public Sql(){
        ConectingToSQL();

    }
}

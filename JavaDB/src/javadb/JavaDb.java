/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javadb;

/**
 *
 * @author VEDANT NANDOSKAR
 */


import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
 
public class JavaDb {
 
    /**
     * @param args the command line arguments
     */
    Connection conn;
    ResultSet rs;
    Statement stmt;
    
    public static void main(String[] args) {
        // TODO code application logic here
         JavaDb mydb= new JavaDb();
	//Connect to database
        mydb.ConnectDb();
        //Display Data
        mydb.DisplayData();
        //Insert new tuple();
        mydb.InsertData();
         //Display Data
        mydb.DisplayData();
        //Delete data
        mydb.DeleteData();
         //Display Data
        mydb.DisplayData();
        //Update the data
        mydb.UpdateData();
         //Display Data
        mydb.DisplayData();
        //Display selected data only
        System.out.print("Winner is =team "+mydb.DisplayDataNew(2));
        //CLose the connection
        mydb.CloseConn();
                        
        
    }
    void InsertData()
    {
        //code to insert a new value in database
        try{
            // stmt=conn.createStatement();
            // String sql="insert into match values(6,'MI','RCB','RCB','Mumbai');";
            // stmt.executeUpdate(sql);
            System.out.println("Insertion done successfully");
        }
        catch(Exception e)
        {
            System.out.println("Error:"+e);
        }
    }//End of inert data
    void DeleteData()
    {
        //Code to delete data
        try{
            // stmt=conn.createStatement();
            // String sql="delete from match where matchid=6;";
            // stmt.executeUpdate(sql);
            System.out.println("Deletion done successfully");
        }
        catch(Exception e)
        {
            System.out.println("Error:"+e);
        }
    }//end of DeleteData
    
    void UpdateData()
    {
        //Code to update the data
        
        try{
            stmt=conn.createStatement();
            String sql="update Customer set age=22 where CustID=1;";
            stmt.executeUpdate(sql);
            System.out.println("Updation done successfully");
        }
        catch(Exception e)
        {
            System.out.println("Error:"+e);
        }
    }//End of UpdateData
    
    void ConnectDb()
    {
        //Code to open connection with database
        System.out.println("-------- PostgreSQL "
				+ "JDBC Connection Testing ------------");
 
		try {
 
			Class.forName("org.postgresql.Driver");
 
		} catch (ClassNotFoundException e) {
 
			System.out.println("Where is your PostgreSQL JDBC Driver? "
					+ "Include in your library path!");
			//e.printStackTrace();
			return;
 
		}
 
		System.out.println("PostgreSQL JDBC Driver Registered!");
 
		conn = null;
                try {
                conn = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/postgres", "postgres","postgres");
                System.out.println("You made it, take control your database now!");
                }
                catch(Exception e){
                        System.out.println("Failed to make connection!"+e);
                        }
    }//end of ConnectDb
    void DisplayData()
    {
        try 
            {
            stmt = conn.createStatement();
            rs = stmt.executeQuery( "SELECT * FROM Customer;" );
            while ( rs.next() ) 
            {
                System.out.println("-----------------");
                System.out.println("Matchid="+rs.getInt(1));
                System.out.println("Team1="+rs.getString(2));
                System.out.println("Team2="+rs.getString(3));
                System.out.println("Winner="+rs.getString(4));
                System.out.println("-----------------");
            }
            rs.close();
            }
            catch (SQLException e) {
			System.out.println("Connection Failed! Check output console "+e);
                    	} 
    }//end of function DisplayData
    
    String[] DisplayDataNew(String custname)
    {
        String[] output = new String[7];
        String queryStr = new String("select movieName, genre, language, t.seatNo, dateOfShow, showtime, price from Ticket t, Movie m, Customer c Where m.movieID = t.movieID and c.custID = t.custID and c.firstName=");
        try 
            {
       
            stmt = conn.createStatement();
            String queryStringFinal = new String(queryStr + "'" + custname + "'");
            rs = stmt.executeQuery(queryStringFinal);
//            System.out.print(stmt);
            if( rs.next() ) 
            {
              output[0]=rs.getString(1);
              output[1]=rs.getString(2);
              output[2]=rs.getString(3);
              output[3]=rs.getString(4);
              output[4]=rs.getString(5);
              output[5]=rs.getString(6);
              output[6]=rs.getString(7);
             
              for (int i = 0; i<7; i++)
                {
                    System.out.println(output[i]);
                }
            }
            else
                for (int i = 0; i<7; i++)
                {
                    output[i]=("No match found");
                }
            rs.close();
            }
        catch (SQLException e) {
			System.out.println("Connection Failed! Check output console "+e);
                    	} 
        return output;
    }//end of function DisplayDataNew
    
    void CloseConn()
    {
        //code to close the connection with database.
        try{
        conn.close();
        conn=null;
        }
        catch(Exception e)
        {
        }
        
    }//end of CloseConn
    
}//end of class




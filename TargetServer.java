import java.io.*;
import java.net.*;
import java.sql.*;
class TargetServer{
	ServerSocket ss;
	Socket s;
	DataInputStream dis;
	String[] str;
	TargetServer(){
		try{
			System.out.println("server Running");
			ss = new ServerSocket(10);
			s=ss.accept();
			System.out.println(s);
			System.out.println("client connected");
			dis = new DataInputStream(s.getInputStream());
			Server();

		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	public void Server() throws Exception{
		str = new String[9];
		for(int i=0;i<9;i++){
			str[i] = dis.readUTF();
			//System.out.println(str);
		}
		database();
	}
	public void database(){
		Connection c = null;
		try{
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			c= DriverManager.getConnection("jdbc:ucanaccess://C:/Users/hp/documents/Database2.accdb");
			String res = "insert into enquiry(Name,Contact,Stream,Branch,Year,Semester,College,Course,Fees) values('"+str[0]+"','"+str[1]+"','"+str[2]+"','"+str[3]+"','"+str[4]+"','"+str[5]+"','"+str[6]+"','"+str[7]+"','"+str[8]+"')";
			Statement s = c.createStatement();
			int x = s.executeUpdate(res);

		}
		catch(SQLException ee){
			ee.printStackTrace();
		}
		catch(Exception eee){
			System.out.println(eee);
		}

	}
	public static void main(String args[]){
		new TargetServer();
	}

}
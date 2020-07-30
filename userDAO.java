package User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class userDAO {
	//JSP���� ȸ�� �����ͺ��̽� ���̺� ������ �� �ֵ��� ����
	//DAO = Data Access Object, ���������� �����ͺ��̽����� ȸ�������� ����, ó���Ҷ� ���
	
	private Connection conn; 
	//DB ���� ��ü
	
	private PreparedStatement pstmt; 
	private ResultSet rs; 
	//������ ���� ��ü
	
	//Ctrl + Shift + O => "Organize Imports" = Ŭ������ ���ʿ��� import������ ����, �ʿ��� ������ �ڵ����� �߰�
	
	public userDAO()
	{
		try
		{
			String dbURL = "jdbc:mysql://localhost:3306/BBS?characterEncoding=UTF-8&serverTimezone=UTC"; 
			//localhost:3306 => ����ǻ�Ϳ� ��ġ�� MySQL, port 3306�� BBS��� DB�� ����
			
			String dbID = "root";
			String dbPassword = "0767";
			Class.forName("com.mysql.jdbc.Driver"); 
			 //MySQL�� ������ �� �ֵ��� �Ű�ü ������ ���ִ� �ϳ��� ���̺귯��, JDBC ����̹� �ε�
			
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
			//DB ���ӵǸ� conn��ü�� ���������� ����
			
			
		}catch(Exception e)
		{
			System.out.println("Exception");
			e.printStackTrace();
		}
	}
	
	public int login(String userID, String userPassword) //�α��� ó���ϴ� �Լ�
	{
		String SQL = "SELECT userPassword FROM USER WHERE userID = ?";
		try
		{
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery(); //���� ����� �ְ�,
			if(rs.next()) //����� �����Ѵٸ�
			{
				if(rs.getString(1).equals(userPassword)) 
					//����� ���� userPassword�� �޾Ƽ� ������ �õ��� userPassword�� �����ϴٸ�
				{
					return 1; //�α��� ����
				}
				else
					return 0; //�α��� ����(��й�ȣ Ʋ��)
					
			}
			return -1; //���̵� ����  userID=?�κ� Ȯ��
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return -2; // �����ͺ��̽� ����
	}
	
	public int join(User user) {

		String SQL = "INSERT INTO USER VALUES (?,?,?,?,?)";

		try {

			pstmt = conn.prepareStatement(SQL);

			pstmt.setString(1, user.getUserID());

			pstmt.setString(2, user.getUserPassword());

			pstmt.setString(3, user.getUserName());

			pstmt.setString(4, user.getUserGender());

			pstmt.setString(5, user.getUserEmail());

			return pstmt.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();

		}

		return -1; // DB ����

	}


}
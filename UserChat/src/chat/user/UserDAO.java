package chat.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import chat.main.DBManager;


public class UserDAO {
	private static final UserDAO UDAO = new UserDAO();

	public static UserDAO getUDAO() {
		return UDAO;
	}
	
	
	public int login(String userID,String userPassword){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "select * from Chatuser where userID = ?";
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getString("userPassword").equals(userPassword)) {
					return 1;	//로그인 성공
				}
				return 2;	//비밀번호 틀림
			}else {
				return 0; //해당 사용자 존재하지 않음
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(con, pstmt, rs);
		}
		return -1;	//데이터 베이스 오류
	}
	
	public int registerCheck(String userID){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "select * from Chatuser where userID = ?";
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			if (rs.next()||userID.equals("")) {
				return 0;	//이미 존재하는 회원
			}else{
				return 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(con, pstmt, rs);
		}
		return -1;	//데이터 베이스 오류
	}
	
	public int register(String userID,String userPassword,String userName,String userAge,String userGender,String userEmail,String userProfile){
		Connection con = null;
		PreparedStatement pstmt = null;
		String SQL = "insert into Chatuser values(?,?,?,?,?,?,?)";
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, userID);
			pstmt.setString(2, userPassword);
			pstmt.setString(3, userName);
			pstmt.setInt(4, Integer.parseInt(userAge));
			pstmt.setString(5, userGender);
			pstmt.setString(6, userEmail);
			pstmt.setString(7, userProfile);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(con, pstmt, null);
		}
		return -1;	//데이터 베이스 오류
	}
}

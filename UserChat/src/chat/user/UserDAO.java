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
	
	public UserDTO getUser(String userID){
		UserDTO user = new UserDTO();
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
				user.setUserID(userID);
				user.setUserPassword(rs.getString("userPassword"));
				user.setUserName(rs.getString("userName"));
				user.setUserAge(rs.getInt("userAge"));
				user.setUserGender(rs.getString("userGender"));
				user.setUserEmail(rs.getString("userEmail"));
				user.setUserProfile(rs.getString("userProfile"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(con, pstmt, rs);
		}
		return user;	//데이터 베이스 오류
	}
	
	public int update(String userID,String userPassword,String userName,String userAge,String userGender,String userEmail){
		Connection con = null;
		PreparedStatement pstmt = null;
		String SQL = "update Chatuser set userPassword =?, userName =?, userAge=?, userGender =?, userEmail=? where userID = ?";
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, userPassword);
			pstmt.setString(2, userName);
			pstmt.setInt(3, Integer.parseInt(userAge));
			pstmt.setString(4, userGender);
			pstmt.setString(5, userEmail);
			pstmt.setString(6, userID);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(con, pstmt, null);
		}
		return -1;	//데이터 베이스 오류
	}
	
	public int profile(String userID,String userProfile){
		Connection con = null;
		PreparedStatement pstmt = null;
		String SQL = "update Chatuser set userProfile = ? where userID = ?";
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, userProfile);
			pstmt.setString(2, userID);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(con, pstmt, null);
		}
		return -1;	//데이터 베이스 오류
	}
	
	public String getProfile(String userID){
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
				if (rs.getString("userProfile").equals("")) {
					return "http://172.16.4.155:8888/UserChat/images/icon.jpg";  
				}
				return "http://172.16.4.155:8888/UserChat/upload/" + rs.getString("userProfile");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(con, pstmt, rs);
		}
		return "http://172.16.4.155:8888/UserChat/images/icon.jpg";
	}
}

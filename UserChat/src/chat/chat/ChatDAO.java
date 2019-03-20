package chat.chat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import chat.main.DBManager;

public class ChatDAO {
	private static final ChatDAO CDAO = new ChatDAO();

	public static ChatDAO getCDAO() {
		return CDAO;
	}
	
	public ArrayList<ChatDTO>getChatListByID(String fromID,String toID,String chatID){
		ArrayList<ChatDTO>chatList = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL ="select * from chat where ((fromID=? and toID=?) or (fromID=? and toID = ?)) and chatID > ? order by chatTime";
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, fromID);
			pstmt.setString(2, toID);
			pstmt.setString(3, toID);
			pstmt.setString(4, fromID);
			pstmt.setInt(5, Integer.parseInt(chatID));
			rs = pstmt.executeQuery();
			chatList = new ArrayList<ChatDTO>();
			while (rs.next()) {
				ChatDTO chat = new ChatDTO();
				chat.setChatID(rs.getInt("chatID"));
				chat.setFromID(rs.getString("fromID").replaceAll(" ","&nbsp;").replaceAll("<","&lt;").replaceAll(">","&gt;").replaceAll("\n","<br>"));
				chat.setToID(rs.getString("toID").replaceAll(" ","&nbsp;").replaceAll("<","&lt;").replaceAll(">","&gt;").replaceAll("\n","<br>"));
				chat.setChatContent(rs.getString("chatContent").replaceAll(" ","&nbsp;").replaceAll("<","&lt;").replaceAll(">","&gt;").replaceAll("\n","<br>"));
				int chatTime = Integer.parseInt(rs.getString("chatTime").substring(11,13));
				String timeType = "오전";
				if (chatTime >=12) {
					timeType = "오후";
					chatTime -= 12;
				}
				chat.setChatTime(rs.getString("chatTime").substring(0,11)+" "+timeType+" "+chatTime+":"+rs.getString("chatTime").substring(14,16)+"" );
				chatList.add(chat);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(con, pstmt, rs);
		}
		return chatList;
	}
	
	public ArrayList<ChatDTO>getChatListByRecent(String fromID,String toID,int number){
		ArrayList<ChatDTO>chatList = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL ="select * from chat where ((fromID=? and toID=?) or (fromID=? and toID = ?)) and chatID > (select max(chatID) - ? from chat) order by chatTime";
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, fromID);
			pstmt.setString(2, toID);
			pstmt.setString(3, toID);
			pstmt.setString(4, fromID);
			pstmt.setInt(5, number);
			rs = pstmt.executeQuery();
			chatList = new ArrayList<ChatDTO>();
			while (rs.next()) {
				ChatDTO chat = new ChatDTO();
				chat.setChatID(rs.getInt("chatID"));
				chat.setFromID(rs.getString("fromID").replaceAll(" ","&nbsp;").replaceAll("<","&lt;").replaceAll(">","&gt;").replaceAll("\n","<br>"));
				chat.setToID(rs.getString("toID").replaceAll(" ","&nbsp;").replaceAll("<","&lt;").replaceAll(">","&gt;").replaceAll("\n","<br>"));
				chat.setChatContent(rs.getString("chatContent").replaceAll(" ","&nbsp;").replaceAll("<","&lt;").replaceAll(">","&gt;").replaceAll("\n","<br>"));
				int chatTime = Integer.parseInt(rs.getString("chatTime").substring(11,13));
				String timeType = "오전";
				if (chatTime >=12) {
					timeType = "오후";
					chatTime -= 12;
				}
				chat.setChatTime(rs.getString("chatTime").substring(0,11)+" "+timeType+" "+chatTime+":"+rs.getString("chatTime").substring(14,16)+"" );
				chatList.add(chat);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(con, pstmt, rs);
		}
		return chatList;
	}
	
	public int submit(String fromID,String toID,String chatContent){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL ="insert into chat values(" + "chatID_seq.nextval,?,?,?,sysdate)";
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, fromID);
			pstmt.setString(2, toID);
			pstmt.setString(3, chatContent);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(con, pstmt, rs);
		}
		return -1;	//데이터 베이스 오류
	}
	
}

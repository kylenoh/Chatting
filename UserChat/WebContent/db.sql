create table Chatuser(
	userID varchar2(20 char),
	userPassword varchar2(20 char),
	userName varchar2(20 char),
	userAge number(5),
	userGender varchar2(20 char),
	userEmail varchar2(50 char),
	userProfile varchar2(50 char)
);
select * from chatuser;

create table Chat(
	chatID number(5)primary key,
	fromID varchar2(20 char),
	toID varchar2(20 char),
	chatContent varchar2(100 char),
	chatTime date,
	chatRead number(5)
);
create sequence chatID_seq;
select * from chat;
drop table chat cascade constraint;
select * from chat where ((fromID=222 and toID=123) or (fromID=123 and toID = 222)) and chatID > (select max(chatID) - 29 from chat where (fromID =222 and toID =123 ) or (fromID =123 and toID =222))order by chatTime

pstmt.setString(1, fromID);
			pstmt.setString(2, toID);
			pstmt.setString(3, toID);
			pstmt.setString(4, fromID);
			pstmt.setInt(5, number);
			pstmt.setString(6, fromID);
			pstmt.setString(7, toID);
			pstmt.setString(8, toID);
			pstmt.setString(9, fromID);
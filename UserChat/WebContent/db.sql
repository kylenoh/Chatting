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
	chatTime date
);
create sequence chatID_seq;
select * from chat;
package chat.chat;

public class ChatDTO {

	private int chatID;
	private String fromID;
	private String toID;
	private String chatContent;
	private String chatTime;
	private int chatRead;
	
	public ChatDTO() {
		// TODO Auto-generated constructor stub
	}

	public ChatDTO(int chatID, String fromID, String toID, String chatContent, String chatTime, int chatRead) {
		super();
		this.chatID = chatID;
		this.fromID = fromID;
		this.toID = toID;
		this.chatContent = chatContent;
		this.chatTime = chatTime;
		this.chatRead = chatRead;
	}

	public int getChatID() {
		return chatID;
	}

	public void setChatID(int chatID) {
		this.chatID = chatID;
	}

	public String getFromID() {
		return fromID;
	}

	public void setFromID(String fromID) {
		this.fromID = fromID;
	}

	public String getToID() {
		return toID;
	}

	public void setToID(String toID) {
		this.toID = toID;
	}

	public String getChatContent() {
		return chatContent;
	}

	public void setChatContent(String chatContent) {
		this.chatContent = chatContent;
	}

	public String getChatTime() {
		return chatTime;
	}

	public void setChatTime(String chatTime) {
		this.chatTime = chatTime;
	}

	public int getChatRead() {
		return chatRead;
	}

	public void setChatRead(int chatRead) {
		this.chatRead = chatRead;
	}

	
}

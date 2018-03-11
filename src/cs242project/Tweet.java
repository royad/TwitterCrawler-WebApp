package cs242project;

public class Tweet {
	long id;
	String text;
	public Tweet(long id, String text) {
		this.id = id;
		this.text = text;
	}
	public long getId() {
		return id;
	}
	public String getText() {
		return text;
	}
}
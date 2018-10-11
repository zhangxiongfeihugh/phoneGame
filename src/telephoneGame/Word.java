package telephoneGame;

public class Word{
	private String origin;
	private String current;

	public Word(String origin, String current) {
		this.setOrigin(origin);
		this.setCurrent(current);
	}

	public String getCurrent() {
		return current;
	}

	public void setCurrent(String current) {
		this.current = current;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

    @Override
    public String toString() {
        return "Word{" +
                "origin='" + origin + '\'' +
                ", current='" + current + '\'' +
                '}';
    }
}

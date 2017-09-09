package yesdoing.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Question {
	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable=false, length=20)
	private String writer;
	@Column(nullable=false, length=100)
	private String title;
	@Column(nullable=false, length=500)
	private String contents;

	public Question() {
	}

	public Question(Long id, String writer, String title, String contents) {
		super();
		this.id = id;
		this.writer = writer;
		this.title = title;
		this.contents = contents;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}
	
		
}
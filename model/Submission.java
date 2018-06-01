package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Submission {

	
    private Float grade;
    private Assignment assignment;
    private Student student;
    private String date;
    private String link;
    
    
    
 
public Submission(Float grade, Assignment assignment, Student student, String date, String link) {
		super();
		this.grade = grade;
		this.assignment = assignment;
		this.student = student;
		this.date = date;
		this.link = link;
	}

public Submission() {}

	public Float getGrade() {
		return grade;
	}



	public void setGrade(Float grade) {
		this.grade = grade;
	}







	public Assignment getAssignment() {
		return assignment;
	}

	public void setAssignment(Assignment assignment) {
		this.assignment = assignment;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getDate() {
		return date;
	}



	public void setDate(String date) {
		this.date = date;
	}



	public String getLink() {
		return link;
	}



	public void setLink(String link) {
		this.link = link;
	}



	@Override
	public String toString() {
		return "Submission [grade=" + grade + ", assignment=" + assignment + ", student=" + student + ", date="
				+ date + ", link=" + link + "]";
	}
    
    
	
    
    
	
	
}

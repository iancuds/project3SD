package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Attendance {

	
    private Student student;
    private Laboratory laboratory;
	public Attendance(Student student, Laboratory laboratory) {
		super();
		this.student = student;
		this.laboratory = laboratory;
	}
	
	public Attendance() {}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Laboratory getLaboratory() {
		return laboratory;
	}
	public void setLaboratory(Laboratory laboratory) {
		this.laboratory = laboratory;
	}
	@Override
	public String toString() {
		return "Attendance [student=" + student + ", laboratory=" + laboratory + "]";
	}
	
	
    
}	
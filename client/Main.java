package client;

import java.util.List;

import model.Assignment;
import model.Attendance;
import model.Laboratory;
import model.Student;
import service.AssignmentService;
import service.AttendanceService;
import service.LaboratoryService;
import service.StudentService;
import service.SubmissionService;

import static java.lang.System.out;

public class Main {

	
	public static void main(String[] args)
	{
		
		
		 String request1="http://localhost:8080/student/";
		 String request2="http://localhost:8080/student/";
		
		StudentService studentService = new StudentService();
		List<Student> students = studentService.getAllStudents(request1);
		
		for(Student s:students)
		{
			System.out.println(s);
		}
		
		LaboratoryService ls = new LaboratoryService();
		List<Laboratory> labs = ls.getAllLaboratories("http://localhost:8080/laboratory/");
		
		for(Laboratory s:labs)
		{
			System.out.println(s);
		}
		
		
		Student std = studentService.getStudentById(request2, (long)5);
		
		System.out.println(std);
		
		String message="";
		boolean login = studentService.logIn("zeEmail", "stringNou", message);
		System.out.println(message);
		System.out.println(login);
		
		
		String request3 = "http://localhost:8080/student/";
		
		String token ="y[[8c;9f3`nDd~RK<WbXsG;{{9:QoTz,Hp77A7v!&x)E~{a18F1`5qf}}mk/m#PW];ny4m`|rqFdRa&uz>P}:F~xZi)y#qP7Ut5nYc|(B;x2Ec6C:8/uVKw60s7K7gZI";
		//Student stdReg = studentService.register(request3, token, "string");
		//out.println("stdReg: "+stdReg);
		
		String request = "http://localhost:8080/student/";
		//Student saveStud = studentService.saveStudent("http://localhost:8080/student/", "ana.moinea2@bla.com", "7","h", "ana moinea");
		//out.println("student salvat: "+saveStud);
		
		//Student upstd = studentService.updateStudent(request, (long) 2, "em", "gr", "ho", "name");
		//out.println("updated: "+upstd);
		
		//out.println(studentService.deleteStudentById(request, (long)4));
		
		//Student changePass = studentService.changePassword(request, "andrei@yahoo.com", "parola", "parola noua");
		//out.println("student with changed passwd: "+changePass);
	
		AttendanceService ats =new AttendanceService();
		List<Attendance> as = ats.getAllByGroup("string");
		for(Attendance a:as)
		{
			out.println(a);
		}
		
	

		
		AssignmentService serv = new AssignmentService();
		List<Assignment>l = serv.getAllAssignments("http://localhost:8080/assignment/");
		
		for(Assignment b:l)
		{
			out.println(b);
		}
	
		SubmissionService subs = new SubmissionService();
		
		subs.gradeSubmission("http://localhost:8080/submission/", (long)4, (float)5);
		
	}
	
	
	
	
	
	
	 
}

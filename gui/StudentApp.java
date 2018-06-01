package gui;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.Assignment;
import model.Attendance;
import model.Laboratory;
import model.Student;
import model.Submission;
import service.AssignmentService;
import service.AttendanceService;
import service.LaboratoryService;
import service.StudentService;
import service.SubmissionService;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StudentApp {

	private JFrame frame;
	private JTable table;
	private JTable table_1;
	private JTextField txtLink;
	private JTextField txtDate;
	private JLabel lblSubmitAssignment;
	private JScrollPane scrollPane_2;
	private JScrollPane scrollPane_3;
	private JLabel lblAttendance;
	private JLabel lblGrading;
	private JTextField txtGroup;
	private JTextField txtAssignmentName;
	private StudentService ss = new StudentService();
	private AttendanceService ats = new AttendanceService();
	private AssignmentService ass = new AssignmentService();
	private LaboratoryService ls = new LaboratoryService();
	private SubmissionService subs = new SubmissionService();
	private String requestStudent = "http://localhost:8080/student/";
	private String requestLaboratory = "http://localhost:8080/laboratory/";
	private String requestAttendance = "http://localhost:8080/attendance/";
	private String requestAssignment = "http://localhost:8080/assignment/";
	private String requestSubmission = "http://localhost:8080/submission/";
	private JTable table_2;
	private JTable table_3;
	private JButton btnA;
	private JButton btnG;
	private JTextField txtAssignment;
	//private Student student = new Student("andrei@yahoo.com", "7","h", "ana moinea","","");
     private Student student;
	
	
	/**
	 * Launch the application.
	 */
	public static void runApp(final Student student) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentApp window = new StudentApp(student);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StudentApp(Student student) {
		this.student=student;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 33, 248, 193);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		populateLab();
		
		JLabel lblLaboratories = new JLabel("Laboratories");
		lblLaboratories.setBounds(20, 10, 150, 14);
		frame.getContentPane().add(lblLaboratories);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(320, 33, 437, 187);
		frame.getContentPane().add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		populateAssignments();
		
		
		JLabel lblAssignments = new JLabel("Assignments");
		lblAssignments.setBounds(320, 10, 150, 14);
		frame.getContentPane().add(lblAssignments);
		
		txtLink = new JTextField();
		txtLink.setText("link");
		txtLink.setBounds(20, 319, 100, 20);
		frame.getContentPane().add(txtLink);
		txtLink.setColumns(10);
		
		txtDate = new JTextField();
		txtDate.setText("date");
		txtDate.setBounds(20, 350, 100, 20);
		frame.getContentPane().add(txtDate);
		txtDate.setColumns(10);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String assign = txtAssignment.getText();
				String link = txtLink.getText();
				String date = txtDate.getText();
				
				System.out.println(assign+ " "+link + "  "+date);
				
				
				List<Submission>list = subs.getAllForAssignment(assign);
			    for(Submission s:list)
			    {
			    	System.out.println(s);
			    }
				
				   
					Long subid = subs.getId(student.getEmail(), assign);
					System.out.println("email:"+student.getEmail());
					System.out.println(subid);
				    if(!(subid.equals((long)-1)))
				    {
				    	Long stid=ss.findIdByEmail(student.getEmail());
				    	Long assid=ass.getIdByName(assign);
				    	Submission s = subs.getSubmissionById(requestSubmission, subid);
				    	subs.updateSubmission(requestSubmission, subid, s.getGrade(), assid, stid, date, link);
				    	
				    	
				    	
				    		
				    		
				    }
				    else
				    {
				    	Long stid=ss.findIdByEmail(student.getEmail());
				    	Long assid=ass.getIdByName(assign);
				    		System.out.println(assid);
				    		
				    		Submission saved=subs.saveSubmission(requestSubmission, (float)0, assid, stid, date, link);
				    		System.out.println("saved:"+saved);
				    }
					
		
				
				List<Student> studs = ss.getAllStudents(requestStudent);
				
				
				
			}	
			
		});
		btnSubmit.setBounds(20, 400, 150, 23);
		frame.getContentPane().add(btnSubmit);
		
		lblSubmitAssignment = new JLabel("Submit assignment");
		lblSubmitAssignment.setBounds(20, 270, 150, 20);
		frame.getContentPane().add(lblSubmitAssignment);
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(320, 299, 207, 213);
		frame.getContentPane().add(scrollPane_2);
		
		table_2 = new JTable();
		scrollPane_2.setViewportView(table_2);
		
		scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(550, 299, 207, 213);
		frame.getContentPane().add(scrollPane_3);
		
		table_3 = new JTable();
		scrollPane_3.setViewportView(table_3);
		
		lblAttendance = new JLabel("Attendance");
		lblAttendance.setBounds(320, 275, 100, 14);
		frame.getContentPane().add(lblAttendance);
		
		lblGrading = new JLabel("Grading");
		lblGrading.setBounds(550, 275, 100, 14);
		frame.getContentPane().add(lblGrading);
		
		txtGroup = new JTextField();
		txtGroup.setText("group");
		txtGroup.setBounds(400, 270, 100, 20);
		frame.getContentPane().add(txtGroup);
		txtGroup.setColumns(10);
		
		txtAssignmentName = new JTextField();
		txtAssignmentName.setText("assignment name");
		txtAssignmentName.setBounds(640, 270, 120, 20);
		frame.getContentPane().add(txtAssignmentName);
		txtAssignmentName.setColumns(10);
		
		btnA = new JButton("A");
		btnA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String group = txtGroup.getText();
				
				populateAttendance(group);
			}
		});
		btnA.setBounds(229, 328, 60, 23);
		frame.getContentPane().add(btnA);
		
		btnG = new JButton("G");
		btnG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String name = txtAssignmentName.getText();
				populateGrading(name);
			}
		});
		btnG.setBounds(229, 367, 60, 23);
		frame.getContentPane().add(btnG);
		
		txtAssignment = new JTextField();
		txtAssignment.setText("assignment");
		txtAssignment.setBounds(20, 288, 100, 20);
		frame.getContentPane().add(txtAssignment);
		txtAssignment.setColumns(10);
	}
	
	void populateLab()
	{
		DefaultTableModel m = new DefaultTableModel();
		String[] columnNames = {"TITLE",
		        "NUMBER",
		        "DATE",
		        "DESCRIPTION",
		   "CURRICULA"};
		m.setColumnIdentifiers(columnNames);
		
			List<Laboratory> labs = ls.getAllLaboratories(requestLaboratory);
			
			for(Laboratory s:labs)
			{String[] o = new String[5];
			o[0] = s.getTitle();
			o[1] = s.getNumber().toString();
			o[2] = s.getDate();
			o[3]=s.getDescription();
			o[4]=s.getCurricula();
			
			m.addRow(o);
				
			}
			table.setModel(m);
	}
	
	void populateAssignments()
	{
		DefaultTableModel m = new DefaultTableModel();
		String[] columnNames = {"NAME",
		        "DEADLINE",
		        "DESCRIPTION",
		        "LABORATORY"};
		m.setColumnIdentifiers(columnNames);
		

		List<Assignment> list = ass.getAllAssignments(requestAssignment);
		for(Assignment l:list)
		{String[] o = new String[4];
		o[0] = l.getName();
		o[1] = l.getDeadline();
		o[2] = l.getDescription();
		o[3]=l.getLaboratory().getTitle();
		
		m.addRow(o);
		}
		table_1.setModel(m);
	}
	
	
	void populateAttendance(String group)
	{
		List<Attendance> list = ats.getAllByGroup(group);
		
		DefaultTableModel m1 = new DefaultTableModel();
		
		String[] columnNames1 = {"TITLE",
		        "STUDENT", "LABNUMBER"};
		         m1.setColumnIdentifiers(columnNames1);
		         
		         for(Attendance a:list)
					{
						String[] o = new String[3];
						o[0] =a.getLaboratory().getTitle();
						o[1] = a.getStudent().getName();
						o[2]=a.getLaboratory().getNumber().toString();
						m1.addRow(o);
					}
		         
		         table_2.setModel(m1);
	}
	
	void populateGrading(String assign)
	{
		List<Submission> list = subs.getAllForAssignment(assign);
		DefaultTableModel m = new DefaultTableModel();

		String[] columnNames1 = {"ASSIGNMENT",
		        "STUDENT", "GRADE"};
		m.setColumnIdentifiers(columnNames1);
		  for(Submission a:list)
			{
			  System.out.println(a);
				String[] o = new String[3];
				o[0] =a.getAssignment().getName();
				o[1] = a.getStudent().getName();
				o[2]=a.getGrade().toString();
				m.addRow(o);
			}
		  
		  table_3.setModel(m);
       
		
	}
	
}

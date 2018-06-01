package gui;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.util.List;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;

import model.Assignment;
import model.Attendance;
import model.Laboratory;
import model.Student;
import model.Submission;
import model.Teacher;
import service.AssignmentService;
import service.AttendanceService;
import service.LaboratoryService;
import service.StudentService;
import service.SubmissionService;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.util.*; 
import javax.activation.*;
//import javax.mail.*;
//import javax.mail.internet.*;
import javax.naming.*;


public class TeacherApp {

	
/*	InitialContext ic = new InitialContext();
	String snName = "java:comp/env/mail/MyMailSession";
	Session session = (Session)ic.lookup(snName);
*/	
	private JFrame frame;
	private Teacher teacher;
	private JTextField txtEmail;
	private JTextField txtName;
	private JTextField txtGroup;
	private JTextField txtHobby;
	private JTable table;
	private StudentService ss = new StudentService();
	private AttendanceService ats = new AttendanceService();
	private AssignmentService ass = new AssignmentService();
	private LaboratoryService ls = new LaboratoryService();
	private SubmissionService subs = new SubmissionService();
	private CardLayout cl = new CardLayout();
	private String requestStudent = "http://localhost:8080/student/";
	private String requestLaboratory = "http://localhost:8080/laboratory/";
	private JTextField txtTitle;
	private JTextField txtNumber;
	private JTextField txtDate;
	private JTable table_1;
    JTextArea txtrCurricula = new JTextArea();
    JTextArea txtrDescription = new JTextArea();
    JTextArea txtrDescription_1 = new JTextArea();
    private JTextField txtGroup_1;
    private JTable table_2;
    private JTable table_3;
	private String requestAttendance = "http://localhost:8080/attendance/";
	private String requestAssignment = "http://localhost:8080/assignment/";
	private String requestSubmission = "http://localhost:8080/submission/";
	private JTextField txtDate_1;
	private JTextField txtLabNumber;
	private JTextField txtName_1;
	private JTextField txtDeadline;
	private JTextField txtLabTitle;
	private JTable table_4;
	private JTextField txtLabTitle_1;
	private JTextField txtLabNumber_1;
	private JTextField txtLabNumber_2;
	private JTable table_5;
	private JTable table_6;
	private JTextField txtNewGrade;
		

	/**
	 * Launch the application.
	 */
	public static void runApp() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeacherApp window = new TeacherApp();
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
	public TeacherApp() {
	
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Teacher");
		frame.setBounds(100, 100, 1000, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		final JPanel panel = new JPanel();
	    panel.setBounds(0, 40, 980, 700);
		panel.setLayout(cl);

	
		
		JPanel panelS = new JPanel();
	    JPanel panelL = new JPanel();
	    JPanel panelAs = new JPanel();
	    JPanel panelAt = new JPanel();
	    JPanel panelG = new JPanel();
	    
	    panel.add(panelS, "1");
	    panelS.setLayout(null);
	    
	    txtEmail = new JTextField();
	    txtEmail.setText("email");
	    txtEmail.setBounds(10, 60, 90, 20);
	    panelS.add(txtEmail);
	    txtEmail.setColumns(10);
	    
	    txtName = new JTextField();
	    txtName.setText("name");
	    txtName.setBounds(10, 90, 90, 20);
	    panelS.add(txtName);
	    txtName.setColumns(10);
	    
	    txtGroup = new JTextField();
	    txtGroup.setText("group");
	    txtGroup.setBounds(10, 120, 90, 20);
	    panelS.add(txtGroup);
	    txtGroup.setColumns(10);
	    
	    txtHobby = new JTextField();
	    txtHobby.setText("hobby");
	    txtHobby.setBounds(10, 150, 90, 20);
	    panelS.add(txtHobby);
	    txtHobby.setColumns(10);
	    
	    JButton btnAddStudent = new JButton("Add Student");
	    btnAddStudent.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		
	    		String email,name,hobby,group;
	    		email=txtEmail.getText();
	    		name=txtName.getText();
	    		group=txtGroup.getText();
	    		hobby=txtHobby.getText();
	    		
	    		ss.saveStudent(requestStudent, email, group, hobby, name);
	    		
	    		populateTableStudent();
	    	}
	    });
	    btnAddStudent.setBounds(10, 190, 150, 20);
	    panelS.add(btnAddStudent);
	    
	    JLabel lblAddAStudent = new JLabel("Add a student:");
	    lblAddAStudent.setBounds(10, 10, 110, 15);
	    panelS.add(lblAddAStudent);
	    
	    JScrollPane scrollPane = new JScrollPane();
	    scrollPane.setBounds(170, 9, 600, 600);
	    panelS.add(scrollPane);
	    table = new JTable();
	    populateTableStudent();
	    
	  
	    scrollPane.setViewportView(table);
	    panel.add(panelL, "2");
	    panelL.setLayout(null);
	    
	    txtTitle = new JTextField();
	    txtTitle.setText("title");
	    txtTitle.setBounds(20, 30, 100, 20);
	    panelL.add(txtTitle);
	    txtTitle.setColumns(10);
	    
	    txtNumber = new JTextField();
	    txtNumber.setText("number");
	    txtNumber.setBounds(20, 60, 100, 20);
	    panelL.add(txtNumber);
	    txtNumber.setColumns(10);
	    
	    txtDate = new JTextField();
	    txtDate.setText("date");
	    txtDate.setBounds(20, 90, 100, 20);
	    panelL.add(txtDate);
	    txtDate.setColumns(10);
	    
	    JScrollPane scrollPane_1 = new JScrollPane();
	    scrollPane_1.setBounds(20, 120, 150, 50);
	    panelL.add(scrollPane_1);
	    
	  
	    txtrCurricula.setText("curricula");
	    txtrCurricula.setEditable(true);
	    scrollPane_1.setViewportView(txtrCurricula);
	    
	    JScrollPane scrollPane_2 = new JScrollPane();
	    scrollPane_2.setBounds(20, 180, 150, 50);
	    panelL.add(scrollPane_2);
	    
	    
	    txtrDescription.setText("description");
	    txtrDescription.setEditable(true);
	    scrollPane_2.setViewportView(txtrDescription);
	    
	    JScrollPane scrollPane_3 = new JScrollPane();
	    scrollPane_3.setBounds(250, 60, 600, 700);
	    panelL.add(scrollPane_3);
	    
	    table_1 = new JTable();
	    scrollPane_3.setViewportView(table_1);
	    
	    JButton btnAddLaboratory = new JButton("Add Laboratory");
	    btnAddLaboratory.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		
	    		String title, date, description, curricula;
	    		Long number = new Long(txtNumber.getText());
	    		title=txtTitle.getText();
	    		date = txtDate.getText();
	    		description=txtrDescription.getText();
	    		curricula=txtrCurricula.getText();
	    		
	    		ls.saveLaboratory(requestLaboratory, number, date, title, curricula, description);
	    		populateTableLaboratory();
	    	}
	    });
	    btnAddLaboratory.setBounds(20, 250, 150, 20);
	    panelL.add(btnAddLaboratory);
	    populateTableLaboratory();
	    
	    
	    panel.add(panelAs, "3");
	    panelAs.setLayout(null);
	    
	    txtGroup_1 = new JTextField();
	    txtGroup_1.setText("group");
	    txtGroup_1.setBounds(10, 20, 100, 20);
	    panelAs.add(txtGroup_1);
	    txtGroup_1.setColumns(10);
	    
	    JScrollPane scrollPane_4 = new JScrollPane();
	    scrollPane_4.setBounds(30, 70, 320, 250);
	    panelAs.add(scrollPane_4);
	    
	    table_2 = new JTable();
	    scrollPane_4.setViewportView(table_2);
	    
	    JScrollPane scrollPane_5 = new JScrollPane();
	    scrollPane_5.setBounds(390, 70, 480, 250);
	    panelAs.add(scrollPane_5);
	    
	    table_3 = new JTable();
	    scrollPane_5.setViewportView(table_3);
	    
	    JButton btnFilter = new JButton("Filter");
	    btnFilter.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		
	    		String group = txtGroup_1.getText();
	    		populateTablesAttendance(group);
	    		
	    	}
	    });
	    btnFilter.setBounds(40, 370, 100, 20);
	    panelAs.add(btnFilter);
	    
	    JButton btnIsHere = new JButton("Is here");
	    btnIsHere.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		String date = txtDate.getText();
	    		String group = txtGroup_1.getText();
	    		Long labnr = new Long(txtLabNumber.getText());
	    		String title=null;
	    		String email = (String) table_2.getValueAt(table_2.getSelectedRow(), 2);
	    		
	    		
	    		
	    		Long ids = ss.findIdByEmail(email);
	    		Long idl = ls.getIdByNumerAndDate(labnr, date);
	    		
	    		ats.saveAttendance(requestAttendance, ids, idl);
	    		populateTablesAttendance(group);
	    		
	    	}
	    });
	    btnIsHere.setBounds(184, 369, 89, 23);
	    panelAs.add(btnIsHere);
	    
	    txtDate_1 = new JTextField();
	    txtDate_1.setText("date");
	    txtDate_1.setBounds(137, 20, 86, 20);
	    panelAs.add(txtDate_1);
	    txtDate_1.setColumns(10);
	    
	    txtLabNumber = new JTextField();
	    txtLabNumber.setText("lab number");
	    txtLabNumber.setBounds(253, 20, 86, 20);
	    panelAs.add(txtLabNumber);
	    txtLabNumber.setColumns(10);
	    panel.add(panelAt, "4");
	    panelAt.setLayout(null);
	    
	    txtName_1 = new JTextField();
	    txtName_1.setText("name");
	    txtName_1.setBounds(20, 50, 100, 20);
	    panelAt.add(txtName_1);
	    txtName_1.setColumns(10);
	    
	    txtDeadline = new JTextField();
	    txtDeadline.setText("deadline");
	    txtDeadline.setBounds(20, 100, 100, 20);
	    panelAt.add(txtDeadline);
	    txtDeadline.setColumns(10);
	    
	    JScrollPane scrollPane_6 = new JScrollPane();
	    scrollPane_6.setBounds(20, 150, 150, 50);
	    panelAt.add(scrollPane_6);
	    
	 
	
	    scrollPane_6.setViewportView(txtrDescription_1);
	    
	    txtLabTitle = new JTextField();
	    txtLabTitle.setText("lab title");
	    txtLabTitle.setBounds(20, 250, 100, 20);
	    panelAt.add(txtLabTitle);
	    txtLabTitle.setColumns(10);
	    
	    JScrollPane scrollPane_7 = new JScrollPane();
	    scrollPane_7.setBounds(280, 100, 500, 500);
	    panelAt.add(scrollPane_7);
	    
	    txtLabTitle_1 = new JTextField();
	    txtLabTitle_1.setText("lab title");
	    txtLabTitle_1.setBounds(280, 35, 100, 20);
	    panelAt.add(txtLabTitle_1);
	    txtLabTitle_1.setColumns(10);
	    
	    txtLabNumber_1 = new JTextField();
	    txtLabNumber_1.setText("lab number");
	    txtLabNumber_1.setBounds(425, 35, 86, 20);
	    panelAt.add(txtLabNumber_1);
	    txtLabNumber_1.setColumns(10);
	    
	    table_4 = new JTable();
	    scrollPane_7.setViewportView(table_4);
	    populateTableAssignment(txtLabTitle_1.getText(),txtLabNumber_1.getText());
	    
	   
	    JButton btnFilter_1 = new JButton("filter");
	    btnFilter_1.setBounds(280, 65, 100, 20);
	    panelAt.add(btnFilter_1);
	    
	    txtLabNumber_2 = new JTextField();
	    txtLabNumber_2.setText("lab number");
	    txtLabNumber_2.setBounds(20, 219, 86, 20);
	    panelAt.add(txtLabNumber_2);
	    txtLabNumber_2.setColumns(10);
	    
	    JButton btnAddAssignment = new JButton("Add Assignment");
	    btnAddAssignment.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		String name, deadline, description,labTitle;
	    		name=txtName_1.getText();
	    		deadline=txtDeadline.getText();
	    		description = txtrDescription_1.getText();
	    		labTitle = txtLabTitle.getText();
	    	   Long labnr =  new Long(txtLabNumber_2.getText());
	    	   
	    	   System.out.println("lab nr: "+labnr);
	    	   System.out.println("lab title: "+labTitle);
	    	   Laboratory lab = ls.findByTitleAndNumber(labTitle, labnr);
	    	   System.out.println(lab);
	    	   Long labId =ls.getIdByNumerAndDate(labnr, lab.getDate());
	    	   ass.saveAssignment(requestAssignment, name, deadline, description,labId );
	    	   populateTableAssignment(lab.getTitle(),labnr.toString());
	    		
	    	}
	    });
	    btnAddAssignment.setBounds(20, 280, 130, 20);
	    panelAt.add(btnAddAssignment);
	    
	   
	    
	    
	    panel.add(panelG, "5");
	    panelG.setLayout(null);
	    
	    JScrollPane scrollPane_8 = new JScrollPane();
	    scrollPane_8.setBounds(46, 69, 300, 185);
	    panelG.add(scrollPane_8);
	    
	    table_5 = new JTable();
	    scrollPane_8.setViewportView(table_5);
	   
	    
	    JScrollPane scrollPane_9 = new JScrollPane();
	    scrollPane_9.setBounds(420, 69, 290, 185);
	    panelG.add(scrollPane_9);
	    
	    table_6 = new JTable();
	    scrollPane_9.setViewportView(table_6);
	    
	    JButton btnGrade = new JButton("grade");
	    btnGrade.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		
	    		String gr = (String) table_5.getValueAt(table_5.getSelectedRow(), 2);
	    		String assignmentName = (String) table_5.getValueAt(table_5.getSelectedRow(), 0);
	    		String student = (String) table_5.getValueAt(table_5.getSelectedRow(), 1);
	    		String newGr = txtNewGrade.getText();
	    		Float newGrade = new Float(newGr);
	    		Float grade = new Float(gr);
	    		
	           Long idSub = subs.getId(student, assignmentName);
	           
	           subs.gradeSubmission(requestSubmission, idSub, newGrade);
	           
	           populateTablesGrade();
	    		
	    		
	    	}
	    });
	    btnGrade.setBounds(46, 310, 89, 23);
	    panelG.add(btnGrade);
	    
	    txtNewGrade = new JTextField();
	    txtNewGrade.setText("new grade");
	    txtNewGrade.setBounds(46, 276, 86, 20);
	    panelG.add(txtNewGrade);
	    txtNewGrade.setColumns(10);
	    
	    
	   
	    frame.getContentPane().add(panel);
	    
	    JButton btnStudents = new JButton("Students");
	    btnStudents.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		cl.show(panel, "1");
	    	}
	    });
	    btnStudents.setBounds(40, 10, 100, 20);
	    frame.getContentPane().add(btnStudents);
	    
	    JButton btnLaboratory = new JButton("Laboratory");
	    btnLaboratory.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		cl.show(panel, "2");
	    	}
	    });
	    btnLaboratory.setBounds(150, 10, 100, 20);
	    frame.getContentPane().add(btnLaboratory);
	    
	    JButton btnAttendance = new JButton("Attendance");
	    btnAttendance.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		cl.show(panel, "3");
	    		
	    	}
	    });
	    btnAttendance.setBounds(260, 10, 100, 20);
	    frame.getContentPane().add(btnAttendance);
	    
	    JButton btnAssignments = new JButton("Assignments");
	    btnAssignments.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		cl.show(panel, "4");
	    	}
	    });
	    btnAssignments.setBounds(370, 10, 120, 20);
	    frame.getContentPane().add(btnAssignments);
	    
	    JButton btnGrading = new JButton("Grading");
	    btnGrading.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		cl.show(panel, "5");
	    		 populateTablesGrade();
	    	}
	    });
	    btnGrading.setBounds(500, 9, 100, 20);
	    frame.getContentPane().add(btnGrading);
		
	}
	
	void populateTableStudent()
	{
		DefaultTableModel m = new DefaultTableModel();
		String[] columnNames = {"EMAIL",
		        "NAME",
		        "GROUP",
		        "HOBBY"};
		m.setColumnIdentifiers(columnNames);
		
			List<Student> students = ss.getAllStudents("http://localhost:8080/student/");
			
			for(Student s:students)
			{String[] o = new String[4];
			o[0] = s.getEmail();
			o[1] = s.getName();
			o[2] = s.getGroup();
			o[3]=s.getHobby();
			
			m.addRow(o);
				
			}
			table.setModel(m);
	}
	
	void populateTableLaboratory()
	{
		DefaultTableModel m = new DefaultTableModel();
		String[] columnNames = {"TITLE",
		        "DATE",
		        "NUMBER",
		        "CURRICULA",
		        "DESCRIPTION"};
		m.setColumnIdentifiers(columnNames);
		
			List<Laboratory> labs = ls.getAllLaboratories(requestLaboratory);
			
			for(Laboratory l:labs)
			{String[] o = new String[5];
			o[0] = l.getTitle();
			o[1] = l.getDate();
			o[2] = l.getNumber().toString();
			o[3]=l.getCurricula();
			o[4]=l.getDescription();
			
			m.addRow(o);
				
			}
			table_1.setModel(m);
	}
	
	
	void populateTablesAttendance(String group)
	{
		DefaultTableModel m = new DefaultTableModel();
		DefaultTableModel m1 = new DefaultTableModel();
		
		String[] columnNames = {"NAME",
		        "GROUP","EMAIL"};
		m.setColumnIdentifiers(columnNames);
		String[] columnNames1 = {"TITLE",
        "STUDENT", "LABNUMBER"};
         m1.setColumnIdentifiers(columnNames1);
		
			
			if(!(group.equals("group"))) {
			List<Student> studs = ss.getStudentsByGroup(group);
			for(Student s:studs)
			{String[] o = new String[3];
			o[0] = s.getName();
			o[1] = s.getGroup();
			o[2]=s.getEmail();
			
			m.addRow(o);
				
			}
			
		
			List<Attendance> attendances = ats.getAllByGroup(group);
			for(Attendance a:attendances)
			{
				String[] o = new String[3];
				o[0] =a.getLaboratory().getTitle();
				o[1] = a.getStudent().getName();
				o[2]=a.getLaboratory().getNumber().toString();
				m1.addRow(o);
			}
			
		
			}
			else
			{
				List<Attendance> all = ats.getAllAttendances(requestAttendance );

				List<Student> stds = ss.getAllStudents(requestStudent);
				
				for(Attendance a:all)
				{
					String[] o = new String[3];
					o[0] =a.getLaboratory().getTitle();
					o[1] = a.getStudent().getName();
					o[2]=a.getLaboratory().getNumber().toString();
					m1.addRow(o);
				}
				
				for(Student s:stds)
				{
					String[] o = new String[3];
					o[0] = s.getName();
					o[1] = s.getGroup();
					o[2]=s.getEmail();
				
					
					m.addRow(o);
				}
			}
			table_2.setModel(m);
			table_3.setModel(m1);
			
	}
	
	void populateTableAssignment(String title, String labnr)
	{
		
		Long nr=(long) 0;
		if(!(labnr.equals("lab number")))
		nr= new Long(labnr);
		DefaultTableModel m = new DefaultTableModel();
		String[] columnNames = {"NAME",
		        "DEADLINE",
		        "DESCRIPTION",
		        "LABORATORY"};
		m.setColumnIdentifiers(columnNames);
		
			if((!(title.equals("title"))) && !(nr.equals((long)0))) {
	        Laboratory lab = ls.findByTitleAndNumber(title, nr);
	        Long labid = ls.getIdByNumerAndDate(lab.getNumber(), lab.getDate());
				
				List<Assignment> list = ass.getAssignmentsByLab(requestAssignment, labid);
			for(Assignment l:list)
			{String[] o = new String[4];
			o[0] = l.getName();
			o[1] = l.getDeadline();
			o[2] = l.getDescription();
			o[3]=l.getLaboratory().getTitle();
			
			m.addRow(o);
			
			}
				
			}
			else 
			{
				List<Assignment> list = ass.getAllAssignments(requestAssignment);
				for(Assignment l:list)
				{String[] o = new String[4];
				o[0] = l.getName();
				o[1] = l.getDeadline();
				o[2] = l.getDescription();
				o[3]=l.getLaboratory().getTitle();
				
				m.addRow(o);
				
				}
				
			}
			table_4.setModel(m);
	}
	
   void populateTablesGrade()
   {
	   DefaultTableModel m = new DefaultTableModel();
		DefaultTableModel m1 = new DefaultTableModel();
		
		String[] columnNames = {"ASSIGNMENT",
		        "STUDENT","GRADE"};
		m.setColumnIdentifiers(columnNames);
		
		
			
			{
				List<Submission> all = subs.getAllSubmissions(requestSubmission);

		
				
				for(Submission a:all)
				{
					System.out.println(a);
					String[] o = new String[3];
					o[0] =a.getAssignment().getName();
					o[1] = a.getStudent().getEmail();
					Float grade = a.getGrade();
					if(grade.equals(null)) o[2]="0";
					else
					o[2]=a.getGrade().toString();
					
					m.addRow(o);
				}
				
			}
			table_5.setModel(m);
   }
  /* 
   void sendEmail(String email)
   {
	   // Recipient's email ID needs to be mentioned.
	      String to = email;

	      // Sender's email ID needs to be mentioned
	      String from = "iancu_daiana28@yahoo.com";

	      // Assuming you are sending email from localhost
	      String host = "localhost";

	      // Get system properties
	      Properties properties = System.getProperties();

	      // Setup mail server
	      properties.setProperty("mail.smtp.host", host);

	      // Get the default Session object.
	      Session session = Session.getDefaultInstance(properties);

	      try {
	         // Create a default MimeMessage object.
	         MimeMessage message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

	         // Set Subject: header field
	         message.setSubject("This is the Subject Line!");

	         // Now set the actual message
	         message.setText("This is actual message");

	         // Send message
	         Transport.send(message);
	         System.out.println("Sent message successfully....");
	      } catch (MessagingException mex) {
	         mex.printStackTrace();
	      }
   }
   */
}

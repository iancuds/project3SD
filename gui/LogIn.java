package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JTextField;

import model.Student;
import service.StudentService;
import service.TeacherService;

import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.awt.event.ActionEvent;

public class LogIn {

	private JFrame frame;
	private JTextField txtEmail;
	private JTextField txtPassword;
	private StudentService studentServ;
	private TeacherService teacherServ;
	private TeacherApp teacherApp;
	private StudentApp studentApp;

	/**
	 * Launch the application.
	 */
	public static void runGUI() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogIn window = new LogIn();
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
	public LogIn() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		studentServ = new StudentService();
		teacherServ = new TeacherService();
		
		frame = new JFrame("Log In");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtEmail = new JTextField();
		txtEmail.setText("email");
		txtEmail.setBounds(140, 60, 90, 20);
		frame.getContentPane().add(txtEmail);
		txtEmail.setColumns(10);
		
		final JTextArea textArea = new JTextArea();
		textArea.setBounds(100, 210, 160, 40);
		frame.getContentPane().add(textArea);
		
		txtPassword = new JTextField();
		txtPassword.setText("password");
		txtPassword.setBounds(140, 120, 90, 20);
		frame.getContentPane().add(txtPassword);
		txtPassword.setColumns(10);
		
		JButton btnLogIn = new JButton("log in");
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email = txtEmail.getText();
				String passwd = txtPassword.getText();
				
				String message1=""; 
				String message2="";
				boolean successT,successS;
				String teacherpass=new String(passwd);
				passwd = encryptPassword(passwd, true);
				successS = studentServ.logIn(email, passwd, message1);
				successT = teacherServ.logIn(email, teacherpass, message2);
		        
		        System.out.println("T:" + successT+" S:"+successS);
		        
		        if(successS) {
		        	textArea.setText(message1);
		        	
		        	
		        	System.out.println("success stud");
		        	
		        	Student std = studentServ.getStudentByEmail(email);
		        	
		        	studentApp.runApp(std);
		        }
		        else if(successT)
		        {
		        	textArea.setText(message2);
		        	System.out.println(message2);
		        	teacherApp.runApp();
		        }
		        else {textArea.setText("E-mail or password incorrect!");}
			}
		});
		btnLogIn.setBounds(140, 180, 90, 20);
		frame.getContentPane().add(btnLogIn);
		
		textArea.setVisible(true);
	}
	
	String encryptPassword(String p, boolean way)
	{
		if(!way)
		return p;
		else 
		{
			
			
				String encpass = p;
				try {
					   MessageDigest md = MessageDigest.getInstance("MD5");
				
					   md.update(p.getBytes());
			            //Get the hash's bytes
			            byte[] bytes = md.digest();
			            //This bytes[] has bytes in decimal format;
			            //Convert it to hexadecimal format
			            StringBuilder sb = new StringBuilder();
			            for(int i=0; i< bytes.length ;i++)
			            {
			                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			            }
			            //Get complete hashed password in hex format
			            encpass = sb.toString();
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return encpass;
			
		}
	}
	
	
}

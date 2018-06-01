package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import model.Student;
import service.StudentService;

import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.awt.event.ActionEvent;

public class Register {

	private JFrame frame;
	private JTextField txtToken;
	private JTextField txtNewPassword;
	private JTextField txtConfirmPassword;
	private JTextArea textArea;
	private JButton btnRegister;
	private StudentService ss ;
	private JButton btnLogIn;
	private String request = "http://localhost:8080/student/";
	private LogIn login;

	/**
	 * Launch the application.
	 */
	public static void runWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register window = new Register();
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
	public Register() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		ss = new StudentService();
		frame = new JFrame("Register");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtToken = new JTextField();
		txtToken.setText("token");
		txtToken.setBounds(140, 20, 100, 20);
		frame.getContentPane().add(txtToken);
		txtToken.setColumns(10);
		
		txtNewPassword = new JTextField();
		txtNewPassword.setText("new password");
		txtNewPassword.setBounds(140, 60, 100, 20);
		frame.getContentPane().add(txtNewPassword);
		txtNewPassword.setColumns(10);
		
		txtConfirmPassword = new JTextField();
		txtConfirmPassword.setText("confirm password");
		txtConfirmPassword.setBounds(140, 100, 100, 20);
		frame.getContentPane().add(txtConfirmPassword);
		txtConfirmPassword.setColumns(10);
		
		textArea = new JTextArea();
		textArea.setBounds(30, 130, 360, 50);
		textArea.setLineWrap(true);
		frame.getContentPane().add(textArea);
		
		btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!(txtConfirmPassword.getText().equals(txtNewPassword.getText())))
				{
					textArea.setText("Passwords don't match!");
				}
				else
				{
					
					String pass = txtNewPassword.getText();
					String passwd = encryptPassword(pass, true);
					
					Student student = ss.register(request, txtToken.getText(), passwd);
					textArea.setText("Congrats, "+student.getName() +"! You have successfully registered! Use your e-mail address to log in: "+student.getEmail());
			
				}
			}
		});
		btnRegister.setBounds(140, 190, 100, 20);
		frame.getContentPane().add(btnRegister);
		
		btnLogIn = new JButton("Log In");
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//log in gui
				LogIn.runGUI();
			}
		});
		btnLogIn.setBounds(140, 220, 100, 20);
		frame.getContentPane().add(btnLogIn);
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

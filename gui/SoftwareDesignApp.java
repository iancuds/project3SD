package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import service.StudentService;
import service.TeacherService;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SoftwareDesignApp {

	private StudentService ss ;
	private TeacherService ts;
	private JFrame frame;
	private LogIn login ;
	private Register register;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SoftwareDesignApp window = new SoftwareDesignApp();
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
	public SoftwareDesignApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		ss=new StudentService();
		ts= new TeacherService();
		frame = new JFrame("Software Design Laboratory");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnLogIn = new JButton("Log In");
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LogIn.runGUI();
			}
		});
		btnLogIn.setBounds(161, 78, 89, 23);
		frame.getContentPane().add(btnLogIn);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Register.runWindow();
				//open window for register
				
			}
		});
		btnRegister.setBounds(161, 141, 89, 23);
		frame.getContentPane().add(btnRegister);
	}
	
	/*idteacher": 4,
    "name": "teacher",
    "email": "teacher@t.com",
    "token": "49*lNQ[qCKRp.dRMg*n$L&6.gZx9;jltmRWQzAk%RxQBTt!JKZkkw`1?0)Z~f1BZD#dn].R`8E9VGKg'l`fR^d}luM>6~;{{rUa^gm*9WrZ:rE<cxJN%wR*|hjtl|wN]",
    "passwd": "teacher"*/

}

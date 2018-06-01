package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Teacher {
	
	
	 private String email;
	    private String name;
	    private String token;
	
	    private String passwd;
	
	    public Teacher(String email, String name, String token,  String passwd) {
			super();
			this.email = email;
			this.name = name;
			this.token = token;
			
			this.passwd = passwd;
		}

	    public Teacher() {}
	    
		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getToken() {
			return token;
		}

		public void setToken(String token) {
			this.token = token;
		}

		
		public String getPasswd() {
			return passwd;
		}

		public void setPasswd(String passwd) {
			this.passwd = passwd;
		}

		@Override
		public String toString() {
			return "Teacher [email=" + email + ", name=" + name + ", token=" + token  + ", passwd="
					+ passwd + "]";
		}
	    
	    


}

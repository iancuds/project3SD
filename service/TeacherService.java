package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.Teacher;

public class TeacherService {
	private ObjectMapper mapper = new ObjectMapper();
	
	public TeacherService()
	{}
	
	
	
	public List<Teacher> getAllTeachers(String request)
	{
		request = request + "getAllTeachers";
		
		List<Teacher> teachers =new ArrayList<Teacher>();
try {

	           DefaultHttpClient httpClient = new DefaultHttpClient();
			
			HttpGet getRequest = new HttpGet(request
					);
				//getRequest.addHeader("accept", "application/json");

				HttpResponse response = httpClient.execute(getRequest);

				if (response.getStatusLine().getStatusCode() != 200) {
					throw new RuntimeException("Failed : HTTP error code : "
					   + response.getStatusLine().getStatusCode());
				}

				BufferedReader br = new BufferedReader(
		                         new InputStreamReader((response.getEntity().getContent())));

				String output = null;
				
				System.out.println("Output from Server .... \n");
				while ((output = br.readLine()) != null) {
					teachers= mapper.readValue(output,  new TypeReference<List<Teacher>>(){});
					
				}
				
				

				httpClient.getConnectionManager().shutdown();

			  } catch (ClientProtocolException e) {
			
				e.printStackTrace();

			  } catch (IOException e) {
			
				e.printStackTrace();
			  }
         
	
			return teachers;
		
		}
	

	public Teacher getTeacherById( String request, Long teacherId)
	{  request = request+"getTeacherById?teacherId="+teacherId.toString();
		Teacher teacher = new Teacher();
		try {DefaultHttpClient httpClient = new DefaultHttpClient();
        	
        	HttpGet getRequest = new HttpGet(request
					);
        	
  

			HttpResponse response = httpClient.execute(getRequest);

			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
				   + response.getStatusLine().getStatusCode());
			}

			BufferedReader br = new BufferedReader(
	                         new InputStreamReader((response.getEntity().getContent())));

			String output = null;
			
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				teacher= mapper.readValue(output,  new TypeReference<Teacher>(){});
				httpClient.getConnectionManager().shutdown();
			}	
        	
        	
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
		
		return teacher;
    }
	
	 public boolean logIn( String email, String passwd, String message){
		   
		 String request = "http://localhost:8080/teacher/logIn?email=%s&passwd=%s";
		 
		 String requestEnc = null;
			try {
				requestEnc = String.format(request,
				  URLEncoder.encode(email, "UTF-8"),
				  URLEncoder.encode(passwd, "UTF-8")
				);
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		  
		   boolean result = false;
	       String failEmail = "The e-mail address is wrong!";
	       String failRegistered="You are not yet registered!";
	       String failPassword = "The password or the e-mail address is wrong!";
	       String success = "Successful login!";
	       String output;
	       
	       
	       DefaultHttpClient httpClient = new DefaultHttpClient();
	       
	       
	       HttpGet getRequest = new HttpGet(requestEnc	);
       	   

			HttpResponse response;
			try {
				response = httpClient.execute(getRequest);
			
			
			BufferedReader br = new BufferedReader(
                    new InputStreamReader((response.getEntity().getContent())));

			
			System.out.println("Output from Server .... \n");
			
			
			message=br.readLine();
	
				httpClient.getConnectionManager().shutdown();
				System.out.println("In teachserv "+message);
				if(message.equals(success)) result =  true;
				else result = false;
			
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      return result;    
	    }
	 

	 public Teacher register(String request, String token, String passwd)
	 {
		 
	     
	     String url = request+"register?token=%s&passwd=%s";
    
	     
	     
	     String requestEnc = null;
		try {
			requestEnc = String.format(url,
			  URLEncoder.encode(token, "UTF-8"),
			  passwd
			);
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(requestEnc);
	   
	   Teacher teacher = new Teacher();
		 
		 try {DefaultHttpClient httpClient = new DefaultHttpClient();
     	
     	HttpPut putRequest = new HttpPut(requestEnc
					);
     	


			HttpResponse response = httpClient.execute(putRequest);

			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
				   + response.getStatusLine().getStatusCode());
			}

			BufferedReader br = new BufferedReader(
	                         new InputStreamReader((response.getEntity().getContent())));

			String output = null;
			
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				teacher= mapper.readValue(output,  new TypeReference<Teacher>(){});
				httpClient.getConnectionManager().shutdown();
			}	
     	
     	
     } catch (Exception e) {
         e.printStackTrace();
         return null;
     }
		
		return teacher;
 
		 
		 
	 }
	 
	 
	 
	 public Teacher saveTeacher(String request, String email,  String group,  String hobby,  String name) {
		  //http://localhost:8080/teacher/saveTeacher?email=e&group=g&hobby=h&name=n
		 
		  
		  String url = request +"saveTeacher?email=%s&group=%s&hobby=%s&name=%s";
		  
		  
		  
		  Teacher teacher = new Teacher();
		 
		  String requestEnc = null;
				try {
					requestEnc = String.format(url,
					  URLEncoder.encode(email, "UTF-8"),
					  URLEncoder.encode(name, "UTF-8")
					);
					
					System.out.println(requestEnc);
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		  
		  DefaultHttpClient httpClient = new DefaultHttpClient();
	      
	      
	      HttpPost postRequest = new HttpPost(requestEnc);
	      
	      try {
			HttpResponse response = httpClient.execute(postRequest);
			
			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
				   + response.getStatusLine().getStatusCode());
			}

			BufferedReader br = new BufferedReader(
	                         new InputStreamReader((response.getEntity().getContent())));

			String output = null;
			
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				teacher= mapper.readValue(output,  new TypeReference<Teacher>(){});
				httpClient.getConnectionManager().shutdown();
			}	
			
			
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  return teacher;
	  }
	  
	  public Teacher updateTeacher( String request, Long teacherId,  String email,  String group,  String hobby,  String name) {

		  //http://localhost:8080/teacher/updateTeacher?teacherId=2&email=erwgt%40gmail.com&group=ewg&hobby=ebeet&name=wb

		  
		  
	  String url = request +"updateTeacher?teacherId=%s&email=%s&group=%s&hobby=%s&name=%s";
		  
		  
		  
		  Teacher teacher = new Teacher();
		 
		  String requestEnc = null;
				try {
					requestEnc = String.format(url,
					  URLEncoder.encode(teacherId.toString(),"UTF-8"),
					  URLEncoder.encode(email, "UTF-8"),
					  URLEncoder.encode(name, "UTF-8")
					);
					
					System.out.println(requestEnc);
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		  
		  DefaultHttpClient httpClient = new DefaultHttpClient();
	      
	      
	      HttpPut putRequest = new HttpPut(requestEnc);
	      
	      try {
			HttpResponse response = httpClient.execute(putRequest);
			
			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
				   + response.getStatusLine().getStatusCode());
			}

			BufferedReader br = new BufferedReader(
	                         new InputStreamReader((response.getEntity().getContent())));

			String output = null;
			
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				teacher= mapper.readValue(output,  new TypeReference<Teacher>(){});
				httpClient.getConnectionManager().shutdown();
			}	
			
			
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  return teacher;
			 
		  }
	  
	  public String deleteTeacherById( String request, Long teacherId)
	  {
		  //http://localhost:8080/teacher/deleteTeacherById?teacherId=2
	  String url = request +"deleteTeacherById?teacherId="+teacherId.toString();
		  
		  
		  
		 String ret=null;
		  
		  DefaultHttpClient httpClient = new DefaultHttpClient();
	      
	      
	      HttpDelete deleteRequest = new HttpDelete(url);
	      
	      try {
			HttpResponse response = httpClient.execute(deleteRequest);
			
			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
				   + response.getStatusLine().getStatusCode());
			}

			BufferedReader br = new BufferedReader(
	                         new InputStreamReader((response.getEntity().getContent())));

		
			
			System.out.println("Output from Server .... \n");
		  ret = br.readLine();
			
			
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  return ret;
		  
	  }
	  
	  public Teacher changePassword(String request, String email, String passwd, String newpass)
	  {
		  //http://localhost:8080/teacher/changePassword?email=zeEmail&passwd=string&newpasswd=stringNou
		  
	 String url = request +"changePassword?email=%s&passwd=%s&newpasswd=%s";
		  
		  
		  
		  Teacher teacher = new Teacher();
		 
		  String requestEnc = null;
				try {
					requestEnc = String.format(url,
					  URLEncoder.encode(email, "UTF-8"),
					  URLEncoder.encode(passwd, "UTF-8"),
					  URLEncoder.encode(newpass, "UTF-8")
					);
					
					System.out.println(requestEnc);
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		  
		  DefaultHttpClient httpClient = new DefaultHttpClient();
	      
	      
	      HttpPut putRequest = new HttpPut(requestEnc);
	      
	      try {
			HttpResponse response = httpClient.execute(putRequest);
			
			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
				   + response.getStatusLine().getStatusCode());
			}

			BufferedReader br = new BufferedReader(
	                         new InputStreamReader((response.getEntity().getContent())));

			String output = null;
			
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				teacher= mapper.readValue(output,  new TypeReference<Teacher>(){});
				httpClient.getConnectionManager().shutdown();
			}	
			
			
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  return teacher;
		  
	  }

	 
}

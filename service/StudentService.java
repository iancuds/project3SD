package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

import model.Student;





public class StudentService {
	private String def = "http://localhost:8080/student/";
	private ObjectMapper mapper = new ObjectMapper();
	//private DefaultHttpClient httpClient;
	
	public StudentService()
	{
		//httpClient = new DefaultHttpClient();
	}
	
	
	
	
	
	public String getDef() {
		return def;
	}





	public void setDef(String def) {
		this.def = def;
	}





	public ObjectMapper getMapper() {
		return mapper;
	}





	public void setMapper(ObjectMapper mapper) {
		this.mapper = mapper;
	}





	public Student getStudentByEmail(String email)
	{
		String request = "http://localhost:8080/student/findStudentByEmail?email=%s";
		 String requestEnc = null;
			try {
				requestEnc = String.format(request,
				  URLEncoder.encode(email, "UTF-8")
				);
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		Student student = new Student();
		
		try {DefaultHttpClient httpClient = new DefaultHttpClient();
    	
    	HttpGet getRequest = new HttpGet(requestEnc
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
			student= mapper.readValue(output,  new TypeReference<Student>(){});
			httpClient.getConnectionManager().shutdown();
		}	
    	
    	
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
	
	return student;
	}
	
	public List<Student> getAllStudents(String request)
	{
		request = request + "getAllStudents";
		
		List<Student> studs =new ArrayList<Student>();
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
					studs= mapper.readValue(output,  new TypeReference<List<Student>>(){});
					
				}
				
				

				httpClient.getConnectionManager().shutdown();

			  } catch (ClientProtocolException e) {
			
				e.printStackTrace();

			  } catch (IOException e) {
			
				e.printStackTrace();
			  }
         
	
			return studs;
		
		}
	

	public Student getStudentById( String request, Long studentId)
	{  
		System.out.println("In get std by id :" + studentId);
		request = request+"getStudentById?studentId="+studentId.toString();

		Student std = new Student();
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
				std= mapper.readValue(output,  new TypeReference<Student>(){});
				httpClient.getConnectionManager().shutdown();
			}	
        	
        	
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
		
		return std;
    }
	
	 public boolean logIn( String email, String passwd, String message){
		   
		 String request = "http://localhost:8080/student/logIn?email=%s&passwd=%s";
		 
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
			
			
			message= br.readLine();
	
				httpClient.getConnectionManager().shutdown();
				System.out.println("In stserv "+message);
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
	 
	 public Student register(String request, String token, String passwd)
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
	   
	   Student std = new Student();
		 
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
				std= mapper.readValue(output,  new TypeReference<Student>(){});
				httpClient.getConnectionManager().shutdown();
			}	
     	
     	
     } catch (Exception e) {
         e.printStackTrace();
         return null;
     }
		
		return std;
 
		 
		 
	 }
	 
  public Student saveStudent(String request, String email,  String group,  String hobby,  String name) {
	  //http://localhost:8080/student/saveStudent?email=e&group=g&hobby=h&name=n
	 
	  
	 
	  
	  
	  
	  Student student = new Student();
	  String url = request +"saveStudent?email=%s&group=%s&hobby=%s&name=%s";
	 
	  String requestEnc = null;
			try {
				requestEnc = String.format(url,
				  URLEncoder.encode(email, "UTF-8"),
				  URLEncoder.encode(group, "UTF-8"),
				  URLEncoder.encode(hobby, "UTF-8"),
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
			student= mapper.readValue(output,  new TypeReference<Student>(){});
			httpClient.getConnectionManager().shutdown();
		}	
		
		
		
	} catch (ClientProtocolException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  return student;
  }
  
  public Student updateStudent( String request, Long studentId,  String email,  String group,  String hobby,  String name) {

	  //http://localhost:8080/student/updateStudent?studentId=2&email=erwgt%40gmail.com&group=ewg&hobby=ebeet&name=wb

	  
	  
  String url = request +"updateStudent?studentId=%s&email=%s&group=%s&hobby=%s&name=%s";
	  
	  
	  
	  Student student = new Student();
	 
	  String requestEnc = null;
			try {
				requestEnc = String.format(url,
				  URLEncoder.encode(studentId.toString(),"UTF-8"),
				  URLEncoder.encode(email, "UTF-8"),
				  URLEncoder.encode(group, "UTF-8"),
				  URLEncoder.encode(hobby, "UTF-8"),
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
			student= mapper.readValue(output,  new TypeReference<Student>(){});
			httpClient.getConnectionManager().shutdown();
		}	
		
		
		
	} catch (ClientProtocolException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  return student;
		 
	  }
  
  public String deleteStudentById( String request, Long studentId)
  {
	  //http://localhost:8080/student/deleteStudentById?studentId=2
  String url = request +"deleteStudentById?studentId="+studentId.toString();
	  
	  
	  
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
  
  public Student changePassword(String request, String email, String passwd, String newpass)
  {
	  //http://localhost:8080/student/changePassword?email=zeEmail&passwd=string&newpasswd=stringNou
	  
 String url = request +"changePassword?email=%s&passwd=%s&newpasswd=%s";
	  
	  
	  
	  Student student = new Student();
	 
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
			student= mapper.readValue(output,  new TypeReference<Student>(){});
			httpClient.getConnectionManager().shutdown();
		}	
		
		
		
	} catch (ClientProtocolException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  return student;
	  
  }
  
  public List<Student> getStudentsByGroup(String group)
  {
	  List<Student> all = getAllStudents(def);
	  List<Student> res = new ArrayList<Student>();
	  
	  for(Student s:all)
	  {
		  if(s.getGroup().equals(group)) res.add(s);
	  }
	  
	  return res;
  }

  
  public Long findIdByEmail(String email)
  {
	  String request = "http://localhost:8080/student/findIdByEmail?email=%s";
	  String requestEnc =null;
	  Long id;
	  try {
		requestEnc = String.format(request,
				  URLEncoder.encode(email, "UTF-8"));
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
	  try {DefaultHttpClient httpClient = new DefaultHttpClient();
  	
  	HttpGet getRequest = new HttpGet(requestEnc
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
		output = br.readLine();
			
		id= new Long(output);	
  	
  	
  } catch (Exception e) {
      e.printStackTrace();
      return null;
  }
	return id;
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

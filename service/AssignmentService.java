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

import model.Assignment;

public class AssignmentService {

	private ObjectMapper mapper = new ObjectMapper();
	
	public AssignmentService() {}
	
	public List<Assignment> getAllAssignments(String request)
	{
		request = request + "getAllAssignments";
		
		List<Assignment> assignments =new ArrayList<Assignment>();
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
					assignments= mapper.readValue(output,  new TypeReference<List<Assignment>>(){});
					
				}
				
				

				httpClient.getConnectionManager().shutdown();

			  } catch (ClientProtocolException e) {
			
				e.printStackTrace();

			  } catch (IOException e) {
			
				e.printStackTrace();
			  }
         
	
			return assignments;
		
		}
	

	public Assignment getAssignmentById( String request, Long assignmentId)
	{  request = request+"getAssignmentById?assignmentId="+assignmentId.toString();
		Assignment assignment = new Assignment();
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
				assignment= mapper.readValue(output,  new TypeReference<Assignment>(){});
				httpClient.getConnectionManager().shutdown();
			}	
        	
        	
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
		
		return assignment;
    }
	
	 
	
/*	private String name;
    private String deadline;
    private String description;
    private Long idlaboratory;*/
	
	 public Assignment saveAssignment(String request, String name, String deadline,  String description,  Long idlaboratory) {
		  // http://localhost:8080/assignment/saveAssignment?grade=2.5&idassignment=3&idstudent=6&date=12.11.17&link=blabla
		 
		  
		  String url = request +"saveAssignment?name=%s&deadline=%s&description=%s&idlaboratory=%s";
		  
		  
		  
		  Assignment assignment = new Assignment();
		 
		  String requestEnc = null;
				try {
					requestEnc = String.format(url,
					  URLEncoder.encode(name, "UTF-8"),
					  URLEncoder.encode(deadline, "UTF-8"),
					  URLEncoder.encode(description, "UTF-8"),
					  URLEncoder.encode(idlaboratory.toString(), "UTF-8")
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
				assignment= mapper.readValue(output,  new TypeReference<Assignment>(){});
				httpClient.getConnectionManager().shutdown();
			}	
			
			
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  return assignment;
	  }
	  
	  public Assignment updateAssignment( String request, Long assignmentId,  String name, String deadline,  String description,  Long idlaboratory) {
// http://localhost:8080/assignment/updateAssignment?assignmentId=4&grade=10&idassignment=3&idstudent=6&date=string&link=string

		  
		  
	  String url = request +"updateAssignment?assignmentId=%s&name=%s&deadline=%s&description=%s&idlaboratory=%s";
		  
		  
		  
		  Assignment assignment = new Assignment();
		 
		  String requestEnc = null;
				try {
					requestEnc = String.format(url,
					  URLEncoder.encode(assignmentId.toString(),"UTF-8"),
					  URLEncoder.encode(name, "UTF-8"),
					  URLEncoder.encode(deadline, "UTF-8"),
					  URLEncoder.encode(description, "UTF-8"),
					  URLEncoder.encode(idlaboratory.toString(), "UTF-8")
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
				assignment= mapper.readValue(output,  new TypeReference<Assignment>(){});
				httpClient.getConnectionManager().shutdown();
			}	
			
			
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  return assignment;
			 
		  }
	  
	  public String deleteAssignmentById( String request, Long assignmentId)
	  {
		  //http://localhost:8080/assignment/deleteAssignmentById?assignmentId=2
	  String url = request +"deleteAssignmentById?assignmentId="+assignmentId.toString();
		  
		  
		  
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
	  
	  public List<Assignment> getAssignmentsByLab(String request, Long idlaboratory) {
	    
		  // http://localhost:8080/assignment/getAssignmentsByLab?idlaboratory=5
		  List<Assignment> list = new ArrayList<Assignment>();
	     
	     String url = request +"getAssignmentsByLab?idlaboratory="+idlaboratory.toString();
		  
	     
	     DefaultHttpClient httpClient = new DefaultHttpClient();
			
	  			HttpGet getRequest = new HttpGet(url
	  					);
	  				//getRequest.addHeader("accept", "application/json");

	  			
	  			try {
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
	  					list= mapper.readValue(output,  new TypeReference<List<Assignment>>(){});
	  					
	  				}
	  				
	  				

	  				httpClient.getConnectionManager().shutdown();

	  			  } catch (ClientProtocolException e) {
	  			
	  				e.printStackTrace();

	  			  } catch (IOException e) {
	  			
	  				e.printStackTrace();
	  			  }
	           
	  	
	  			
	     
	     
	     
	     return list;
	  
	  }
	  
	  public Long getIdByName(String name)
	  {
		  //http://localhost:8080/assignment/getIdByName?name=string2
		  String request = "http://localhost:8080/assignment/getIdByName?name=%s";
		  Long res=null;
		  String requestEnc = null;
			try {
				requestEnc = String.format(request,
				  URLEncoder.encode(name, "UTF-8")
				);
				
				System.out.println(requestEnc);
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		  
			 DefaultHttpClient httpClient = new DefaultHttpClient();
		      
		      
		      HttpGet postRequest = new HttpGet(requestEnc);
		      
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
				if((output = br.readLine())!=null)
				res = new Long(output);
				else res = (long)-1;
				
				
				
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  return res;
	  }
	  
	
}

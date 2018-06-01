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

import model.Submission;

public class SubmissionService {

	private ObjectMapper mapper = new ObjectMapper();
	public SubmissionService(){}
	

	
	public List<Submission> getAllSubmissions(String request)
	{
		request = request + "getAllSubmissions";
		
		List<Submission> submissions =new ArrayList<Submission>();
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
					submissions= mapper.readValue(output,  new TypeReference<List<Submission>>(){});
					
				}
				
				

				httpClient.getConnectionManager().shutdown();

			  } catch (ClientProtocolException e) {
			
				e.printStackTrace();

			  } catch (IOException e) {
			
				e.printStackTrace();
			  }
         
	
			return submissions;
		
		}
	

	public Submission getSubmissionById( String request, Long submissionId)
	{  request = request+"getSubmissionById?submissionId="+submissionId.toString();
		Submission submission = new Submission();
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
				submission= mapper.readValue(output,  new TypeReference<Submission>(){});
				httpClient.getConnectionManager().shutdown();
			}	
        	
        	
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
		
		return submission;
    }
	
	 
	
	/*   private Float grade;
    private Long idassignment;
    private Long idstudent;
    private String date;
    private String link;
    */
	
	 public Submission saveSubmission(String request, Float grade, Long idassignment, Long idstudent,  String date,  String link) {
		  // http://localhost:8080/submission/saveSubmission?grade=2.5&idassignment=3&idstudent=6&date=12.11.17&link=blabla
		 
		  
		  String url = request +"saveSubmission?grade=%s&idassignment=%s&idstudent=%s&date=%s&link=%s";
		  
		  
		  
		  Submission submission = new Submission();
		 
		  String requestEnc = null;
				try {
					requestEnc = String.format(url,
					  URLEncoder.encode(grade.toString(), "UTF-8"),
					  URLEncoder.encode(idassignment.toString(), "UTF-8"),
					  URLEncoder.encode(idstudent.toString(), "UTF-8"),
					  URLEncoder.encode(date, "UTF-8"),
					  URLEncoder.encode(link, "UTF-8")
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
				submission= mapper.readValue(output,  new TypeReference<Submission>(){});
				httpClient.getConnectionManager().shutdown();
			}	
			
			
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  return submission;
	  }
	  
	  public Submission updateSubmission( String request, Long submissionId,   Float grade, Long idassignment, Long idstudent,  String date,  String link) {
// http://localhost:8080/submission/updateSubmission?submissionId=4&grade=10&idassignment=3&idstudent=6&date=string&link=string

		  
		  
	  String url = request +"updateSubmission?submissionId=%s&grade=%s&idassignment=%s&idstudent=%s&date=%s&link=%s";
		  
		  
		  
		  Submission submission = new Submission();
		 
		  String requestEnc = null;
				try {
					requestEnc = String.format(url,
					  URLEncoder.encode(submissionId.toString(),"UTF-8"),
					  URLEncoder.encode(grade.toString(), "UTF-8"),
					  URLEncoder.encode(idassignment.toString(), "UTF-8"),
					  URLEncoder.encode(idstudent.toString(), "UTF-8"),
					  URLEncoder.encode(date, "UTF-8"),
					  URLEncoder.encode(link, "UTF-8")
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
				submission= mapper.readValue(output,  new TypeReference<Submission>(){});
				httpClient.getConnectionManager().shutdown();
			}	
			
			
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  return submission;
			 
		  }
	  
	  public String deleteSubmissionById( String request, Long submissionId)
	  {
		  //http://localhost:8080/submission/deleteSubmissionById?submissionId=2
	  String url = request +"deleteSubmissionById?submissionId="+submissionId.toString();
		  
		  
		  
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
	  
	  public List<Submission> getAllSubmissionsByStudent(String request, Long idstudent) {
	    
		  // http://localhost:8080/submission/getAllSubmissionsByStudent?idstudent=5
		  List<Submission> list = new ArrayList<Submission>();
	     
	     String url = request +"getAllSubmissionsByStudent?idstudent="+idstudent.toString();
		  
	     
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
	  					list= mapper.readValue(output,  new TypeReference<List<Submission>>(){});
	  					
	  				}
	  				
	  				

	  				httpClient.getConnectionManager().shutdown();

	  			  } catch (ClientProtocolException e) {
	  			
	  				e.printStackTrace();

	  			  } catch (IOException e) {
	  			
	  				e.printStackTrace();
	  			  }
	           
	  	
	  			
	     
	     
	     
	     return list;
	  
	  }
	  
	  
	  public Submission gradeSubmission( String request,Long submissionId,  Float grade)
	  {
		  String url = request+"gradeSubmission?submissionId=%s&grade=%s";
		  
		  Submission submission = new Submission();
		  
 DefaultHttpClient httpClient = new DefaultHttpClient();
	      
	      
 
 String requestEnc = null;
	try {
		requestEnc = String.format(url,
		  URLEncoder.encode(submissionId.toString(),"UTF-8"),
		  URLEncoder.encode(grade.toString(), "UTF-8")
		);
		
		System.out.println(requestEnc);
	} catch (UnsupportedEncodingException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
 
 
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
				submission= mapper.readValue(output,  new TypeReference<Submission>(){});
				httpClient.getConnectionManager().shutdown();
			}	
			
			
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  return submission;
		  
	  }
	 
	  
	  
	  public List<Submission> getAllForAssignment(String name)
	  {
		  List<Submission> list = getAllSubmissions("http://localhost:8080/submission/");
		  
		  List<Submission>res = new ArrayList<Submission>();
		  for(Submission s:list)
		  {
			  if(s.getAssignment().getName().equals(name))
				  res.add(s);
		  }
		  return res;
	  }
	  
	  public List<String> getAllGradesForAssignment(String request, Long assignmentId)
	  {
		  
		  // http://localhost:8080/submission/getAllGradesForAssignment?assignmentId=2
		  List<String> grades = new ArrayList<String>();
		  
		  
		  String url = request+"getAllGradesForAssignment?assignmentId=%s";
		  String requestEnc = null;
			try {
				requestEnc = String.format(
				  URLEncoder.encode(assignmentId.toString(),"UTF-8")
				);
				
				System.out.println(requestEnc);
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		 
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
	  					grades= mapper.readValue(output,  new TypeReference<List<String>>(){});
	  					
	  				}
	  				
	  				

	  				httpClient.getConnectionManager().shutdown();

	  			  } catch (ClientProtocolException e) {
	  			
	  				e.printStackTrace();

	  			  } catch (IOException e) {
	  			
	  				e.printStackTrace();
	  			  }
	           
	  	
		  
		  
		  return grades;
		  
	  }
	  
	  public Long getId(String email, String name)
	  {
		 String request = "http://localhost:8080/submission/getIdByStudentAndAssignment?email=%s&name=%s";
		  
		 Long result =null;
		 String requestEnc = null;
		
				try {
					requestEnc = String.format(request,
					  URLEncoder.encode(email,"UTF-8"),
					  URLEncoder.encode(name, "UTF-8")
					);
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
				if ((output = br.readLine()) != null)
					result = new Long(output);
				else
				 result=(long)-1;
					
					httpClient.getConnectionManager().shutdown();
				
	        	
	        	
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
			
		  return result;
	  }
	  
	  
}


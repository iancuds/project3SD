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

import model.Attendance;

public class AttendanceService {

	private String def="http://localhost:8080/attendance/";
	private ObjectMapper mapper = new ObjectMapper();
	
	
	
	public AttendanceService() {}
	
	
	
	
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




	public List<Attendance> getAllAttendances(String request)
	{
		request = request + "getAllAttendances";
		
		String req = "http://localhost:8080/attendance/getAllAttendances";
		System.out.println(req);
		List<Attendance> attendances =new ArrayList<Attendance>();
try {

	           DefaultHttpClient httpClient = new DefaultHttpClient();
			
			HttpGet getRequest = new HttpGet(req
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
					attendances= mapper.readValue(output,  new TypeReference<List<Attendance>>(){});
					
				}
				
				

				httpClient.getConnectionManager().shutdown();

			  } catch (ClientProtocolException e) {
			
				e.printStackTrace();

			  } catch (IOException e) {
			
				e.printStackTrace();
			  }
         
	
			return attendances;
		
		}
	

	public Attendance getAttendanceById( String request, Long attendanceId)
	{  request = request+"getAttendanceById?attendanceId="+attendanceId.toString();
		Attendance attendance = new Attendance();
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
				attendance= mapper.readValue(output,  new TypeReference<Attendance>(){});
				httpClient.getConnectionManager().shutdown();
			}	
        	
        	
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
		
		return attendance;
    }
	
	 
/*    private Long idstudent;
    private Long idlaboratory;*/
	
	 public Attendance saveAttendance(String request, Long idstudent,  Long idlaboratory) {
		  // http://localhost:8080/attendance/saveAttendance?grade=2.5&idattendance=3&idstudent=6&date=12.11.17&link=blabla
		 
		  
		  String url = request +"saveAttendance?idstudent=%s&idlaboratory=%s";
		  
		  
		  
		  Attendance attendance = new Attendance();
		 
		  String requestEnc = null;
				try {
					requestEnc = String.format(url,
					  URLEncoder.encode(idstudent.toString(), "UTF-8"),
	
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
				attendance= mapper.readValue(output,  new TypeReference<Attendance>(){});
				httpClient.getConnectionManager().shutdown();
			}	
			
			
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  return attendance;
	  }
	  
	  public Attendance updateAttendance( String request, Long attendanceId,  Long idstudent,  Long idlaboratory) {
// http://localhost:8080/attendance/updateAttendance?attendanceId=4&grade=10&idattendance=3&idstudent=6&date=string&link=string

		  
		  
	  String url = request +"updateAttendance?attendanceId=%s&idstudent=%s&idlaboratory=%s";
		  
		  
		  
		  Attendance attendance = new Attendance();
		 
		  String requestEnc = null;
				try {
					requestEnc = String.format(url,
					  URLEncoder.encode(attendanceId.toString(),"UTF-8"),
					  URLEncoder.encode(idstudent.toString(), "UTF-8"),
						
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
				attendance= mapper.readValue(output,  new TypeReference<Attendance>(){});
				httpClient.getConnectionManager().shutdown();
			}	
			
			
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  return attendance;
			 
		  }
	  
	  public String deleteAttendanceById( String request, Long attendanceId)
	  {
		  //http://localhost:8080/attendance/deleteAttendanceById?attendanceId=2
	  String url = request +"deleteAttendanceById?attendanceId="+attendanceId.toString();
		  
		  
		  
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
	  
	  public List<Attendance> getAttendanceByLaboratoryId(String request, Long idlaboratory) {
	    
		  // http://localhost:8080/attendance/getAttendancesByLab?idlaboratory=5
		  List<Attendance> list = new ArrayList<Attendance>();
	     
	     String url = request +"getAttendanceByLaboratoryId?labId="+idlaboratory.toString();
		  
	     
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
	  					list= mapper.readValue(output,  new TypeReference<List<Attendance>>(){});
	  					
	  				}
	  				
	  				

	  				httpClient.getConnectionManager().shutdown();

	  			  } catch (ClientProtocolException e) {
	  			
	  				e.printStackTrace();

	  			  } catch (IOException e) {
	  			
	  				e.printStackTrace();
	  			  }
	           
	  	
	  			
	     
	     
	     
	     return list;
	  
	  }
	  
	  public List<Attendance> getAllByGroup(String group)
	  {
		  String request =  "http://localhost:8080/attendance/getAllAttendances";
		  StudentService ss = new StudentService();
		  List<Attendance>all = getAllAttendances(request);
		  
		  List<Attendance>res = new ArrayList<Attendance>();
		  for(Attendance a:all)
		  {
			 // System.out.println(a);
			 
			
			  if(a.getStudent().getGroup().equals(group)) res.add(a);
		  }
		  return res;
	  }
	  
	 
	  
}

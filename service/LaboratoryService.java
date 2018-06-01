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

import model.Laboratory;

public class LaboratoryService {
	
	private ObjectMapper mapper = new ObjectMapper();
	private String def = "http://localhost:8080/laboratory/";
	
	public LaboratoryService() {}
	
	

	
	public List<Laboratory> getAllLaboratories(String request)
	{
		request = request + "getAllLaboratories";
		
		List<Laboratory> laboratories =new ArrayList<Laboratory>();
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
					laboratories= mapper.readValue(output,  new TypeReference<List<Laboratory>>(){});
					
				}
				
				

				httpClient.getConnectionManager().shutdown();

			  } catch (ClientProtocolException e) {
			
				e.printStackTrace();

			  } catch (IOException e) {
			
				e.printStackTrace();
			  }
         
	
			return laboratories;
		
		}
	

	public Laboratory getLaboratoryById( String request, Long laboratoryId)
	{  request = request+"getLaboratoryById?laboratoryId="+laboratoryId.toString();
		Laboratory laboratory = new Laboratory();
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
				laboratory= mapper.readValue(output,  new TypeReference<Laboratory>(){});
				httpClient.getConnectionManager().shutdown();
			}	
        	
        	
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
		
		return laboratory;
    }
	
	 
	
	/* private Long number;
    private String date;
    private String title;
    private String curricula;
    private String description;*/
	
	 public Laboratory saveLaboratory(String request, Long number,String date, String title,   String curricula,  String description) {
// http://localhost:8080/laboratory/saveLaboratory?number=1&date=%25s&title=%25s&curricula=%25s&description=%25s
		 
		  
		  String url = request +"saveLaboratory?number=%s&date=%s&title=%s&curricula=%s&description=%s";
		  
		  
		  
		  Laboratory laboratory = new Laboratory();
		 
		  String requestEnc = null;
				try {
					requestEnc = String.format(url,
					  URLEncoder.encode(number.toString(), "UTF-8"),
					  URLEncoder.encode(date, "UTF-8"),
					  URLEncoder.encode(title, "UTF-8"),
					  URLEncoder.encode(curricula, "UTF-8"),
					  URLEncoder.encode(description, "UTF-8")
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
				laboratory= mapper.readValue(output,  new TypeReference<Laboratory>(){});
				httpClient.getConnectionManager().shutdown();
			}	
			
			
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  return laboratory;
	  }
	  
	  public Laboratory updateLaboratory( String request, String laboratoryId, Long number,String date, String title,   String curricula,  String description) {
//  http://localhost:8080/laboratory/updateLaboratory?labId=5&number=1&date=%25s&title=%25s&curricula=%25s&description=%25s
		  
		  
	  String url = request +"updateLaboratory?labId=%s&number=%s&date=%s&title=%s&curricula=%s&description=%s";
		  
		  
		  
		  Laboratory laboratory = new Laboratory();
		 
		  String requestEnc = null;
				try {
					requestEnc = String.format(url,
					  URLEncoder.encode(laboratoryId.toString(),"UTF-8"),
					  URLEncoder.encode(number.toString(), "UTF-8"),
					  URLEncoder.encode(date, "UTF-8"),
					  URLEncoder.encode(title, "UTF-8"),
					  URLEncoder.encode(curricula, "UTF-8"),
					  URLEncoder.encode(description, "UTF-8")
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
				laboratory= mapper.readValue(output,  new TypeReference<Laboratory>(){});
				httpClient.getConnectionManager().shutdown();
			}	
			
			
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  return laboratory;
			 
		  }
	  
	  public String deleteLaboratoryById( String request, Long laboratoryId)
	  {
		  //http://localhost:8080/laboratory/deleteLaboratoryById?laboratoryId=2
	  String url = request +"deleteLaboratoryById?laboratoryId="+laboratoryId.toString();
		  
		  
		  
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
	  
	
	  
	public Long getIdByNumerAndDate(Long number, String date)
	{
		String request = "http://localhost:8080/laboratory/getLabIdByNr?nr=%s&date=%s";
		String enc=null;
		try {
			enc = String.format(request,
			  URLEncoder.encode(number.toString(),"UTF-8"),
			  URLEncoder.encode(date, "UTF-8")
			  
			);
			
			System.out.println(enc);
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Long id;
		
		try {DefaultHttpClient httpClient = new DefaultHttpClient();
    	
    	HttpGet getRequest = new HttpGet(enc
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
	 
	public Laboratory findByTitleAndNumber(String title, Long number)
	{
		List<Laboratory> ll =getAllLaboratories(def);
		for(Laboratory l:ll)
		{
			System.out.println(l);
			if(l.getTitle().equals(title) && l.getNumber().equals(number)) return l;
		}
		return null;
		
	}
	

}

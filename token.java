package translator;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class token {
	static String accessToken;
	static public String clientId="swarnima_15";
	static public String clientSecret="b+9PvxB6sed1WsAMu2CXRS/D4WgiwE7B+7t5YQT93oI=";
	public String token;
	public static final String DatamarketAccessUri = "https://datamarket.accesscontrol.windows.net/v2/OAuth2-13";
	static String[] r=new String[2];
	
	public static String tokenRequest() throws IOException{                          //method to get the token
		
		clientId= URLEncoder.encode(clientId,"UTF-8");
	    clientSecret= URLEncoder.encode(clientSecret,"UTF-8");
	    String request;
	    request="grant_type=client_credentials&client_id="+ clientId +"&client_secret="+ clientSecret +"&scope=http://api.microsofttranslator.com";
	    URL url= new URL(DatamarketAccessUri);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
	    conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
	    DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
		wr.writeBytes(request);
		wr.flush();
		wr.close();

		int responseCode = conn.getResponseCode();
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		return response.toString();
	}
	
public static String[] getText() throws IOException, ParseException                       //translation done through API
{
	infoToken();
	int i=0,o=0;
	BufferedReader br = new BufferedReader(new FileReader("/home/swarnimagupta/Desktop/demo.csv"));
	String line=null;
	String [] country;
	BufferedReader in=null;
	while ((line = br.readLine()) != null) {
		if(i>=1){
			country = line.split(",");
	URL url2= new URL("http://api.microsofttranslator.com/V2/Http.svc/Translate?text="+URLEncoder.encode(country[2],"UTF-8")+"&from="+country[3]+"&to="+country[4]);
	HttpURLConnection conn2 = (HttpURLConnection) url2.openConnection();
	conn2.setRequestProperty("Authorization", "Bearer "+accessToken);
	conn2.setRequestMethod("GET");
    conn2.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

	int responseCode = conn2.getResponseCode();
    in = new BufferedReader(new InputStreamReader(conn2.getInputStream()));
	String inputLine;
	StringBuffer response = new StringBuffer();
	
	while ((inputLine = in.readLine()) != null) {
		response.append(inputLine);
	     String sss=response.toString();
		 sss = sss.substring(sss.indexOf(">") + 1);
		 sss = sss.substring(0, sss.indexOf("<"));		
		 r[o]=sss;
		 o++;
	}in.close();
	}i++;
	}
	return r;
}

public static void infoToken()throws IOException, ParseException   //method containing information about token
{
	String tok=tokenRequest();
	JSONParser parsor= new JSONParser();
	JSONObject object=(JSONObject) parsor.parse(tok);
    accessToken= (String)object.get("access_token");
	String tokenType=(String)object.get("token_type");
	String expireTime=(String)object.get("expires_in");
	String scope=(String)object.get("scope");
	
}
}
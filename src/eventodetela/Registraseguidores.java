package eventodetela;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import br.com.sankhya.extensions.eventoprogramavel.EventoProgramavelJava;
import br.com.sankhya.jape.core.JapeSession;
import br.com.sankhya.jape.core.JapeSession.SessionHandle;
import br.com.sankhya.jape.event.PersistenceEvent;
import br.com.sankhya.jape.event.TransactionContext;
import br.com.sankhya.jape.vo.DynamicVO;
import br.com.sankhya.jape.wrapper.JapeFactory;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Registraseguidores implements EventoProgramavelJava{

	@Override
	public void afterDelete(PersistenceEvent arg0) throws Exception {
	
		
	}

	@Override
	public void afterInsert(PersistenceEvent event) throws Exception {
		  
		
	}

	@Override
	public void afterUpdate(PersistenceEvent event) throws Exception {
		 DynamicVO inf = (DynamicVO) event.getVo();
		 
		//CAPTURA ID
		   BigDecimal id = inf.asBigDecimal("ID");
		   BigDecimal sequencia = inf.asBigDecimal("SEQUENCIA");
		   
		   String username = inf.asString("INSTAGRAM");
		   
		 if(username!=null) {  
		 
	        String instagramProfileUrl = "https://www.instagram.com/" + username + "/";

	        OkHttpClient client = new OkHttpClient();
	        Request request = new Request.Builder()
	                .url(instagramProfileUrl)
	                .build();

	        try {
	            Response response = client.newCall(request).execute();
	            String html = response.body().string();

	          
	            Document document = Jsoup.parse(html);

	            
	            Element followersElement = document.selectFirst("meta[property=og:description]");
	            if (followersElement != null) {
	                String followersText = followersElement.attr("content");
	                String[] parts = followersText.split(" ");
	                String followersCount = parts[0];
	                
	                if(followersCount != null) {
	                	addfollowers(id,followersCount,sequencia);
	                }
	                
	               
	                
	                response.close(); }
	                
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        
	    }
	}
	

	private void addfollowers(BigDecimal id, String followersCount, BigDecimal sequencia) {
		SessionHandle hnd = null;
		
		if(followersCount.equals("0")) {
			followersCount = "Conta privada ou usuario incorreto";
		}

		try {
			hnd = JapeSession.open();
			JapeFactory.dao("AD_FORMS").prepareToUpdateByPK(id,sequencia)
			.set("SEGUIDORES", followersCount)

			.update();
			
		
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
		} finally {
			JapeSession.close(hnd);
		}
		
	}
		
	

	@Override
	public void beforeCommit(TransactionContext arg0) throws Exception {
	
		
	}

	@Override
	public void beforeDelete(PersistenceEvent arg0) throws Exception {
	
		
	}

	public void beforeInsert(PersistenceEvent event) throws Exception {
	
		
	}

	@Override
	public void beforeUpdate(PersistenceEvent arg0) throws Exception {
	
		
	}

}

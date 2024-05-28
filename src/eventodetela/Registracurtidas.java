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

public class Registracurtidas implements EventoProgramavelJava{

	@Override
	public void afterDelete(PersistenceEvent arg0) throws Exception {
		// TODO Auto-generated method stub
		
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
			   String dados = inf.asString("DADOSPOST");
			   
			   String link = inf.asString("LINK");
			   
			 if(link!=null && dados == null) {  
			 
				 String instagramPostUrl = link;
			        OkHttpClient client = new OkHttpClient();
			        Request request = new Request.Builder()
			                .url(instagramPostUrl)
			                .build();

			        try {
			            Response response = client.newCall(request).execute();
			            String html = response.body().string();

			            // Parse HTML using JSoup
			            Document document = Jsoup.parse(html);

			            // Find and extract likes information (example)
			            Element likesElement = document.select("meta[property=og:description]").first();
			            if (likesElement != null) {
			                String likesText = likesElement.attr("content");
			                addlikess(id, likesText, sequencia);
			                
			                
			            } 

			            response.close();
			        } catch (IOException e) {
			            e.printStackTrace();
			        }
			    }
			}

		private void addlikess(BigDecimal id, String likessCount, BigDecimal sequencia) {
			SessionHandle hnd = null;
			

			try {
				hnd = JapeSession.open();
				JapeFactory.dao("AD_FORMS").prepareToUpdateByPK(id,sequencia)
				.set("DADOSPOST", likessCount)

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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeDelete(PersistenceEvent arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeInsert(PersistenceEvent arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeUpdate(PersistenceEvent arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

}

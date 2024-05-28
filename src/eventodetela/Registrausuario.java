package eventodetela;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;

import br.com.sankhya.extensions.eventoprogramavel.EventoProgramavelJava;
import br.com.sankhya.jape.core.JapeSession;
import br.com.sankhya.jape.core.JapeSession.SessionHandle;
import br.com.sankhya.jape.event.PersistenceEvent;
import br.com.sankhya.jape.event.TransactionContext;
import br.com.sankhya.jape.sql.NativeSql;
import br.com.sankhya.jape.vo.DynamicVO;
import br.com.sankhya.jape.wrapper.JapeFactory;
import br.com.sankhya.modelcore.auth.AuthenticationInfo;
import br.com.sankhya.modelcore.dwfdata.vo.tsi.UsuarioVO;

public class Registrausuario implements EventoProgramavelJava{

	@Override
	public void afterDelete(PersistenceEvent arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterInsert(PersistenceEvent event) throws Exception {
		   DynamicVO inf = (DynamicVO) event.getVo();
		   
		   //CAPTURA ID
		   BigDecimal id = inf.asBigDecimal("ID");
		   
		   if(id!=null) {
			   
			   //CAPTURA USUARIO LOGADO
			   UsuarioVO codusu = AuthenticationInfo.getCurrent().getUsuVO();
			   
			   
			   gravaregistro(id, codusu.getCODUSU());
		   }
		
	}

	//INSERE USUARIO E CÓDIGO DO USUARIO
	private void gravaregistro(BigDecimal id, BigDecimal codusu) {
		SessionHandle hnd = null;

		try {
			hnd = JapeSession.open();
			JapeFactory.dao("AD_INFLUENCER").prepareToUpdateByPK(id)
			.set("USUINCLUSAO", codusu.toString()+"-"+usuario(codusu) )

			.update();
			
		
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
		} finally {
			JapeSession.close(hnd);
		}
		
	}

	//CONSULTA
	public String usuario(BigDecimal codusu) throws Exception {
		String usuario = NativeSql.getString("NOMEUSU", "TSIUSU", "CODUSU=?", codusu);
		return usuario;
	}
		
	
	@Override
	public void afterUpdate(PersistenceEvent arg0) throws Exception {
		// TODO Auto-generated method stub
		
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

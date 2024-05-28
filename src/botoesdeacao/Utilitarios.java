package botoesdeacao;

public class Utilitarios {

	
	
	public String iconyoutube(){
		
		String iconyoutube ="https://www.internetmatters.org/wp-content/uploads/2018/01/Youtube-app-2.png";
		
		return iconyoutube;
		
	}
	
	
    public String iconinstagram(){
		
		String iconinstagram= "https://img.freepik.com/vetores-gratis/instagram-icone-novo_1057-2227.jpg?size=338&ext=jpg&ga=GA1.1.2082370165.1716336000&semt=ais_user";
		
		return iconinstagram;
		
	}
    
    public String iconfacebook() {
    	String iconfacebook = "https://img.odcdn.com.br/wp-content/uploads/2023/09/facebook-logo.png";
    	
		return iconfacebook;
    }
	
	
    public String conteudoyoutube() {
        String html1 = "<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/8__OBmykGeM?si=aIsJ8UjsbZPy1NzI\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>";

        String conteudooyoutube = "<style>" +
                "body > div.modal-type-warning.modal.modal-message.sk-popup.ng-isolate-scope.in > div > div { width: 900px; height: 600px; float: left; margin-left: calc(50% - 450px); }'" +
                "body > div.modal-type-warning.modal.modal-message.sk-popup.ng-isolate-scope.in > div > div > div.modal-footer { display: none; }'" +
                "body > div.modal-type-warning.modal.modal-message.sk-popup.ng-isolate-scope.in > div > div > div.modal-header.ng-scope { background-color: #fff; }'"+
                "body > div.modal-type-warning.modal.modal-message.sk-popup.ng-isolate-scope.in > div > div > div.modal-body > div > iframe { position: relative; height: 500px; width: 100%; border: none; padding: 0; }'" +
                
    	        "</style>" +
    	        "<div id=\"modalYoutube\" class=\"modal\" style=\"display: none;\">" +
    	        "<div class=\"modal-content\">" +
    	        "<div class=\"modal-container\">" +
    	        html1 +
    	        "</div>" +
    	        "<span class=\"close-btn\" onclick=\"closeModal('modalYoutube')\">&times;</span>" +
    	        "<button onclick=\"closeModal('modalYoutube')\">Fechar</button>" +
    	        "</div>" +
    	        "</div>";
    	
		return conteudooyoutube;
    }
    
    
    
    

	
}

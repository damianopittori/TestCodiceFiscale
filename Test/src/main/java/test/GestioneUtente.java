package test;



public class GestioneUtente {

	public static void creaUtente(String nome, String cognome, String dataDiNascita, String luogoDiNascita){
		
		Utente utente = new Utente();
		
		ServicesCrud crud_services = new ServicesCrud("jpa-example");
		
		String codiceCatastale=(String) crud_services.jpaRead("SELECT codFisco FROM comuni "
				+ " WHERE Comune = " + luogoDiNascita).getSingleResult();
	
	
	
	
	
	}
	
	
}

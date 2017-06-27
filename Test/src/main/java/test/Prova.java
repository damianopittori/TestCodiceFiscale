package test;

public class Prova {

	public static void main(String[] args) {
		Utente u = new Utente();
		u.setCognome("pittori");
		u.setNome("damiano");
		u.setDataNascita("30/03/1997");
		u.setComune("Roma");
		u.setSesso("M");
		u.creaESettaCF(u);
		System.out.println(u);
		u.creaRigaDB(u);
		System.out.println("riga creata");
	}
	
	
	
}

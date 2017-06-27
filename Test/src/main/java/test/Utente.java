package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Query;
import javax.persistence.Table;

import com.mysql.jdbc.Statement;

@Entity
@Table(name = "Utente", schema = "test")
public class Utente {

	@Id
	@GeneratedValue
	private int id;
	private String codFis;
	private String nome;
	private String cognome;
	private String dataNascita;
	private String comune;
	private String sesso;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodFis() {
		return codFis;
	}

	public void setCodFis(String codFis) {
		this.codFis = codFis;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(String dataNascita) {
		this.dataNascita = dataNascita;
	}

	public String getComune() {
		return comune;
	}

	public void setComune(String comune) {
		this.comune = comune;
	}

	public String getSesso() {
		return sesso;
	}

	public void setSesso(String sesso) {
		this.sesso = sesso;
	}

	public void creaESettaCF(Utente u) {

		String nome = u.getNome();
		String cognome = u.getCognome();
		String dataNascita = u.getDataNascita();
		String comune = u.getComune();
		String sesso = u.getSesso();

		String codFis = "";

		/* calcolo prime 3 lettere */
		int cont = 0;
		cognome = cognome.toUpperCase();
		nome = nome.toUpperCase();

		/* caso cognome minore di 3 lettere */
		if (cognome.length() < 3) {
			codFis += cognome;
			while (codFis.length() < 3)
				codFis += "X";
			cont = 3;
		}
		/* caso normale */
		for (int i = 0; i < cognome.length(); i++) {
			if (cont == 3)
				break;
			if (cognome.charAt(i) != 'A' && cognome.charAt(i) != 'E' && cognome.charAt(i) != 'I'
					&& cognome.charAt(i) != 'O' && cognome.charAt(i) != 'U') {
				codFis += Character.toString(cognome.charAt(i));
				cont++;
			}
		}
		/* nel casoci siano meno di 3 consonanti */
		while (cont < 3) {
			for (int i = 0; i < cognome.length(); i++) {
				if (cont == 3)
					break;
				if (cognome.charAt(i) == 'A' || cognome.charAt(i) == 'E' || cognome.charAt(i) == 'I'
						|| cognome.charAt(i) == 'O' || cognome.charAt(i) == 'U') {
					codFis += Character.toString(cognome.charAt(i));
					cont++;
				}
			}
		}
		/* lettere nome */
		cont = 0;
		/* caso nome minore di 3 lettere */
		if (nome.length() < 3) {
			codFis += nome;
			while (codFis.length() < 6)
				codFis += "X";
			cont = 3;
		}
		/* caso normale */
		for (int i = 0; i < nome.length(); i++) {
			if (cont == 3)
				break;
			if (nome.charAt(i) != 'A' && nome.charAt(i) != 'E' && nome.charAt(i) != 'I' && nome.charAt(i) != 'O'
					&& nome.charAt(i) != 'U') {
				codFis += Character.toString(nome.charAt(i));
				cont++;
			}
		}
		/* nel casoci siano meno di 3 consonanti */
		while (cont < 3) {
			for (int i = 0; i < nome.length(); i++) {
				if (cont == 3)
					break;
				if (nome.charAt(i) == 'A' || nome.charAt(i) == 'E' || nome.charAt(i) == 'I' || nome.charAt(i) == 'O'
						|| nome.charAt(i) == 'U') {
					codFis += Character.toString(nome.charAt(i));
					cont++;
				}
			}
		}
		/* anno */
		codFis += dataNascita.substring(8, 10);
		/* Mese */
		int mese = 0;
		if (dataNascita.charAt(3) == '0')
			mese = Integer.parseInt(dataNascita.substring(4, 5));
		else
			mese = Integer.parseInt(dataNascita.substring(3, 5));
		switch (mese) {
		case 1: {
			codFis += "A";
			break;
		}
		case 2: {
			codFis += "B";
			break;
		}
		case 3: {
			codFis += "C";
			break;
		}
		case 4: {
			codFis += "D";
			break;
		}
		case 5: {
			codFis += "E";
			break;
		}
		case 6: {
			codFis += "H";
			break;
		}
		case 7: {
			codFis += "L";
			break;
		}
		case 8: {
			codFis += "M";
			break;
		}
		case 9: {
			codFis += "P";
			break;
		}
		case 10: {
			codFis += "R";
			break;
		}
		case 11: {
			codFis += "S";
			break;
		}
		case 12: {
			codFis += "T";
			break;
		}
		}
		/* giorno */
		int giorno = 0;
		if (dataNascita.charAt(0) == '0')
			giorno = Integer.parseInt(dataNascita.substring(0, 1));
		else
			giorno = Integer.parseInt(dataNascita.substring(0, 2));
		if (sesso == "M")
			codFis += giorno;
		else {
			giorno += 40;
			codFis += Integer.toString(giorno);
		}
		/* comune nascita */

		Connection c;
		try {
			c = DriverManager.getConnection("jdbc:mysql://localhost/test?useSSL=false", "root", "root");
			Statement s = (Statement) c.createStatement();
			ResultSet comuni = s.executeQuery("SELECT codFisco FROM comuni WHERE Comune = '" + comune + "'");

			if (comuni.next()) {
				String codReg = comuni.getString("codFisco");
				codFis += codReg;
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		/* Carattere di controllo */
		int sommaPari = 0;
		for (int i = 1; i <= 13; i += 2) {
			switch (codFis.charAt(i)) {
			case '0': {
				sommaPari += 0;
				break;
			}
			case '1': {
				sommaPari += 1;
				break;
			}
			case '2': {
				sommaPari += 2;
				break;
			}
			case '3': {
				sommaPari += 3;
				break;
			}
			case '4': {
				sommaPari += 4;
				break;
			}
			case '5': {
				sommaPari += 5;
				break;
			}
			case '6': {
				sommaPari += 6;
				break;
			}
			case '7': {
				sommaPari += 7;
				break;
			}
			case '8': {
				sommaPari += 8;
				break;
			}
			case '9': {
				sommaPari += 9;
				break;
			}
			case 'A': {
				sommaPari += 0;
				break;
			}
			case 'B': {
				sommaPari += 1;
				break;
			}
			case 'C': {
				sommaPari += 2;
				break;
			}
			case 'D': {
				sommaPari += 3;
				break;
			}
			case 'E': {
				sommaPari += 4;
				break;
			}
			case 'F': {
				sommaPari += 5;
				break;
			}
			case 'G': {
				sommaPari += 6;
				break;
			}
			case 'H': {
				sommaPari += 7;
				break;
			}
			case 'I': {
				sommaPari += 8;
				break;
			}
			case 'J': {
				sommaPari += 9;
				break;
			}
			case 'K': {
				sommaPari += 10;
				break;
			}
			case 'L': {
				sommaPari += 11;
				break;
			}
			case 'M': {
				sommaPari += 12;
				break;
			}
			case 'N': {
				sommaPari += 13;
				break;
			}
			case 'O': {
				sommaPari += 14;
				break;
			}
			case 'P': {
				sommaPari += 15;
				break;
			}
			case 'Q': {
				sommaPari += 16;
				break;
			}
			case 'R': {
				sommaPari += 17;
				break;
			}
			case 'S': {
				sommaPari += 18;
				break;
			}
			case 'T': {
				sommaPari += 19;
				break;
			}
			case 'U': {
				sommaPari += 20;
				break;
			}
			case 'V': {
				sommaPari += 21;
				break;
			}
			case 'W': {
				sommaPari += 22;
				break;
			}
			case 'X': {
				sommaPari += 23;
				break;
			}
			case 'Y': {
				sommaPari += 24;
				break;
			}
			case 'Z': {
				sommaPari += 25;
				break;
			}
			}
		}
		int sommaDispari = 0;
		for (int i = 0; i <= 14; i += 2) {
			switch (codFis.charAt(i)) {
			case '0': {
				sommaDispari += 1;
				break;
			}
			case '1': {
				sommaDispari += 0;
				break;
			}
			case '2': {
				sommaDispari += 5;
				break;
			}
			case '3': {
				sommaDispari += 7;
				break;
			}
			case '4': {
				sommaDispari += 9;
				break;
			}
			case '5': {
				sommaDispari += 13;
				break;
			}
			case '6': {
				sommaDispari += 15;
				break;
			}
			case '7': {
				sommaDispari += 17;
				break;
			}
			case '8': {
				sommaDispari += 19;
				break;
			}
			case '9': {
				sommaDispari += 21;
				break;
			}
			case 'A': {
				sommaDispari += 1;
				break;
			}
			case 'B': {
				sommaDispari += 0;
				break;
			}
			case 'C': {
				sommaDispari += 5;
				break;
			}
			case 'D': {
				sommaDispari += 7;
				break;
			}
			case 'E': {
				sommaDispari += 9;
				break;
			}
			case 'F': {
				sommaDispari += 13;
				break;
			}
			case 'G': {
				sommaDispari += 15;
				break;
			}
			case 'H': {
				sommaDispari += 17;
				break;
			}
			case 'I': {
				sommaDispari += 19;
				break;
			}
			case 'J': {
				sommaDispari += 21;
				break;
			}
			case 'K': {
				sommaDispari += 2;
				break;
			}
			case 'L': {
				sommaDispari += 4;
				break;
			}
			case 'M': {
				sommaDispari += 18;
				break;
			}
			case 'N': {
				sommaDispari += 20;
				break;
			}
			case 'O': {
				sommaDispari += 11;
				break;
			}
			case 'P': {
				sommaDispari += 3;
				break;
			}
			case 'Q': {
				sommaDispari += 6;
				break;
			}
			case 'R': {
				sommaDispari += 8;
				break;
			}
			case 'S': {
				sommaDispari += 12;
				break;
			}
			case 'T': {
				sommaDispari += 14;
				break;
			}
			case 'U': {
				sommaDispari += 16;
				break;
			}
			case 'V': {
				sommaDispari += 10;
				break;
			}
			case 'W': {
				sommaDispari += 22;
				break;
			}
			case 'X': {
				sommaDispari += 25;
				break;
			}
			case 'Y': {
				sommaDispari += 24;
				break;
			}
			case 'Z': {
				sommaDispari += 23;
				break;
			}
			}
		}
		int interoControllo = (sommaPari + sommaDispari) % 26;
		String carattereControllo = "";
		switch (interoControllo) {
		case 0: {
			carattereControllo = "A";
			break;
		}
		case 1: {
			carattereControllo = "B";
			break;
		}
		case 2: {
			carattereControllo = "C";
			break;
		}
		case 3: {
			carattereControllo = "D";
			break;
		}
		case 4: {
			carattereControllo = "E";
			break;
		}
		case 5: {
			carattereControllo = "F";
			break;
		}
		case 6: {
			carattereControllo = "G";
			break;
		}
		case 7: {
			carattereControllo = "H";
			break;
		}
		case 8: {
			carattereControllo = "I";
			break;
		}
		case 9: {
			carattereControllo = "J";
			break;
		}
		case 10: {
			carattereControllo = "K";
			break;
		}
		case 11: {
			carattereControllo = "L";
			break;
		}
		case 12: {
			carattereControllo = "M";
			break;
		}
		case 13: {
			carattereControllo = "N";
			break;
		}
		case 14: {
			carattereControllo = "O";
			break;
		}
		case 15: {
			carattereControllo = "P";
			break;
		}
		case 16: {
			carattereControllo = "Q";
			break;
		}
		case 17: {
			carattereControllo = "R";
			break;
		}
		case 18: {
			carattereControllo = "S";
			break;
		}
		case 19: {
			carattereControllo = "T";
			break;
		}
		case 20: {
			carattereControllo = "U";
			break;
		}
		case 21: {
			carattereControllo = "V";
			break;
		}
		case 22: {
			carattereControllo = "W";
			break;
		}
		case 23: {
			carattereControllo = "X";
			break;
		}
		case 24: {
			carattereControllo = "Y";
			break;
		}
		case 25: {
			carattereControllo = "Z";
			break;
		}
		}
		codFis += carattereControllo;
		u.setCodFis(codFis);
	}

	public void creaRigaDB(Utente u) {
		ServicesCrud crud_services = new ServicesCrud("jpa-example");
		crud_services.jpaCreate(u);
		crud_services.closeLogicaJPA();
	}
	
	public String controlloDatiGiaEsistentiConSetCfECreazioneRiga(Utente u){
		ServicesCrud crud_services = new ServicesCrud("jpa-example");
		String controllo=null;
		Query q = crud_services.jpaRead("select u from Utente u");
		List<Utente> lista = q.getResultList();
		System.out.println(lista);
		for (int i = 0; i<lista.size(); i++){
			if (lista.get(i).getNome().equals(u.getNome())&&lista.get(i).getCognome().equals(u.getCognome())
					&&lista.get(i).getDataNascita().equals(u.getDataNascita())&&lista.get(i).getComune().equals(u.getComune())){
				u.setCodFis(lista.get(i).getCodFis());
				//System.out.println("cf non calcolato ma recuperato da db perche gia esistente");
				controllo = "cf non calcolato ma recuperato da db perche gia esistente";
				
			}
			}
		if(u.getCodFis()==null){
			u.creaESettaCF(u);
			u.creaRigaDB(u);
			//System.out.println("codice creato");
			controllo="codice creato";
		
		}
		return controllo;
	}
	

	@Override
	public String toString() {
		return "Utente [id=" + id + ", codiceFiscale=" + codFis + ", nome=" + nome + ", cognome=" + cognome
				+ ", dataNascita=" + dataNascita + ", comune=" + comune + ", sesso=" + sesso + "]";
	}
}

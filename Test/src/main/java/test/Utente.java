package test;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


	
	@Entity
	@Table(name = "Utente", schema = "test")
	public class Utente {
		
		
		@Id
		@GeneratedValue
		private int id;
		private String codiceFiscale;
		private String nome;
		private String cognome;
		private String dataDiNascita;
		private String luogoDiNascita;
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getCodiceFiscale() {
			return codiceFiscale;
		}
		public void setCodiceFiscale(String codiceFiscale) {
			this.codiceFiscale = codiceFiscale;
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
		public String getDataDiNascita() {
			return dataDiNascita;
		}
		public void setDataDiNascita(String dataDiNascita) {
			this.dataDiNascita = dataDiNascita;
		}
		
		
		public String getLuogoDiNascita() {
			return luogoDiNascita;
		}
		public void setLuogoDiNascita(String luogoDiNascita) {
			this.luogoDiNascita = luogoDiNascita;
		}
		
		
	


}

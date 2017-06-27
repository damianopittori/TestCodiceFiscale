<%@page import="test.Utente"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>cf e riga creati</title>
</head>
<body>
<h1>

<%String comune = request.getParameter("comune");
String nome = request.getParameter("nome");
String cognome = request.getParameter("cognome");
String data = request.getParameter("data");

String sesso = request.getParameter("sesso");

if(nome==null||cognome==null||data==null||comune==null||sesso==null||data.charAt(2)!='/'
||data.charAt(5)!='/'||data.length()!=10){%>
	<p>Torna <a href="http://localhost:8080/Test/Servlet">indietro </a> e inserisci tutti i dati correttamente</p>
<%}else{

Utente u = new Utente();
u.setCognome(cognome);
u.setNome(nome);
u.setComune(comune);
u.setDataNascita(data);
u.setSesso(sesso);

String risultato = u.controlloDatiGiaEsistentiConSetCfECreazioneRiga(u);%>
<%=risultato %>
<%=u.getCodFis()%>
<p>Torna alla <a href="http://localhost:8080/Test/Servlet"> pagina iniziale</a></p>
<% 
}
 %>



</h1>
	
</body>
</html>
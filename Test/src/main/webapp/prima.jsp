<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Inserisci dati</title>
</head>
<body>


	<%
	List<String> listaComuni = (ArrayList<String>) request.getAttribute("listaComuni");

%>
	<form action="seconda.jsp" method="post">

		Nome <br /> <input type="text" name="nome"><br />
		cognome<br /> <input type="text" name="cognome"><br />
		Data di nascita (gg/mm/aaaa)<br /> <input type="text" name="data"><br /> 
		
		<p>Sesso: M<input type="radio" name="sesso" value="M"/>
          F<input type="radio" name="sesso" value="F"/></p>
		Lista comuni:
		<select	name="comune">
			<%
				for (String c : listaComuni) {
			%>
			<option value="<%=c %>">
				<%=c%>
			</option>

			<%
				}
			%>
		</select>  <br>
		<br> <input type="submit"value="genera cf">
	</form>

	
</body>
</html>
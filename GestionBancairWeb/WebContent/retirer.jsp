<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="header.html"%>


<div class="col-md-12">
	<!-- Horizontal Form -->
	<center><c:out value="${sessionScope.nomClient.nom}"></c:out></center>
	<div class="box box-info">
		<div class="box-header with-border">
			<h3 class="box-title">Ajouter Compte</h3>
		</div>
		<!-- /.box-header -->
		<!-- form start -->

		<form class="form-horizontal" action="CompteBancaireServlet?action=retirer" method="post">
		<input type="hidden" name="rib" value=${param["rib"]} >
		
		<div class="form-group">
		<%
		String solde=request.getParameter("solde");
		%>
		<label for="inputEmail3"  class="col-sm-2 control-label">Solde : 
		<% if(solde!=null){
			%>
			<%=solde %>
			<%
		}
		
		%>
		
		<c:if test="${requestScope.solde!=null}">
		<c:out value="${requestScope.solde}"></c:out>
		</c:if>
		</label>
			
		</div>
					
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label">Solde a retirer</label>

					<div class="col-sm-10">
						<input type="text" class="form-control" id="inputEmail3"
							name="soldeAretirer"  required>
					</div>
				</div>
				
				<c:if test="${requestScope.erreurRetirer==1}">
				
				<div class="alert alert-danger">
  				<strong> Votre Solde insuffisant.</strong>
				</div>
				<c:remove var="erreurRetirer" scope="request" />
			
				
				</c:if>
			<!-- /.box-body -->
			<div class="box-footer">
				<button type="reset" class="btn btn-default">Cancel</button>
				<button type="submit" class="btn btn-info pull-right">retirer
				</button>
			</div>
			<!-- /.box-footer -->
		</form>
	</div>
	<!-- /.box -->
	<!-- general form elements disabled -->
</div>



<%@include file="footer.html"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="header.html"%>

<div class="col-md-12">
	<!-- Horizontal Form -->
	<div class="box box-info">
		<div class="box-header with-border">
			<h3 class="box-title">Ajouter et Modifier Client</h3>
		</div>
		<!-- /.box-header -->
		<!-- form start -->

		<form class="form-horizontal" action="ClientBanqueServlet" method="post">
			<div class="box-body">
		<input type="hidden" name="id" value="${sessionScope.obj.cin }" >

				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label">Cin</label>

					<div class="col-sm-10">
						<input type="text" class="form-control" id="inputEmail3"
							name="cin" value="${sessionScope.obj.cin}" required>
							
							<c:if test="${requestScope.erreurCin==1}">
				
				<div class="alert alert-danger">
  				<strong>Cin existe</strong>
				</div>
				<c:remove var="erreurCin" scope="request" />
			
				
				</c:if>
					</div>
				</div>

				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label">Nom</label>

					<div class="col-sm-10">
						<input type="text" class="form-control" id="inputEmail3"
							name="nom" value="${sessionScope.obj.nom}" required>
					</div>
				</div>
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label">Prenom</label>

					<div class="col-sm-10">
						<input type="text" class="form-control" id="inputEmail3"
							name="prenom" value="${sessionScope.obj.prenom}" required>
					</div>
				</div>
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label">Adresse</label>

					<div class="col-sm-10">
						<input type="text" class="form-control" id="inputEmail3"
							name="adresse" value="${sessionScope.obj.adresse}" required>
					</div>
				</div>

			</div>
			<!-- /.box-body -->
			<div class="box-footer">
				<button type="reset" class="btn btn-default">Cancel</button>
				<button type="submit" class="btn btn-info pull-right">Submit
				</button>
			</div>
			<!-- /.box-footer -->
		</form>
	</div>
	<!-- /.box -->
	<!-- general form elements disabled -->
</div>


<div class="col-md-12">

	<div class="box">
		<div class="box-header">
			<h3 class="box-title">Gestion Client</h3>
		</div>
		<!-- /.box-header -->
		<div class="box-body no-padding">
			<table class="table table-striped">
				<tbody>
					<tr>
						<th>Numero</th>

						<th>Cin</th>
						<th>Nom</th>
						<th>Prenom</th>
						<th>Adresse</th>
						
						<th>Modifier</th>
						<th>Supprimer</th>
						<th>Gerer Compte</th>



					</tr>
					<%
						int i = 1;
					%>
					<c:forEach items="${sessionScope.listeClient}" var="listeClient">
						<tr>

							<td><%=i++%></td>

							<td><c:out value="${listeClient.cin}"></c:out></td>
							<td><c:out value="${listeClient.nom}"></c:out></td>
							<td><c:out value="${listeClient.prenom}"></c:out></td>
							<td><c:out value="${listeClient.adresse}"></c:out></td>

							<td><a href=ClientBanqueServlet?action=update&id=<c:out value="${listeClient.cin}"></c:out>>
									<button type="button" class="btn btn-default">

										<span class="glyphicon glyphicon-edit"></span>
									</button>
							</a></td>

							<td><a href=ClientBanqueServlet?action=delete&id=<c:out value="${listeClient.cin}"></c:out>>
									<button type="button" class="btn btn-default">
										<span class="glyphicon glyphicon-remove"></span>

									</button>
							</a></td>
							<td>
							<c:remove var="cin" scope="session" />
							<a href=CompteBancaireServlet?cin=<c:out value="${listeClient.cin}"></c:out>>
									<button type="button" class="btn btn-default">
										<span class="glyphicon glyphicon-link"></span>

									</button>
							</a></td>
						</tr>
					</c:forEach>

				</tbody>
			</table>
		</div>
		<!-- /.box-body -->

		
	</div>
	<!-- /.box -->
</div>

<!-- /.box -->
<!-- general form elements disabled -->

<%@include file="footer.html"%>
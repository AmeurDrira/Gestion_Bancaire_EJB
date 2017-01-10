<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="header.html"%>
<div class="col-md-12">
	<!-- Horizontal Form -->
	<center><c:out value="${sessionScope.nomClient.nom}"></c:out><c:out value="${sessionScope.nomClient.prenom}"></c:out></center>
	<div class="box box-info">
		<div class="box-header with-border">
			<h3 class="box-title">Ajouter Compte</h3>
		</div>
		<!-- /.box-header -->
		<!-- form start -->

		<form class="form-horizontal" action="CompteBancaireServlet" method="post">
		<input type="hidden" name="cin" value="${sessionScope.cin }" >
					
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label">Solde</label>

					<div class="col-sm-10">
						<input type="text" class="form-control" id="inputEmail3"
							name="solde"  required>
					</div>
				</div>
			<!-- /.box-body -->
			<div class="box-footer">
				<button type="reset" class="btn btn-default">Cancel</button>
				<button type="submit" class="btn btn-info pull-right">Add
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
			<h3 class="box-title">Gestion Compte</h3>
		</div>
		<!-- /.box-header -->
		<div class="box-body no-padding">
			<table class="table table-striped">
				<tbody>
					<tr>
						<th>Rib</th>
						<th>Solde</th>
						<th>Supprimer</th>
						<th>Deposer</th>
						<th>Retirer</th>
						<th>transferer</th>
						



					</tr>
					<c:forEach items="${sessionScope.listeCompte}" var="listeCompte">
						<tr>					

							<td><c:out value="${listeCompte.rib}"></c:out></td>
							<td>
							<c:set var="soldeValue" value="${listeCompte.solde}" />
							<fmt:formatNumber  var="i" type="number" maxFractionDigits="2" value="${soldeValue}"/>							
								<c:out value="${i}"></c:out>
							</td>
							<td><a href=CompteBancaireServlet?action=delete&rib=<c:out value="${listeCompte.rib}"></c:out>>
									<button type="button" class="btn btn-default">
										<span class="glyphicon glyphicon-remove"></span>

									</button>
							</a></td>
							
							<td>
							<c:url value="deposer.jsp" var="deposer">
					
 						 <c:param name="solde" value="${listeCompte.solde}" />
 						 <c:param name="rib"  value="${listeCompte.rib}" />
						</c:url>	
							
							<a href=<c:out value="${deposer}" />>
									<button type="button" class="btn btn-default">
										<span class="glyphicon glyphicon-arrow-up"></span>

									</button>
							</a></td>
							
							<td>							
							<c:url value="retirer.jsp" var="displayURL">
					
 						 <c:param name="solde" value="${listeCompte.solde}" />
 						 <c:param name="rib"  value="${listeCompte.rib}" />
						</c:url>					
							
							<a  href=<c:out value="${displayURL}" />>
									<button type="button" class="btn btn-default">
										<span class="glyphicon glyphicon-arrow-down"></span>
										</button>
							</a>
							</td>
							
							
							<td>
							<c:url value="transfere.jsp" var="transfere">
					
 						 <c:param name="solde" value="${listeCompte.solde}" />
 						 <c:param name="rib"  value="${listeCompte.rib}" />
						</c:url>
							<a href=<c:out value="${transfere}" />>
									<button type="button" class="btn btn-default">
										<span class="glyphicon glyphicon-transfer"></span>

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
<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/tld/html_basic.tld" prefix="h"%>
<%@ taglib uri="/WEB-INF/tld/jsf_core.tld" prefix="f"%>
<%@ taglib uri="/WEB-INF/tld/ajax4jsf.tld" prefix="a4j"%>
<%@ taglib uri="/WEB-INF/tld/richfaces.tld" prefix="rich"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>TGCC</title>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<style>
			.bodyLogin {
				background-image: url('${pageContext.request.contextPath}/common/img/login.png');
				background-repeat: no-repeat; 
				background-position: center;
				background-attachment:fixed; 
				margin-top: 17%;
			}
		</style>
		
	</head>

	<body class="bodyLogin">
		<f:view>

			<h:form id="formLogin" >
				
				<a4j:loadBundle basename="trazabilidad.common.labels.Labels" var="labels" />
				<br/><br/><br/><br/><br/><br/><br/><br/><br/>
				<center>
					<rich:panel style="width: 400px;">
						<f:facet name="header">
							<h:outputLabel
								value=""></h:outputLabel>
						</f:facet>
						
						<h:panelGrid columns="2" border="0" cellpadding="2" cellspacing="1">
							<h:outputText value="#{labels.login_usuario}" />
							<h:inputText id="username" value="#{loginBean.user}" required="true" label="" />
							<h:outputText value="#{labels.login_contrasenia}" />
							<h:inputSecret id="password" value="#{loginBean.password}" required="true" label="" />

						</h:panelGrid>
						
						<h:panelGrid columns="1">
							<h:commandButton value="Login" action="#{loginBean.login}" />
						</h:panelGrid>
						
						<h:messages styleClass="errors" layout="table"/>
						<h:message for="formLogin" errorClass="errors"></h:message>

					</rich:panel>

				</center>

			</h:form>
		</f:view>
	</body>

</html>

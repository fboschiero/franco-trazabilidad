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
			.bodyInicio {
				background-image: url('${pageContext.request.contextPath}/common/img/institucional.png');
				
				background-repeat: no-repeat; 
				background-position: center;
				background-attachment:fixed; 
				margin-top: 5%;
			}
		</style>
	</head>

	<body class="bodyInicio">
		<f:view>

			<h:form id="form">
				
				<a4j:loadBundle basename="trazabilidad.common.labels.Labels" var="labels" />
				
				<div align="center" >
					
					<h:outputText value="#{labels.jamonartesanal_imprimirNroLote}" style="font-size: 20px; font-weight: bold" />
					
					<br/><br/><br/><br/><br/><br/>
										
					<div style="background-color: white; width:350px; " >
						
						<br/><br/>
						
						<h:outputText value="#{labels.jamonartesanal_nroLote} :" />
						<h:outputText value="#{jamonartesanal.nroLote}" />
						
						<h:panelGrid columns="1">
							<h:commandButton value="#{labels.generic_imprimir}" />
						</h:panelGrid>
						
						<br/><br/>
						
					</div>	
						
				</div>

			</h:form>

		</f:view>
	</body>

</html>

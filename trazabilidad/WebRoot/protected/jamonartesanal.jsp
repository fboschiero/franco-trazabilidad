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
				
					<h:selectOneRadio  binding="#{jamonartesanal.radioEtapa}" style="background-color: white;" border="2">
						<f:selectItem itemValue="1" itemLabel="Inicio inyección"/>
						<f:selectItem itemValue="2" itemLabel="Fin inyección"/>
					 	<f:selectItem itemValue="3" itemLabel="Inicio cocción"/>
					 	<f:selectItem itemValue="4" itemLabel="Fin cocción"/>
					 	<a4j:support event="onchange" reRender="form" action="#{jamonartesanal.limpiar}"/>
					</h:selectOneRadio>
					<br/>
					
					<div style="background-color: white; width:350px; " >
					<h:panelGroup rendered="#{jamonartesanal.radioEtapa.value == 1}" >
						<h:outputText value="#{labels.jamonartesanal_tituloInicioInyeccion}" style="font-size: 20px; font-weight: bold" />
						
						<br/><br/>
						
						<h:panelGrid columns="2" border="0" cellpadding="2" cellspacing="1" >
						
							<h:outputText value="#{labels.jamonartesanal_kgDeCarne}" />
							<h:inputText value="#{jamonartesanal.kgCarne}" />
						
							<h:outputText value="#{labels.jamonartesanal_horaIngreso}" />
							<h:inputText value="#{jamonartesanal.horaIngresoInyeccion}" />
							
							<h:outputText value="#{labels.jamonartesanal_tempIngreso}" />
							<h:inputText value="#{jamonartesanal.tempIngresoInyeccion}" />
							
							<h:outputText value="#{labels.login_usuario}" />
							<h:inputText value="#{jamonartesanal.usuarioInyeccion}" />
							
							<h:outputText value="#{labels.login_contrasenia}" />
							<h:inputSecret value="#{jamonartesanal.contraseniaInyeccion}" />
						
						</h:panelGrid>
						
						<h:panelGrid columns="1">
							<h:commandButton value="#{labels.generic_confirmar}" action="#{jamonartesanal.crearNuevoLote}" />
						</h:panelGrid>
						
						<h:messages styleClass="errors" layout="table"/>
						<h:message for="form" errorClass="errors"></h:message>
						
					</h:panelGroup>
				</div>	
				
				<div style="background-color: white; width:350px; " >
					<h:panelGroup rendered="#{jamonartesanal.radioEtapa.value == 2}" >
						<h:outputText value="#{labels.jamonartesanal_tituloFinInyeccion}" style="font-size: 20px; font-weight: bold" />
						
						<br/><br/>
						
						<h:panelGrid columns="3" border="0" cellpadding="2" cellspacing="1" rendered="#{jamonartesanal.lote == null}">
						
							<h:outputText value="#{labels.jamonartesanal_nroLote}" />
							<h:inputText value="#{jamonartesanal.nroLote}" />
							<h:commandButton value="#{labels.generic_buscar}" action="#{jamonartesanal.buscarLote}" >
								<a4j:support event="oncomplete" reRender="form" />
							</h:commandButton>
							
						</h:panelGrid>
						
						<h:panelGrid columns="2" border="0" cellpadding="2" cellspacing="1" rendered="#{jamonartesanal.lote != null}">
												
							<h:outputText value="#{labels.jamonartesanal_nroLote}" />
							<h:outputText value="#{jamonartesanal.lote.nroLote}" />
							
							<h:outputText value="#{labels.jamonartesanal_kgDeCarne}" />
							<h:outputText value="#{jamonartesanal.lote.kgCarne}" />
						
							<h:outputText value="#{labels.jamonartesanal_horaIngreso}" />
							<h:outputText value="#{jamonartesanal.lote.horaIngresoInyeccion}" />
							
							<h:outputText value="#{labels.jamonartesanal_tempIngreso}" />
							<h:outputText value="#{jamonartesanal.lote.tempIngresoInyeccion}" />
							
							<h:outputText value="#{labels.login_usuario}" />
							<h:outputText value="#{jamonartesanal.lote.operarioResponsableInyeccion}" />
						
							<h:outputText value="#{labels.jamonartesanal_horaSalida}" />
							<h:inputText value="#{jamonartesanal.horaSalidaInyeccion}" />
							
							<h:outputText value="#{labels.jamonartesanal_tempSalida}" />
							<h:inputText value="#{jamonartesanal.tempSalidaInyeccion}" />
							
							<h:outputText value="#{labels.login_usuario}" />
							<h:inputText value="#{jamonartesanal.usuarioInyeccion}" />
							
							<h:outputText value="#{labels.login_contrasenia}" />
							<h:inputSecret value="#{jamonartesanal.contraseniaInyeccion}" />
						
						</h:panelGrid>
						
						<h:panelGrid columns="1" rendered="#{jamonartesanal.lote != null}" >
							<h:commandButton value="#{labels.generic_confirmar}" action="#{jamonartesanal.confirmarEtapa2}" />
						</h:panelGrid>
						
						<h:messages styleClass="errors" layout="table"/>
						<h:message for="form" errorClass="errors"></h:message>
						
					</h:panelGroup>
				</div>	
				
				<div style="background-color: white; width:350px; " >
				<h:panelGroup rendered="#{jamonartesanal.radioEtapa.value == 3}" >
					<h:outputText value="#{labels.jamonartesanal_tituloInicioCoccion}" style="font-size: 20px; font-weight: bold" />
						
						<br/><br/>
						
						<h:panelGrid columns="3" border="0" cellpadding="2" cellspacing="1" rendered="#{jamonartesanal.lote == null}">
						
							<h:outputText value="#{labels.jamonartesanal_nroLote}" />
							<h:inputText value="#{jamonartesanal.nroLote}" />
							<h:commandButton value="#{labels.generic_buscar}" action="#{jamonartesanal.buscarLote}" >
								<a4j:support event="oncomplete" reRender="form" />
							</h:commandButton>
							
						</h:panelGrid>
						
						<h:panelGrid columns="2" border="0" cellpadding="2" cellspacing="1" rendered="#{jamonartesanal.lote != null}">
						
							<h:outputText value="#{labels.jamonartesanal_nroLote}" />
							<h:outputText value="#{jamonartesanal.lote.nroLote}" />
							
							<h:outputText value="#{labels.jamonartesanal_kgDeCarne}" />
							<h:outputText value="#{jamonartesanal.lote.kgCarne}" />
						
							<h:outputText value="#{labels.jamonartesanal_horaIngreso}" />
							<h:outputText value="#{jamonartesanal.lote.horaIngresoInyeccion}" />
							
							<h:outputText value="#{labels.jamonartesanal_tempIngreso}" />
							<h:outputText value="#{jamonartesanal.lote.tempIngresoInyeccion}" />
							
							<h:outputText value="#{labels.login_usuario}" />
							<h:outputText value="#{jamonartesanal.lote.operarioResponsableInyeccion}" />
						
							<h:outputText value="#{labels.jamonartesanal_horaSalida}" />
							<h:outputText value="#{jamonartesanal.lote.horaSalidaInyeccion}" />
							
							<h:outputText value="#{labels.jamonartesanal_tempSalida}" />
							<h:outputText value="#{jamonartesanal.lote.tempSalidaInyeccion}" />
						
							<h:outputText value="#{labels.jamonartesanal_horaIngreso}" />
							<h:inputText value="#{jamonartesanal.horaIngresoCoccion}" />
							
							<h:outputText value="#{labels.jamonartesanal_tempIngreso}" />
							<h:inputText value="#{jamonartesanal.tempIngresoCoccion}" />
							
							<h:outputText value="#{labels.jamonartesanal_nroTacho}" />
							<h:inputText value="#{jamonartesanal.nroTacho}" />
															
							<h:outputText value="#{labels.login_usuario}" />
							<h:inputText value="#{jamonartesanal.usuarioCoccion}" />
							
							<h:outputText value="#{labels.login_contrasenia}" />
							<h:inputSecret value="#{jamonartesanal.contraseniaCoccion}" />
						
						</h:panelGrid>
						
						<h:panelGrid columns="1" rendered="#{jamonartesanal.lote != null}">
							<h:commandButton value="#{labels.generic_confirmar}" action="#{jamonartesanal.confirmarEtapa3}" />
						</h:panelGrid>
						
						<h:messages styleClass="errors" layout="table"/>
						<h:message for="form" errorClass="errors"></h:message>
						
					</h:panelGroup>
				</div>	

				<div style="background-color: white; width:350px;"  >
					<h:panelGroup rendered="#{jamonartesanal.radioEtapa.value == 4}">
					<h:outputText value="#{labels.jamonartesanal_tituloFinCoccion}" style="font-size: 20px; font-weight: bold" />
						
						<br/><br/>
						
						<h:panelGrid columns="3" border="0" cellpadding="2" cellspacing="1" rendered="#{jamonartesanal.lote == null}">
						
							<h:outputText value="#{labels.jamonartesanal_nroLote}" />
							<h:inputText value="#{jamonartesanal.nroLote}" />
							<h:commandButton value="#{labels.generic_buscar}" action="#{jamonartesanal.buscarLote}" >
								<a4j:support event="oncomplete" reRender="form" />
							</h:commandButton>
							
						</h:panelGrid>
						
						<h:panelGrid columns="2" border="0" cellpadding="2" cellspacing="1" rendered="#{jamonartesanal.lote != null}">
						
							<h:outputText value="#{labels.jamonartesanal_nroLote}" />
							<h:outputText value="#{jamonartesanal.lote.nroLote}" />
							
							<h:outputText value="#{labels.jamonartesanal_kgDeCarne}" />
							<h:outputText value="#{jamonartesanal.lote.kgCarne}" />
						
							<h:outputText value="#{labels.jamonartesanal_horaIngreso}" />
							<h:outputText value="#{jamonartesanal.lote.horaIngresoInyeccion}" />
							
							<h:outputText value="#{labels.jamonartesanal_tempIngreso}" />
							<h:outputText value="#{jamonartesanal.lote.tempIngresoInyeccion}" />
							
							<h:outputText value="#{labels.login_usuario}" />
							<h:outputText value="#{jamonartesanal.lote.operarioResponsableInyeccion}" />
						
							<h:outputText value="#{labels.jamonartesanal_horaSalida}" />
							<h:outputText value="#{jamonartesanal.lote.horaSalidaInyeccion}" />
							
							<h:outputText value="#{labels.jamonartesanal_tempSalida}" />
							<h:outputText value="#{jamonartesanal.lote.tempSalidaInyeccion}" />
						
							<h:outputText value="#{labels.jamonartesanal_horaIngreso}" />
							<h:outputText value="#{jamonartesanal.lote.horaIngresoCoccion}" />
							
							<h:outputText value="#{labels.jamonartesanal_tempIngreso}" />
							<h:outputText value="#{jamonartesanal.lote.tempIngresoCoccion}" />
							
							<h:outputText value="#{labels.jamonartesanal_nroTacho}" />
							<h:outputText value="#{jamonartesanal.lote.nroTacho}" />
													
														
							<h:outputText value="#{labels.jamonartesanal_horaSalida}" />
							<h:inputText value="#{jamonartesanal.horaSalidaCoccion}" />
							
							<h:outputText value="#{labels.jamonartesanal_tempSalida}" />
							<h:inputText value="#{jamonartesanal.tempSalidaCoccion}" />
														
							<h:outputText value="#{labels.login_usuario}" />
							<h:inputText value="#{jamonartesanal.usuarioCoccion}" />
							
							<h:outputText value="#{labels.login_contrasenia}" />
							<h:inputSecret value="#{jamonartesanal.contraseniaCoccion}" />
						
						</h:panelGrid>
						
						<h:panelGrid columns="1" rendered="#{jamonartesanal.lote != null}">
							<h:commandButton value="#{labels.generic_confirmar}" action="#{jamonartesanal.confirmarEtapa4}" />
						</h:panelGrid>
						
						<h:messages styleClass="errors" layout="table"/>
						<h:message for="form" errorClass="errors"></h:message>
					
					</h:panelGroup>
				</div>	
					
	   			<br/><br/>
	   			<a4j:commandButton value="#{labels.generic_volver}" action="volver"  />
	   			
			</div>
		</h:form>
	</f:view>
</body>
</html>
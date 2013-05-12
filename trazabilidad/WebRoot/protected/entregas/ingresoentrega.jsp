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
			<h:form id="formulario">
			
				<a4j:loadBundle basename="trazabilidad.common.labels.Labels" var="labels" />
				<div align="center" >
					<rich:panel style="width: 50%;">
					<h:panelGrid columns="2" bgcolor="white" >
					
						<h:outputLabel value="#{labels.entregas_fecha_ingreso}" />
						<rich:calendar value="#{entregasBean.fechaIngreso}" direction="auto" inputSize="10" datePattern="dd/MM/yyyy" />
										
						<h:outputLabel value="#{labels.entregas_materia_prima}" />
						<rich:comboBox suggestionValues="#{entregasBean.tiposMateriaPrima}" converter="MateriaPrimaConverter" value="#{entregasBean.tipoMateriaPrima}" />				
										
						<h:outputLabel value="#{labels.entregas_proveedor}" />
						<rich:comboBox suggestionValues="#{entregasBean.proveedores}" converter="ProveedorConverter" value="#{entregasBean.proveedor}" />
						
						<h:outputText value="#{labels.entregas_cantidad}" />
						<h:inputText value="#{entregasBean.cantidad}"/>
						
						<h:outputText value="#{labels.entregas_temp_ingreso}" />
						<h:inputText value="#{entregasBean.tempIngreso}"/>
						
						<h:outputLabel value="#{labels.entregas_fecha_elaboracion}" />
						<rich:calendar value="#{entregasBean.fechaElaboracion}" direction="auto" inputSize="10" datePattern="dd/MM/yyyy"  />
						
						<h:outputLabel value="#{labels.entregas_fecha_vencimiento}" />
						<rich:calendar value="#{entregasBean.fechaVencimiento}" direction="auto" inputSize="10" datePattern="dd/MM/yyyy"  />
						
						<h:outputText value="#{labels.entregas_unidad}" />
						<h:inputText value="#{entregasBean.unidad}"/>
						
						<h:outputText value="#{labels.entregas_cantidad_pallets}" />
						<h:inputText value="#{entregasBean.cantidadPallets}"/>
						
						<h:outputText value="#{labels.entregas_cantidad_cajas}" />
						<h:inputText value="#{entregasBean.cantidadCajas}"/>
					
					</h:panelGrid>
					</rich:panel>
				</div>
				<br/>
			
				<div align="center">
					<a4j:commandButton value="#{labels.generic_aceptar}" action="#{entregasBean.aceptar}" reRender="formulario">
	   					<rich:componentControl for="popupMensaje" operation="#{(entregasBean.hayErrores) ? 'hide' : 'show'}" event="oncomplete"/>
	   				</a4j:commandButton>
	   				<br/><br/><br/>
	   				<a4j:commandButton value="#{labels.generic_volver}" action="volver"  />
   				</div>
			</h:form>	
			
			<rich:modalPanel id="popupMensaje" height="200" width="300">
				<f:facet name="header">
	                <h:outputLabel value="#{labels.generic_mensaje}"></h:outputLabel>
		        </f:facet>
				<f:facet name="controls">
		            <a4j:commandLink immediate="true" >
		                <h:graphicImage value="/common/img/close.png" id="hidelink"/>
		                <rich:componentControl for="popupMensaje" attachTo="hidelink" operation="hide" event="onclick" />
		            </a4j:commandLink>
		        </f:facet>
				
				<a4j:form id="formMensaje">
					
					<div align="center">
						<h:outputText value="#{labels.entregas_texto}" />
						<br/><br/><br/>
						<a4j:commandButton value="#{labels.generic_cerrar}" immediate="true"  >
							<rich:componentControl for="popupMensaje" operation="hide" event="onclick"/>
						</a4j:commandButton>
					</div>
				</a4j:form>
			</rich:modalPanel> 
			
		</f:view>
	</body>
</html>
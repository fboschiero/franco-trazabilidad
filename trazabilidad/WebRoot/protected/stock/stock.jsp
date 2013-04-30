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
			
			.tablaPpal {
		        width: 80%;
			}
			
			.col {
		        width: 20%;
			}
			
			.colImage {
		        width: 20px;
		        text-align: center;
			}
			
		</style>
	</head>

	<body class="bodyInicio">
		<f:view>
			<h:form id="formulario">
			
				<a4j:loadBundle basename="trazabilidad.common.labels.Labels" var="labels" />
				
				<div align="center">
					<rich:simpleTogglePanel switchType="client" label="Filtros" opened="false" width="450px">
						<div align="center">
							<rich:panel id="panelFiltros">
							
								<h:outputLabel value="#{labels.entregas_tipo_materia_prima}" />
								<rich:comboBox suggestionValues="#{stockBean.tiposMateriaPrima}" converter="MateriaPrimaConverter" value="#{stockBean.tipoMateriaPrima}" />				
												
								<h:outputLabel value="#{labels.entregas_proveedor}" />
								<rich:comboBox suggestionValues="#{stockBean.proveedores}" converter="ProveedorConverter" value="#{stockBean.proveedor}" />
						
								
								<br/><br/>
					   			<a4j:commandButton value="#{labels.generic_buscar}" action="#{stockBean.buscar}" reRender="tablaStock" />
				   			
							</rich:panel>
						</div>
					</rich:simpleTogglePanel>
				</div>
				<br/>
				<rich:dataTable id="tablaStock" value="#{stockBean.listadoStock}" var="item" align="center" columnClasses="col,col,col,colImage,colImage">
					<f:facet name="header">
		                <h:outputLabel value="#{labels.stock_titulo_tabla}"></h:outputLabel>
			        </f:facet>
					<rich:column>
						<f:facet name="header">
							<h:outputText value="#{labels.stock_materia_prima}" />
						</f:facet>
						<h:outputText value="#{item.materiaPrima.codigo} - #{item.materiaPrima.nombre}" />
					</rich:column>
					
					<rich:column>
						<f:facet name="header">
							<h:outputText value="#{labels.stock_tipo_materia_prima}" />
						</f:facet>
						<h:outputText value="#{item.tipoMateriaPrima.codigo} - #{item.tipoMateriaPrima.nombre}" />
					</rich:column>
					
					<rich:column>
						<f:facet name="header">
							<h:outputText value="#{labels.stock_proveedor}" />
						</f:facet>
						<h:outputText value="#{item.proveedor.codigo} - #{item.proveedor.nombre}" />
					</rich:column>
					
					<rich:column>
						<f:facet name="header">
							<h:outputText value="#{labels.stock_cantidad}" />
						</f:facet>
						<h:outputText value="#{item.cantidad}" />
					</rich:column>
				
				</rich:dataTable>
				
				<div align="center">
					<br/><br/>
	   				<a4j:commandButton value="#{labels.stock_baja_stock}" >
	   					<rich:componentControl for="popupBajaStock" operation="show" event="oncomplete" />
	   				</a4j:commandButton>	
					<br/><br/>
	   				<a4j:commandButton value="#{labels.generic_volver}" action="volver"  />
   				</div>
				
			</h:form>	
			
			<rich:modalPanel id="popupBajaStock" height="280" width="300">
				<f:facet name="header">
	                <h:outputLabel value="#{labels.stock_retiro_stock}"></h:outputLabel>
		        </f:facet>
				<f:facet name="controls">
		            <a4j:commandLink immediate="true">
		                <h:graphicImage value="/common/img/close.png" id="hidelink"/>
		                <rich:componentControl for="popupBajaStock" attachTo="hidelink" operation="hide" event="onclick"/>
		            </a4j:commandLink>
		        </f:facet>
				
				<a4j:form id="formBajaStock">
					<rich:panel style="height: 300; overflow: auto; border: 0;">
						<h:panelGrid columns="2" width="100%" >
						
							<h:outputLabel value="#{labels.entregas_tipo_materia_prima}" />
							<rich:comboBox suggestionValues="#{stockBean.tiposMateriaPrima}" converter="MateriaPrimaConverter" value="#{stockBean.tipoMateriaPrima}" />				
											
							<h:outputLabel value="#{labels.entregas_proveedor}" />
							<rich:comboBox suggestionValues="#{stockBean.proveedores}" converter="ProveedorConverter" value="#{stockBean.proveedor}" />
							
							<h:outputText value="#{labels.entregas_cantidad}" />
							<h:inputText value="#{stockBean.cantidad}"/>
							
						</h:panelGrid>
						<div align="center">
							<h:messages styleClass="errors" layout="table"/>
							<h:message for="formBajaStock" errorClass="errors"></h:message>
						</div>
						<br/>
						<div align="center">
							<a4j:commandButton value="#{labels.generic_aceptar}"
								action="#{stockBean.bajaStock}" reRender="tablaStock">
								<rich:componentControl for="popupBajaStock" operation="#{(stockBean.cerrarPopUp) ? 'hide' : 'show'}" event="oncomplete"/>
							</a4j:commandButton>
							
							<a4j:commandButton value="#{labels.generic_cancelar}" immediate="true" >
								<rich:componentControl for="popupBajaStock" operation="hide" event="onclick"/>
							</a4j:commandButton>
						</div>
					</rich:panel>
				</a4j:form>
			</rich:modalPanel>
			
		</f:view>
	</body>
</html>
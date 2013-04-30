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
							
								<h:outputLabel value="#{labels.generic_fecha_desde}" />
								<rich:calendar value="#{entregasBean.fechaDesde}" direction="auto" inputSize="10" datePattern="dd/MM/yyyy"  />
								<h:outputLabel value="#{labels.generic_fecha_hasta}" />
								<rich:calendar value="#{entregasBean.fechaHasta}" direction="auto" inputSize="10" datePattern="dd/MM/yyyy"  />
								<br/>
								<h:outputLabel value="#{labels.entregas_materia_prima}" />
								<rich:comboBox suggestionValues="#{entregasBean.tiposMateriaPrima}" converter="MateriaPrimaConverter" value="#{entregasBean.tipoMateriaPrima}" />				
												
								<h:outputLabel value="#{labels.entregas_proveedor}" />
								<rich:comboBox suggestionValues="#{entregasBean.proveedores}" converter="ProveedorConverter" value="#{entregasBean.proveedor}" />
						
								
								<br/><br/>
					   			<a4j:commandButton value="#{labels.generic_buscar}" action="#{entregasBean.buscar}" reRender="tablaEntregas" />
				   			
							</rich:panel>
						</div>
					</rich:simpleTogglePanel>
				</div>
				<br/>
				<rich:dataTable id="tablaEntregas" value="#{entregasBean.entregas}" var="entrega" align="center" columnClasses="col,col,col,colImage,colImage">
					<f:facet name="header">
		                <h:outputLabel value="#{labels.entregas_listado}"></h:outputLabel>
			        </f:facet>
					<rich:column>
						<f:facet name="header">
							<h:outputText value="#{labels.entregas_fecha_ingreso}" />
						</f:facet>
						<h:outputText value="#{entrega.fechaIngreso}" >
							<f:convertDateTime type="date" pattern="dd/MM/yyyy"/>
						</h:outputText>
					</rich:column>
					
					<rich:column>
						<f:facet name="header">
							<h:outputText value="#{labels.entregas_materia_prima}" />
						</f:facet>
						<h:outputText value="#{entrega.materiaPrima.codigo} - #{entrega.materiaPrima.nombre}" />
					</rich:column>
					
					<rich:column>
						<f:facet name="header">
							<h:outputText value="#{labels.entregas_proveedor}" />
						</f:facet>
						<h:outputText value="#{entrega.proveedor.codigo} - #{entrega.proveedor.nombre}" />
					</rich:column>
					
					<rich:column>
						<f:facet name="header">
							<h:outputText value="#{labels.entregas_cantidad}" />
						</f:facet>
						<h:outputText value="#{entrega.cantidad}" />
					</rich:column>
					
					<rich:column>
						<f:facet name="header">
							<h:outputText value="#{labels.entregas_temp_ingreso}" />
						</f:facet>
						<h:outputText value="#{entrega.tempIngreso}" />
					</rich:column>
					
					<rich:column>
						<f:facet name="header">
							<h:outputText value="#{labels.entregas_fecha_elaboracion}" />
						</f:facet>
						<h:outputText value="#{entrega.fechaElaboracion}" >
							<f:convertDateTime type="date" pattern="dd/MM/yyyy"/>
						</h:outputText>
					</rich:column>
					
					<rich:column>
						<f:facet name="header">
							<h:outputText value="#{labels.entregas_fecha_vencimiento}" />
						</f:facet>
						<h:outputText value="#{entrega.fechaVencimiento}" >
							<f:convertDateTime type="date" pattern="dd/MM/yyyy"/>
						</h:outputText>
					</rich:column>
					
					<rich:column>
						<f:facet name="header">
							<h:outputText value="#{labels.entregas_cantidad_pallets}" />
						</f:facet>
						<h:outputText value="#{entrega.cantidadPallets}" />
					</rich:column>
					
					<rich:column>
						<f:facet name="header">
							<h:outputText value="#{labels.entregas_cantidad_cajas}" />
						</f:facet>
						<h:outputText value="#{entrega.cantidadCajas}" />
					</rich:column>
					
					<rich:column>
						<a4j:commandButton image="../../common/img/remove.png" />
					</rich:column>
					
					<rich:column>
						<a4j:commandButton image="../../common/img/edit.png" />
					</rich:column> 
				
				</rich:dataTable>
				
				<div align="center">
					<br/><br/>
	   				<a4j:commandButton value="#{labels.generic_volver}" action="volver"  />
   				</div>
				
			</h:form>	
		</f:view>
	</body>
</html>
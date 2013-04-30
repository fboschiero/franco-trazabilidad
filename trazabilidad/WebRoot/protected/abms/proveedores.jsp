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
				
				<rich:dataTable id="tablaProveedores" value="#{proveedoresBean.proveedores}" var="proveedor" align="center" columnClasses="null, null, null, colImage, colImage">
					<f:facet name="header">
		                <h:outputLabel value="#{labels.proveedores_titulo_tabla}"></h:outputLabel>
			        </f:facet>
					<rich:column>
						<f:facet name="header">
							<h:outputText value="#{labels.proveedores_tabla_codigo}" />
						</f:facet>
						<h:outputText value="#{proveedor.codigo}" />
					</rich:column>
					<rich:column>
						<f:facet name="header">
							<h:outputText value="#{labels.proveedores_tabla_nombre}" />
						</f:facet>
						<h:outputText value="#{proveedor.nombre}" />
					</rich:column>
					<rich:column>
						<f:facet name="header">
							<h:outputText value="#{labels.proveedores_tabla_direccion}" />
						</f:facet>
						<h:outputText value="#{proveedor.direccion}" />
					</rich:column>
					
					<rich:column>
						<a4j:commandButton image="../../common/img/remove.png" actionListener="#{proveedoresBean.seleccionarProveedor}"  
							 data="#{proveedor}" immediate="true"  reRender="formBorrarProveedor" title="#{labels.generic_borrar}" >
							 <rich:componentControl for="popupBorrarProveedor" operation="show" event="oncomplete" />
						</a4j:commandButton>
					</rich:column>
					
					<rich:column>
						<a4j:commandButton image="../../common/img/edit.png" actionListener="#{proveedoresBean.seleccionarProveedor}" 
							data="#{proveedor}" immediate="true" reRender="formActualizarProveedor" title="#{labels.generic_editar}">
							<rich:componentControl for="popupActualizarProveedor" operation="show" event="oncomplete" />
						</a4j:commandButton>
					</rich:column> 
				
				</rich:dataTable>
				<br/>
				<div align="center">
					<a4j:commandButton value="#{labels.generic_nuevo}" >
	   					<rich:componentControl for="popupNuevoProveedor" operation="show" event="oncomplete" />
	   				</a4j:commandButton>
	   				
	   				<br/><br/><br/>
	   				<a4j:commandButton value="#{labels.generic_volver}" action="volver"  />
	   			</div>
			</h:form>	
			
			<rich:modalPanel id="popupNuevoProveedor" height="280" width="300">
				<f:facet name="header">
	                <h:outputLabel value="#{labels.proveedores_titulo_pupup_nuevo}"></h:outputLabel>
		        </f:facet>
				<f:facet name="controls">
		            <a4j:commandLink immediate="true">
		                <h:graphicImage value="/common/img/close.png" id="hidelink"/>
		                <rich:componentControl for="popupNuevoProveedor" attachTo="hidelink" operation="hide" event="onclick"/>
		            </a4j:commandLink>
		        </f:facet>
				
				<a4j:form id="formNuevoProveedor">
					<rich:panel style="height: 300; overflow: auto; border: 0;">
						<h:panelGrid columns="2" width="100%" >
						
							<h:outputText value="#{labels.proveedores_tabla_codigo}" />
							<h:inputText value="#{proveedoresBean.codigo}" required="true" ></h:inputText>
						
							<h:outputText value="#{labels.proveedores_tabla_nombre}" />
							<h:inputText value="#{proveedoresBean.nombre}" required="true" ></h:inputText>
							
							<h:outputText value="#{labels.proveedores_tabla_direccion}" />
							<h:inputText value="#{proveedoresBean.direccion}" ></h:inputText>
							
						</h:panelGrid>
						<div align="center">
							<h:messages styleClass="errors" layout="table"/>
							<h:message for="formNuevoProveedor" errorClass="errors"></h:message>
						</div>
						<br/>
						<div align="center">
							<a4j:commandButton value="#{labels.generic_aceptar}"
								action="#{proveedoresBean.nuevoProveedor}" reRender="tablaProveedores formNuevoProveedor">
								<rich:componentControl for="popupNuevoProveedor" operation="#{(proveedoresBean.cerrarPopUp) ? 'hide' : 'show'}" event="oncomplete"/>
							</a4j:commandButton>
							
							<a4j:commandButton value="#{labels.generic_cancelar}" immediate="true" >
								<rich:componentControl for="popupNuevoProveedor" operation="hide" event="onclick"/>
							</a4j:commandButton>
						</div>
					</rich:panel>
				</a4j:form>
			</rich:modalPanel>
			
			<rich:modalPanel id="popupActualizarProveedor" height="280" width="300">
				<f:facet name="header">
	                <h:outputLabel value="#{labels.proveedores_titulo_pupup_actualizar}"></h:outputLabel>
		        </f:facet>
				<f:facet name="controls">
		            <a4j:commandLink immediate="true" >
		                <h:graphicImage value="/common/img/close.png" id="hidelinkEditBranch"/>
		                <rich:componentControl for="popupActualizarProveedor" attachTo="hidelinkEditBranch" operation="hide" event="onclick" />
		            </a4j:commandLink>
		        </f:facet>
				
				<a4j:form id="formActualizarProveedor">
					<rich:panel style="height: 300; overflow: auto; border: 0;">
						<h:panelGrid columns="2" width="100%" >
							
							<h:outputText value="#{labels.proveedores_tabla_codigo}" />
							<h:inputText value="#{proveedoresBean.codigo}" required="true" ></h:inputText>
						
							<h:outputText value="#{labels.proveedores_tabla_nombre}" />
							<h:inputText value="#{proveedoresBean.nombre}" required="true" ></h:inputText>
							
							<h:outputText value="#{labels.proveedores_tabla_direccion}" />
							<h:inputText value="#{proveedoresBean.direccion}" ></h:inputText>
							
						</h:panelGrid>	
						<div align="center">
							<h:messages styleClass="errors" layout="table"/>
							<h:message for="formActualizarProveedor" errorClass="errors"></h:message>
						</div>
						<br/>
						<div align="center">
							<a4j:commandButton value="#{labels.generic_aceptar}"
										action="#{proveedoresBean.actualizarProveedor}" reRender="tablaProveedores">
								<rich:componentControl for="popupActualizarProveedor" operation="#{(proveedoresBean.cerrarPopUp) ? 'hide' : 'show'}" event="oncomplete"/>
							</a4j:commandButton>
							
							<a4j:commandButton value="#{labels.generic_cancelar}" immediate="true">
								<rich:componentControl for="popupActualizarProveedor" operation="hide" event="onclick"/>
							</a4j:commandButton>
						</div>
					</rich:panel>
				</a4j:form>
			</rich:modalPanel>
			
			<rich:modalPanel id="popupBorrarProveedor" height="300" width="400">
				<f:facet name="header">
	                <h:outputLabel value="#{labels.proveedores_titulo_pupup_borrar}"></h:outputLabel>
		        </f:facet>
				<f:facet name="controls">
		            <a4j:commandLink immediate="true" >
		                <h:graphicImage value="/common/img/close.png" id="hidelinkDeleteBranch"/>
		                <rich:componentControl for="popupBorrarProveedor" attachTo="hidelinkDeleteBranch" operation="hide" event="onclick" />
		            </a4j:commandLink>
		        </f:facet>
				
				<a4j:form id="formBorrarProveedor">
					
					<rich:panel style="height: 300;  width: 400; border: 0;" >
						<h:panelGrid columns="2" width="100%">
							
							<h:outputText value="#{labels.proveedores_tabla_codigo}" />
							<h:outputText value="#{proveedoresBean.codigo}"  />
						
							<h:outputText value="#{labels.proveedores_tabla_nombre}" />
							<h:outputText value="#{proveedoresBean.nombre}"  />
							
							<h:outputText value="#{labels.proveedores_tabla_direccion}" />
							<h:outputText value="#{proveedoresBean.direccion}" />
							
						</h:panelGrid>	
						<div align="center">
							<h:messages styleClass="errors" layout="table"/>
							<h:message for="formBorrarProveedor" errorClass="errors"></h:message>
						</div>
						<br/>
						<div align="center">
							<a4j:commandButton value="#{labels.generic_borrar}" action="#{proveedoresBean.borrarProveedor}" reRender="tablaProveedores">
								<rich:componentControl for="popupBorrarProveedor" operation="#{(proveedoresBean.cerrarPopUp) ? 'hide' : 'show'}" event="oncomplete"/>
							</a4j:commandButton>
							
							<a4j:commandButton value="#{labels.generic_cancelar}" immediate="true"  >
								<rich:componentControl for="popupBorrarProveedor" operation="hide" event="onclick"/>
							</a4j:commandButton>
						</div>
					</rich:panel>
				</a4j:form>
			</rich:modalPanel> 
		
		</f:view>
	</body>
</html>
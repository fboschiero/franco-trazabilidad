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
				
				<rich:dataTable id="tablaMateriasPrimas" value="#{tipoMateriasPrimasBean.tipos}" var="materiaPrima" align="center" columnClasses="col,col,col,colImage,colImage">
					<f:facet name="header">
		                <h:outputLabel value="#{labels.tipo_materias_primas_titulo_tabla}"></h:outputLabel>
			        </f:facet>
					<rich:column>
						<f:facet name="header">
							<h:outputText value="#{labels.tipo_materias_primas_tabla_codigo}" />
						</f:facet>
						<h:outputText value="#{materiaPrima.codigo}" />
					</rich:column>
					<rich:column>
						<f:facet name="header">
							<h:outputText value="#{labels.tipo_materias_primas_tabla_nombre}" />
						</f:facet>
						<h:outputText value="#{materiaPrima.nombre}" />
					</rich:column>
					<rich:column>
						<f:facet name="header">
							<h:outputText value="#{labels.tipo_materias_primas_tabla_descripcion}" />
						</f:facet>
						<h:outputText value="#{materiaPrima.descripcion}" />
					</rich:column>
					
					<rich:column>
						<a4j:commandButton image="../../common/img/remove.png" actionListener="#{tipoMateriasPrimasBean.seleccionar}"  
							 data="#{materiaPrima}" immediate="true"  reRender="formBorrarMateriaPrima" title="#{labels.generic_borrar}" >
							 <rich:componentControl for="popupBorrarMateriaPrima" operation="show" event="oncomplete" />
						</a4j:commandButton>
					</rich:column>
					
					<rich:column>
						<a4j:commandButton image="../../common/img/edit.png" actionListener="#{tipoMateriasPrimasBean.seleccionar}" 
							data="#{materiaPrima}" immediate="true" reRender="formActualizarMateriaPrima" title="#{labels.generic_editar}">
							<rich:componentControl for="popupActualizarMateriaPrima" operation="show" event="oncomplete" />
						</a4j:commandButton>
					</rich:column> 
				
				</rich:dataTable>
				<br/>
				<div align="center">
					<a4j:commandButton value="#{labels.generic_nuevo}" >
	   					<rich:componentControl for="popupNuevaMateriaPrima" operation="show" event="oncomplete" />
	   				</a4j:commandButton>
	   				<br/><br/><br/>
	   				<a4j:commandButton value="#{labels.generic_volver}" action="volver"  />
   				</div>
			</h:form>	
			
			<rich:modalPanel id="popupNuevaMateriaPrima" height="280" width="300">
				<f:facet name="header">
	                <h:outputLabel value="#{labels.tipo_materias_primas_titulo_pupup_nuevo}"></h:outputLabel>
		        </f:facet>
				<f:facet name="controls">
		            <a4j:commandLink immediate="true">
		                <h:graphicImage value="/common/img/close.png" id="hidelink"/>
		                <rich:componentControl for="popupNuevaMateriaPrima" attachTo="hidelink" operation="hide" event="onclick"/>
		            </a4j:commandLink>
		        </f:facet>
				
				<a4j:form id="formNuevaMateriaPrima">
					<rich:panel style="height: 300; overflow: auto; border: 0;">
						<h:panelGrid columns="2" width="100%" >
						
							<h:outputText value="#{labels.tipo_materias_primas_tabla_codigo}" />
							<h:inputText value="#{tipoMateriasPrimasBean.codigo}" required="true" ></h:inputText>
						
							<h:outputText value="#{labels.tipo_materias_primas_tabla_nombre}" />
							<h:inputText value="#{tipoMateriasPrimasBean.nombre}" required="true" ></h:inputText>
							
							<h:outputText value="#{labels.tipo_materias_primas_tabla_descripcion}" />
							<h:inputText value="#{tipoMateriasPrimasBean.descripcion}" ></h:inputText>
							
						</h:panelGrid>
						<div align="center">
							<h:messages styleClass="errors" layout="table"/>
							<h:message for="formNuevaMateriaPrima" errorClass="errors"></h:message>
						</div>
						<br/>
						<div align="center">
							<a4j:commandButton value="#{labels.generic_aceptar}"
								action="#{tipoMateriasPrimasBean.nuevo}" reRender="tablaMateriasPrimas">
								<rich:componentControl for="popupNuevaMateriaPrima" operation="#{(tipoMateriasPrimasBean.cerrarPopUp) ? 'hide' : 'show'}" event="oncomplete"/>
							</a4j:commandButton>
							
							<a4j:commandButton value="#{labels.generic_cancelar}" immediate="true" >
								<rich:componentControl for="popupNuevaMateriaPrima" operation="hide" event="onclick"/>
							</a4j:commandButton>
						</div>
					</rich:panel>
				</a4j:form>
			</rich:modalPanel>
			
			<rich:modalPanel id="popupActualizarMateriaPrima" height="280" width="300">
				<f:facet name="header">
	                <h:outputLabel value="#{labels.tipo_materias_primas_titulo_pupup_actualizar}"></h:outputLabel>
		        </f:facet>
				<f:facet name="controls">
		            <a4j:commandLink immediate="true" >
		                <h:graphicImage value="/common/img/close.png" id="hidelinkEditBranch"/>
		                <rich:componentControl for="popupActualizarMateriaPrima" attachTo="hidelinkEditBranch" operation="hide" event="onclick" />
		            </a4j:commandLink>
		        </f:facet>
				
				<a4j:form id="formActualizarMateriaPrima">
					<rich:panel style="height: 300; overflow: auto; border: 0;">
						<h:panelGrid columns="2" width="100%" >
							
							<h:outputText value="#{labels.tipo_materias_primas_tabla_codigo}" />
							<h:inputText value="#{tipoMateriasPrimasBean.codigo}" required="true" ></h:inputText>
						
							<h:outputText value="#{labels.tipo_materias_primas_tabla_nombre}" />
							<h:inputText value="#{tipoMateriasPrimasBean.nombre}" required="true" ></h:inputText>
							
							<h:outputText value="#{labels.tipo_materias_primas_tabla_descripcion}" />
							<h:inputText value="#{tipoMateriasPrimasBean.descripcion}" ></h:inputText>
							
						</h:panelGrid>	
						<div align="center">
							<h:messages styleClass="errors" layout="table"/>
							<h:message for="formActualizarMateriaPrima" errorClass="errors"></h:message>
						</div>
						<br/>
						<div align="center">
							<a4j:commandButton value="#{labels.generic_aceptar}"
										action="#{tipoMateriasPrimasBean.modificar}" reRender="tablaMateriasPrimas">
								<rich:componentControl for="popupActualizarMateriaPrima" operation="#{(tipoMateriasPrimasBean.cerrarPopUp) ? 'hide' : 'show'}" event="oncomplete"/>
							</a4j:commandButton>
							
							<a4j:commandButton value="#{labels.generic_cancelar}" immediate="true">
								<rich:componentControl for="popupActualizarMateriaPrima" operation="hide" event="onclick"/>
							</a4j:commandButton>
						</div>
					</rich:panel>
				</a4j:form>
			</rich:modalPanel>
			 
			<rich:modalPanel id="popupBorrarMateriaPrima" height="300" width="400">
				<f:facet name="header">
	                <h:outputLabel value="#{labels.tipo_materias_primas_titulo_pupup_borrar}"></h:outputLabel>
		        </f:facet>
				<f:facet name="controls">
		            <a4j:commandLink immediate="true" >
		                <h:graphicImage value="/common/img/close.png" id="hidelinkDeleteBranch"/>
		                <rich:componentControl for="popupBorrarProveedor" attachTo="hidelinkDeleteBranch" operation="hide" event="onclick" />
		            </a4j:commandLink>
		        </f:facet>
				
				<a4j:form id="formBorrarMateriaPrima">
					
					<rich:panel style="height: 300;  width: 400; border: 0;" >
						<h:panelGrid columns="2" width="100%">
							
							<h:outputText value="#{labels.tipo_materias_primas_tabla_codigo}" />
							<h:outputText value="#{tipoMateriasPrimasBean.codigo}"  />
						
							<h:outputText value="#{labels.tipo_materias_primas_tabla_nombre}" />
							<h:outputText value="#{tipoMateriasPrimasBean.nombre}"  />
							
							<h:outputText value="#{labels.tipo_materias_primas_tabla_descripcion}" />
							<h:outputText value="#{tipoMateriasPrimasBean.descripcion}" />
							
						</h:panelGrid>	
						<div align="center">
							<h:messages styleClass="errors" layout="table"/>
							<h:message for="formBorrarMateriaPrima" errorClass="errors"></h:message>
						</div>
						<br/>
						<div align="center">
							<a4j:commandButton value="#{labels.generic_borrar}" action="#{tipoMateriasPrimasBean.borrar}" reRender="tablaMateriasPrimas">
								<rich:componentControl for="popupBorrarMateriaPrima" operation="#{(tipoMateriasPrimasBean.cerrarPopUp) ? 'hide' : 'show'}" event="oncomplete"/>
							</a4j:commandButton>
							
							<a4j:commandButton value="#{labels.generic_cancelar}" immediate="true"  >
								<rich:componentControl for="popupBorrarMateriaPrima" operation="hide" event="onclick"/>
							</a4j:commandButton>
						</div>
					</rich:panel>
				</a4j:form>
			</rich:modalPanel> 
		 
		</f:view>
	</body>
</html>
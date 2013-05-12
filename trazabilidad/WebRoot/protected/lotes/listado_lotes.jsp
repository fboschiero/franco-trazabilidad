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
								<rich:calendar value="#{loteBean.fechaDesde}" direction="auto" inputSize="10" datePattern="dd/MM/yyyy"  />
								<h:outputLabel value="#{labels.generic_fecha_hasta}" />
								<rich:calendar value="#{loteBean.fechaHasta}" direction="auto" inputSize="10" datePattern="dd/MM/yyyy"  />
									
								<br/><br/>
					   			<a4j:commandButton value="#{labels.generic_buscar}" action="#{loteBean.buscar}" reRender="tablaLotes" />
				   			
							</rich:panel>
						</div>
					</rich:simpleTogglePanel>
				</div>
				
				<br/>
				<rich:dataTable id="tablaLotes" value="#{loteBean.lotes}" var="lote" align="center" >
					<f:facet name="header">
		                <h:outputLabel value="#{labels.lote_titulo_tabla}"></h:outputLabel>
			        </f:facet>
					
					<rich:column>
						<f:facet name="header">
							<h:outputText value="#{labels.lote_nro_lote}" />
						</f:facet>
						<h:outputText value="#{lote.nroLote}" />
					</rich:column>
					
					<rich:column>
						<f:facet name="header">
							<h:outputText value="Tipo" />
						</f:facet>
						<h:outputText value="Jamon Artesanal" />
					</rich:column>
					
					<rich:column>
						<f:facet name="header">
							<h:outputText value="#{labels.lote_fecha_creado}" />
						</f:facet>
						<h:outputText value="#{lote.fechaCreado}" >
							<f:convertDateTime type="date" pattern="dd/MM/yyyy"/>
						</h:outputText>
					</rich:column>
					
					<rich:column>
						<f:facet name="header">
							<h:outputText value="#{labels.jamonartesanal_kgDeCarne}" />
						</f:facet>
						<h:outputText value="#{lote.kgCarne}" />
					</rich:column>
					
					<rich:column>
						<f:facet name="header">
							<h:outputText value="#{labels.jamonartesanal_horaIngreso}" />
						</f:facet>
						<h:outputText value="#{lote.horaIngresoInyeccion}" />
					</rich:column>
					
					<rich:column>
						<f:facet name="header">
							<h:outputText value="#{labels.jamonartesanal_tempIngreso}" />
						</f:facet>
						<h:outputText value="#{lote.tempIngresoInyeccion} ºC" rendered="#{lote.tempIngresoInyeccion > 9}" title="Jamon se abre, separacion de las pulpas en producto terminado" style="background: red"/>
						<h:outputText value="#{lote.tempIngresoInyeccion} ºC" rendered="#{lote.tempIngresoInyeccion <= 9}"/>
					</rich:column>
					
					<rich:column>
						<f:facet name="header">
							<h:outputText value="#{labels.login_usuario}" />
						</f:facet>
						<h:outputText value="#{lote.operarioResponsableInyeccion}" />
					</rich:column>
							
					<rich:column>
						<f:facet name="header">
							<h:outputText value="#{labels.jamonartesanal_horaSalida}" />
						</f:facet>
						<h:outputText value="#{lote.horaSalidaInyeccion}" />
					</rich:column>		
							
					<rich:column>
						<f:facet name="header">
							<h:outputText value="#{labels.jamonartesanal_tempSalida}" />
						</f:facet>
						<h:outputText value="#{lote.tempSalidaInyeccion} ºC" />
					</rich:column>			
							
					<rich:column>
						<f:facet name="header">
							<h:outputText value="#{labels.jamonartesanal_horaIngreso}" />
						</f:facet>
						<h:outputText value="#{lote.horaIngresoCoccion}" />
					</rich:column>		
						
					<rich:column>
						<f:facet name="header">
							<h:outputText value="#{labels.jamonartesanal_tempIngreso}" />
						</f:facet>
						<h:outputText value="#{lote.tempIngresoCoccion} ºC" />
					</rich:column>	
					
					<rich:column>
						<f:facet name="header">
							<h:outputText value="#{labels.jamonartesanal_nroTacho}" />
						</f:facet>
						<h:outputText value="#{lote.nroTacho}" />
					</rich:column>			
							
					<rich:column>
						<f:facet name="header">
							<h:outputText value="#{labels.jamonartesanal_horaSalida}" />
						</f:facet>
						<h:outputText value="#{lote.horaSalidaCoccion}" />
					</rich:column>			
					
					<rich:column>
						<f:facet name="header">
							<h:outputText value="#{labels.jamonartesanal_tempSalida}" />
						</f:facet>
						<h:outputText value="#{lote.tempSalidaCoccion} ºC" rendered="#{lote.tempSalidaCoccion < 70}" title="Jamones crudos, problemas microbiologicos por no destruccion de microorganismos patógenos" style="background: red"/>
						<h:outputText value="#{lote.tempSalidaCoccion} ºC" rendered="#{lote.tempSalidaCoccion >= 70}"/>
					</rich:column>		
									
					<rich:column>
						<a4j:commandButton image="../../common/img/edit.png" actionListener="#{loteBean.seleccionarLote}"  
							 data="#{lote}" immediate="true"  reRender="formDetalleLote" title="#{labels.generic_ver_detalle}" >
							 <rich:componentControl for="popupDetalleLote" operation="show" event="oncomplete" />
						</a4j:commandButton>
					</rich:column> 
				
				</rich:dataTable>
				
				<div align="center">
					<br/><br/>
	   				<a4j:commandButton value="#{labels.generic_volver}" action="volver" />
   				</div>
				
			</h:form>	
			
			<rich:modalPanel id="popupDetalleLote" height="550" width="400">
				<f:facet name="header">
	                <h:outputLabel value="#{labels.lote_pop_up_titulo}"></h:outputLabel>
		        </f:facet>
				<f:facet name="controls">
		            <a4j:commandLink immediate="true" >
		                <h:graphicImage value="/common/img/close.png" id="hidelink"/>
		                <rich:componentControl for="popupDetalleLote" attachTo="hidelink" operation="hide" event="onclick" />
		            </a4j:commandLink>
		        </f:facet>
				
				<a4j:form id="formDetalleLote" style="height: 500; overflow: auto; border: 0;">
					
							<rich:panel >	
								<f:facet name="header">
					                <h:outputLabel value="INYECCION"></h:outputLabel>
						        </f:facet>				
								<h:panelGrid columns="2" border="0" cellpadding="2" cellspacing="1" >
									<h:outputText value="#{labels.jamonartesanal_nroLote}" style="font-weight: bold"/>
									<h:outputText value="#{loteBean.loteSeleccionado.nroLote}" />
									
									<h:outputText value="#{labels.generic_fecha}" style="font-weight: bold"/>
									<h:outputText value="#{loteBean.loteSeleccionado.fechaCreado}" >
										<f:convertDateTime type="date" pattern="dd/MM/yyyy"/>
									</h:outputText>
									
									<h:outputText value="#{labels.generic_proveedor}" style="font-weight: bold"/>
									<h:outputText value="#{loteBean.loteSeleccionado.proveedor.nombre}" />
									
									<h:outputText value="#{labels.jamonartesanal_kgDeCarne}" style="font-weight: bold"/>
									<h:outputText value="#{loteBean.loteSeleccionado.kgCarne}" />
								
									<h:outputText value="#{labels.jamonartesanal_horaIngreso}" style="font-weight: bold"/>
									<h:outputText value="#{loteBean.loteSeleccionado.horaIngresoInyeccion}" />
									
									<h:outputText value="#{labels.jamonartesanal_tempIngreso}" style="font-weight: bold"/>
									<h:outputText value="#{loteBean.loteSeleccionado.tempIngresoInyeccion} ºC" />
									
									<h:outputText value="#{labels.jamonartesanal_tempSalmuera}" style="font-weight: bold"/>
									<h:outputText value="#{loteBean.loteSeleccionado.tempSalmuera} ºC" />
									
									<h:outputText value="#{labels.jamonartesanal_horaSalida}" style="font-weight: bold"/>
									<h:outputText value="#{loteBean.loteSeleccionado.horaSalidaInyeccion}" />
									
									<h:outputText value="#{labels.jamonartesanal_tempSalida}" style="font-weight: bold"/>
									<h:outputText value="#{loteBean.loteSeleccionado.tempSalidaInyeccion} ºC" />
									
									<h:outputText value="#{labels.login_usuario}" style="font-weight: bold"/>
									<h:outputText value="#{loteBean.loteSeleccionado.operarioResponsableInyeccion}" />
								</h:panelGrid>	
							</rich:panel>
							
							<rich:panel>
								<f:facet name="header">
					                <h:outputLabel value="BOMBO"></h:outputLabel>
						        </f:facet>						
								<h:panelGrid columns="2" border="0" cellpadding="2" cellspacing="1" >
									<h:outputText value="#{labels.jamonartesanal_horaIngresoBombo}" style="font-weight: bold"/>
									<h:outputText value="#{loteBean.loteSeleccionado.horaIngresoBombo}" />
									
									<h:outputText value="#{labels.jamonartesanal_horaSalidaBombo}" style="font-weight: bold"/>
									<h:outputText value="#{loteBean.loteSeleccionado.horaSalidaBombo}" />
								
									<h:outputText value="#{labels.login_usuario}" style="font-weight: bold"/>
									<h:outputText value="#{loteBean.loteSeleccionado.operarioResponsableInyeccion}" />
								</h:panelGrid>
							</rich:panel>
						
							<rich:panel>
								<f:facet name="header">
					                <h:outputLabel value="COCCION"></h:outputLabel>
						        </f:facet>			
								<h:panelGrid columns="2" border="0" cellpadding="2" cellspacing="1" >
																						
									<h:outputText value="#{labels.jamonartesanal_horaIngreso}" style="font-weight: bold"/>
									<h:outputText value="#{loteBean.loteSeleccionado.horaIngresoCoccion}" />
											
									<h:outputText value="#{labels.jamonartesanal_tempIngreso}" style="font-weight: bold"/>
									<h:outputText value="#{loteBean.loteSeleccionado.tempIngresoCoccion} ºC" />
									
									<h:outputText value="#{labels.jamonartesanal_nroTacho}" style="font-weight: bold"/>
									<h:outputText value="#{loteBean.loteSeleccionado.nroTacho}" />
																	
									<h:outputText value="#{labels.jamonartesanal_horaSalida}" style="font-weight: bold"/>
									<h:outputText value="#{loteBean.loteSeleccionado.horaSalidaCoccion}" />
									
									<h:outputText value="#{labels.jamonartesanal_tempSalida}" style="font-weight: bold"/>
									<h:outputText value="#{loteBean.loteSeleccionado.tempSalidaCoccion} ºC" />								
																	
									<h:outputText value="#{labels.login_usuario}" style="font-weight: bold"/>
									<h:outputText value="#{loteBean.loteSeleccionado.operarioResponsableCoccion}" />
								
								</h:panelGrid>
							</rich:panel>
						
							<rich:panel>
								<f:facet name="header">
					                <h:outputLabel value="ENFRIADO"></h:outputLabel>
						        </f:facet>	
						        <h:panelGrid columns="2" border="0" cellpadding="2" cellspacing="1" >
														
									<h:outputText value="#{labels.jamonartesanal_nroTacho_salida}" style="font-weight: bold"/>
									<h:outputText value="#{loteBean.loteSeleccionado.nroTachoEnfriado}" />
																	
									<h:outputText value="#{labels.jamonartesanal_horaSalidaCamara}" style="font-weight: bold"/>
									<h:outputText value="#{loteBean.loteSeleccionado.horaSalidaACamara}" />
									
									<h:outputText value="#{labels.jamonartesanal_tempSalidaCamara}" style="font-weight: bold"/>
									<h:outputText value="#{loteBean.loteSeleccionado.tempSalidaACamara}" />	
									
								</h:panelGrid>
							</rich:panel>		
						
						<br/>
						<div align="center">
							
							<a4j:commandButton value="#{labels.generic_cerrar}" immediate="true">
								<rich:componentControl for="popupDetalleLote" operation="hide" event="onclick"/>
							</a4j:commandButton>
						</div>
				</a4j:form>
			</rich:modalPanel>
		</f:view>
	</body>
</html>
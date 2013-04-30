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
				margin-top: 17%;
				
			}
			
			.jamonartesanal {
				font-size: 200px;
			}
			
			.centrado{
				text-align: center;
			}
		</style>
	</head>

	<body class="bodyInicio">
		<f:view>

			<h:form id="form">
				<div align="center">
					<h:panelGrid columns="8" >
						
						<h:panelGrid columns="1" styleClass="centrado">
							<h:commandButton value="Jamon Artesanal" action="irAJAmonArtesanal" image="/common/img/jamon_artesanal_chico.jpg"></h:commandButton>
							<h:commandButton value="Jamon Artesanal" action="irAJAmonArtesanal" styleClass="jamonartesanal"></h:commandButton>
						</h:panelGrid>
					
						<rich:spacer width="30px" />
						
						<h:panelGrid columns="1" styleClass="centrado">
							<h:commandButton value="Stock" action="irAStock" image="/common/img/stock.jpg"></h:commandButton>
							<h:commandButton value="Stock" action="irAStock" styleClass="jamonartesanal"></h:commandButton>
						</h:panelGrid>
						
						<rich:spacer width="30px" />
						
						<h:panelGrid columns="1" styleClass="centrado">
							<h:commandButton value="Proveedores" action="irAProveedores" image="/common/img/proveedores2.jpg"></h:commandButton>	
							<h:commandButton value="Proveedores" action="irAProveedores" styleClass="jamonartesanal"></h:commandButton>	
						</h:panelGrid>
						
						<rich:spacer width="30px" />
						
						<h:panelGrid columns="1" styleClass="centrado">
							<h:commandButton value="Materias primas" action="irMateriasPrimas" image="/common/img/materia_prima.jpg"></h:commandButton>
							<h:commandButton value="Materias primas" action="irMateriasPrimas" styleClass="jamonartesanal"></h:commandButton>
						</h:panelGrid>
						
						<rich:spacer width="30px" />
						
						<h:panelGrid columns="1" styleClass="centrado">
						<h:commandButton value="Ingresar entrega" action="irAIngresoDeEntregas" image="/common/img/entrega.jpg" ></h:commandButton>
							<h:commandButton value="Ingresar entrega" action="irAIngresoDeEntregas" styleClass="jamonartesanal"></h:commandButton>
						</h:panelGrid>
						
						<rich:spacer width="30px" />
						
						<h:panelGrid columns="1" styleClass="centrado">
							<h:commandButton value="Listado de entregas" action="irAListadoDeEntregas" image="/common/img/listado_entregas.jpg"></h:commandButton>
							<h:commandButton value="Listado de entregas" action="irAListadoDeEntregas" styleClass="jamonartesanal"></h:commandButton>
						</h:panelGrid>
						
						<rich:spacer width="30px" />
						
						<h:panelGrid columns="1" styleClass="centrado">
							<h:commandButton value="Listado de lotes" action="irAListadoDeLotes" image="/common/img/lotes.jpg"></h:commandButton>
							<h:commandButton value="Listado de lotes" action="irAListadoDeLotes" styleClass="jamonartesanal"></h:commandButton>
						</h:panelGrid>
						
						<rich:spacer width="30px" />
						
						<h:panelGrid columns="1" styleClass="centrado">
							<h:commandButton value="Tipo materia prima" action="irATipoMateriaPrima" image="/common/img/materia_prima2.jpg"></h:commandButton>
							<h:commandButton value="Tipo materia prima" action="irATipoMateriaPrima" styleClass="jamonartesanal"></h:commandButton>
						</h:panelGrid>
				
					</h:panelGrid>
				</div>
			</h:form>

		</f:view>
	</body>

</html>

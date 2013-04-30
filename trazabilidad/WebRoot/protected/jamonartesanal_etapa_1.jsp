
<html>
	

	<body class="bodyInicio">
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
	</body>	
</html>

<ui:composition xmlns="http://www.w3.org/1999/xhtml"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:a4j="http://richfaces.org/a4j"
	xmlns:rich="http://richfaces.org/rich">

	<f:view>

		<h:form>
			<head>
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.0.0/animate.min.css"
	rel="stylesheet" type="text/css" />

<style rel="stylesheet" type="text/css">
.text {
	font-weight: 700;
	font-size: 40px;
	color: #f5f5f5;
	text-shadow: 1px 1px 1px #919191, 1px 2px 1px #919191, 1px 3px 1px
		#919191, 1px 4px 1px #919191, 1px 5px 1px #919191, 1px 6px 1px #919191,
		1px 7px 1px #919191, 1px 8px 1px #919191, 1px 9px 1px #919191, 1px
		10px 1px #919191, 1px 18px 6px rgba(16, 16, 16, 0.4), 1px 22px 10px
		rgba(16, 16, 16, 0.2), 1px 25px 35px rgba(16, 16, 16, 0.2), 1px 30px
		60px rgba(16, 16, 16, 0.4);
}

.table {
	background-color: #e6f9ff;
}

.TableHead {
	background-color: #253f8a;
}

.inputtext {
	border-radius: 6px;
	padding: 5px 5px;
	height: 30px;
	width: 300px;
	box-shadow: 1px 1px 4px 4px gray;
	border: 1px solid #669999;
}

.inputtext1 {
	border-radius: 6px;
	padding: 5px 5px;
	height: 30px;
	box-shadow: 1px 1px 4px 4px gray;
	border: 1px solid #669999;
}

.dropdown-menu {
	box-shadow: 1px 1px 2px 3px gray;
}

.form-control {
	border-radius: 6px;
	padding: 5px 5px;
	height: 30px;
	width: 30%;
	box-shadow: 1px 1px 15px lightsteelblue;
	border: 1px solid #669999;
}
</style>
			</head>
			<div class="row">
				<rich:spacer height="15px;"></rich:spacer>
			</div>
			<div class="row">
				<a4j:outputPanel id="msg">
					<div class="row col-md-12 wow shake" style="margin-top: 10px;">
						<h:messages errorStyle="color:red" style="font-size: 18px;"
							styleClass="generalExciseStyle" layout="table"
							infoStyle="color:green" />
					</div>
				</a4j:outputPanel>
			</div>

			<div class="row" align="center" style="background-color: #253f8a;">
				<h:outputText
					value="Entry Of Import Order Recieved By The Distillery From Other Countries"
					style="FONT-SIZE: xx-large; FONT-FAMILY: 'Agency FB'; COLOR: #ffffff; TEXT-DECORATION: underline;" />
				<h:inputHidden value="#{importOrderEntry_spirit_action.hidden}"></h:inputHidden>
			</div>

			<div class="row">
				<rich:spacer height="15px;"></rich:spacer>
			</div>
			<rich:separator lineType="dashed"></rich:separator>

			<div class="row">
				<rich:spacer height="10px;"></rich:spacer>
			</div>
			
				
					<div>
						<div class="row">
							<rich:spacer height="20px;"></rich:spacer>
						</div>

						<div class="row">
							<div class="col-md-4" align="right">
								<h:outputText
									value="Country From which these Order Has Been Recieved :"
									style="FONT-WEIGHT: bold; font-size: 15px"></h:outputText>
							</div>
							<div class="col-md-4" align="left">
								<h:selectOneMenu onchange="this.form.submit();"
									styleClass="form-control"
									style="border-radius: 6px;padding: 5px 5px;height: 30px;width: 30%;box-shadow: 1px 1px 15px lightsteelblue;border: 1px solid #669999;"
									value="#{importOrderEntry_spirit_action.country_id }">
									<f:selectItems
										value="#{importOrderEntry_spirit_action.country_list}"></f:selectItems>
								</h:selectOneMenu>


							</div>


						</div>

						<div class="row">
							<rich:spacer height="10px;"></rich:spacer>
						</div>

						<div class="col-md-12 row">
							
							<div class="col-md-3" align="right">
								<h:outputText value="Name Of Importing Unit :"
									style="FONT-WEIGHT: bold; font-size: 15px"></h:outputText>
							</div>
							<div class="col-md-3" align="left">

								<h:inputText value="#{importOrderEntry_spirit_action.unit_nm}"
									style="FONT-FAMILY: 'Arial'; border-radius: 6px;padding: 5px 5px;height: 30px;width: 300px;box-shadow: 1px 1px 4px 4px gray;border: 1px solid #669999;">

								</h:inputText>

							</div>
								<div class="col-md-3" align="right">
								<h:outputText value="Address Of Importing Unit :"
									style="FONT-WEIGHT: bold; font-size: 15px"></h:outputText>
							</div>
							<div class="col-md-3" align="left">

								<h:inputText value="#{importOrderEntry_spirit_action.unit_add}"
									style="FONT-FAMILY: 'Arial'; border-radius: 6px;padding: 5px 5px;height: 30px;width: 300px;box-shadow: 1px 1px 4px 4px gray;border: 1px solid #669999;">

								</h:inputText>
							


						</div>

						<div class="row">
							<rich:spacer height="20px;"></rich:spacer>
						</div>

						<div class="col-md-12 row">
							<div class="col-md-2" align="right">
								<h:outputText value="Purchase Order No :"
									style="FONT-WEIGHT: bold; font-size: 15px"></h:outputText>
							</div>
							<div class="col-md-4" align="left">

								<h:inputText
									value="#{importOrderEntry_spirit_action.purchase_ordrno}"
									styleClass="inputtext">

								</h:inputText>
							</div>
							<div class="col-md-2" align="right">
								<h:outputText value="Dated :"
									style="FONT-WEIGHT: bold; font-size: 15px"></h:outputText>
							</div>
							<div class="col-md-4" align="left">

								<rich:calendar value="#{importOrderEntry_spirit_action.date}"
									styleClass="form-control"></rich:calendar>
							</div>


						</div>



						<rich:spacer height="10px;"></rich:spacer>
						<!-- fisrt uploader -->
						<div class="col-md-12 row">
							<div class="col-md-2" align="right">
								<h:outputLabel value="Upload Purcahse Order (pdf only) :"
									style="FONT-WEIGHT: bold; font-size: 15px" />

							</div>
							<div class="col-md-3" align="left">


								<rich:fileUpload listHeight="40px" listWidth="225px"
									fileUploadListener="#{importOrderEntry_spirit_action.doc1uploadMethod}"
									maxFilesQuantity="1"
									ontyperejected="if (!confirm('Only pdf files are accepted')) return false"
									sizeErrorLabel="" acceptedTypes="pdf" addControlLabel="Add PDF">

									<a4j:support event="onuploadcomplete" reRender="renpdftrue1"></a4j:support>
								</rich:fileUpload>





							</div>

							<div class="col-md-3 " align="left" style="FONT-SIZE: xx-small;">
								<h:commandButton styleClass="btn btn-default"
									action="#{importOrderEntry_spirit_action.resetpage}"
									value="Upload Pdf" />
							</div>

							<div class="col-md-1 img-responsive">
								<a4j:outputPanel id="renpdftrue1">
									<h:outputLink
										rendered="#{importOrderEntry_spirit_action.doc1upload}"
										target="_blank"
										value="/doc/ExciseUp/ExportOutsideIndia/SPIRIT/ImportOrder/#{importOrderEntry_spirit_action.recordFile}">

										<h:graphicImage value="/img/download.gif" alt="view document"
											style="width : 60px; height : 35px;"></h:graphicImage>
									</h:outputLink>

								</a4j:outputPanel>
							</div>

						</div>




						<!-- end of first uploader  -->
						<div>
							<rich:spacer height="10px;"></rich:spacer>
						</div>

						<div class="col-md-12 row">

							<div class="col-md-2" align="right">
								<h:outputText value="Validity Of Purchase Order  :"
									style="FONT-WEIGHT: bold; font-size: 15px"></h:outputText>
							</div>
							<div class="col-md-4" align="left">

								<rich:calendar value="#{importOrderEntry_spirit_action.validtyDt}"
									styleClass="form-control"></rich:calendar>
							</div>


						</div>

<div class="row">

			<div class="col-md-2" align="right"><h:outputText value="Spirit Type :"
			style="FONT-WEIGHT: bold; font-size: 15px">
					</h:outputText></div>
			<div class="col-md-4" align="left"><h:selectOneMenu onchange="this.form.submit();"
					 styleClass="dropdown-menu"
						value="#{importOrderEntry_spirit_action.sprit_type}"
						style="FONT-FAMILY: 'Arial';">
						<f:selectItems
							value="#{importOrderEntry_spirit_action.spirit_list}" />
					</h:selectOneMenu></div>
			</div>
			
<rich:spacer height="20px" />

           <div>
           <div class="col-md-2" align="right"><h:outputText value="Quantity(BL) :"
           style="FONT-WEIGHT: bold; font-size: 15px">
					</h:outputText></div>
            <div class="col-md-2" align="left"><h:inputText
            value="#{importOrderEntry_spirit_action.quantity_bl}">
            <a4j:support reRender="bl" event="onkeyup"></a4j:support>
            
            </h:inputText></div>
             <div class="col-md-2" align="right"><h:outputText value="Strength :"
             style="FONT-WEIGHT: bold; font-size: 15px">
					</h:outputText></div>
              <div class="col-md-2" align="left"><h:inputText
              value="#{importOrderEntry_spirit_action.strenght}">
               <a4j:support reRender="bl" event="onkeyup"></a4j:support>
              </h:inputText></div>
               <div class="col-md-2" align="right"><h:outputText value="Quantity(AL) :"
               style="FONT-WEIGHT: bold; font-size: 15px">
					</h:outputText></div>
                <div class="col-md-2" align="left">
                <a4j:outputPanel id="bl">
               <h:inputText value="#{importOrderEntry_spirit_action.quantity_al}" 
               disabled="true"></h:inputText>
              </a4j:outputPanel>
              </div>
           
           </div>




					</div>
				</div>
			
			<div>
				<rich:spacer height="10px" />
			</div>

		
			<div>
				<rich:spacer height="10px" />
			</div>
			<rich:spacer height="10px" />
			
			<div align="center">
				<rich:spacer height="30px" />
				<h:commandButton action="#{importOrderEntry_spirit_action.save}"
					value="Save" styleClass="btn-sm btn-primary" />
				<rich:spacer width="10px" />
				<h:commandButton action="#{importOrderEntry_spirit_action.reset}"
					value="Reset" styleClass="btn-sm btn-info" />




			</div>
			<rich:spacer height="15px" />
			<div>
				<rich:spacer height="10px" />
			</div>
			<h:panelGroup rendered="#{importOrderEntry_spirit_action.viewflg eq 'T'}">


				<div class="row">

					<div align="left">
						<h:outputText value="Recieved  Import Order Entries  :"
							style=" text-shadow: 2px 2px 4px #000000;FONT-WEIGHT: bold; margin-top:10px;FONT-SIZE: 25px;font-family:Comic Sans MS;"></h:outputText>

					</div>
				</div>
				<div>
					<rich:spacer height="10px" />
				</div>
				<div align="center">
					<div style="overflow-y: scroll; height: 500px; align: center;">
						<rich:dataTable id="table2" width="98%" var="list"
							styleClass="table table-hover"
							value="#{importOrderEntry_spirit_action.submitOrder}"
							headerClass="TableHead" footerClass="TableHead"
							rowClasses="TableRow1,TableRow2">

							<rich:column>
								<f:facet name="header">
									<h:outputText value="Sr No." style="color:#ffffff;"
										styleClass="generalExciseStyle"></h:outputText>
								</f:facet>

								<h:outputText value="#{list.sr_n1}"
									styleClass="generalExciseStyle"></h:outputText>
							</rich:column>

							<rich:column>
								<f:facet name="header">
									<h:outputText value="Country" style="color:#ffffff;"
										styleClass="generalExciseStyle"></h:outputText>
								</f:facet>

								<h:outputText value="#{list.countryNm}"
									styleClass="generalExciseStyle"></h:outputText>
							</rich:column>


							<rich:column>
								<f:facet name="header">
									<h:outputText value="Unit Name" style="color:#ffffff;"
										styleClass="generalExciseStyle"></h:outputText>
								</f:facet>
								<h:outputText value="#{list.unitNM}"
									styleClass="generalExciseStyle"></h:outputText>
							</rich:column>
							<rich:column>
								<f:facet name="header">
									<h:outputText value="Unit Address" style="color:#ffffff;"
										styleClass="generalExciseStyle"></h:outputText>
								</f:facet>
								<h:outputText value="#{list.unit_add}"
									styleClass="generalExciseStyle"></h:outputText>
							</rich:column>
							<rich:column>
								<f:facet name="header">
									<h:outputText value="Purchase Order No" style="color:#ffffff;"
										styleClass="generalExciseStyle"></h:outputText>
								</f:facet>
								<h:outputText value="#{list.purchaseNo}"
									styleClass="generalExciseStyle">

								</h:outputText>
							</rich:column>
							<rich:column>
								<f:facet name="header">
									<h:outputText value="Date " style="color:#ffffff;"
										styleClass="generalExciseStyle"></h:outputText>
								</f:facet>
								<h:outputText value="#{list.crDate}"
									styleClass="generalExciseStyle">

								</h:outputText>
							</rich:column>
							<rich:column>
								<f:facet name="header">
									<h:outputText value="Purcahse Order (pdf)"
										style="color:#ffffff;" styleClass="generalExciseStyle"></h:outputText>
								</f:facet>
								<h:outputLink
									value="/doc/ExciseUp/ExportOutsideIndia/SPIRIT/ImportOrder/#{list.orderCopy}"
									target="_blank">

									<h:outputText value="View Pdf "
										style="TEXT-DECORATION: underline; FONT-STYLE: italic; COLOR: #0000ff;"></h:outputText>

								</h:outputLink>
							</rich:column>
							<rich:column>
								<f:facet name="header">
									<h:outputText value="Validity Date " style="color:#ffffff;"
										styleClass="generalExciseStyle"></h:outputText>
								</f:facet>
								<h:outputText value="#{list.validDate}"
									styleClass="generalExciseStyle">

								</h:outputText>
							</rich:column>
							<rich:column>
								<f:facet name="header">
									<h:outputText value="Spirit Type" style="color:#ffffff;"
										styleClass="generalExciseStyle"></h:outputText>
								</f:facet>
								<h:outputText value="#{list.spirit_name_dt}"
									styleClass="generalExciseStyle">

								</h:outputText>
							</rich:column>
							
							<rich:column>
								<f:facet name="header">
									<h:outputText value="Quantity(BL)"
										style="color:#ffffff;" styleClass="generalExciseStyle"></h:outputText>
								</f:facet>
								<h:outputText value="#{list.qantity_bl_dt}"
									styleClass="generalExciseStyle">

								</h:outputText>
							</rich:column>
							
							<rich:column>
								<f:facet name="header">
									<h:outputText value="Strength)"
										style="color:#ffffff;" styleClass="generalExciseStyle"></h:outputText>
								</f:facet>
								<h:outputText value="#{list.strength_dt}"
									styleClass="generalExciseStyle">

								</h:outputText>
							</rich:column>
							
							<rich:column>
								<f:facet name="header">
									<h:outputText value="Quantity(AL)"
										style="color:#ffffff;" styleClass="generalExciseStyle"></h:outputText>
								</f:facet>
								<h:outputText value="#{list.quantity_al_dt}"
									styleClass="generalExciseStyle">

								</h:outputText>
							</rich:column>

                        <rich:column>
							<f:facet name="header">
								
							</f:facet>
							<h:commandButton
							style = "border-radius:5px;"
							actionListener="#{importOrderEntry_spirit_action.delete}"
							disabled="#{list.flg==false}"
							value="Delete" styleClass="btn btn-danger" />
						</rich:column>












						</rich:dataTable>
					</div>
				</div>
			</h:panelGroup>
			<rich:spacer height="30px" />
<div style="FONT-SIZE: xx-small;"></div>
		</h:form>
	</f:view>
</ui:composition>
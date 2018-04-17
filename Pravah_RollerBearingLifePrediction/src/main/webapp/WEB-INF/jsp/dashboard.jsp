<html ng-app="dashboardApp">
<head>
	<jsp:include page="head.jsp" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/zingchart.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/controllers/zingchart-angularjs.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/controllers/dashboardController.js"></script> 
</head>

<body ng-controller="dashboardController">
	<jsp:include page="header.jsp" />
<div class="container padding-10">
	<div class="panel with-nav-tabs panel-info">
        <div class="panel-heading">
	        <ul class="nav nav-tabs">
	            <li class="active"><a href="#dashboard" data-toggle="tab">Dashboard</a></li>
	            <li><a href="#trends" data-toggle="tab">Trends</a></li>
	            <li><a href="#alarms" data-toggle="tab">Alarms</a></li>
	            <li><a href="#extension" data-toggle="tab">Extensions</a></li>
	            <li><button class="btn btn-default" ng-click="ReConfigure()">Re-Configure</button></li>
				<li><button class="btn btn-default" ng-click="logout()">Logout</button></li>
	        </ul>
        </div>
 
	
	<div class="panel-body">
		<div class="tab-content">
		  <div id="dashboard" class="tab-pane fade in active">
		    <!-- ##### load indicators ##### --> 
			<div class="container-fluid">
				<div class="row margin-top-2">
					<div class="col-md-8">
						<div class="row  padding-10">
							<div class="col-md-2 padding-10">
								<div class="row border-lg-1 margin-top-1">
									<div class="col-md-12" ng-style="showNoInput">
										<h4>No Input</h4>
									</div>
								</div>
								
								<div class="row border-lg-1 margin-top-1">
									<div class="col-md-12" ng-style="showNoLoad">
										<h4>No Load</h4>
									</div>
								</div>
								
								<div class="row border-lg-1 margin-top-1">
									<div class="col-md-12" ng-style="showFullLoad">
										<h4>Full Load </h4>
									</div>
								</div>
								
								<div class="row border-lg-1 margin-top-1">
									<div class="col-md-12" ng-style="showShockLoad">
										<h4>Shock Load</h4>
									</div>
								</div>
							</div>
							<div class="col-md-10 padding-10">
								<div class="row padding-10 margin-top-1" >
									<div class="col-md-2"></div>
									<div class="col-md-8">
										<div class="row margin-top-2">
											<div class="col-md-12">
												<label>Pressure : {{pressure}}</label><br>
												<label>Temperature: {{temperature}}</label><br>
												<label>Rotations per min : {{rpm}}</label><br>
											</div>
										</div>
										<div class="row margin-top-2">
											<div class="col-md-12">
												<label>Equivalent Dynamic Load : {{load}}</label><br>
												<label>Expansion Factor: {{expansionFactor}}</label><br>
												<label>Lubrication Factor: {{lubricationFactor}}</label><br>
												<label>Adjusted Rating : {{adjustedRating}}</label><br>
												<label>Service Life Consumed(in hrs) : {{serviceLifeConsumed}}</label><br>
												<label>Service Life Left(in hrs) : {{serviceLifeLeft}}</label>
											</div>
										</div>
										
									</div>
								</div>
							</div>
						</div>
						
						<div class="row">
							<div class="col-md-6">
								<div zingchart id="chart-1" zc-json="myData1" zc-width="100%" zc-height="300px"></div>
							</div>
							<div class="col-md-6">
								<div zingchart id="chart-2" zc-json="myData2" zc-width="100%" zc-height="300px"></div>
							</div>
						</div>
					</div>
					
					<div class="col-md-4">
						<div class="row">
							<div class="col-md-12" ng-show="showStopImg">
								 <img src="${images}/img_roller_stop.jpg" style="width:100%;">
							</div>
							<div class="col-md-12" ng-show="showStartImg">
								 <img src="${images}/img_roller_start.jpg" style="width:100%;">
							</div>
						</div>
					</div>
					
				</div>
				
			</div>
			
		  </div>
		 
		  <div id="trends" class="tab-pane fade">
		    <div class="row border-lg-1 padding-25">
	            <div class="col-md-6 padding-10 bg-lg">
	                <div zingchart zc-json="line1" zc-width="100%" zc-height="300px"></div>
	            </div>
	       
	            <div class="col-md-6 padding-10 bg-lg">
	                <div zingchart zc-json="line2" zc-width="100%" zc-height="300px"></div>
	            </div>
	        </div>
	        <div class="row border-lg-1 padding-25 margin-top-1">
	            <div class="col-md-6 padding-10 bg-lg">
	                <div zingchart zc-json="line3" zc-width="100%" zc-height="300px"></div>
	            </div>
	            
	            <div class="col-md-6 padding-10 bg-lg">
	                <div zingchart zc-json="line4" zc-width="100%" zc-height="300px"></div>
	            </div>
	        </div>
	        <div class="row border-lg-1 padding-25 margin-top-1">
	            <div class="col-md-12 padding-10 bg-lg">
	                <div zingchart zc-json="line5" zc-width="100%" zc-height="300px"></div>
	            </div>
	        </div>
	        <div class="row border-lg-1 padding-25 margin-top-1">
	            <div class="col-md-12 padding-10 bg-lg">
	                <div zingchart zc-json="line6" zc-width="100%" zc-height="300px"></div>
	            </div>
	        </div>
		  </div>
		 
		  <div id="alarms" class="tab-pane fade">
		    <div class="row padding-25">
		    	<div class="col-md-12">
		    		<div class="row padding-10 border-lg-1" ng-show="lifeLeft20">
		    			<div class="col-md-1 bg-yellow">
		    				<h4>Warning</h4>
		    			</div>
		    			<div class="col-md-6 bg-lg">
		    				<h4>Roller bearing service life left to 20%.</h4>
		    			</div>
		    			<div class="col-md-2 bg-lightblue">
		    				<h4>{{ today1 | date : "short" }}</h4>
		    			</div>
		    		</div>
		    		<div class="row"><hr></div>
		    		<div class="row padding-10 border-lg-1" ng-show="lifeLeft10">
		    			<div class="col-md-1 bg-yellow">
		    				<h4>Warning</h4>
		    			</div>
		    			<div class="col-md-6 bg-lg">
		    				<h4>Roller bearing service life left to 10%.</h4>
		    			</div>
		    			<div class="col-md-2 bg-lightblue">
		    				<h4>{{ today2 | date : "short" }}</h4>
		    			</div>
		    		</div>
		    		<div class="row"><hr></div>
		    		<div class="row padding-10 border-lg-1" ng-show="lifeLeft5">
		    			<div class="col-md-1 bg-red text-white">
		    				<h4>Warning</h4>
		    			</div>
		    			<div class="col-md-6 bg-lg">
		    				<h4>Roller bearing service life left to 5%.</h4>
		    			</div>
		    			<div class="col-md-2 bg-lightblue">
		    				<h4>{{ today3 | date : "short" }}</h4>
		    			</div>
		    		</div>
		    		<div class="row"><hr></div>
		    		<div class="row padding-10 border-lg-1" ng-show="tempHigh">
		    			<div class="col-md-1 bg-yellow">
		    				<h4>Warning</h4>
		    			</div>
		    			<div class="col-md-6 bg-lg">
		    				<h4>Roller bearing temperature is very high.</h4>
		    			</div>
		    			<div class="col-md-2 bg-lightblue">
		    				<h4>{{ today4 | date : "short" }}</h4>
		    			</div>
		    		</div>
		    		<div class="row"><hr></div>
		    		<div class="row padding-10 border-lg-1" ng-show="pressureHigh">
		    			<div class="col-md-1 bg-red text-white">
		    				<h4>Warning</h4>
		    			</div>
		    			<div class="col-md-6 bg-lg">
		    				<h4>Hydraulic pressure is very high.</h4>
		    			</div>
		    			<div class="col-md-2 bg-lightblue">
		    				<h4>{{ today5 | date : "short" }}</h4>
		    			</div>
		    		</div>
		    	</div>
		    </div>
		  </div>
		  
		  <div id="extension" class="tab-pane fade">
		    <div class="row padding-25">
		    	<div class="col-md-12 border-lg-1 padding-10">
		    		<div class="row">
		    			<div class="col-md-4" ng-click="showIot=true">
		    				<img src="${images}/img_extn_iot.jpg" style="width:100%;">
		    			</div>
		    			<div class="col-md-4" ng-click="showExtn=true">
		    				<img src="${images}/img_extn_predictive.jpg" style="width:100%;">
		    			</div>
		    			<div class="col-md-4" ng-click="showContacts=true">
		    				<img src="${images}/img_extn_contact.jpg" style="width:100%;">
		    			</div>
		    		</div>
		    		
		    		<div class="row" ng-show="showContacts">
		    			<div class="col-md-8">
		    				<h4>Siemens Technology and Services Private Limited</h4>
		    				<h4>CT RDA DS AA PD AE-E PRAVAH</h4>
		    				<h4>IFFCO Tower, Plot No. 3, Sector 29</h4>
		    				<h4>Gurgaon 122001, Indien</h4>
		    				<h4>Tel.: +91 124 2704233</h4>
							<h4>Fax: +91 124 2886608</h4>
							<h4>mailto:pravah@siemens.com</h4>
							<h4>www.siemens.co.in/STS</h4>
							<h4>www.siemens.com/ingenuityforlife</h4>
		    			</div>
		    		</div>
		    		
		    	</div>
		    </div>
		  </div>
		</div>
	</div>
	</div>
</div>
</body>
</html>
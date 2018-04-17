<!DOCTYPE html>
<html lang="en">

  <head>
  	<jsp:include page="head.jsp"/>	  
  	<script type="text/javascript" src="${pageContext.request.contextPath}/controllers/logout.js"></script> 
  </head>


<body>
	
	<jsp:include page="header.jsp"/>
	
	<div class="container" ng-app="configureApp">
		<div ng-controller="configureController">
			<div class="row">
				<div class="col-md-12">
					<div class="row">
						<div class="col-md-6"><h1>Configuration Page</h1></div>
						<div class="col-md-5"></div>
						<div class="col-md-1">
							<button class="btn btn-default margin-top-2" ng-click="Logout()">Logout</button>
						</div>
					</div>
				</div>
				
				<div class="col-md-12 border-lg padding-10">
					<h3>Static inputs for bearing life calculation</h3>
					<div class="row margin-top-2">
						<div class="col-md-12">
								<form>
									<div class="row">
										<div class="col-md-3 margin-top-1"><label>Mass of the rotor : </label></div>
										<div class="col-md-3 margin-top-1"><input type="text" name="rotorMass" ng-model="rotorMass"></div>
										<div class="col-md-3 margin-top-1"><label>Hydraulics cylinder diameter : </label></div>
										<div class="col-md-3 margin-top-1"><input type="text" name="cylinderDiameter" ng-model="cylinderDiameter"></div>
									</div>
									
									<div class="row">
										<div class="col-md-3 margin-top-1"><label>No. of cylinders : </label></div>
										<div class="col-md-3 margin-top-1"><input type="text" name="cylinderNo" ng-model="cylinderNo"></div>
										<div class="col-md-3 margin-top-1"><label>Safety factor for bending load per bearing : </label></div>
										<div class="col-md-3 margin-top-1"><input type="text" name="bearingSafetyFactor" ng-model="bearingSafetyFactor"></div>
									</div>
									
									<div class="row">
										<div class="col-md-3 margin-top-1"><label>Bearing center to center distance : </label></div>
										<div class="col-md-3 margin-top-1"><input type="text" name="bearngCenterDistance" ng-model="bearngCenterDistance"></div>
										<div class="col-md-3 margin-top-1"><label>Bearing A : </label></div>
										<div class="col-md-3 margin-top-1"><input type="text" name="bearingA" ng-model="bearingA"></div>
									</div>
									
									<div class="row">
										<div class="col-md-3 margin-top-1"><label>Bearing B : </label></div>
										<div class="col-md-3 margin-top-1"><input type="text" name="bearingB" ng-model="bearingB"></div>
										<div class="col-md-3 margin-top-1"><label>Bearing dynamic load : </label></div>
										<div class="col-md-3 margin-top-1"><input type="text" name="bearingDynamicLoad" ng-model="bearingDynamicLoad"></div>
									</div>

									<div class="row">
										<div class="col-md-3 margin-top-1"><label>Life exponent : </label></div>
										<div class="col-md-3 margin-top-1"><input type="text" name="lifeExponent" ng-model="lifeExponent"></div>
										<div class="col-md-3 margin-top-1"><label>Bearing fatigue load : </label></div>
										<div class="col-md-3 margin-top-1"><input type="text" name="bearingFatigueLoad" ng-model="bearingFatigueLoad"></div>
									</div>

									<div class="row">
										<div class="col-md-3 margin-top-1"><label>Factor X : </label></div>
										<div class="col-md-3 margin-top-1"><input type="text" name="factorX" ng-model="factorX"></div>
										<div class="col-md-3 margin-top-1"><label>Factor Y : </label></div>
										<div class="col-md-3 margin-top-1"><input type="text" name="factorY" ng-model="factorY"></div>
									</div>
									
									<div class="row">
										<div class="col-md-3 margin-top-1"><label>Bearing Inner diameter : </label></div>
										<div class="col-md-3 margin-top-1"><input type="text" name="bearingID" ng-model="bearingID"></div>
										<div class="col-md-3 margin-top-1"><label>Bearing Outer diameter : </label></div>
										<div class="col-md-3 margin-top-1"><input type="text" name="bearingOD" ng-model="bearingOD"></div>
									</div>

									<div class="row margin-top-2 margin-bot-2">
									<div class="col-md-1"></div>
									<div class="col-md-9"></div>
									<div class="col-md-2">
										<button ng-click="submitConfigData()" class="btn btn-default margin-top-1">Configure</button>
									</div>
					
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

</html>
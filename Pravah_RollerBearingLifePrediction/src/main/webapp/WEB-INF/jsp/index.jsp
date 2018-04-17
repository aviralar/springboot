<!DOCTYPE html>
<html lang="en">

  <head>
  	<jsp:include page="head.jsp"/>	  
  	<script type="text/javascript" src="${pageContext.request.contextPath}/controllers/loginPageController.js"></script>
  	<style type="text/css">
		.container {
		    position: relative;
		    text-align: center;
		    color: white;
		}

		/* Centered text */
		.centered {
		    position: absolute;
		    top: 50%;
		    left: 50%;
		    transform: translate(-50%, -50%);
		}
	</style>  
  </head>


<body>
	
	<jsp:include page="header.jsp"/>

	<div class="container-fluid padding-0" ng-app="loginApp">
		<div class="row border-lg bg-biege" ng-controller="loginController">
			<img src="${images}/img_bearing.jpg" style="width: 100%;"/>
			<div class="centered">
				<div class="col-md-1"></div>
				<div class="col-md-10 padding-50">
					<div class="row padding-25 border-lg bg-lightblack">
						<div class="col-md-12">
							<form>
								<div class="row">
									<div class="col-md-8">
										<label for="username" class="text-white">Username : </label>
										<input type="text" placeholder="Enter username" name="username" ng-model="username" required><br>
									</div>
								</div>

								<div class="row">
									<div class="col-md-8">
										<label for="password "class="text-white margin-top-1">Password : </label>
										<input type="password" placeholder="Enter password" name="password" ng-model="password" required><br>
									</div>
								</div>

								<div class="row">
									<div class="col-md-8">
										<button class="btn btn-default margin-top-1" ng-click="authenticationCheck()">Login</button>
									</div>
									<div class="col-md-4"></div>
								</div>

							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- ###### Footer ###### -->
	<div class="footer">
	  <p class="margin-top-1">&copy; Team Pravah</p>
	  <p>Developed by : Aviral Jain</p>
	</div>
	
</body>

</html>
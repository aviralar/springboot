var app = angular.module('dashboardApp',['zingchart-angularjs']);

app.controller('dashboardController',["$http", "$scope", "$interval", "$window", function($http,$scope,$interval,$window){
	var i = 1;
	var shockLoadCount = 0;
	var shockLoadCountArray = [];
	var maxServiceLife = 0;
	var showLoad = {
			"color" : "white",
		    "background-color" : "lightblue"
	}
	
	var hideLoad = {
			"color" : "black",
		    "background-color" : "white"
	}
	// initializing the angular view models
	
	$scope.showStartImg = false;
	$scope.showStopImg = false;
	$scope.showNoInput = showLoad;
	$scope.showNoLoad = hideLoad;
	$scope.showFullLoad = hideLoad;
	$scope.showShockLoad = hideLoad;
	
	// getting the id of the first cycle
	$http.post('getFirstId').then(function success(response){
		i = response.data.id;
		console.log(i);
	});
	
	var pressure = [];
	var temperature = [];
	var rpm = [];
	var slc = [];
	var sll = [];
	
	$interval(function () {
	
		$http.post('getLiveData',i).then(function success(response) {
					
					$scope.pressure = response.data.pressure;
					pressure.push(response.data.pressure);
					
					$scope.temperature = response.data.temperature;
					temperature.push(response.data.temperature);
					
					$scope.rpm = response.data.rpm;
					rpm.push(response.data.rpm);
					
					$scope.load = response.data.load;
					$scope.expansionFactor = response.data.expansionFactor;
					$scope.lubricationFactor = response.data.lubricationFactor;
					$scope.adjustedRating = response.data.adjustedRating;
					
					$scope.serviceLifeConsumed = response.data.serviceLifeConsumed;
					slc.push(response.data.serviceLifeConsumed);
					if(response.data.serviceLifeConsumed > maxServiceLife)
						maxServiceLife = response.data.serviceLifeConsumed;
					
					$scope.serviceLifeLeft = response.data.serviceLifeLeft;
					sll.push(response.data.serviceLifeLeft);
					
					// to show the type of load
					showLoadTypeInView(response.data.pressure);
					
					// shock load count array per cycle
					shockLoadCountArray.push(shockLoadCount);
					
					// to show start image of the roller
					if(response.data.pressure > 0)
						{
							$scope.showStartImg = true;
							$scope.showStopImg = false;
						}
					else if (response.data.pressure <= 0)
						{
							$scope.showStartImg = false;
							$scope.showStopImg = true;
						}
					else
						{
							$scope.showStartImg = false;
							$scope.showStopImg = false;
						}
					// gauge chart on dash board to indicate service life
					// consumed
					$scope.myData1 = {
							type : "gauge",
							title : {
								text : 'Service life per cycle(lac hrs):',
								fontSize : 14
							},
			                scaleR: {
			 	                values: "0:10:1",
			                    aperture : 220,
			                    ring : {
			                        size : 15,
			                        backgroundColor : '#d3d3d3'
			                    },
			                    center : {
			                        size : 5,
			                        backgroundColor : '#d3d3d3'
			                    }

			 	            },
			                 series : [
					            {
						            values : [response.data.serviceLifeConsumed/100000],
			                        csize : "5%",
			                        size : "75%",
						            backgroundColor:'#000000'
					            }
			                ]
					};
					// pie chart on dash board for service life left
					$scope.myData2 = {
					        globals: {
					            shadow: false,
					            fontFamily: "Verdana",
					            fontWeight: "100"
					        },
					        type: "pie",
					     
					        backgroundColor: "#fff",

					        legend: {
					            layout: "x2",
					            position: "50%",
					            borderColor: "transparent",
					            marker: {
					                borderRadius: 10,
					                borderColor: "transparent"
					            }
					        },
					        tooltip: {
					            text: "%v service life"
					        },
					        plot: {
					            refAngle: "-90",
					            borderWidth: "0px",
					            valueBox: {
					                placement: "in",
					                text: "%npv %",
					                fontSize: "15px",
					                textAlpha: 1,
					            }
					        }, 
					        series: [{
					            text: "Service life left",
					            values: [response.data.serviceLifeLeft],
					            backgroundColor: "#FA6E6E #FA9494",
					        },  {
					            text: "Service Life Consumed",
					            values: [maxServiceLife - response.data.serviceLifeLeft],
					            backgroundColor: "#D2D6DE",
					        }]
					    };	
					
					// render line charts on TRENDS pane
					renderLineCharts();
					
					// check for warnings and alarms
					checkAlarms(response.data.pressure, response.data.temperature, response.data.serviceLifeLeft);
			
		})
		.catch(function(response){
			$scope.pressure = 0;
			$scope.temperature = 0;
			$scope.rpm = 0;
			$scope.load = 0;
			$scope.expansionFactor = 0;
			$scope.lubricationFactor = 0;
			$scope.adjustedRating = 0;
			$scope.serviceLifeConsumed = 0;
			$scope.serviceLifeLeft = 0;
			$scope.showStartImg = false;
			$scope.showStopImg = true;
			$scope.showNoInput = showLoad;
			$scope.showNoLoad = hideLoad;
			$scope.showFullLoad = hideLoad;
			$scope.showShockLoad = hideLoad;
			console.log("aviral was here");
		});
		
		i=i+1;
		
		
		
	  }, 1000);
	
	$scope.logout = function() {
		$window.location.href="/index";
	}
	
	$scope.ReConfigure = function() {
		$window.location.href="/configPage";
	}
	
	function showLoadTypeInView(p){
		
		if(p == 1)
		{
			$scope.showNoInput = hideLoad;
			$scope.showNoLoad = showLoad;
			$scope.showFullLoad = hideLoad;
			$scope.showShockLoad = hideLoad;
		}
		else if (p > 1 && p <= 13)
		{
			$scope.showNoInput = hideLoad;
			$scope.showNoLoad = hideLoad;
			$scope.showFullLoad = showLoad;
			$scope.showShockLoad = hideLoad;
		}
		else if (p > 14)
		{
			shockLoadCount++;
			$scope.showNoInput = hideLoad;
			$scope.showNoLoad = hideLoad;
			$scope.showFullLoad = hideLoad;
			$scope.showShockLoad = showLoad;
		}
		else
		{
			$scope.showNoInput = showLoad;
			$scope.showNoLoad = hideLoad;
			$scope.showFullLoad = hideLoad;
			$scope.showShockLoad = hideLoad;
		}
	}
	
	function checkAlarms(p,t,life){
		
		if(p>16.77)
			{
				$scope.today5 = new Date();
				$scope.pressureHigh = true;
				
			}
		if(t>25)
			{
				$scope.today4 = new Date();
				$scope.tempHigh = true;
			}
		if(life < (0.2*maxServiceLife))
			{
				$scope.today1 = new Date();
				$scope.lifeLeft20 = true;
			}
		if (life < (0.1*maxServiceLife))
			{
			$scope.today2 = new Date();
			$scope.lifeLeft10 = true;
			}
		if (life < (0.05*maxServiceLife))
		{
			$scope.today3 = new Date();
			$scope.lifeLeft5 = true;
		}
	}
	
	function renderLineCharts(){
		 $scope.line1 = {
	                title: {
	                text: "Pressure per cycle",
	                fontSize: 16
	                },
	                type: "line",
	                scaleX: {
	                maxItems: 8,
	                lineWidth: "1px"
	                },
	                scaleY: {
	                lineWidth: "1px"
	                },
	                plot: {
	                aspect: "spline"
	                },
	                series: [{
	                values: pressure
	                }]

	            }
		
		 $scope.line2 = {
	                title: {
	                text: "Temperature per cycle",
	                fontSize: 16
	                },
	                type: "line",
	                scaleX: {
	                maxItems: 8,
	                lineWidth: "1px"
	                },
	                scaleY: {
	                lineWidth: "1px"
	                },
	                plot: {
	                aspect: "spline"
	                },
	                series: [{
	                values: temperature
	                }]

	            }
		 
		 $scope.line3 = {
	                title: {
	                text: "RPM per cycle",
	                fontSize: 16
	                },
	                type: "line",
	                scaleX: {
	                maxItems: 8,
	                lineWidth: "1px"
	                },
	                scaleY: {
	                lineWidth: "1px"
	                },
	                plot: {
	                aspect: "spline"
	                },
	                series: [{
	                values: rpm
	                }]

	            }
		 
		 $scope.line4 = {
	                title: {
	                text: "Overall Shock Load Count per cycle",
	                fontSize: 16
	                },
	                type: "line",
	                scaleX: {
	                maxItems: 8,
	                lineWidth: "1px"
	                },
	                scaleY: {
	                lineWidth: "1px"
	                },
	                plot: {
	                aspect: "spline"
	                },
	                series: [{
	                values: shockLoadCountArray
	                }]

	            }
		 
		 $scope.line5 = {
	                title: {
	                text: "Service life consumption per cycle",
	                fontSize: 16
	                },
	                type: "line",
	                scaleX: {
	                maxItems: 8,
	                lineWidth: "1px"
	                },
	                scaleY: {
	                lineWidth: "1px"
	                },
	                plot: {
	                aspect: "spline"
	                },
	                series: [{
	                values: slc
	                }]

	            }
		 
		 $scope.line6 = {
	                title: {
	                text: "Total Service Life left",
	                fontSize: 16
	                },
	                type: "line",
	                scaleX: {
	                maxItems: 8,
	                lineWidth: "1px",
	                label : "Cycle count"
	                },
	                scaleY: {
	                lineWidth: "1px",
	                label: "in lac hours"
	                },
	                plot: {
	                aspect: "spline"
	                },
	                series: [{
	                values: sll
	                }]

	            }
	}
}]);
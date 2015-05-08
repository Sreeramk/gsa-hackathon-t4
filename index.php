<?php
error_reporting(-1);
ini_set('display_errors', 'On');

$db = new PDO('mysql:host=localhost;dbname=gsahackathon', 'root', 'root');
$select = 'SELECT Building_Number, Building_Name, Building, State, A10_Cleaning, A30_Utilities_Fuel, A40_Mechanic_Oper_Maint,
				Grand_Total, Overall_Satisfaction, Occupancy, Building_Sq_Ft
			FROM satisfaction';

$data = [];
$result = $db->prepare($select);
$result->execute();

$indexArray = [];
$dataArray = [];

while ($row = $result->fetch(PDO::FETCH_ASSOC)) {
	$indexArray[] = array($row["Overall_Satisfaction"]);
	$dataArray[] = array($row["A10_Cleaning"]);
}

?>

<!DOCTYPE html>
<html>
<head>
	<title></title>
	<script type="application/javascript" src="js/jquery.min.js" charset="utf-8"></script>
	<script type="application/javascript" src="js/d3js.js" charset="utf-8"></script>
	<script type="application/javascript" src="js/charts.js" charset="utf-8"></script>
	<style>
		.chart {
			margin: 30px;
		}
	</style>
</head>
<body>
	<canvas class="chart" id="chart" width="1000" height="700"></canvas>
	<canvas class="chart" id="lineGraph" width="1000" height="700"></canvas>
	<script type="text/javascript">
		$(document).ready(function() {
			var indexArray = <?php echo json_encode($indexArray); ?>;
			var dataArray = <?php echo json_encode($dataArray); ?>;

			datasetsArray = [];
			for (var i = 0; i < indexArray.length; i++){
				var color = parseInt(127+127*(i+1)/indexArray.length);
				var rankedArray = [0,0,0,0,0];
				rankedArray[indexArray[i]] = dataArray[i];

				datasetsArray[i] = {
					label: indexArray[i],
					fillColor: "rgba(128,128,"+color+",0)",
					strokeColor: "rgba(128,128,"+color+",0)",
					pointColor: "rgba(128,128,"+color+",1)",
					pointStrokeColor: "#fff",
					pointHighlightFill: "#fff",
					pointHighlightStroke: "rgba(128,128,"+color+",1)",
					data: rankedArray
				};
			}

			var ctx = document.getElementById("chart").getContext("2d");
			var data = {
				labels: [1,2,3,4,5],
				datasets: datasetsArray
			};
			var myRadarChart = new Chart(ctx).Radar(data, {});


			/*lineDatasetsArray = [];
			for (var j = 0; j < lineGraphArray.length; j++){
				var lineColor = parseInt(127+127*(j+1)/indexArray.length);
				console.log(lineColor);

				datasetsArray[j] = {
					label: lineIndexArray[j],
					fillColor: "rgba(220,220,220,0)",
					strokeColor: "rgba(220,220,220,0)",
					pointColor: "rgba(220,220,220,1)",
					pointStrokeColor: "#fff",
					pointHighlightFill: "#fff",
					pointHighlightStroke: "rgba(220,220,220,0)",
					data: lineDatasetsArray[i]
				};
			}

			var linectx = document.getElementById("lineGraph").getContext("2d");
			var lineData = {
				labels: ['1','2','3','4','5'],
				datasets: datasetsArray
			};
			console.log(lineData);
			var myLineChart = new Chart(linectx).Line(lineData, {});*/
		});
	</script>
</body>
</html>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="semantic"/>
		<title>Line Graph</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/c3/0.4.10/c3.min.css" type="text/css">
	</head>
	<body>
		<div class="ui segment">
			<h1 class="ui header">Line Graph</h1>
			<div class="ui labeled input">
				<div class="ui label">
					Client ID
				</div>
				<g:select name="clientId" class="ui dropdown" from="${ clientIds }" />
			</div>
			<div class="ui divider"></div>
			<div id="graph"></div>
		</div>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/d3/3.5.5/d3.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/c3/0.4.10/c3.min.js"></script>
		<asset:javascript src="graph/index.js" />
		<script>
			$(function() {
				$('.ui.dropdown').dropdown();
				$('#clientId').change(getData);
				$('#clientId').trigger('change');
			});
		</script>
	</body>
</html>

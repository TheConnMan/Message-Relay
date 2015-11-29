<!doctype html>
<html>
	<head>
		<meta name="layout" content="semantic"/>
		<title>Line Graph</title>
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
		</div>
		<script>
			$(function() {
				$('.ui.dropdown').dropdown();
			});
		</script>
	</body>
</html>

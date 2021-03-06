<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title><g:layoutTitle default="Message Relay"/></title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.0/jquery.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.0.4/semantic.min.js"></script>
		<script src="//cdnjs.cloudflare.com/ajax/libs/jquery.blockUI/2.66.0-2013.10.09/jquery.blockUI.min.js"></script>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.0.4/semantic.min.css" type="text/css">
		<asset:stylesheet href="custom.css" />
		<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/d3/3.5.5/d3.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/d3-tip/0.6.3/d3-tip.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/0.5.0/sweet-alert.min.js"></script>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/0.5.0/sweet-alert.css" type="text/css"> -->

		<g:layoutHead/>
	</head>

	<body>
		<div class="menu-wrapper">
			<div class="ui menu">
				<a class="item menu-home" href="/">
					<i class="icon home"></i>
					Home
				</a>
				<sec:ifAllGranted roles="ROLE_ADMIN">
					<g:link class="item" controller="graph">
						<i class="icon line graph"></i>
						Graph
					</g:link>
					<g:link class="item" controller="message">
						<i class="icon mail"></i>
						Messages
					</g:link>
				</sec:ifAllGranted>
				<div class="right menu">
					<sec:ifLoggedIn>
						<div class="item">
							 <sec:username/>
						</div>
						<relay:avatar class="ui avatar circular image" style="width: 40px; height: 40px; float: right;" />
					</sec:ifLoggedIn>
					<sec:ifNotLoggedIn>
						<oauth:connect provider="github" class="item">
							<i class="github square icon"></i>
							Log In With GitHub
						</oauth:connect>
					</sec:ifNotLoggedIn>
				</div>
			</div>
		</div>

		<div class="content">
			<g:layoutBody/>
		</div>

		<div class="menu-wrapper">
			<div class="ui segment" style="margin-bottom: 15px;">
				<b>Message Relay ${grailsApplication.metadata['app.version']}</b>
			</div>
		</div>

	</body>
</html>

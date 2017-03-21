<body class="login_body">
	<div class="container">
		<div class="row">

			<div class="main">

				<h3 class="login_label_color">Please Log In</h3>
				<div class="row">
					<div class="col-xs-6 col-sm-6 col-md-6">
						<a class="btn btn-lg btn-primary btn-block dashboard"
							href="${pageContext.request.contextPath}/dashboard">Facebook</a>
					</div>
					<div class="col-xs-6 col-sm-6 col-md-6">
						<a class="btn btn-lg btn-info btn-block dashboard"
							href="${pageContext.request.contextPath}/dashboard">Google</a>
					</div>
				</div>
				<div class="login-or">
					<hr class="hr-or">
					<span class="span-or">or</span>
				</div>

				<form role="form">
					<div class="form-group">
						<label for="inputUsernameEmail" class="login_label_color">Username
							or Email</label> <input type="text" class="form-control"
							id="inputUsernameEmail">
					</div>
					<div class="form-group">
						<a class="pull-right" href="#">Forgot password?</a> <label
							for="inputPassword" class="login_label_color">Password</label> <input
							type="password" class="form-control" id="inputPassword">
					</div>
					<div class="checkbox pull-right">
						<label class="login_label_color"> <input type="checkbox">
							Remember me
						</label>
					</div>
					<button type="submit" class="btn btn btn-primary">Log In</button>
				</form>

			</div>

		</div>
	</div>
</body>

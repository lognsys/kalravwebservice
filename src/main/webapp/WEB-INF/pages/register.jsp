<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="container">
	<h1 class="well">Registration Form</h1>
	<div class="col-lg-12 well">
		<div class="row">
			<%-- <form> --%>
			<form:form method="POST" action="register" modelAttribute="users">
				<div class="col-sm-12">
					<div class="row">
						<div class="col-sm-6 form-group">
							<label>First Name</label>
							 <form:input type="text"
								placeholder="Enter First Name Here.." class="form-control"  path="firstname"  />
							<form:errors path="firstname" cssClass="error" />
						</div>
						<div class="col-sm-6 form-group">
							<label>Last Name</label>
							 <form:input type="text"
								placeholder="Enter Last Name Here.." class="form-control" path="lastname" />
								<form:errors path="lastname" cssClass="error" />
						</div>
					</div>
					<div class="form-group">
						<label>Address</label>
						<form:textarea placeholder="Enter Address Here.." rows="3"
							class="form-control"  path="address" />
							<form:errors path="address" cssClass="error" />
					</div>
					<div class="row">
						<div class="col-sm-4 form-group">
							<label>City</label>
							 <form:input type="text"
								placeholder="Enter City Name Here.." class="form-control"   path="city" />
									<form:errors path="city" cssClass="error" />
						</div>
						<div class="col-sm-4 form-group">
							<label>State</label> 
							<form:input type="text"
								placeholder="Enter State Name Here.." class="form-control"  path="state"  />
									<form:errors path="state" cssClass="error" />
						</div>
						<div class="col-sm-4 form-group">
							<label>Zip</label>
							 <form:input type="text"
								placeholder="Enter Zip Code Here.." class="form-control" path="zipcode" />
									<form:errors path="zipcode" cssClass="error" />
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6 form-group">
							<label>Title</label>
							 <form:input type="text"
								placeholder="Enter Designation Here.." class="form-control" path="title" />
						</div>
						<div class="col-sm-6 form-group">
							<label>Company</label>
							 <form:input type="text"
								placeholder="Enter Company Name Here.." class="form-control" path="company_name" />
						</div>
					</div>
					<div class="form-group">
						<label>Phone Number</label>
						 <form:input type="text"
							placeholder="Enter Phone Number Here.." class="form-control"  path="phone" />
								<form:errors path="phone" cssClass="error" />
					</div>
					<div class="form-group">
						<label>Email Address</label>
						 <form:input type="text"
							placeholder="Enter Email Address Here.." class="form-control" path="email" />
								<form:errors path="email" cssClass="error" />
					</div>

					<form:button type="submit" class="btn btn-lg btn-info" >Submit</form:button>
				</div>
			</form:form>
		</div>
	</div>
</div>
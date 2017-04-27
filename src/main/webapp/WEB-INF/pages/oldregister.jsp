<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="container">
	<h1 class="well">Registration Form</h1>
		<form:form method="POST" action="register" modelAttribute="users">
		<div class="row" cssClass="error">
						<form:errors path="firstname" element="div"  />
						<form:errors path="lastname"  element="div" />
						<form:errors path="address" element="div" />
						<form:errors path="city"   element="div" />
						<form:errors path="state" element="div" />
						<form:errors path="zipcode"  element="div" />
						<form:errors path="phone"  element="div" />
						<form:errors path="username" element="div" />
					
		</div>
	
	<div class="col-lg-12 well">
	
		<div class="row">
			<%-- <form> --%>
		
			
					<div class="col-sm-12">
					
					<div class="row">
						<div class="col-sm-6 form-group">
							<label>First Name</label>
							 <form:input type="text"
								placeholder="Enter First Name Here.." class="form-control"  path="firstname"  />
							
						</div>
						<div class="col-sm-6 form-group">
							<label>Last Name</label>
							 <form:input type="text"
								placeholder="Enter Last Name Here.." class="form-control" path="lastname" />
								
						</div>
					</div>
					<div class="form-group">
						<label>Address</label>
						<form:textarea placeholder="Enter Address Here.." rows="3"
							class="form-control"  path="address" />
					</div>
					<div class="row">
						<div class="col-sm-4 form-group">
							<label>City</label>
							 <form:input type="text"
								placeholder="Enter City Name Here.." class="form-control"   path="city" />
						</div>
						<div class="col-sm-4 form-group">
							<label>State</label> 
							<form:input type="text"
								placeholder="Enter State Name Here.." class="form-control"  path="state"  />
						</div>
						<div class="col-sm-4 form-group">
							<label>Zip</label>
							 <form:input type="text"
								placeholder="Enter Zip Code Here.." class="form-control" path="zipcode" />
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
							
					</div>
					<div class="form-group">
						<label>Email Address</label>
						 <form:input type="text"
							placeholder="Enter Email Address Here.." class="form-control" path="username" />
								
					</div>

					<form:button type="submit" class="btn btn-lg btn-info" >Submit</form:button>
				</div>
			</form:form>
		</div>
	</div>
</div>
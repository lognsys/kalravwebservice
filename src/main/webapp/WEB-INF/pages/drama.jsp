<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="container">
	<h1 class="well">Drama Form</h1>
		<form:form method="POST" action="drama" modelAttribute="drama">
		<%-- <div class="row" >
						<form:errors path="title" element="div"  />
						<form:errors path="lastname"  element="div" />
						<form:errors path="address" element="div" />
						<form:errors path="city"   element="div" />
						<form:errors path="state" element="div" />
						<form:errors path="zipcode"  element="div" />
						<form:errors path="phone"  element="div" />
						<form:errors path="email" element="div" />
					
					</div> --%>
	
	<div class="col-lg-12 well">
	
		<div class="row">
			<%-- <form> --%>
		
			
					<div class="col-sm-12">
					
					<div class="row">
						<div class="col-sm-6 form-group">
							<label>Title</label>
							 <form:input type="text"
								placeholder="Enter Title Here.." class="form-control"  path="title"  />
							
						</div>
						<div class="col-sm-6 form-group">
							<label>Genre</label>
							 <form:input type="text"
								placeholder="Enter Genre Here.." class="form-control" path="genre" />
								
						</div>
					</div>
					<div class="form-group">
						<label>Star Cast Name</label>
						 <form:textarea type="text"
								placeholder="Enter Star Cast Name Here.." class="form-control" path="star_cast" />
								
					</div>
					<div class="row">
						<div class="col-sm-4 form-group">
							<label>Director Name</label>
							 <form:input type="text"
								placeholder="Enter Director Name Here.." class="form-control"   path="director" />
						</div>
						<div class="col-sm-4 form-group">
							<label>Writer Name</label> 
							<form:input type="text"
								placeholder="Enter Writer Name Here.." class="form-control"  path="writer"  />
						</div>
						
					</div>
					<div class="form-group">
							<label>Description</label>
							 <form:textarea type="text"
								placeholder="Enter description Here.." class="form-control" path="description" />
						</div>
						<div class="form-group">
							<label>Image To upload</label>
							 <form:textarea type="text"
								placeholder="Enter description Here.." class="form-control" path="description" />
						</div>
					<form:button type="submit" class="btn btn-lg btn-info" >Submit</form:button>
				</div>
			</form:form>
		</div>
	</div>
</div>
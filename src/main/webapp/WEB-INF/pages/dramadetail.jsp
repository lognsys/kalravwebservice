<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div style="overflow: scroll;">
	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
		<div class="row">
			<ol class="breadcrumb">
				<li><a href="#"><svg class="glyph stroked home">
							<use xlink:href="#stroked-home"></use></svg></a></li>
				<li class="active">Drama Detail</li>
			</ol>
		</div>
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Drama Detail</h1>
			</div>
		</div>
		<div class="panel panel-default">

			<form:form method="post" action="dramadetail" modelAttribute="drama" enctype="multipart/form-data" >
				<div class="panel-body" Class="error">
					<form:errors path="title" element="div" />
					<form:errors path="drama_length" element="div" />
					<form:errors path="drama_language" element="div" />
					<form:errors path="date" element="div" />
					<form:errors path="genre" element="div" />
					<form:errors path="star_cast" element="div" />
					<form:errors path="description" element="div" />
					<form:errors path="director" element="div" />
					<form:errors path="writer" element="div" />
					<form:errors path="music" element="div" />
					<form:errors path="avg_rating" element="div" />

				</div>
				<div class="col-sm-12 panel panel-default">
				<fieldset>
					<div id="dramadetail_dramas" class="col-sm-12 panel panel-default">
						<div class="row">
							<div class="col-sm-6 form-group">
								<label>Drama Name </label>
								<form:input id="title" type="text" name="title" path="title"
									placeholder="Enter Drama Name Here...." class="form-control"></form:input>
							</div>
							<div class="col-sm-3 form-group">
								<label>Drama Length </label>
								<%-- <form:input  id="drama_length"  type="text" name="drama_length" path="drama_length"
									placeholder="Enter Drama  Here...." class="form-control" /> --%>
						 	 
							 <form:select  name="drama_length" path="drama_length" placeholder="Enter Drama Length Here...." class="form-control">
							  <option  id="drama_length" >1:00:00 - 1:30:00</option>
							  <option id="drama_length" >1:00:00 - 2:00:00</option>
							  <option id="drama_length" >2:00:00 - 2:30:00</option>
							  <option  id="drama_length" >2:30:00 - 3:00:00</option>
							  <option  id="drama_length" >3:00:00 - 3:30:00</option>
							  <option id="drama_length" >3:30:00 - 4:00:00</option>
							</form:select>
							</div>
							<div class="col-sm-3 form-group">
								<label>Select Drama Language </label>
								
							<form:select  name="drama_language" path="drama_language" placeholder="Enter Select Language...." class="form-control">
							  <option  id="drama_language" >English</option>
							  <option id="drama_language" >Hindi</option>
							  <option id="drama_language" >Marathi</option>
							  <option  id="drama_language" >Gujarati</option>
							  <option  id="drama_language" >Tamil</option>
							  <option id="drama_language" >Spanish</option>
							</form:select>
									
									
							</div>
							<div class="row">
							<div class="col-sm-6 form-group">
								<label>Drama Genre </label>
								<form:input  id="genre"  type="text" name="genre" path="genre"
									placeholder="Enter Drama Genre Here...." class="form-control" />
							</div>
							<div class="col-sm-6 form-group">
								<label>Star Cast </label>
								<form:input id="star_cast" type="text" name="star_cast" path="star_cast"
									placeholder="Enter Star Cast Name Here...."
									class="form-control" />
							</div>
							<div   class="col-sm-3 form-group">
								<label for="dtp_input1">Select
									DateTime </label> <br />
								
										<div class="input-group date form_datetime col-md-10"
								id="dtp_input1"
								data-date-format="yyyy-mm-dd  hh:MM a"
								data-link-field="dtp_input1">
								<form:input id="date" type="text" path="date"
									placeholder="Select Drama DateTime Length Here...."
									class="form-control" value=""  /> <span
									class="input-group-addon"><span
									class="glyphicon glyphicon-remove"></span></span>
									 <span
									class="input-group-addon"><span
									class="glyphicon glyphicon-th"></span></span>
							</div>
							<form:input type="hidden" id="dtp_input1" value="" name="date"
								path="" />
								<br />
							</div>
						</div>
							
						</div>
						
						<div class="form-group">
							<label>Description</label>
							<form:textarea id="description" placeholder="Enter Description Here.."
								path="description" name="description" rows="2"
								class="form-control"></form:textarea>
						</div>
						<div class="row">
							<div class="col-sm-6 form-group">
								<label>Director Name </label>
								<form:input type="text" id="director" path="director"
									placeholder="Enter Director Name Here...." name="director"
									class="form-control" />

							</div>
							<div class="col-sm-6 form-group">
								<label>Writer Name </label>
								<form:input id="writer" type="text" name="writer" path="writer"
									placeholder="Enter Writer Name Here...." class="form-control" />

							</div>
						</div>
						<div class="row">
							<div class="col-sm-6 form-group">
								<label>Music By </label>
								<form:input id="music" type="text" name="music" path="music"
									placeholder="Enter Music By Here...." class="form-control" />

							</div>
							<div class="col-sm-6 form-group">
								<label>Drama Average Rating </label>
								<form:input id="avg_rating" type="text"
									placeholder="Enter Drama Average Rating  Here...."
									path="avg_rating" name="avg_rating" class="form-control" />

							</div>
						</div>
						<div class="row">
							<div class="col-sm-3 form-group">
								<label>Group Name </label>
							<form:select path="group"  class="form-control">
									<option value="NONE">--- Select ---</option>
									<form:options items="${groupsList}"></form:options>
								</form:select>
							</div>
							<div class="col-sm-3 form-group">
								<label>Auditorium Name </label>
						<form:select path="auditorium"  class="form-control">
									<option value="NONE">--- Select ---</option>
									<form:options items="${auditoriumList}"></form:options>
								</form:select>
							</div>
						</div>
						<div class="row">

							<div class="col-sm-6 form-group">
								<label>Upload Drama Image </label>
								
								  <input type="file"name="file" /> 
													
							    <br />
							    Name: <input type="text" name="name" >
							    <br />
							</div>
						</div>
						<form:button type="submit" class="btn btn-lg btn-info">Submit</form:button>
					</div>
				</fieldset>
				</div>
							</form:form>
							
		</div>
	</div>
</div>
</div>
</div>
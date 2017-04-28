<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Drama Details</title>
</head>
<body class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main" > --%>
<div style="overflow: scroll;">
	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Drama Details</h1>
			</div>
		</div>
		<div >
			<form method="post" action="dramaDetail" modelAttribute="drama">
				


				<div class="row">
					<div class="col-sm-6 form-group">
						<label>Drama Name </label> <input type="text" name="title"
							class="form-control" />

					</div>
					<div class="col-sm-6 form-group">
						<label>Drama Length </label> <input type="text"
							name="drama_length" class="form-control" />

					</div>
					<div class="col-sm-6 form-group">
						<label>Drama Date </label> <input type="text" name="date"
							class="form-control" />

					</div>
				</div>

				<div class="row">
					<div class="col-sm-6 form-group">
						<label>Drama Genre </label> <input type="text" name="genre"
							class="form-control" />

					</div>
					<div class="col-sm-6 form-group">
						<label>Star Cast </label> <input type="text" name="star_cast"
							class="form-control" />

					</div>
				</div>
				<div class="form-group">
					<label>Description</label>
					<textarea placeholder="Enter Description Here.." name="description" rows="3"
						class="form-control"></textarea>
				</div>
				
				
				<div class="row">
					<div class="col-sm-6 form-group">
						<label>Drama Director </label> <input type="text" name="director"
							class="form-control" />

					</div>
					<div class="col-sm-6 form-group">
						<label>Drama Writer </label> <input type="text" name="writer"
							class="form-control" />

					</div>
				</div>
				<div class="row">
					<div class="col-sm-6 form-group">
						<label>Drama Music By </label> <input type="text" name="music"
							class="form-control" />

					</div>
					<div class="col-sm-6 form-group">
						<label>Drama Average Rating </label> <input type="text" name="avg_rating"
							class="form-control" />

					</div>
				</div>
				<div class="row">
					
					<div class="col-sm-6 form-group">
						<label>Upload Drama Image </label> <input type="file" name="imageurl"
							class="form-control" />

					</div>
				</div>
					<button type="submit" class="btn btn-lg btn-info">Submit</button>
		</form>
		</div>
	</div>
	</div>
	</body>
	</html>
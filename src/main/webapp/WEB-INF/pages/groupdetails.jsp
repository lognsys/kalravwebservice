<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div style="overflow: scroll;">
	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
		<div class="row">
			<ol class="breadcrumb">
				<li><a href="#"><svg class="glyph stroked home">
							<use xlink:href="#stroked-home"></use></svg></a></li>
				<li class="active">Groups</li>
			</ol>
		</div>
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Group</h1>
			</div>
		</div>
		<div class="panel panel-default">
			<form id="group" action="groupdetails" method="post" modelAttribute="groups">
				<div  class="col-sm-12 panel panel-default">
				<div class="row">
							<div class="col-sm-6 form-group">
								<label>Group Name </label>
								<form:input id="group_name" type="text" path="" 
									placeholder="Enter Group Name Here...." class="form-control" />
							</div>
							<div class="col-sm-6 form-group">
								<label>Select Groups</label>
								<form:input  type="text"
									path="" placeholder="Enter Select Group Name Here...."
									class="form-control" />
							</div>
							</div>
							<form:button type="submit" class="btn btn-lg btn-info">Submit</form:button>
				</div>
				
			</form>
		</div>
	</div>

</div>
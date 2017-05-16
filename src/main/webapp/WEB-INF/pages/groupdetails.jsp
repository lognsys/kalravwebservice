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
			<form:form method="POST" action="groupdetails" modelAttribute="groups">

				<div class="col-sm-12 panel panel-default">

					<div class="row">
						<div class="col-sm-6 form-group">
							<label>Group Name</label>
							<form:input id="group_name" type="text" name=group_name
								placeholder="Enter Group Name Here.."
								class="form-control text ui-widget-content ui-corner-all"
								path="group_name" />

						</div>

					</div>

					<div class="row ">
						<div class="col-sm-6  form-group">

							<label>Sub group name</label>
							<table id="myTable">
								<tr>
									<td><input type="text" class="fname" /></td>
									<td><input width="20px" height="20px" id="delete"
										type="button" class="buttonDelete" /></td>
								</tr>

							</table>
							<p>
								<input type="button" class="buttonAdd" width="20px" height="20px"/>
							</p>
						</div>
					</div>
					<div class="action_buttons">
	<div class="row">
								<div class="col-sm-4 form-group">
									<form:button id="add_button" type="submit"
						class="btn btn-lg btn-info">Add</form:button>
								</div>
								<div class="col-sm-4 form-group">
								<form:button id="edit_button" type="submit"
						class="btn btn-lg btn-info" >Edit</form:button>
								</div>
								
							</div>

					</div>
				</div>
			</form:form>
		</div>



	</div>

</div>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div style="overflow: scroll;">
	<form:form name="myform" method="post">
	
	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">			
		<div class="row">
			<ol class="breadcrumb">
				<li><a href="#"><svg class="glyph stroked home"><use xlink:href="#stroked-home"></use></svg></a></li>
				<li class="active">Users</li>
			</ol>
		</div><!--/.row-->
		
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Users</h1>
			</div>
		</div><!--/.row-->
				
		
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading"><svg class="glyph stroked male user"><use xlink:href="#stroked-male-user"></use></svg> 9 /128</div>
					<div class="panel-body">
						<table data-toggle="table" data-url="static/tables/users.json"  data-show-refresh="true" data-show-toggle="true" data-show-columns="true" data-search="true" data-select-item-name="toolbar1" data-pagination="true" data-sort-name="name" data-sort-order="desc">
						    <thead>
						    <tr>
						        <th data-field="id" data-checkbox="true" >ID</th>
						        <th data-field="name" data-sortable="true">Name</th>
						        <th data-field="email" data-sortable="true">Email</th>
						       <!--  <th data-field="group" data-sortable="true">Group</th> -->
						        <th data-field="status" data-sortable="true">Status</th>
						    </tr>
						    </thead>
						</table>
					</div>
				</div>
			</div>
		</div><!--/.row-->	
	
	
	
	
	
	

<%-- 		<div align="center">
			<h1>User List</h1>
			<h3>
				<div class="container">
					<div class="row">
						<nav class="navbar navbar-default">
							<div class="container">

								<p id="demo" class="navbar-text, text-center">User List Form</p>
							</div>
							</n/av>
					</div>
					<div class="row">
						<div class="col-xl-10 col-xl-offset-1">
							<div class="panel panel-default panel-table">
								<div class="panel-heading">
									<div class="row">
										<div class="col col-xs-6">
											<h3 class="panel-title">Panel title</h3>
										</div>
										<div class="col col-xs-6 text-right">
											<div class="pull-right">
												<div class="btn-group" data-toggle="buttons">
													<label class="btn btn-success btn-filter active"
														data-target="completed"> <input type="radio"
														name="options" id="option1" autocomplete="off" checked>
														Completed
													</label> <label class="btn btn-warning btn-filter"
														data-target="pending"> <input type="radio"
														name="options" id="option2" autocomplete="off">
														Pending
													</label> <label class="btn btn-default btn-filter"
														data-target="all"> <input type="radio"
														name="options" id="option3" autocomplete="off">
														All
													</label>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="panel-body">
									<table id="mytable"
										class="table table-striped table-bordered table-list">
										<thead>
											<tr>
												<th class="col-check"><input type="checkbox"
													name="Select All" id="select_all"
													onclick="selectAllCheckBox();" /></th>
												<th class="hidden-xs">ID</th>
												<th class="col-text">Name</th>
												<th class="col-text">Email</th>
												<th class="col-text">Phone</th>
												<th class="col-text">Status</th>
											</tr>
										</thead>
										<tbody>

											<c:forEach var="users" items="${listOfUsers}">
												<tr data-status="completed">
													<td align="center"><input name="checkboxname"
														type="checkbox" value="${users.id}" /></td>
													<td class="hidden-xs">${users.id}</td>
													<td>${users.realname}</td>
													<td>${users.username}</td>
													<td>${users.phone}</td>
													<td>New</td>
												</tr>

											</c:forEach>

										</tbody>
									</table>

								</div>
								<div class="panel-footer">
									<div class="row">
										<div class="col col-xs-offset-3 col-xs-6">
											<nav aria-label="Page navigation" class="text-center">
												<ul class="pagination">
													<li><a href="#" aria-label="Previous"> <span
															aria-hidden="true">«</span>
													</a></li>
													<li class="active"><a href="#">1</a></li>
													<li><a href="#">2</a></li>
													<li><a href="#">3</a></li>
													<li><a href="#">4</a></li>
													<li><a href="#">5</a></li>
													<li><a href="#" aria-label="Next"> <span
															aria-hidden="true">»</span>
													</a></li>
												</ul>
											</nav>
										</div>

									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<nav class="navbar navbar-default, text-center">
							<div class="container-fluid">
								<!--  <p class="navbar-text">A simple example of how-to put a bordered table within a panel. Responsive, place
                    holders in header/footer
                    for buttons or pagination.</p>-->
								<input type="submit" name="userAction" value="Cancel" /> <input
									type="submit" name="userAction" value="Edit" /> <input
									type="submit" name="userAction" value="Delete" /> <input
									type="submit" name="userAction" value="Add" />

							</div>
						</nav>
					</div>
				</div> --%>
	</form:form>
</div>
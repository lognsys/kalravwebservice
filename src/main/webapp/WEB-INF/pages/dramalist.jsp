<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"  isELIgnored="false"%>
<div style="overflow: scroll;">
	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
		<div class="row">
			<ol class="breadcrumb">
				<li><a href="#"><svg class="glyph stroked home">
							<use xlink:href="#stroked-home"></use></svg></a></li>
				<li class="active">Dramas</li>
			</ol>
		</div>
		<!--/.row-->

		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Dramas</h1>
			</div>
		</div>
		<!--/.row-->


		<div class="" id="eventsResult"></div>
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						<svg class="glyph stroked male user">
							<use xlink:href="#stroked-male-user"></use></svg>
					</div>
					<div class="panel-body">
						<table id="eventsTable" data-toggle="table"
							data-url="static/tables/dramas.json" data-show-refresh="true"
							data-show-toggle="true" data-show-columns="true"
							data-search="true" data-select-item-name="toolbar1"
							data-pagination="true" data-sort-name="name"
							data-sort-order="desc">
							<thead>
								<tr>
									<th data-field="checked" data-checkbox="true"></th>
									<th data-field="id" data-sortable="true" data-visible="false">ID</th>
									<th data-field="dramaname" data-sortable="true">Drama Name</th>
									<th data-field="groupname" data-sortable="true">Group Name</th>
									<th data-field="auditoriumname" data-sortable="true">Auditorium Name</th>
									<th data-field="datetime" data-sortable="true">DateTime</th>
								</tr>
							</thead>
						</table>
						<div class="action_buttons">
							<hr>
							<button id="useradd" type="submit"
								class="btn btn-primary pull-right rbtnMargin">Add</button>
							<button id="userdelete" type="submit"
								class="btn btn-danger pull-right rbtnMargin">Delete</button>
							<button id="useredit" type="submit"
								class="btn btn-warning pull-right rbtnMargin">Edit</button>
							<button id="usercancel" type="reset"
								class="btn btn-default pull-right rbtnMargin">Cancel</button>
						</div>
					</div>

				</div>

			</div>
		</div>
		<!--/.row-->
	</div>

	<ul id="output"></ul>
</div>
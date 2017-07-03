<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	isELIgnored="false"%>
<div style="overflow: scroll;">
	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
		<div class="row">
			<ol class="breadcrumb">
				<li><a href="#"><svg class="glyph stroked home">
							<use xlink:href="#stroked-home"></use></svg></a></li>
				<li class="active">Notification List</li>
			</ol>
		</div>
		<!--/.row-->

		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Notification</h1>
			</div>
		</div>
		<!--/.row-->


		<div id="errors">
			<ul id="error_list"></ul>
		</div>
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						<svg class="glyph stroked male user">
							<use xlink:href="#stroked-male-user"></use></svg>
					</div>
					<div class="panel-body">
						<table id="notificationTable" data-toggle="table"
							data-url="static/tables/notification.json" data-show-refresh="true"
							data-show-toggle="true" data-show-columns="true"
							data-search="true" data-select-item-name="toolbar1"
							data-pagination="true" data-sort-name="name"
							data-sort-order="desc">
							<thead>
								<tr>
									<th data-field="checked" data-checkbox="true"></th>
									<th data-field="id" data-sortable="true" data-visible="false">ID</th>
									<th data-field="notify" data-sortable="true">Notify</th>
									<th data-field="message" data-sortable="true">Message</th>
									<th data-field="user_id" data-sortable="true" data-visible="false">ID</th>
									<th data-field="drama_id" data-sortable="true" data-visible="false">ID</th>
									<th data-field="realname" data-sortable="true">Real Name</th>
									<th data-field="dramaTitle" data-sortable="true">Drama Title</th>
								</tr>
							</thead>
						</table> 
						<div class="action_buttons">
							<hr>
							<button id="notificationadd" type="submit"
								class="btn btn-primary pull-right rbtnMargin">Add</button>
							<button id="notificationdelete" type="submit"
								class="btn btn-danger pull-right rbtnMargin">Delete</button>
							<button id="notificationedit" type="submit"
								class="btn btn-warning pull-right rbtnMargin">Edit</button>
							<button id="notificationcancel" type="reset"
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

<!-- modal form for edit user -->
<div id="dialog-form" title="Edit User">
	<p class="validateTips"></p>

	<form id="editUser" action="/edituser" method="post">
		<div id="editform" class="col-sm-12 panel panel-default"></div>
	</form>
</div>

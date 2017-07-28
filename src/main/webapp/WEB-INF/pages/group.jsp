<!-- Group CRUD -->
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	isELIgnored="false"%>
<div style="overflow: scroll;">

	<!-- Breadcrum  -->
	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
		<div class="row">
			<ol class="breadcrumb">
				<li><a href="#"><svg class="glyph social myspace">
							<use xlink:href="#social-myspace" /></svg>
						<li class="active">Group</li>
			</ol>
		</div>
		<!--/.row-->

		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Group</h1>

			</div>
		</div>

		<!-- Panel with Add Group -->
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">

					<div class="panel-heading">
						<svg class="glyph social myspace">
					<use xlink:href="#social-myspace" /></svg>

						<button
							class="w3-button w3-medium w3-blue w3-padding-small w3-round w3-right">Add
							New Group</button>

					</div>
					<div class="panel-body">

						<!--  Card Rows 1 -->
						<div class="row w3-row">
							<!-- row1 -->
							<div class="w3-margin w3-col s3 w3-card">
								<header class="w3-container w3-light-grey w3-padding w3-center">
									<span class="w3-margin-left w3-text-grey"
										style="font-size: 150%">Kalrav</span> <i
										class="material-icons close w3-right">close</i>
								</header>
								<div class="subgroup w3-container w3-padding w3-center">
									<div>
										<span
											class="subgroup_name w3-padding w3-margin-left w3-text-dark-grey">COUPLE</span>
										<i class="material-icons subgroup_add w3-text-blue w3-right">add</i>
										<i class="material-icons subgroup_remove w3-text-red w3-right">remove</i>
										<hr class="line_break">
									</div>
								</div>
								<div class="card_button w3-container w3-padding">
									<button
										class="w3-button w3-green w3-round  w3-margin-left w3-right">Save</button>
									<button class="w3-button w3-round w3-right ">Cancel</button>
								</div>
							</div>
						</div>
						
						<!--  Card Rows 2 -->
						<div class="row w3-row">
							<!-- row1 -->
							<div class="w3-margin w3-col s3 w3-card">
								<header class="w3-container w3-light-grey w3-padding w3-center">
									<span class="w3-margin-left w3-text-grey"
										style="font-size: 150%">Ghogha</span> <i
										class="material-icons close w3-right">close</i>
								</header>
								<div class="subgroup w3-container w3-padding w3-center">
									<div>
										<span
											class="subgroup_name w3-padding w3-margin-left w3-text-dark-grey">COUPLE</span>
										<i class="material-icons subgroup_add w3-text-blue w3-right">add</i>
										<i class="material-icons subgroup_remove w3-text-red w3-right">remove</i>
										<hr class="line_break">
									</div>
								</div>
								<div class="card_button w3-container w3-padding">
									<button
										class="w3-button w3-green w3-round  w3-margin-left w3-right">Save</button>
									<button class="w3-button w3-round w3-right ">Cancel</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- End of Panel -->
</div>
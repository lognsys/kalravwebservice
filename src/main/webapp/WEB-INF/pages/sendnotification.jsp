<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div style="overflow: scroll;">
	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
		<div class="row">
			<ol class="breadcrumb">
				<li><a href="#"><svg class="glyph stroked home">
							<use xlink:href="#stroked-home"></use></svg></a></li>
				<li class="active">Notify</li>
			</ol>
		</div>
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Notify</h1>
			</div>
		</div>
		<div class="panel panel-default">
			<form:form method="POST" action="sendnotification" modelAttribute="notifications">
				<div class="panel-body" cssClass="error">
					<form:errors path="notify" element="div" />
					<form:errors path="message" element="div" />
									</div>
				<div class="col-sm-12 panel panel-default">
					<fieldset>
						<div id="notify_user" class="col-sm-12 panel panel-default">


							<div class="form-group">
									<label>Enter Message to  Notify</label>
									<form:textarea id="message" type="text"
										placeholder="Enter Message Here.." 
										rows="2"
										class="form-control text ui-widget-content ui-corner-all"
										path="message"></form:textarea>

							</div>
							<div class="row">
								<div class="col-sm-4 form-group">
									<label>Are You Sure you want to  notify?</label>
									<form:select path="notify"  id="notify" type="text"
										class="form-control text ui-widget-content ui-corner-all">
										<option value="True">True</option>
										<option value="False">False</option>
								</form:select>
								</div>
							</div>
							 <div class="row">
							 <div class="col-sm-4  form-group">
								<label>Notify By Drama</label>
								<form:select path="dramaId">
									<option value="0">--- Select ---</option>
									<form:options items="${dramasList}"></form:options>
								</form:select>
								</div>
								<div class="col-sm-4 ">
								<label>Notify To User</label>
								<form:select path="userId">
									<option value="0">--- Select ---</option>
									<form:options items="${usersList}"></form:options>
								</form:select>
								</div>
							</div>
							<%-- <div class="form-group">
								<label>Groups</label>
								<form:select path="group">
									<option value="NONE">--- Select ---</option>
									<form:options items="${groupsList}"></form:options>
								</form:select>
							</div>
							<div class="form-group">
								<label>Roles</label>
								<form:select path="role">
									<option value="NONE">--- Select ---</option>
									<form:options items="${rolesList}"></form:options>
								</form:select>
							</div> --%>

							<form:button  type="submit"
								class="btn btn-lg btn-info">Submit</form:button>
						</div>
					</fieldset>

				</div>
			</form:form>
		</div>
	</div>
</div>
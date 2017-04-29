
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
					 <div class="form-group">
                <label for="dtp_input1" class="col-md-2 control-label">DateTime Picking</label>
                <div class="input-group date form_datetime col-md-5" data-date="1979-09-16T05:25:07Z" data-date-format="dd MM yyyy - HH:ii p" data-link-field="dtp_input1">
                    <input class="form-control"  type="text" value="" readonly>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
					<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                </div>
				<input type="hidden" id="dtp_input1" value="" name="date" /><br/>
            <!-- <script type="text/javascript">
				  $('.form_datetime').datetimepicker({
					    //language:  'fr',
					    weekStart: 1,
					    todayBtn:  1,
						autoclose: 1,
						todayHighlight: 1,
						startView: 2,
						forceParse: 0,
					    showMeridian: 1
					});
				</script> -->
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
/*
 * kalrav.js
 * @author : 01/04/17 - PJD
 */
$(document)
    .ready(
        function() {
            // Add background image to login page
            $(".container_login")
                .closest('div')
                .closest('body')
                .css({
                    "background": 'url("static/images/stage_backdrop.jpg") no-repeat center fixed',
                    "-webkit-background-size": "cover",
                    "-moz-background-size": "cover",
                    "-o-background-size": "cover",
                    "background-size": "cover",
                    "overflow": "auto"
                });

            /********** start user table ***********/
            // user tables list
            var checkedRows = [];

            // check individual row
            $('#eventsTable').on('check.bs.table', function(e, row) {
                checkedRows.push({
                    id: row.id,
                    email: row.email
                });
                console.log(JSON.stringify(checkedRows));
            });

            // uncheck individual row
            $('#eventsTable').on('uncheck.bs.table', function(e, row) {

                $.each(checkedRows, function(index, value) {
                    if (value.id === row.id) {
                        checkedRows.splice(index, 1);
                    }
                });
                console.log(JSON.stringify(checkedRows));
            });

            // remove all checked rows
            $('#eventsTable').on('uncheck-all.bs.table', function(e) {
                checkedRows.splice(0, checkedRows.length);
            });

            // check all rows
            $('#eventsTable').on('check-all.bs.table', function(e) {
                $("#eventsTable tr:has(:checkbox:checked) td:nth-child(3)").each(function() {
                    checkedRows.push($(this).text());
                });
            });

            // toggle button to disable add/edit button for multiple
            // checkbox select
            $('#eventsTable tr').find('input:checkbox:first').change(
                function() {
                    // this will contain a reference to the checkbox
                    if (this.checked) {
                        $('#useradd').prop('disabled', true);
                        $('#useredit').prop('disabled', true);
                    } else {
                        $('#useradd').prop('disabled', false);
                        $('#useredit').prop('disabled', false);
                    }
                });

            // Userlist delete function
            $('#userdelete').click(
                function(event) {

                    if ($('#eventsTable tr:has(:checkbox:checked)').length == 0) {
                        $('<a href="#" class="close" data-dismiss="alert">' + 'Please select one or more user</a>').appendTo('#eventsResult').addClass('alert alert-danger fade in').attr('data-dismiss="alert"');
                    } else {

                        var params = {
                            "userIds": JSON.stringify(checkedRows),
                            "userAction": "delete"
                        }
                        $.ajax({
                            url: '/userlist',
                            type: "POST",
                            data: params,
                            success: function(data) {
                            	 window.location.href = "http://localhost:8080/userlist"
                            }
                        });

                        event.preventDefault();
                    }
                });



            // Userlist add function
            $('#useradd').click(
                function(event) {
                    window.location.href = "http://localhost:8080/register";
                    event.preventDefault();
                });

            // Userlist edit function
            $('#useredit').click(
                function(event) {

                    if ($('#eventsTable tr:has(:checkbox:checked)').length != 1) {
                        $('<a href="#" class="close" data-dismiss="alert">' + 'Please select single user for edit</a>').appendTo('#eventsResult').addClass('alert alert-danger fade in').attr('data-dismiss="alert"');
                    } else {
                    	console.log(JSON.stringify(checkedRows));
                        var params = {
                            "userIds": JSON.stringify(checkedRows),
                            "userAction": "edit"
                        }
                        $.ajax({
                            url: '/userlist',
                            type: "POST",
                            data: params,
                            success: function(data) {
                            	console.log(data);
                            }
                        });
                    }
                    event.preventDefault();
                });

            // Userlist delete function
            $('#usercancel').click(
                function(event) {
                    window.location.href = "http://localhost:8080/dashboard";
                    event.preventDefault();
             });
            
            
            
            

  });
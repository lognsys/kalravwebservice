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

            /**********  userlist table function ***********/
            // userlist tables 
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
                        var regUser = "";
                        $.ajax({
                            url: '/userlist',
                            type: "POST",
                            data: params,

                            success: function(data) {

                                //get subelements from div('#register_user')
                                regUserElements = $(data).find("#register_user").html();

                                //clear previous added html elements
                                $('#editform').empty();

                                //append html subelements
                                $(regUserElements).appendTo("#editform");

                                /*** edit user dialog form *****/
                                var dialog, form;

//                                // From http://www.whatwg.org/specs/web-apps/current-work/multipage/states-of-the-type-attribute.html#e-mail-state-%28type=email%29
//                                emailRegex = /^[a-zA-Z0-9.!#$%&'*+\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/,
//                                    fname = $("#firstname"),
//                                    lname = $("#lastname"),
//                                    username = $("#username"),
//                                    address = $("#address"),
//                                    city = $("#city"),
//                                    state = $("#state"),
//                                    zipcode = $("#zipcode"),
//                                    company_name = $("#company_name"),
//                                    phone = $("#phone"),
//                                    allFields = $([]).add(fname).add(lname).add(username).add(address).
//                                add(city).add(state).add(zipcode).add(company_name).add(phone),
//                                    tips = $(".validateTips");
//
//                                function updateTips(t) {
//                                    tips
//                                        .text(t)
//                                        .addClass("ui-state-highlight");
//                                    setTimeout(function() {
//                                        tips.removeClass("ui-state-highlight", 1500);
//                                    }, 500);
//                                }
//
//                                function checkLength(o, n, min, max) {
//                                    if (o.val().length > max || o.val().length < min) {
//                                        o.addClass("ui-state-error");
//                                        updateTips("Length of " + n + " must be between " +
//                                            min + " and " + max + ".");
//                                        return false;
//                                    } else {
//                                        return true;
//                                    }
//                                }
//
//                                function checkRegexp(o, regexp, n) {
//                                    if (!(regexp.test(o.val()))) {
//                                        o.addClass("ui-state-error");
//                                        updateTips(n);
//                                        return false;
//                                    } else {
//                                        return true;
//                                    }
//                                }
//
//                                function editUser() {
//                                    var valid = true;
//                                    allFields.removeClass("ui-state-error");
//
//                                    valid = valid && checkLength(fname, "firstname", 2, 80);
//                                    valid = valid && checkLength(lname, "lastname", 3, 80);
//                                    valid = valid && checkLength(address, "address", 3, 80);
//                                    valid = valid && checkLength(city, "city", 3, 80);
//                                    valid = valid && checkLength(state, "state", 3, 80);
//                                    valid = valid && checkLength(zipcode, "zipcode", 5, 6);
//                                    valid = valid && checkLength(company_name, "company", 3, 80);
//                                    valid = valid && checkLength(phone, "phone", 8, 10);
//
//                                    //valid = valid && checkLength( password, "password", 5, 16 );
//
//                                    //valid = valid && checkRegexp( name, /^[a-z]([0-9a-z_\s])+$/i, "Username may consist of a-z, 0-9, underscores, spaces and must begin with a letter." );
//                                  //  valid = valid && checkRegexp(username, emailRegex, "eg. pdoshi@yahoo.com");
//                                    //valid = valid && checkRegexp( password, /^([0-9a-zA-Z])+$/, "Password field only allow : a-z 0-9" );
//
//                                    return valid;
//                                }


                                dialog = $("#dialog-form").dialog({
                                    autoOpen: false,
                                    resizable: true,
                                    height: 525,
                                    width: 600,
                                    modal: true,
                                    
                                    position: {
                                        my: "center",
                                        at: "top+320",
                                        of: "body"
                                    },
                                    buttons: {
                                        Close: function() {
                                            dialog.dialog("close");
                                        }
                                    },
//                                    close: function() {
//                                  //      form[0].reset();
//                                        allFields.removeClass("ui-state-error");
//                                    }
                                });

                                //default open dialog on ajax success
                                dialog.dialog("open");

//                                form = dialog.find("form").on("submit", function(event) {
//                                    event.preventDefault();
//                                    editUser();
//                                });

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

        });
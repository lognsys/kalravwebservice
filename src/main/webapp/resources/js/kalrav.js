/*
 * kalrav.js
 * @author : 01/04/17 - PJD
 * 
 * Description : 
 * This contains all the jquery code used for CRUD of Users module , 
 * Group module , Drama module.
 * 
 * 
 * CHANGELOG : PJD - 25/07/17 Created Group Module
 * 
 * Notes: 
 * 1. JQuery delegate function is used for dynamically generated html
 * 2. Bootstrap 
 * 
 * 
 * GROUP MODULE TEST CASES:
 * 1)  DELETE ALL SUBGROUPS OF SAME GROUP
 * 2)  DELETE GROUP WITH SUBGROUPS 
 * 3)  ADD SUBGROUPS
 * 4)  ADD & DELETE ALL SUBGROUP OF SAME GROUP
 * 5)  ADD GROUP IN SAME ROW IF GROUP CARDS < 3 OR ADD GROUP CARD TO A NEW ROW
 * 6)  ADD FIRST GROUP WITH BLANK SUBGROUP
 * 8)  DELETE LAST SUBGROUP THEN SHOW "ADD SUBGROUP BUTTON"
 * 9)  CHECK FOR DUPLICATE GROUP AND THROW DIALOG "GROUP EXISTS"
 * 10) CHECK FOR DUPLICATE SUBGROUP OF SAME GROUP AND THROW DIALOG "SUBGROUP EXISTS"
 * 11) SAVE CHANGES OF CURRENT GROUP AND SUBGROUPS
 * 13) CANCEL BUTTON  RETURNS SUBGROUPS OF DESIGNATED GROUP IN ORIGINAL FORM
 * 14) DIALOG BOX ON WORKING ON DIFFERENT GROUP AND NOT SAVING CHANGES OF PREVIOUS GROUP 
 * 15) DELETE GROUP AND REMOVE IF LAST GROUP
 * 16) CANCEL BUTTON WORKS ON CURRENT GROUP OR THROWS ERROR
 * 
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

            /*********************************** userlist table function ***********************************/
            // userlist tables 
            var checkedRows = [];

            // check individual row
            $('#userTable').on('check.bs.table', function(e, row) {
                checkedRows.push({
                    id: row.id,
                    email: row.email
                });
                console.log(JSON.stringify(checkedRows));
            });

            // uncheck individual row
            $('#userTable').on('uncheck.bs.table', function(e, row) {

                $.each(checkedRows, function(index, value) {
                    if (value.id === row.id) {
                        checkedRows.splice(index, 1);
                    }
                });
                console.log(JSON.stringify(checkedRows));
            });

            // remove all checked rows
            $('#userTable').on('uncheck-all.bs.table', function(e) {
                checkedRows.splice(0, checkedRows.length);
                console.log(JSON.stringify(checkedRows));
            });

            // check all rows
            $('#userTable').on('check-all.bs.table', function(e) {
                //Assumption if one or multiple row is checked
                checkedRows.splice(0, checkedRows.length);
                $("#userTable tr:has(:checkbox:checked) td:nth-child(3)").each(function() {
                    checkedRows.push({
                        email: $(this).text()
                    });
                });
                console.log(JSON.stringify(checkedRows));
            });

            // toggle button to disable add/edit button for multiple
            // checkbox select
            $('#userTable tr').find('input:checkbox:first').change(
                function() {

                    // this will contain a reference to the checkbox
                    if (this.checked) {
                        $('#useradd').prop('disabled', true);
                        $('#useredit').prop('disabled', true);

                        // monika 4/5/2017 add   drama add & edit buttons disable 
                        $('#dramaadd').prop('disabled', true);
                        $('#dramaedit').prop('disabled', true);

                    } else {
                        $('#useradd').prop('disabled', false);
                        $('#useredit').prop('disabled', false);

                        //monika 4/5/2017 add   drama add & edit buttons disable 
                        $('#dramaadd').prop('disabled', false);
                        $('#dramaedit').prop('disabled', false);
                    }


                });

            // check if more than one checkbox checked
            if ($('#userTable tr:has(:checkbox:checked)').length > 1) {
                $('#useradd').prop('disabled', true);
                $('#useredit').prop('disabled', true);
            } else {
                $('#useradd').prop('disabled', false);
                $('#useredit').prop('disabled', false);
            }



            // Userlist delete function
            $('#userdelete').click(
                function(event) {

                    if ($('#userTable tr:has(:checkbox:checked)').length == 0) {
                        $('<li class="alert alert-danger alert-dismissable"><a href="#" class="close" data-dismiss="alert" aria-label="close">×</a>' + '<strong>Error</strong> Please select one or more user from the list...</a></li>').appendTo('#error_list');
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

            // Userlist add function will call /register page
            $('#useradd').click(
                function(event) {
                    window.location.href = "http://localhost:8080/register";
                    event.preventDefault();
                });


            // Userlist edit function
            $('#useredit').click(
                function(event) {

                    if ($('#userTable tr:has(:checkbox:checked)').length == 0) {
                        $('<li class="alert alert-danger alert-dismissable"><a href="#" class="close" data-dismiss="alert" aria-label="close">×</a>' + '<strong>Error</strong> Please select a user from the list...</a></li>').appendTo('#error_list');
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

                                //get subelements from div('#register_user') from register.jsp
                                regUserElements = $(data).find("#register_user").html();

                                //clear previous added html elements
                                $('#editform').empty();

                                //append html subelements
                                $(regUserElements).appendTo("#editform");

                                //mark username field as read-only
                                $("#username").prop("readonly", true);

                                dialog = $("#dialog-form").dialog({
                                    autoOpen: false,
                                    resizable: false,
                                    height: 525,
                                    width: 600,
                                    modal: true,

                                    position: {
                                        my: "center+50",
                                        at: "center+50",
                                        of: "body"


                                    },
                                    close: function() {
                                        form[0].reset();
                                        allFields.removeClass("ui-state-error");
                                    }
                                });

                                //default open dialog on ajax success
                                dialog.dialog("open");

                                form = dialog.find("form").on("submit", function(event) {

                                    var isValid = editUser();
                                    if (isValid) {
                                        console.log("VALID - " + isValid);
                                        return;
                                    }
                                    event.preventDefault();

                                });

                                /*** edit user dialog form validation *****/
                                var dialog, form;

                                // From http://www.whatwg.org/specs/web-apps/current-work/multipage/states-of-the-type-attribute.html#e-mail-state-%28type=email%29
                                emailRegex = /^[a-zA-Z0-9.!#$%&'*+\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/,
                                    fname = $("#firstname"),
                                    lname = $("#lastname"),
                                    // username = $("#username"),
                                    address = $("#address"),
                                    city = $("#city"),
                                    state = $("#state"),
                                    zipcode = $("#zipcode"),
                                    company_name = $("#company_name"),
                                    phone = $("#phone"),
                                    allFields = $([]).add(fname).add(lname).add(address).
                                add(city).add(state).add(zipcode).add(company_name).add(phone),
                                    tips = $(".validateTips");

                                function updateTips(t) {
                                    tips
                                        .text(t)
                                        .addClass("ui-state-highlight");
                                    setTimeout(function() {
                                        tips.removeClass("ui-state-highlight", 1500);
                                    }, 500);
                                }

                                function checkLength(o, n, min, max) {
                                    if (o.val().length > max || o.val().length < min) {
                                        o.addClass("ui-state-error");
                                        updateTips("Length of " + n + " must be between " +
                                            min + " and " + max + ".");
                                        return false;
                                    } else {
                                        return true;
                                    }
                                }

                                function checkRegexp(o, regexp, n) {
                                    if (!(regexp.test(o.val()))) {
                                        o.addClass("ui-state-error");
                                        updateTips(n);
                                        return false;
                                    } else {
                                        return true;
                                    }
                                }

                                function editUser() {
                                    var valid = true;
                                    allFields.removeClass("ui-state-error");

                                    valid = valid && checkLength(fname, "firstname", 3, 80);
                                    valid = valid && checkLength(lname, "lastname", 3, 80);
                                    valid = valid && checkLength(address, "address", 3, 80);
                                    valid = valid && checkLength(city, "city", 3, 80);
                                    valid = valid && checkLength(state, "state", 3, 80);
                                    valid = valid && checkLength(zipcode, "zipcode", 6, 6);
                                    valid = valid && checkLength(company_name, "company", 3, 80);
                                    valid = valid && checkLength(phone, "phone", 10, 10);
                                    valid = valid && checkRegexp(username, emailRegex, "eg. xyz@abc.com");

                                    //valid = valid && checkLength( password, "password", 5, 16 );
                                    //valid = valid && checkRegexp( name, /^[a-z]([0-9a-z_\s])+$/i, "Username may consist of a-z, 0-9, underscores, spaces and must begin with a letter." );
                                    //valid = valid && checkRegexp( password, /^([0-9a-zA-Z])+$/, "Password field only allow : a-z 0-9" );
                                    return valid;
                                }


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



            /*********************************** Drama table function ***********************************/




            /*
             * DRAMA DETAILS  STARTS 
             */
            $('.form_datetime').datetimepicker({
                /* //language:  'fr',
                 weekStart: 1,
                 todayBtn: 1,
                 autoclose: 1,
                 todayHighlight: 1,
                 startView: 2,
                 forceParse: 0,
                 showMeridian: 1*/

                autoclose: 1,
                todayBtn: 1,
                format: "yyyy-mm-dd  hh:ii"
            });

            //IMAGE PREVIEW
            function handleFileSelect(evt) {
                var files = evt.target.files; // FileList object

                // Loop through the FileList and render image files as thumbnails.
                for (var i = 0, f; f = files[i]; i++) {

                    // Only process image files.
                    if (!f.type.match('image.*')) {
                        continue;
                    }

                    var reader = new FileReader();

                    // Closure to capture the file information.
                    reader.onload = (function(theFile) {
                        return function(e) {
                            // Render thumbnail.
                            var span = document.createElement('span');
                            span.innerHTML = ['<img class="thumb" style="width:304px;height:228px;" src="', e.target.result,
                                '" title="', escape(theFile.name), '"/>'
                            ].join('');
                            document.getElementById('list').insertBefore(span, null);
                        };
                    })(f);

                    // Read in the image file as a data URL.
                    reader.readAsDataURL(f);
                }
            }
            var el = document.getElementById('files');
            if (el) {
                el.addEventListener('change', handleFileSelect, false);
            }


            /*
             * DRAMA LIST STARTS HERE
             */

            // ADD function
            $('#dramaadd').click(
                function(event) {
                    console.log("Event === " + event);
                    window.location.href = "http://localhost:8080/dramadetail";
                    event.preventDefault();
                });

            //DELETE FUNCTION
            $('#dramadelete').click(
                function(event) {

                    if ($('#dramaTable tr:has(:checkbox:checked)').length == 0) {
                        $('<a href="#" class="close" data-dismiss="alert">' + 'Please select one or more user</a>').appendTo('#error_list').addClass('alert alert-danger fade in').attr('data-dismiss="alert"');
                    } else {

                        var params = {
                            "dramaIds": JSON.stringify(checkedRows),
                            "dramaAction": "delete"
                        }
                        $.ajax({
                            url: '/dramalist',
                            type: "POST",
                            data: params,
                            success: function(data) {
                                window.location.href = "http://localhost:8080/dramalist"
                            }
                        });

                        event.preventDefault();
                    }
                });

            //EDIT FUNCTION

            // Userlist edit function
            $('#dramaedit').click(
                function(event) {

                    if ($('#dramaTable tr:has(:checkbox:checked)').length != 1) {
                        $('<a href="#" class="close" data-dismiss="alert">' + 'Please select single user for edit</a>').appendTo('#eventsResult').addClass('alert alert-danger fade in').attr('data-dismiss="alert"');
                    } else {
                        console.log(JSON.stringify(checkedRows));
                        var params = {
                            "dramaIds": JSON.stringify(checkedRows),
                            "dramaAction": "edit"
                        }
                        var regUser = "";
                        $.ajax({
                            url: '/dramalist',
                            type: "POST",
                            data: params,

                            success: function(data) {

                                //get subelements from div('#register_user')
                                regUserElements = $(data).find("#dramadetail_dramas").html();

                                //clear previous added html elements
                                $('#editform').empty();

                                //append html subelements
                                $(regUserElements).appendTo("#editform");



                                dialog = $("#dialog-form").dialog({
                                    autoOpen: false,
                                    resizable: false,
                                    height: 525,
                                    width: 600,
                                    modal: true,

                                    position: {
                                        my: "right",
                                        at: "top+320 left+400",
                                        of: "body"
                                    },
                                    close: function() {
                                        form[0].reset();
                                        allFields.removeClass("ui-state-error");
                                    }
                                });

                                //default open dialog on ajax success
                                dialog.dialog("open");

                                form = dialog.find("form").on("submit", function(event) {

                                    var isValid = editUser();
                                    if (isValid) {
                                        console.log("VALID - " + isValid);
                                        return;
                                    }
                                    event.preventDefault();



                                });

                                /*** edit user dialog form validation *****/
                                var dialog, form;

                                // From http://www.whatwg.org/specs/web-apps/current-work/multipage/states-of-the-type-attribute.html#e-mail-state-%28type=email%29
                                emailRegex = /^[a-zA-Z0-9.!#$%&'*+\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/,
                                    title = $("#title"),
                                    /*lname = $("#lastname"),
                                    username = $("#username"),
                                    address = $("#address"),
                                    city = $("#city"),
                                    state = $("#state"),
                                    zipcode = $("#zipcode"),
                                    company_name = $("#company_name"),
                                    phone = $("#phone"),*/
                                    allFields = $([]).add(title),
                                    tips = $(".validateTips");

                                function updateTips(t) {
                                    tips
                                        .text(t)
                                        .addClass("ui-state-highlight");
                                    setTimeout(function() {
                                        tips.removeClass("ui-state-highlight", 1500);
                                    }, 500);
                                }

                                function checkLength(o, n, min, max) {
                                    if (o.val().length > max || o.val().length < min) {
                                        o.addClass("ui-state-error");
                                        updateTips("Length of " + n + " must be between " +
                                            min + " and " + max + ".");
                                        return false;
                                    } else {
                                        return true;
                                    }
                                }

                                function checkRegexp(o, regexp, n) {
                                    if (!(regexp.test(o.val()))) {
                                        o.addClass("ui-state-error");
                                        updateTips(n);
                                        return false;
                                    } else {
                                        return true;
                                    }
                                }

                                function editUser() {
                                    var valid = true;
                                    allFields.removeClass("ui-state-error");

                                    valid = valid && checkLength(title, "title", 3, 80);
                                    return valid;
                                }


                            }

                        });
                    }
                    event.preventDefault();
                });
            // ends edit



            // Userlist delete function
            $('#dramacancel').click(
                function(event) {
                    window.location.href = "http://localhost:8080/dashboard";
                    event.preventDefault();
                });

            // groupdetail to add subgroup on click of plus
            $('#myTable').on('click', 'input[type="button"]', function() {
                $(this).closest('tr').remove();
            })
            $('p input[type="button"]').click(function() {
                $('#myTable').append('<tr><td><input type="text" class="fname" /></td><td><input type="button" class="buttonDelete" " /></td></tr>')
            });



            // groupdetail edit function
            $('#edit_button').click(
                function(event) {

                    if ($('#userTable tr:has(:checkbox:checked)').length == 0) {
                        $('<li class="alert alert-danger alert-dismissable"><a href="#" class="close" data-dismiss="alert" aria-label="close">×</a>' + '<strong>Error</strong> Please select a user from the list...</a></li>').appendTo('#error_list');
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

                                dialog = $("#dialog-form").dialog({
                                    autoOpen: false,
                                    resizable: false,
                                    height: 525,
                                    width: 600,
                                    modal: true,

                                    position: {
                                        my: "center+50",
                                        at: "center+50",
                                        of: "body"


                                    },
                                    close: function() {
                                        form[0].reset();
                                        allFields.removeClass("ui-state-error");
                                    }
                                });

                                //default open dialog on ajax success
                                dialog.dialog("open");

                                form = dialog.find("form").on("submit", function(event) {

                                    var isValid = editUser();
                                    if (isValid) {
                                        console.log("VALID - " + isValid);
                                        return;
                                    }
                                    event.preventDefault();



                                });

                                /*** edit user dialog form validation *****/
                                var dialog, form;

                                // From http://www.whatwg.org/specs/web-apps/current-work/multipage/states-of-the-type-attribute.html#e-mail-state-%28type=email%29
                                emailRegex = /^[a-zA-Z0-9.!#$%&'*+\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/,
                                    fname = $("#firstname"),
                                    lname = $("#lastname"),
                                    username = $("#username"),
                                    address = $("#address"),
                                    city = $("#city"),
                                    state = $("#state"),
                                    zipcode = $("#zipcode"),
                                    company_name = $("#company_name"),
                                    phone = $("#phone"),
                                    allFields = $([]).add(fname).add(lname).add(username).add(address).
                                add(city).add(state).add(zipcode).add(company_name).add(phone),
                                    tips = $(".validateTips");

                                function updateTips(t) {
                                    tips
                                        .text(t)
                                        .addClass("ui-state-highlight");
                                    setTimeout(function() {
                                        tips.removeClass("ui-state-highlight", 1500);
                                    }, 500);
                                }

                                function checkLength(o, n, min, max) {
                                    if (o.val().length > max || o.val().length < min) {
                                        o.addClass("ui-state-error");
                                        updateTips("Length of " + n + " must be between " +
                                            min + " and " + max + ".");
                                        return false;
                                    } else {
                                        return true;
                                    }
                                }

                                function checkRegexp(o, regexp, n) {
                                    if (!(regexp.test(o.val()))) {
                                        o.addClass("ui-state-error");
                                        updateTips(n);
                                        return false;
                                    } else {
                                        return true;
                                    }
                                }

                                function editUser() {
                                    var valid = true;
                                    allFields.removeClass("ui-state-error");

                                    valid = valid && checkLength(fname, "firstname", 3, 80);
                                    valid = valid && checkLength(lname, "lastname", 3, 80);
                                    valid = valid && checkLength(address, "address", 3, 80);
                                    valid = valid && checkLength(city, "city", 3, 80);
                                    valid = valid && checkLength(state, "state", 3, 80);
                                    valid = valid && checkLength(zipcode, "zipcode", 6, 6);
                                    valid = valid && checkLength(company_name, "company", 3, 80);
                                    valid = valid && checkLength(phone, "phone", 10, 10);
                                    valid = valid && checkRegexp(username, emailRegex, "eg. pdoshi@yahoo.com");

                                    //valid = valid && checkLength( password, "password", 5, 16 );
                                    //valid = valid && checkRegexp( name, /^[a-zA-Z]([a-zA-Z_\s])+$/i, "Username may consist of a-z, 0-9, underscores, spaces and must begin with a letter." );
                                    valid = valid && checkRegexp(fname, /^[a-zA-Z]([a-zA-Z_\s])+$/i, "First Name may consist of Upper Case, Lower Case, underscores, spaces and must begin with a letter.");
                                    valid = valid && checkRegexp(lname, /^[a-zA-Z]([a-zA-Z_\s])+$/i, "Last Name may consist of Upper Case, Lower Case, underscores, spaces and must begin with a letter.");
                                    valid = valid && checkRegexp(address, /^[a-zA-Z]([a-zA-Z_\s])+$/i, "Address Name may consist of Upper Case, Lower Case, underscores, spaces and must begin with a letter.");
                                    valid = valid && checkRegexp(city, /^[a-zA-Z]([a-zA-Z_\s])+$/i, "City may consist of Upper Case, Lower Case, underscores, spaces and must begin with a letter.");
                                    valid = valid && checkRegexp(state, /^[a-zA-Z]([a-zA-Z_\s])+$/i, "State may consist of Upper Case, Lower Case, underscores, spaces and must begin with a letter.");
                                    valid = valid && checkRegexp(phone, /^[\+]?[(]?[0-9]{3}[)]?[-\s\.]?[0-9]{3}[-\s\.]?[0-9]{4,6}$/im, "Phone number must be in format eg: 123-456-7890, 1234567890");
                                    //valid = valid && checkRegexp( password, /^([0-9a-zA-Z])+$/, "Password field only allow : a-z 0-9" );
                                    return valid;
                                }
                            }

                        });
                    }
                    event.preventDefault();
                });


            //            GROUP LIST
            $('#add_button').click(
                function(event) {

                    var params = {
                        "groupAction": "add"
                    }
                    $.ajax({
                        url: '/groupdetails',
                        type: "POST",
                        data: params,
                        success: function(data) {
                            window.location.href = "http://localhost:8080/groupdetails"
                        }
                    });

                    event.preventDefault();

                });

            /*********************************** END OF DRAMA DETAILS ***********************************/

            //=====================================================NOTIFICATION LIST===========================================================================


            var checkedRows = [];

            // check individual row
            $('#notificationTable').on('check.bs.table', function(e, row) {
                checkedRows.push({
                    id: row.id,
                    message: row.message
                });
                console.log(JSON.stringify(checkedRows));
            });

            // uncheck individual row
            $('#notificationTable').on('uncheck.bs.table', function(e, row) {

                $.each(checkedRows, function(index, value) {
                    if (value.id === row.id) {
                        checkedRows.splice(index, 1);
                    }
                });
                console.log(JSON.stringify(checkedRows));
            });

            // remove all checked rows
            $('#notificationTable').on('uncheck-all.bs.table', function(e) {
                checkedRows.splice(0, checkedRows.length);
                console.log(JSON.stringify(checkedRows));
            });

            // check all rows
            $('#notificationTable').on('check-all.bs.table', function(e) {
                //Assumption if one or multiple row is checked
                checkedRows.splice(0, checkedRows.length);
                $("#notificationTable tr:has(:checkbox:checked) td:nth-child(3)").each(function() {
                    checkedRows.push({
                        message: $(this).text()
                    });
                });
                console.log(JSON.stringify(checkedRows));
            });

            // toggle button to disable add/edit button for multiple
            // checkbox select
            $('#notificationTable tr').find('input:checkbox:first').change(
                function() {

                    // this will contain a reference to the checkbox
                    if (this.checked) {
                        $('#notificationadd').prop('disabled', true);
                        $('#notificationedit').prop('disabled', true);

                    } else {
                        $('#notificationadd').prop('disabled', false);
                        $('#notificationedit').prop('disabled', false);

                    }


                });

            // check if more than one checkbox checked
            if ($('#notificationTable tr:has(:checkbox:checked)').length > 1) {
                $('#notificationTable').prop('disabled', true);
                $('#notificationedit').prop('disabled', true);
            } else {
                $('#notificationadd').prop('disabled', false);
                $('#notificationedit').prop('disabled', false);
            }



            // Userlist delete function
            $('#notificationdelete').click(
                function(event) {
                    console.log("VALID event- " + event);

                    console.log("VALID event - notificationTable " + ('#notificationTable tr:has(:checkbox:checked)').length == 0);

                    if ($('#notificationTable tr:has(:checkbox:checked)').length == 0) {

                        $('<li class="alert alert-danger alert-dismissable"><a href="#" class="close" data-dismiss="alert" aria-label="close">×</a>' + '<strong>Error</strong> Please select one or more user from the list...</a></li>').appendTo('#error_list');
                    } else {
                        console.log("VALID params- " + params);

                        var params = {
                            "notificationIds": JSON.stringify(checkedRows),
                            "notifyAction": "delete"
                        }
                        $.ajax({
                            url: '/notificationlist',
                            type: "POST",
                            data: params,
                            success: function(data) {
                                window.location.href = "http://localhost:8080/notificationlist"
                            }
                        });

                        event.preventDefault();
                    }
                });

            // notificationlist add function
            $('#notificationadd').click(
                function(event) {
                    window.location.href = "http://localhost:8080/sendnotification";
                    event.preventDefault();
                });


            // Notificationlist edit function
            $('#notificationedit').click(
                function(event) {

                    if ($('#notificationTable tr:has(:checkbox:checked)').length == 0) {
                        $('<li class="alert alert-danger alert-dismissable"><a href="#" class="close" data-dismiss="alert" aria-label="close">×</a>' + '<strong>Error</strong> Please select a user from the list...</a></li>').appendTo('#error_list');
                    } else {
                        console.log("notificationedit event- " + event);

                        var params = {
                            "notificationIds": JSON.stringify(checkedRows),
                            "notifyAction": "edit"
                        }
                        console.log("notificationedit params- " + JSON.stringify(params));

                        var regUser = "";
                        $.ajax({
                            url: '/notificationlist',
                            type: "POST",
                            data: params,

                            success: function(data) {

                                //get subelements from div('#notify_user') from sendnotification.jsp
                                regUserElements = $(data).find("#notify_user").html();

                                //clear previous added html elements
                                $('#editform').empty();

                                //append html subelements
                                $(regUserElements).appendTo("#editform");


                                dialog = $("#dialog-form").dialog({
                                    autoOpen: false,
                                    resizable: false,
                                    height: 525,
                                    width: 600,
                                    modal: true,

                                    position: {
                                        my: "center+50",
                                        at: "center+50",
                                        of: "body"


                                    },
                                    close: function() {
                                        form[0].reset();
                                        allFields.removeClass("ui-state-error");
                                    }
                                });

                                //default open dialog on ajax success
                                dialog.dialog("open");

                                form = dialog.find("form").on("submit", function(event) {

                                    var isValid = editUser();
                                    console.log("notificationedit isValid- " + isValid);

                                    if (isValid) {
                                        console.log("VALID - " + isValid);
                                        return;
                                    }
                                    event.preventDefault();

                                });

                                /*** edit user dialog form validation *****/
                                var dialog, form;

                                // From http://www.whatwg.org/specs/web-apps/current-work/multipage/states-of-the-type-attribute.html#e-mail-state-%28type=email%29
                                notify = $("#notify"),
                                    message = $("#message"),
                                    allFields = $([]).add(notify).add(message),
                                    tips = $(".validateTips");

                                function updateTips(t) {
                                    tips
                                        .text(t)
                                        .addClass("ui-state-highlight");
                                    setTimeout(function() {
                                        tips.removeClass("ui-state-highlight", 1500);
                                    }, 500);
                                }

                                function checkLength(o, n, min, max) {
                                    if (o.val().length > max || o.val().length < min) {
                                        o.addClass("ui-state-error");
                                        updateTips("Length of " + n + " must be between " +
                                            min + " and " + max + ".");
                                        return false;
                                    } else {
                                        return true;
                                    }
                                }

                                function checkRegexp(o, regexp, n) {
                                    if (!(regexp.test(o.val()))) {
                                        o.addClass("ui-state-error");
                                        updateTips(n);
                                        return false;
                                    } else {
                                        return true;
                                    }
                                }

                                function editUser() {
                                    var valid = true;
                                    allFields.removeClass("ui-state-error");
                                    valid = valid && checkLength(message, "message", 3, 80);
                                    return valid;
                                }


                            }

                        });
                    }
                    event.preventDefault();
                });




            // Userlist delete function
            $('#notificationcancel').click(
                function(event) {
                    window.location.href = "http://localhost:8080/dashboard";
                    event.preventDefault();
                });
            //=====================================================NOTIFICATION LIST===========================================================================


            /******************************************** GROUP.JSP MODULE **********************************************/

            // HTML TEMPLATES ////
            //add subgroup input field
            var tpl_add_subgroup_row = '<div><input class="subgroup_name" type="text" name="subgroupname" placeholder="Enter Sub-Group">' +
                '<i class="material-icons subgroup_add w3-text-blue w3-right">add</i>' +
                '<i class="material-icons subgroup_remove w3-text-red w3-right">remove</i>' +
                '<hr class="line_break"></div>';
            //subgroup button
            var tpl_add_button = '<button class="button_add_subgroup  w3-button w3-blue w3-round">Add Sub-Group</button>';
            //add row for col
            var tpl_add_group_row = '<div class="row w3-row w3-margin-left row_append_group"></div>';
            // group card 
            var tpl_add_groupcard = '<div class="card_group w3-margin w3-col s3 w3-card"><header class="w3-container w3-light-grey w3-padding w3-center"><span class="w3-margin-left w3-text-grey" style="font-size: 150%"></span><i class="material-icons group_delete close w3-right">close</i></header><div class="subgroup w3-container w3-padding w3-center"><button class="button_add_subgroup w3-button w3-blue w3-round">Add Sub-Group</button></div><div class="card_button w3-container w3-padding"><button class="button_card_cancel w3-button w3-round">Cancel</button><button class="button_card_save w3-margin-left w3-button w3-green w3-round w3-right" style="width:35%">Save</button></div></div>';

            var tpl_add_subgroup_row_span ='<div><span class="subgroup_name  w3-margin-left w3-text-dark-grey">placeholder_name</span>'+
            							   '<i class="material-icons subgroup_add w3-text-blue w3-right">add</i>'+
            							   '<i class="material-icons subgroup_remove w3-text-red w3-right">remove</i>'+
            							   '<hr class="line_break"></div>';
            
            //GLOBAL OBJECTS DECLARATION
            var addSubgroup = [];
            var removeSubgroup = [];
            var groupObj = {};
            var del_obj = {};
            var add_obj = {};
            var init_subgroups = [];
            var isSubgroupPopulated = false;


            //CONSTANTS
            var key_delete = "remove";
            var key_add = "add";
            var const_group = "group";
            var const_subgroup = "subgroup";
            var countSubgroup = 0;
            
            var show_subgroup=[];
            var hide_subgroup=[];


            // HOVER CHANGE COLOR ADD/DELETE SUBGROUP BUTTON  
            $(document).delegate('.subgroup_add', 'mouseover mouseout', function(event) {
                if (event.type === 'mouseover') {
                    $(this).addClass('w3-text-light-grey').removeClass('w3-text-blue');
                } else {
                    $(this).addClass('w3-text-blue').removeClass('w3-text-light-grey');
                }
            });

            $(document).delegate('.subgroup_remove', 'mouseover mouseout', function(event) {
                if (event.type === 'mouseover') {
                    $(this).addClass('w3-text-light-grey').removeClass('w3-text-red');
                } else {
                    $(this).addClass('w3-text-red').removeClass('w3-text-light-grey');
                }
            });
            // END OF CARD HOVER ADD/REMOVE BUTTON


            //START OF EDIT INPLACE FROM SPAN TO INPUT
            var switchToInput = function() {
                var $input = $("<input>", {
                    val: $(this).text(),
                    placeholder: (!$(this).text().trim()) ? "Enter Sub-Group" : $(this).text(),
                    type: "text"
                });
                $input.addClass("subgroup_name");
                $(this).replaceWith($input);
                $input.on("blur", switchToSpan);
                $input.select();
            }

            var switchToSpan = function() {
                if ($(this).val() == "" || $(this).val() == "Enter Sub-Group") {
                    var message = "Please enter valid Sub-Group";
                    var title = "Error";
                    generalDialog(message, title);
                    return false;
                }
                
                for (var i in init_subgroups) {
                	
                	if($(this).val().trim() === init_subgroups[i]) {
                	     var message = "Subgroup already exists.<br> If subgroup is removed from list please save it before re-adding.";
                         var title = "Error";
                         generalDialog(message, title);
                         return false;
                    
                	}
                } 

                var $span = $("<span>", {
                    text: (!$(this).val()) ? "Enter Sub-Group" : $(this).val(),

                });

                $span.addClass("subgroup_name");
                $(this).replaceWith($span);
                $span.on("click", switchToInput);
            }
            $(document).delegate('.subgroup_name', 'click', switchToInput);
            //END OF EDIT INPLACE



            //START OF CARD ADD/REMOVE SUBGROUP ROW 
            ////SCENARIO 1: ADD	SUBGROUP USING "+" button 

            $(document).delegate('.subgroup_add', 'click', function() {

                //get the group_name of CARD to check with subgroup object and compare group when SAVING
                var $this_card = $(this).closest('.card_group');
                var group_name = $this_card.find('header > span').text();

                //check if group is undefined set global group object
                if (groupObj[const_group] === undefined) {
                    groupObj[const_group] = group_name;
                }


                //Very first step is to populate subgroups in array if not initialized
                if (!isSubgroupPopulated) {
                    init_subgroups = getCurrentSubgroups($this_card);
                    isSubgroupPopulated = true;
                    console.log("ADD: INIT SUBGROUP - "+JSON.stringify(init_subgroups));
                }

                //validate input field to check if empty and force user to input subgroup name before adding new subgroup
                //checking sibling element "input" for any value 
                var $inputVal = $(this).siblings('input').val();
                var message = "Please input valid subgroup name";
                var title = "Error";
                if ($inputVal == '' || $inputVal === "Enter Sub-Group") {
                    generalDialog(message, title);
                    return false; //// disable default link action
                } else {
                    $(this).closest('.subgroup').append(tpl_add_subgroup_row);
                }
                //ending validation


                //check if global groupObj is same as current card group
                //if global object not same as local groupname then throw alert box
                if (groupObj.group !== group_name) {
                    //open dialog when user clicks on different Group's Subgroup and leave current one.
                    var message = "Please save your changes for Group - " + groupObj.group;
                    var title = "ERROR";
                    generalDialog(message, title);

                } else {

                    //countSubgroup counter will increment when you add subgroup
                    countSubgroup++;

                    //get group_name
                    var group_name = $(this).closest('.subgroup').closest('.card_group').find('header > span').text();

                    //global object set group
                    groupObj[const_group] = group_name;

                    //set local object 
                    var local_groupObj = {};
                    local_groupObj[const_group] = group_name;

                    //added key "add" in add_obj so that latest subgroups will be saved
                    add_obj[key_add] = local_groupObj;

                }
            });
            //END OF ADD SUBGROUP

            //START OF DELETE SUBGROUP        	
            //SCENARIO 2: REMOVE SUBGROUP USING "-" button
            $(document).delegate('.subgroup_remove', 'click', function() {

                var local_groupObj = {};

                //get the parent element of this child button ".subgroup_remove"
                var $parent = $(this).closest('.subgroup');

                //get the root element of the card
                $this_card = $parent.closest('.card_group');

                //get groupname from ".card_group"
                var group_name = $this_card.find('header > span').text();

                //Very first step is to populate subgroups in array if not initialized
                if (!isSubgroupPopulated) {
                    init_subgroups = getCurrentSubgroups($this_card);
                    isSubgroupPopulated = true;
                    console.log("SUBGROUP REMOVE FN: INIT SUBGROUP - "+JSON.stringify(init_subgroups));
                }

                //validate input field to check if empty and force user to input subgroup name before adding new subgroup
                //checking sibling element "input" for any value 
                var $inputVal = $(this).siblings('input').val();
                var message = "Please input valid subgroup name";
                var title = "Error";
                if ($inputVal == '' || $inputVal === "Enter Sub-Group") {
                    generalDialog(message, title);
                    return false; //// disable default link action
                }


                //add group name in localObj
                local_groupObj[const_group] = group_name;

                //STEP: 1 if subgroupObj is null then add group
                if (groupObj.group == null) {
                    groupObj[const_group] = group_name;
                }

                //STEP: 2 check if other group's subgroup is selected
                if (groupObj.group !== group_name) {
                    //open dialog when user clicks on different Group's Subgroup and leave current one.
                    var message = "Please save your changes for Group - " + groupObj.group;
                    var title = "Warning";
                    generalDialog(message, title);

                } else {

                    if (countSubgroup > 0)
                        countSubgroup--;

                    //remove the current div of element ".subgroup_remove"
                    $(this).parent('div').remove();

                    //get subgroup name from current card
                    var subgroup_name = $(this).parent('div').children('.subgroup_name').text();

                    removeSubgroup.push(subgroup_name);

                    //get length of the total div of subgroups
                    var totalDivsInSubgroup = $parent.children('div').length;

                    //After removing all subgroup rows. "Add Subgroup" Button is added
                    if (totalDivsInSubgroup == 0) {
                    	$parent.append(tpl_add_button);
                    }

                    //check if removeSubgroup array is > 0
                    if (removeSubgroup.length > 0) {
                    	local_groupObj[const_subgroup] = removeSubgroup;
                    }

                	del_obj[key_delete] = local_groupObj;
                }

            });
            
            
            
            //CARD CANCEL CHANGES MADE TO BUTTON
            $(document).delegate('.button_card_cancel', 'click', function() {
            	

                //get the group_name to check with subgroup object and compare group
                var $this_card = $(this).closest('.card_group');
                var group_name = $this_card.find('header > span').text();
                
                //check global object groupObj has group name
                if (groupObj[const_group] !== undefined && group_name !== groupObj[const_group]) {
                    var message = "Please complete changes of previous group - " + groupObj[const_group];
                    var title = "OOPS!";
                    generalDialog(message, title);
                    return;
                    
                } else {
                	
                    //Very first step is to populate subgroups in array if not initialized
                    if (!isSubgroupPopulated) {
                        init_subgroups = getCurrentSubgroups($this_card);
                        isSubgroupPopulated = true;
                        console.log("CANCEL: INIT SUBGROUP - "+JSON.stringify(init_subgroups));
                    }
              
                    //CANCEL Case:1 if card is empty remove new subgroups
                    if(init_subgroups.length == 0) {
                    	$this_card.find('.subgroup').children().remove();
                    	$this_card.find('.subgroup').append(tpl_add_button);
                    } else {
                    	//remove add button
                    	$this_card.find('.button_add_subgroup').remove(); 
                    	
                     // loop through each subgroup and remove all subgroups
                	$this_card.find('.subgroup_name').each(function(index) {
                		//remove all the rows of subgroup_name
                		$(this).parent().remove();
                   
                    });
                	
                	
                	
                	//again add old subgroups, thus reverse the changes
                	  for(var i in init_subgroups) {
                		  //replace dummy value "placeholder_subgroup" with subgroup name
                		  var row_subgroup =  tpl_add_subgroup_row_span.replace("placeholder_name",init_subgroups[i]);
                		  $this_card.find('.subgroup').append(row_subgroup);
                	  }
                    
                    }
                    
                    delete groupObj[const_group];
                    delete groupObj[const_subgroup];
                    isSubgroupPopulated = false;
                    init_subgroups.splice(0, init_subgroups.length);
                }
            });
            
            
            

            //Card save button to add or remove subgroup
            $(document).delegate('.button_card_save', 'click', function() {
	
                //initialise  local object
                var local_groupObj = {};

                //get the group_name to check with subgroup object and compare group
                var $cardElement = $(this).closest('.card_group');
                var group_name = $cardElement.find('header > span').text();
                //save group name in local_groupObj
                local_groupObj[const_group] = group_name;


                //check global object groupObj has group name
                if (groupObj[const_group] !== undefined && group_name !== groupObj[const_group] && init_subgroups.length > 0) {
                    var message = "Please complete changes of previous group - " + groupObj[const_group];
                    var title = "OOPS!";
                    generalDialog(message, title);
                    return;
                    
                } else {

                	//CASES : Deletion of new subgroups
                    //CASE 1: check deletion of new subgroup(i.e adding & deleting new subgroup) 
                	//check if {remove:{}} object exists and sub object subgroup and init_subgroup length > 0
                    if (del_obj[key_delete] != undefined &&
                        del_obj[key_delete][const_subgroup].length > 0 &&
                        init_subgroups.length > 0) {
                    	
                    	//isSubgroup 
                    	var isSubgroupExist=false;
                    	
                    	//define array arrNewSubgroups
                    	var arrNewSubgroups = [];
                    	for (var j in del_obj[key_delete][const_subgroup]) {
                        	for (var i in init_subgroups) {
                        		if(del_obj[key_delete][const_subgroup][j] === init_subgroups[i]) {
                        			isSubgroupExist=true;
                            		break;
                            	} 
                            }
                        	
                        	if(!isSubgroupExist){
                        		arrNewSubgroups.push(j);
                        	}
                        	isSubgroupExist=false;
                            
                       }
                    	//remove all non saved newsubgroups 
                    	for(var i in arrNewSubgroups) {
                    		 del_obj[key_delete][const_subgroup].splice(arrNewSubgroups[i]);
                    	}
                        
                    	//empty array
                    	arrNewSubgroups = [];
                        console.log("DEL NEW SUBGROUP AFTER  - "+del_obj[key_delete][const_subgroup]);
                    }

                    //CASE 2: check if subgroups empty then additions / deletion events nullify
                    if (init_subgroups.length == 0) {
                        //del_obj[key_delete][const_subgroup].splice(0, del_obj[key_delete][const_subgroup].length);
                    	console.log("ZERO SUBGROUPS: INIT SUBGROUP - "+JSON.stringify(init_subgroups));
                        delete del_obj[key_delete];
                    }


                    // ADDING SUBGROUPS
                    if (add_obj.hasOwnProperty(key_add)) {

                        //get total number of subgroups rows
                        var $rows = $cardElement.find('span.subgroup_name').length;

                        // loop through each subgroup
                        $cardElement.find('span.subgroup_name').each(function(index) {
                        	//if newly added subgroup == index then add in addSubgroup array
                            //loop through >= 1 rows of new subgroups
                            if (index == ($rows - countSubgroup) || $rows == 1) {
                            	addSubgroup.push($(this).text().trim());
                                countSubgroup--;
                            }

                        });

                        //Adding subgroup array to groupObj Object
                        local_groupObj[const_subgroup] = addSubgroup;
                        //Adding group object to add_obj
                        add_obj[key_add] = local_groupObj;

                        if (addSubgroup.length !== 0) {

                            $.ajax({
                                url: '/group/add',
                                type: "POST",
                                datatype: "application/json",
                                contentType: "text/plain",
                                data: JSON.stringify(add_obj),

                                success: function(data, textStatus, xhr) {
                                    var title = "Success";
                                    var message = "Successfully added subgroups  - " + add_obj.add.group;
                                    generalDialog(message, title);
                                    console.log("AJAX Success: Successfully added groups and subgroups" + JSON.stringify(add_obj));
                                    addSubgroup.splice(0, addSubgroup.length);
                                    delete add_obj[key_add];

                                    console.log("STATUS - " + xhr.status);
                                    //console.log("DATA - "+data);

                                },

                                error: function(data) {
                                    var title = "Error";
                                    var message = "Unexpected error occured while adding subgroups for Group - " + groupObj[const_group] + "\n Please try again..";
                                    generalDialog(message, title);
                                    console.log("AJAX Failure: Failed to add groups and subgroups" + data);
                                    //console.log("ERROR - "+JSON.stringify(data));
                                }
                            });
                        } else {
                            //do nothing
                        }

                    }


                    //DELETING SUBBROUPS
                    //check if del_obj has property "remove" and length of subgroup > 0
                    if (del_obj.hasOwnProperty(key_delete) && del_obj[key_delete][const_subgroup].length > 0) {

                        console.log("DEL OBJ - " + JSON.stringify(del_obj));
                        $.ajax({
                            url: '/group/delete',
                            type: "POST",
                            datatype: "application/json",
                            contentType: "text/plain",
                            data: JSON.stringify(del_obj),

                            success: function(data) {
                                var title = "Success";
                                var message = "Successfully deleted subgroups  - " + del_obj.remove.group;
                                generalDialog(message, title);
                                console.log("AJAX Success: Successfully delete & subgroups" + JSON.stringify(del_obj));
                                removeSubgroup.splice(0, removeSubgroup.length);
                                delete del_obj[key_delete];
                            },
                            error: function(data) {
                                var title = "Error";
                                var message = "Unexpected error occured while removing subgroups of Group - " + groupObj[const_group] + "\n Please try again..";
                                generalDialog(message, title);
                                console.log("AJAX Failure: Failed to delete groups and subgroups");
                            }

                        });

                    }

                    //CLEARING AND RESETING OBJECTS ONCE SAVED SUCCESSFULLY
                    countSubgroup = 0;
                    delete groupObj[const_group];
                    delete groupObj[const_subgroup];
                    isSubgroupPopulated = false;
                    init_subgroups.splice(0, init_subgroups.length);

                }

            });
            //  END OF REMOVE SUBGROUP 


            //SCENARIO 5: 
            //START OF ADD SUBGROUP BUTTON (when all subgroup deleted)
            $(document).delegate('.button_add_subgroup', 'click', function() {

                //get the group_name of CARD to check with subgroup object and compare group when SAVING
                var group_name = $(this).closest('.card_group').find('header > span').text();

                $this_card = $(this).closest('.card_group');
                //get total number of rows of subgroups of current card
                var $rows = $this_card.find('span.subgroup_name').length;

                //check if global group object initialized when subgroups = 0
                if (groupObj[const_group] == undefined && $rows == 0) {
                    groupObj[const_group] = group_name;
                }

                //check if global groupObj is same as local Group Object && initsubgroup length > 0
                if (groupObj[const_group] != undefined && groupObj.group !== group_name) {
                    //open dialog when user clicks on different Group's Subgroup and leave current one.
                    var message = "You forgot to save changes for Group - " + groupObj.group;
                    var title = "OOPS!";
                    generalDialog(message, title);

                } else {

                    //Very first step is to populate subgroups in array if not initialized
                    if (!isSubgroupPopulated) {
                        init_subgroups = getCurrentSubgroups($this_card);
                        isSubgroupPopulated = true;
                        console.log("ADD NEW SUBGROUP: INIT SUBGROUP - "+JSON.stringify(init_subgroups));
                    }

                    //hide button
                    $(this).hide();
                    $(this).closest('.subgroup').append(tpl_add_subgroup_row);
                    countSubgroup++;
                    groupObj[const_group] = group_name;
                    add_obj[key_add] = groupObj;
                }
            });
            //END OF ADD SUBGROUP BUTTON


            //DELETE CARD 
            //SCENARIO 3: DELETE CARD & REMOVE GROUP & CORRESPONDING SUBGROUPS
            $(document).delegate('.group_delete', 'click', function() {

                var delete_group = {};

                var $parent = $(this).closest('.row_append_group');

                //get the no of total cards in a row,
                //delete row if all cards are deleted
                var count = $parent.children('.card_group').length;

                //current_card 
                var current_card = $(this).closest('.card_group');

                //remove card
                current_card.remove();

                //get group_name from card header > span
                var group_name = current_card.find('header > span').text();

                groupObj[const_group] = group_name;

                //reset subgroup before adding them
                removeSubgroup.splice(0, removeSubgroup.length);

                //for each function 
                current_card.find('.subgroup_name').each(function() {
                    removeSubgroup.push($(this).text());
                });


                //add subgroup to groupObj 
                if (removeSubgroup.length > 0) {
                    groupObj[const_subgroup] = removeSubgroup;
                }

                //add group_obj to delete group object with key "remove"
                delete_group[key_delete] = groupObj;

                console.log("DEL ALL GROUP & SUBGROUP - " + JSON.stringify(delete_group))

                $.ajax({
                    url: '/group/delete',
                    type: "POST",
                    datatype: "application/json",
                    contentType: "text/plain",
                    data: JSON.stringify(delete_group),
                    success: function(data) {


                        console.log("AJAX Success: delete groups and subgroups");
                    },
                    complete: function(data) {
                        // delete groupObj[const_group];
                        delete groupObj[const_subgroup];
                        delete delete_group[key_delete];
                        removeSubgroup.splice(0, removeSubgroup.length);
                        console.log("AJAX Complete: delete groups and subgroups");
                    }
                });

                //if last card (card=1) deleted then delete entire row with selector (.row_append_group)
                if (count == 1)
                    $parent.remove();
            });
            //end of delete card

            //START OF ADD NEW GROUP
            //SCENARIO 4: ADD ONLY GROUP. Call AJAX to SAVE NEW GROUP INTO DB
            var textValue;
            $('.button_add_group').click(function() {
                $("#groupDialog").dialog("open");
                $('#group_name').val("");
            });

            $("#groupDialog").dialog({
                autoOpen: false,
                modal: true,
                title: "Enter Group",
                buttons: {
                    'Cancel': function() {
                        $(this).dialog('close');
                        $("#error").remove();
                    },
                    'Save': function() {

                        textValue = $('#group_name').val();
                        var errorMsg = "";
                        var isValid = true;


                        if (textValue != "") {
                            isValid = true;
                        } else {
                            isValid = false
                            errorMsg = "<br/> Group Name cannot be empty...";
                        }

                        var regx = /^[A-Za-z0-9]+$/;

                        if (!regx.test(textValue)) {
                            isValid = false;
                            errorMsg = errorMsg + "<br/> Group Name cannot have special characters...";
                        } else isValid = true;


                        if (isValid) {
                            //group Add 
                            var groupAdd = {};

                            groupObj[const_group] = textValue;
                            groupAdd[key_add] = groupObj;

                            console.log("ADD ONLY GROUP NAME - " + JSON.stringify(groupAdd));

                            //Ajax POST group name to controller
                            $.ajax({
                                url: '/group/add',
                                type: "POST",
                                datatype: "application/json",
                                contentType: "text/plain",
                                data: JSON.stringify(groupAdd),
                                success: function(data) {


                                    console.log("AJAX Success: adding groups");
                                },
                                failure: function(data) {
                                    console.log("AJAX Failure: adding failure");
                                }
                            });

                            addCard(textValue);
                            $("#error").remove();
                            $(this).dialog('close');
                        } else {

                            if ($('#error').length == 0) {
                                $(this).append('<p id="error">' + errorMsg + '</p>');
                                $("#error").css({
                                    "color": "red",
                                    "padding-top": "5px"
                                });
                            }
                        }
                    }
                }
            });

            // add card in each row. Max 3 cards/row
            function addCard(group_name) {
                //check total rows
                var rows = $('.row_append_group').length;

                //add row if not exists
                if (rows == 0) {
                    //add_group div should be added
                    $('.add_group').append(tpl_add_group_row);
                    //add row_append_group div should be added inside "add_group"
                    $('.row_append_group').append(tpl_add_groupcard);
                    //add cards inside div "row_append_group"
                    $('.card_group:last').find('header > span').text(group_name);
                }

                //add card to existing rows
                if (rows > 0) {
                    var row;
                    var total_no_cards = 0;

                    //this loop is helps to get the row div to add new card
                    for (var i = 0; i < rows; i++) {
                    	//get row div from ".eq(i)"
                        row = $('.row_append_group').eq(i);
                       
                        //get total number of cards
                        total_no_cards = row.find('.card_group').length;
                        
                        if (total_no_cards < 3) {
                            break;
                        }
                    }

                    //check if cards in row < 3
                    if (total_no_cards < 3) {
                        $(row).append(tpl_add_groupcard);

                        //Adding value to span
                        $(row).children('.card_group:last').find('header > span').text(group_name);
                    }

                    //Add new row when row is full(max 3 cards/row)
                    if (total_no_cards == 3) {

                        //add new row div to add new card
                        $('.add_group').append(tpl_add_group_row);

                        //re-check length of rows added and new card (group) in row
                        var rows = $('.row_append_group').length;
                        var row = $('.row_append_group').eq(rows - 1);
                        $(row).append(tpl_add_groupcard);

                        //Adding value to span
                        $(row).children('.card_group:last').find('header > span').text(group_name);
                    }
                }
            }
            //END OF ADD NEW GROUP 

            //Generic Dialog Box called by methods to display alert messages
            function generalDialog(message, title) {
                //open dialog when user clicks on different Group's Subgroup and leave current one.
                $('<div></div>').dialog({
                    modal: true,
                    title: title,
                    open: function() {
                        $(this).html(message);
                    },
                    buttons: {
                        Close: function() {
                            // $parent.children().show();
                            $(this).dialog("close");
                        }
                    }
                });
            }


            // Get initial subgroups from card that is updated
            function getCurrentSubgroups($this_card) {

                var _initArr = [];

                // loop through each newly added subgroup
                $this_card.find('span.subgroup_name').each(function(index) {
                	
                		_initArr.push($(this).text());
                	
                });

                return _initArr;
            }

        }); //end of document jQuery
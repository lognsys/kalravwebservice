
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ page import="com.lognsys.model.Users" %>
<%@ page import="java.util.*"%>
 
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Manager </title>
     <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
	<script type="text/javascript">
	
	
	
	function getCheckedCheckboxesFor(checkboxName) {
	    var checkboxes = document.querySelectorAll('input[name="' + checkboxName + '"]:checked'), values = [];
	    var x = document.getElementById("demo");
	    x.innerHTML=checkboxes;
	       
        Array.prototype.forEach.call(checkboxes, function(el) {
        		
	        values.push(el.value);
	        x.innerHTML=checkboxes+ "   "+el.value+ "  "+values;
		    
	    });
	    return values;}
</script>
    </head>
<body>
<form name="myform" method="post" ></form>
   
    <div align="center">
            <h1>User List</h1>
            <h3>
            <div class="container">
    <div class="row">
        <nav class="navbar navbar-default">
            <div class="container">
             
                <p  id="demo" class="navbar-text, text-center">User List Form</p>
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
                                    <label class="btn btn-success btn-filter active" data-target="completed">
                                        <input type="radio" name="options" id="option1" autocomplete="off" checked>
                                        Completed
                                    </label>
                                    <label class="btn btn-warning btn-filter" data-target="pending">
                                        <input type="radio" name="options" id="option2" autocomplete="off"> Pending
                                    </label>
                                    <label class="btn btn-default btn-filter" data-target="all">
                                        <input type="radio" name="options" id="option3" autocomplete="off"> All
                                    </label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="panel-body">
                    <table id="mytable" class="table table-striped table-bordered table-list">
                        <thead>
                        <tr>
                          <th class="col-check"><input type="checkbox" name="Select All" id="select_all" onclick="selectAllCheckBox();"/>
                            </th>
                            <th class="hidden-xs">ID</th>
                            <th class="col-text">Name</th>
                            <th class="col-text">Email</th>
                            <th class="col-text">Phone</th>
                            <th class="col-text">Status</th>
                        </tr>
                        </thead>
                        <tbody>
                    
                      
                        <c:forEach var="users" items="${user_listitems}">   
                        <tr data-status="completed">
                            <td align="center"><input name="users" type="checkbox" value="${users.id}" /></td>
                            
                             <td class="hidden-xs">${users.id}</td>
                            <td>${users.realname}</td>
                            <td>${users.username}</td>
                            <td>${users.phone}</td>
                          <td><a href="deleteemp/${users.id}">Delete</a></td> 
                            <td>
                            
                                 <div class="btn-group">
                                      <button type="button" class="btn ">Action</button>
                                      <button type="button" class="btn dropdown-toggle" data-toggle="dropdown">
                                        <span class="caret"></span>
                                        <span class="sr-only">Toggle Dropdown</span>
                                      </button>
                                      
                                      <ul class="dropdown-menu" role="menu">
                                        <li><a href="#">New</a></li>
                                        <li><a href="#">Returning</a></li>
                                        <li><a href="#">Inactive</a></li>
                                        <li class="divider"></li>
                                        <li><a href="#">Separated link</a></li>
                                      </ul>
                                </div>
                             </td>
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
                                    <li>
                                        <a href="#" aria-label="Previous">
                                            <span aria-hidden="true">«</span>
                                        </a>
                                    </li>
                                    <li class="active"><a href="#">1</a></li>
                                    <li><a href="#">2</a></li>
                                    <li><a href="#">3</a></li>
                                    <li><a href="#">4</a></li>
                                    <li><a href="#">5</a></li>
                                    <li>
                                        <a href="#" aria-label="Next">
                                            <span aria-hidden="true">»</span>
                                        </a>
                                    </li>
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
                <button type="button" class="btn btn-primary">
                                    <span class="glyphicon glyphicon-plus"
                                          aria-hidden="true"></span>
                                   Cancel
                                </button> 
                                    <button type="button" class="btn btn-primary">
                                    <span class="glyphicon glyphicon-plus"
                                          aria-hidden="true"></span>
                                    Edit
                                </button>   
                                    <input type="submit" class="btn btn-primary" value="Add" href="${pageContext.request.contextPath}/register" />
                                    <span class="glyphicon "
                                          aria-hidden="true"></span>
                                       
                                    <input type="submit" class="btn btn-primary" value="Delete" name="submit" onclick="alert(getCheckedCheckboxesFor('users'));"  />
                                 
                                    <span class="glyphicon "
                                          aria-hidden="true"></span>
                                    
            </div>
        </nav>
    </div>
</div>
</form>
</body>
</html>
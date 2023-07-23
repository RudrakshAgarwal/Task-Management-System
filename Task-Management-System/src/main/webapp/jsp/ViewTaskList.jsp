<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>

<!DOCTYPE html>

<head>
    <meta charset="ISO-8859-1">
    <title>View Task List</title>

    <link rel="stylesheet"
        	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">
        <script
        	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script
        	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script
            src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>

    <style>
        a{
            color: white;
        }
        a:hover {
            color: white;
            text-decoration: none;
        }
    </style>

</head>
<body>

    <div class="container">

        <h1 class="p-3"> Task Management List <small class="text-muted">using Rest</small> </h1>

        <form:form>

            <table class="table table-bordered">
            	<tr>
            		<th>Id</th>
            		<th>Title</th>
            		<th>Date</th>
            		<th>Status</th>
            		<th>Mark Completed</th>
            		<th>Edit</th>
            		<th>Delete</th>
            	</tr>

            	<c:forEach var="task" items="${list}">
                    <tr>
                		<td>${task.id}</td>
                		<td>${task.title}</td>
                		<td>${task.date}</td>
                		<td>${task.status}</td>
                		<td><button type="button" class="btn btn-success">
                		    <a href="/updateTaskStatus/${task.id}">Mark Complete</a>
                		</button></td>
                		<td><button type="button" class="btn btn-primary">
                		    <a href="/editTask/${task.id}">Edit</a>
                		</button></td>
                		<td><button type="button" class="btn btn-danger">
                			<a href="/deleteTask/${task.id}">Delete</a>
                		</button></td>
                	</tr>

            	</c:forEach>

            </table>

        </form:form>

        <button type="button" class="btn btn-primary btn-block">
        	<a href="/addTask">Add New Task</a>
        </button>

    </div>

    <script th:inline="javascript">
                window.onload = function() {

                    var msg = "${message}";
                    
                    if (msg == "Save Success") {
        				Command: toastr["success"]("Item added successfully!!")
        			} else if (msg == "Delete Success") {
        				Command: toastr["success"]("Item deleted successfully!!")
        			} else if (msg == "Delete Failure") {
        			    Command: toastr["error"]("Some error occurred, couldn't delete item")
        			} else if (msg == "Edit Success") {
        				Command: toastr["success"]("Item updated successfully!!")
        			}

        			toastr.options = {
                          "closeButton": true,
                          "debug": false,
                          "newestOnTop": false,
                          "progressBar": true,
                          "positionClass": "toast-top-right",
                          "preventDuplicates": false,
                          "showDuration": "300",
                          "hideDuration": "1000",
                          "timeOut": "5000",
                          "extendedTimeOut": "1000",
                          "showEasing": "swing",
                          "hideEasing": "linear",
                          "showMethod": "fadeIn",
                          "hideMethod": "fadeOut"
                        }
        	    }
            </script>

</body>

</html>
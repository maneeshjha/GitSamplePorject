<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Untitled Document</title>
</head>

<body>
<link rel="stylesheet" type="text/css"
	href="//cdn.datatables.net/1.10.0/css/jquery.dataTables.css">
<script type="text/javascript"
	src="//code.jquery.com/jquery-1.10.2.min.js"></script>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.1/jquery-ui.min.js"></script>
<script type="text/javascript"
	src="//cdn.datatables.net/1.10.4/js/jquery.dataTables.min.js"></script>
<script src="jquery.jeditable.js" type="text/javascript"></script>
<script src="jquery.dataTables.editable.js" type="text/javascript"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.13.1/jquery.validate.js"></script>
<script type="text/javascript">
	$(document).ready(function() {

		$("#example").dataTable({
			"bProcessing" : false,
			"bServerSide" : false,
			"sort" : "position",
			"bVisible": false,
			"sAjaxSource" : "./PaginationServlet",
			"aoColumns" : [ {
				"mData" : "field1"
			}, {
				"mData" : "field2"
			}, ]
		}).makeEditable({
			//"sServerMethod": "GET",
			"sUpdateURL":"./updateServlet",
            "sDeleteURL":"./DeleteServlet",
            "sAddURL": "./AddServlet",
           // "sAddHttpMethod": "GET",
			"bServerSide" : true,
			"processing" : false,
			"aoColumns" : [null, {


				//indicator : 'Saving...',
				//type : 'textarea',
				//submit : 'update'

			} ]
		});

	});
</script>
</head>
<body>

		<h2>
			Jquery DataTable<br> <br>
		</h2>

			<form id="formAddNewRow"  title="Add New User">
			<label for="name">UserId:</label><input type="text"
               name="user_id" id="user_id" class="required" /><br>
        <label for="name">Name:</label><input type="text"
               name="name" id="name" class="required" />

    <button id="btnAddNewRowOk">Add</button>
    <button id="btnAddNewRowCancel">Cancel</button>
</form>
		<table width="70%"
			style="border: 3px; background: rgb(243, 244, 248);">
			<tr>
				<td>
					<table id="example" class="display" cellspacing="0" width="100%">
						<thead>
							<tr>
								<th>User Id</th>
								<th>Name</th>
							</tr>
						</thead>
					</table>
				</td>
			</tr>
		</table>
<button id="btnAddNewRow">Add</button>
	<button id="btnDeleteRow">Delete</button>
</body>
</html>

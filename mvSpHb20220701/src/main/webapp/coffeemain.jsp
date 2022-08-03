<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Coffee</title>
</head>
<body>
<script  src="http://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
Supplier ID: <input type="text" id="sid" value="105"/><br/>
Coffee Name: <input type="text" id="cname" value="Brown Coffee"/><br/>
price: <input type="text" id="price" value="30"/><br/>
sale: <input type="text" id="sale" value="35"/><br/>
total: <input type="text" id="total" value="100"/><br/>
<button id="add" >Add</button>
<button id="update" >update</button>
<button id="remove" >delete</button>
<button id="find" >Find Coffee</button>

<p>
<div id="message"></div>
<script>
    function result(data){
    	$("#message").html(data);
    }
    function add(){
    	var  id= $("#sid").val();
    	var  cname= $("#cname").val();
    	var  price= $("#price").val();
    	var  sale= $("#sale").val();
    	var  total= $("#total").val();
    	
    	$.post("CoffeeServlet",{"id":id ,"cname":cname,"price":price,"sale":sale,"total":total,"method":"add"},result);    	
    }
    function update(){
    	var  id= $("#sid").val();
    	var  cname= $("#cname").val();
    	var  price= $("#price").val();
    	var  sale= $("#sale").val();
    	var  total= $("#total").val();
    	
    	$.post("CoffeeServlet",{"id":id ,"cname":cname,"price":price,"sale":sale,"total":total,"method":"update"},result);
    	
    }
    function find(){
    	var  cname= $("#cname").val();
      	$.post("CoffeeServlet",{"cname":cname,"method":"find"},result);    	
          	
    }
    function remove(){
    	var  id= $("#sid").val();
    	var  cname= $("#cname").val();
    	var  price= $("#price").val();
    	var  sale= $("#sale").val();
    	var  total= $("#total").val();
    	
    	$.post("CoffeeServlet",{"id":id ,"cname":cname,"price":price,"sale":sale,"total":total,"method":"remove"},result);
    	
    }
    function events(){
    	$("#add").click(add);
    	$("#update").click(update);
    	$("#find").click(find);
    	$("#remove").click(remove);
    }
   $(document).ready(events);
</script>
</body>
</html>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    												pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Red Black Trees</title>

<link rel="stylesheet"
              href="<c:url value="/resources/stylesheet/binarySearchTrees.css" />" />

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

<script>
"use strict";
function canvasSupport() {
	return !!document.createElement('canvas').getContext;
} 

function canvasApp() {
	
	console.log("canvasApp begin");
	
	var tree = null;

	var y = null;
	var z = null;
	
	function NodeJSON(depth, index, key, color) {
		this.mKey = key;
		this.mColor = color;
		this.xPos = xPos[depth][index];// matrix mDepth x mIndex  
		this.yPos = yPos[depth];
		this.mRadius = 15;
		this.yConnU = this.yPos - this.mRadius;
		this.yConnD = this.yPos + this.mRadius;
	}// NodeJSON
	

	console.log("canvasApp sator");
	
	// canvas context
	if (!canvasSupport()) {
		alert("canvas not supported");
		return;
	} else {
		var theCanvas = document.getElementById("canvas");
		var context = theCanvas.getContext("2d");
	}// if
	
	console.log("canvasApp arepo");
	
	var xMin = 0;
	var yMin = 0;
	var xMax = theCanvas.width;
	var yMax = theCanvas.height;
	 
	var xPos = [];
	var yPos = [100, 200, 300, 400, 500];

	initGeometry();
	
	
	function treeJSONDisplay(treeArray) {// actual drawing
		console.log("treeJSONDisplay begin " + treeArray.length);
		fillBackground();
		setTextStyle();
	   
		var data = null;
		var index = 0;
		for (var depth = 0; depth < 5; depth++) {
			data = treeArray[depth]; // nodes on depth
			console.log("treeJSONDisplay sator " + data);
			console.log("treeJSONDisplay length " + data.length);
			for (var i = 0; i < data.length; i++) {// all nodes on depth
				var nodeResult = data[i];
			
				var key = nodeResult["key"];
				var color = nodeResult["color"];
				var index = nodeResult["index"];
				var node = new NodeJSON(depth, index, key, color);
				drawNodeJSON(node);

				if (depth > 0) {// if not root draw connection to parent node
					
					var indexP = nodeResult["parentIndex"];           
					
					var xPosP = xPos[depth-1][indexP];// parent node  
					var yConnD = yPos[depth-1] + node.mRadius;
					context.strokeStyle = "black";
					context.beginPath();				
					context.moveTo(node.xPos, node.yConnU);// actual node
					context.lineTo(xPosP, yConnD); // draw line from actual node to parent
					context.stroke();
					context.closePath();    
				}// if          
			}// for
		}// for
		
	}// treeJSONDisplay

	function setTextStyle() {
		context.fillStyle    = '#000000';
		context.font         = '15px _sans';
		context.textBaseline = "middle";
		context.textAlign = "center";
	}
	
	function initGeometry() {
		console.log("initGeometry begin");
		var xPos4 = [];
		var xPos3 = [];
		var xPos2 = [];
		var xPos1 = [];
		var xPos0 = [400];

		for (var i = 0; i < 16; i++) {
			xPos4[i] = 40 + i * 48;
		}

		for (var i = 0; i < 8; i++) {
			xPos3.push(Math.floor( (xPos4[2*i] + xPos4[2*i+1])/2 ) );
		}

		for (var i = 0; i < 4; i++) {
			xPos2.push(Math.floor( (xPos3[2*i] + xPos3[2*i+1])/2 ) );
		}

		for (var i = 0; i < 2; i++) {
			xPos1.push(Math.floor( (xPos2[2*i] + xPos2[2*i+1])/2 ) );
		}

		xPos = [xPos0, xPos1, xPos2, xPos3, xPos4];

		console.log("initGeometry completed");
	}// initGeometry
	
	function drawNodeJSON(node) {// here node attribute are mKey, xPos, yPos, yConnU, yConnD 
		context.strokeStyle = (node.mColor == "BLACK") ? "black" : "red";
		context.beginPath();
		context.lineWidth = 2;
		context.arc(node.xPos, node.yPos, node.mRadius, (Math.PI/180)*0, (Math.PI/180)*360, true); // draw full circle
		context.stroke();
		context.closePath();
		// draw text inside the circle
		//Debugger.log("drawNode " + node.mKey + " xPos " + node.xPos + " yPos " + node.yPos);
		context.fillText(node.mKey, node.xPos, node.yPos);
	}// drawNode
	
	function fillBackground() {
		//Debugger.log("fillBackground");
		// draw background
		context.fillStyle = '#ffffff';
		context.fillRect(xMin, yMin, xMax, yMax);    
	}// fillBackground

	var N = 8;// initial number of nodes

	var keys = new Array(N);// for initialization

	var nodes = new Array(N);// for initialization
	
	$("#initelem").submit(function(event) { initialize(N); return false; });

	$("#searchelem").submit(function(event) { search(); return false; });

	$("#insertelem").submit(function(event) { insert(); return false; });

	$("#deleteelem").submit(function(event) { remove(tree); return false; });
	  
	function search() {

		console.log("search begin");
		  
		var searchkey = $('#searchkey').val();
		
		console.log("searchkey " + searchkey);
		
		var message = {"type" : "SEARCH", "key" : searchkey};
		
		
		console.log("message " + message["type"]);
		
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : '<c:url value="/act" />',
			data : JSON.stringify(message),
			dataType : 'json',
			timeout : 100000,
			success : function(data) {
				console.log("SUCCESS");
				
				var found = data["type"];
				
				console.log(found);
				
				if (found == "FOUND") {
					$('#found').text('key ' + searchkey + ' found')
	
				} else {
					$('#found').text('key ' + searchkey + ' not found')
				}
		
			},
			
			error : function(e) {
				console.log("ERROR: ", e);
			},
			done : function(e) {
				console.log("DONE");
			}
		});

	    console.log("search completed");
	}
	
	function insert() {
		console.log("insert begin");
		var insertkey = $('#insertkey').val();
		
		console.log("insertkey " + insertkey);
		
		// send an INIT message to initialize the server side
		var message = { "type": "INSERT", "key": insertkey };
		
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : '<c:url value="/act" />',
			data : JSON.stringify(message),
			dataType : 'json',
			timeout : 100000,
			success : function(data) {
				console.log("SUCCESS");
				var type = data["type"];
				console.log("type " + type);
				if (type == "OK") {
					var treeArray = data["treeArray"];
					console.log(data["treeArray"]);
					treeJSONDisplay(treeArray);
				} else if (type == "FORBIDDEN") {
					alert("maximal depth exceeded")
				} else if (type == "NODE_PRESENT") {
					alert("key already present");
				}
			},
			
			error : function(e) {
				console.log("ERROR: ", e);
			},
			done : function(e) {
				console.log("DONE");
			}
		});
		
	    console.log("insert completed");
	}
	
	function remove() {
		console.log("remove begin");
		
		var delkey = $('#delkey').val();
		
		console.log("delkey " + delkey);
		
		var message = {"type" : "DELETE", "key" : delkey};
	    
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : '<c:url value="/act" />',
			data : JSON.stringify(message),
			dataType : 'json',
			timeout : 100000,
			success : function(data) {
				console.log("SUCCESS");
				var type = data["type"];
				console.log("type " + type);
				
				if (type == "OK") {
					var treeArray = data["treeArray"];
					console.log(data["treeArray"]);
					treeJSONDisplay(treeArray);
				} else if (type == "NOT_FOUND") {
					alert("key not found")
				}
			},
			
			error : function(e) {
				console.log("ERROR: ", e);
			},
			done : function(e) {
				console.log("DONE");
			}
		});
	    
	    console.log("remove completed");
	}
	
	function initialize(N) {
		console.log("initialize begin");
		randomize(N);
		
		console.log(keys);
		
		// send an INIT message to initialize the server side
		var message = { "type": "INIT", "keys": keys };
		
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : '<c:url value="/act" />',
			data : JSON.stringify(message),
			dataType : 'json',
			timeout : 100000,
			success : function(data) {
				console.log("SUCCESS");
				
				var treeArray = data["treeArray"];
				
				console.log(data["treeArray"]);
				treeJSONDisplay(treeArray);
				
			},
			
			error : function(e) {
				console.log("ERROR: ", e);
			},
			done : function(e) {
				console.log("DONE");
			}
		});
		
		console.log("keys: " + keys);
		console.log("initialize completed");
	}
	
	function randomize(N) {
		var val;
		var more;

		for (var i = 0; i < N; i++) {
			more = true;// flag
			while(more) {
				more = false;
				val = Math.floor(Math.random() * 100);// range 0 to 99 included
				for (var j = 0; j < keys.length; j++) {
					if (val == keys[j]) {
						more = true;
						break;
					}// if 
				}// for
			}// while
			keys[i] = val;    
		}// for

		//keys = [ 78, 72, 12, 97, 98 ]
	}// randomize

	initialize(N);
	

	
	console.log("canvasApp completed");
}


$(document).ready(canvasApp);
</script>
</head>

<body>

  <nav>
  <br>
  </nav>
  <br>
  <section id="intro">
  <h1>Red Black Tree demonstration</h1>
  <p>I present here a Java demonstration of a Red Black Tree. You can call interactively the methods search(), insert(), remove() on the tree. The tree is initialized with 8 random values. To keep the display simple, the tree depth should not exceed 4. Therefore any insert that would result in a depth > 4 is not executed and an alert is displayed instead. 
  </p>
  </section>


  <section id="display">
    <canvas id="canvas" width="800" height="600">
    Your browser does not support HTML 5 Canvas
    </canvas>
    <a id=animanc></a>
   
  </section>

  <section id="controls">
    <div id="randomize">
      <p>Click here to initialize the tree with random values:</p>
      <form name="initialize" id="initelem">
        <input type="submit" name="randomize-btn" value="Initialize">
      </form>
    </div>

    <div id="search">
      <p>Click here to search for a key</p>
      <form name="search" id="searchelem">
        Key to search: <input type="text" id="searchkey" size="2">
        <input type="submit" name="search-btn" value="Search">
      </form>
      <p id="found"></p>
    </div>

    <div id="insertion">
      <p>Click here to insert a new key</p>
      <form name="insert" id="insertelem">
        New key to insert: <input type="text" id="insertkey" size="2">
        <input type="submit" name="insert-btn" value="Insert">
      </form>
    </div>

    <div id="deletion">
      <p>Click here to delete a key</p>
      <form name="delete" id="deleteelem">
        Key to delete: <input type="text" id="delkey" size="2">
        <input type="submit" name="delete-btn" value="Delete">
      </form>
      <p id="empty"></p>
    </div>

    <footer>
      <p>Dominique Ubersfeld, Cachan, France</p>
    </footer>
  </section>


</body>
</html>
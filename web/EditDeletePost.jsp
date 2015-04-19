<%-- 
    Document   : EditDeletePost
    Created on : 18-Apr-2015, 8:14:35 PM
    Author     : c0647610
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
    <head>
         <link href="https://bootswatch.com/flatly/bootstrap.min.css" rel="stylesheet">
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title>My Blog</title>

        <script src="http://code.jquery.com/jquery.min.js"></script>
       <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
        <script>
            $(document).ready(function() {
                // Go Grab the Blog Data and Place it on the Page
                $.getJSON('./webresources/insert/', getBlog);    
                 $('#text').addClass('invisible');
                  $('#title').addClass('invisible');
                    $('#send').addClass('invisible');                // Configure an On-Click Listener to Update the Form
              /* $('#send').click(function() {
                    $.ajax({
                        url: "./webresources/insert/",
                        dataType: "json",
                        contentType: 'application/json; charset=UTF-8',
                        data: JSON.stringify({"title": $("#title").val(),
                            "description": $("#text").val()}),
                        method: "post",
                        success: getBlog
                    });
                }); */               
            });
            // Configure a Callback to Update the Blog
            $('#title')
            var getBlog = function(data) {
                $('#blog').html('');
                for (var i = 0; i < data.length; i++)
                {                    $('#blog').append('<h3>' + data[i].title + '</h3><h4>' + 
                       data[i].description  + '</h4><small>' + data[i].date+
                        '</small>&nbsp;&nbsp;&nbsp;<p align="right"><button class="btn btn-info" onclick="doDelete('+ 
                        data[i].p_id + ')">Delete</button>&nbsp;&nbsp;<button class="btn btn-info" onclick="stageUpdate('+ 
                        data[i].p_id + ')">Edit</button></p>');
            }
                $('#text').val('');
                $('#title').val('');
            };                            
            // Perform a Deletion on the DB
            var doDelete = function(id) {
                $.ajax({
                    url: "./webresources/insert/" + id,
                    dataType: "json",
                    contentType: 'application/json; charset=UTF-8',                        
                    method: "delete",
                    success: getBlog
                });
            };
            // Stage an Update on the DB
            var stageUpdate = function(id) {
                 $('#text').removeClass('invisible');
                  $('#title').removeClass('invisible');
                $.getJSON('./webresources/insert/' + id, function(data) {
                    console.log(data);
                    $('#title').val(data[0].title);
                    $('#text').val(data[0].description);
                    $('#update').removeClass('invisible');
                    $('#update').click(function() {
                        $.ajax({
                            url: "./webresources/insert/" + id,
                            dataType: "json",
                            contentType: 'application/json; charset=UTF-8',
                            data: JSON.stringify({"title": $("#title").val(),
                                "description": $("#text").val()}),
                            method: "put",
                            success: function(data) {
                                getBlog(data);
                                $('#update').addClass('invisible');
                                $('#text').addClass('invisible');
                                 $('#title').addClass('invisible');
                            }
                        });    
                    });
                });
            };
        </script>
    </head>
    <body>
        <section class='container'>
          
                <h1>Welcome to my Blog </h1>
                <p align="right">
                <a href="testinsert.jsp">Add a new Post</a>
                <a href="login.xhtml">Logout</a>
                </p>
                 <div id="blog" class="panel-body"></div>
            
            <div class="form-group">
                    <input id="title" class="form-control"/>
                
                <textarea id="text" rows="3" class="form-control"></textarea>               
                <button id="update" class="btn btn-default invisible">Update</button>  
                 
                
               </div>
                      
        </section>
    </body>
</html>


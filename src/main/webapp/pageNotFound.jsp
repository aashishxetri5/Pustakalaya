<%-- 
    Document   : pageNotFound
    Created on : Jan 10, 2022, 12:13:07 PM
    Author     : Aashish Katwal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>404: Server not found</title>
        <%--<%@include file="components/all_css_js.jsp" %>--%>


        <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">

        <style>
            .container-fluid{
                transform: translateY(50%);
            }

            .error-template{
                padding: 40px 15px;
                text-align: center;
            }

            .error-actions{
                margin-top:15px;
                margin-bottom:15px;
            }

            .error-actions .btn{
                margin-right:10px;
            }
        </style>
    </head>
    <body>

        <div class="container-fluid">
            <div class="row">
                <div class="col-md-12">
                    <div class="error-template">
                        <h1>Oops!</h1>
                        <h2> Requested operation could not be performed! </h2>
                        <div class="error-details">
                            Sorry, an error has occured, Requested page not found!
                        </div>
                        <div class="error-actions">
                            <a href="home" class="btn btn-primary btn-lg">
                                <span class="glyphicon glyphicon-home"></span>
                                Take Me Home 
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>


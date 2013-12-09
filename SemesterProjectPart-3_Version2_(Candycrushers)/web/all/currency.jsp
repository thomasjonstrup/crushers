<%-- 
    Document   : currency
    Created on : 06-12-2013, 12:28:56
    Author     : Thomas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="shortcut icon" type="image/x-icon" href="pics/favicon.ico"/>
        <link rel="stylesheet" type="text/css" href="css/CSSFIL.css">
        <title>Currency Page</title>
    </head>
    <script src="js/jquery-2.0.3.js"></script>  
    <script>

        $(document).ready(function() {
            $("#btn").click(function() {
                $.ajax({
                    url: "CurrencyServlet",
                    data: "code=" + $("#code").val(),
                    cache: false,
                    dataType: "json",
                    success: dataReady
                });
            })

            function dataReady(data) {
                $("#desc").val(data.currencyDesc);
                $("#rate").val(data.currencyRate);

            }

        });
</script>
     
    <body>

        <!-- Hoved div: -->
        <div id="hoved">

            <!-- Top div: -->

            <div id="topleft"></div>

            <!-- Top right div: -->

            <div id="topcenter"></div>

            <!-- Top left div: -->

            <div id="topright">
                <h2 style="color: white;">Name: ${currentPerson.firstName}</h2>
                <a href="Controller?command=logout_command"><button>Log out</button></a>

            </div>

            <!--  Menu div: -->

            <div id="menu">



                <li id="linav">
                    <p id="menutop" class="nav">Menu</p>
                <li/>

                <div id="menulink">
                    <a href="Controller?command=main" class ="nav">- Back to main</a>
                </div>

            </div


            <!-- Indhold div: -->

            <div id="indhold">
                
                <h1>Currency Rates</h1>
                
                <input type="text" id="code"> 
                <button id="btn">Get Currency Rate</button>
                
                <form name="Curency">
                <tr>
                            <td>Description</td>
                            <td><input type="text" id="desc" name="desc" value="" ></td>
                        </tr>
                        <tr>
                            <td>Rate</td>
                            <td><input type="text" id="rate" name="rate" value=""></td>
                        </tr>
                </form>
                
            </div>

            <div id="footer">
                <div>
                    <p class="footer">Candy Bank 2013<br>Copyright &copy</p>
                </div>
            </div>

        </div> 

    </body>
</html> 

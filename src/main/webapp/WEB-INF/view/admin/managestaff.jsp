<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script>
function fn1(btn)
{
	var r=btn.parentNode.parentNode;
	var tb1=document.getElementById("d0");
	
	alert(r.rowIndex);
	tb1.deleteRow(r.rowIndex);
	alert(r.rowIndex);
	if(r.rowIndex==-1)
		{
		console.log("hi")
			tb1.style.display="none";
		}
}
var r="";
function fn2(bt1)
{
	r=bt1.parentNode.parentNode;
	var c0=r.cells[0];
	var c1=r.cells[1];
	var x=document.getElementById("t1");
	
	x.value=c0.innerHTML;
	
	var a=document.getElementById("a1");
	var u=document.getElementById("u1");
	a.style.display="none";
	u.style.display="";
	fn3(c0,c1);

}
function fn3()
{
	var c0=r.cells[0];
	
	var x=document.getElementById("t1");
	
	c0.innerHTML=x.value;
	
}
function fn()
{
	var x=document.getElementById("t1");
	
	var tb1=document.getElementById("d0");
	
	var r=tb1.insertRow(-1);
	var co=r.insertCell(0);
	
	var c2=r.insertCell(1);
	if(x.value=="")
	{
		alert("Enter first name");
	}
	
	
	tb1.style.display="";
	co.innerHTML=x.value;
	
	c2.innerHTML="<input type='button' value='EDIT' onclick=fn2(this)><input type='button' value='delete' onclick=fn1(this)>"	
	
	
	
}

</script>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="description" content="Seipkon is a Premium Quality Admin Site Responsive Template" />
      <meta name="keywords" content="admin template, admin, admin dashboard, cms, Seipkon Admin, premium admin templates, responsive admin, panel, software, ui, web app, application" />
      <meta name="author" content="Themescare">
      <!-- Title -->
      <title>Seipkon - Bootstrap Admin Template</title>
      <!-- Favicon -->
      <link rel="icon" type="image/png" sizes="32x32" href="<%=request.getContextPath() %>/adminResources/image/favicon-32x32.png">
      <!-- Animate CSS -->
      <link rel="stylesheet" href="<%=request.getContextPath() %>/adminResources/css/animate.min.css">
      <!-- Bootstrap CSS -->
      <link rel="stylesheet" href="<%=request.getContextPath() %>/adminResources/css/bootstrap.min.css">
      <!-- Font awesome CSS -->
      <link rel="stylesheet" href="<%=request.getContextPath() %>/adminResources/css/font-awesome.min.css">
      <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/adminResources/css/style1.css">
      <!-- Themify Icon CSS -->
      <link rel="stylesheet" href="<%=request.getContextPath() %>/adminResources/css/themify-icons.css">
      <!-- Perfect Scrollbar CSS -->
      <link rel="stylesheet" href="<%=request.getContextPath() %>/adminResources/css/perfect-scrollbar.min.css">
      <!-- Toggles CSS -->
      <link rel="stylesheet" href="<%=request.getContextPath() %>/adminResources/css/toggles-full.css">
      <!-- Select2 CSS -->
      <link rel="stylesheet" href="<%=request.getContextPath() %>/adminResources/css/select2.min.css">
      <!-- Color Picker CSS -->
      <link rel="stylesheet" href="<%=request.getContextPath() %>/adminResources/css/bootstrap-colorpicker.min.css">
      <!-- Main CSS -->
      <link rel="stylesheet" href="<%=request.getContextPath() %>/adminResources/css/seipkon.css">
      <!-- Responsive CSS -->
      <link rel="stylesheet" href="<%=request.getContextPath() %>/adminResources/css/responsive.css">
   </head>
   <body>
       
      <!-- Start Page Loading -->
      <div id="loader-wrapper">
         <div id="loader"></div>
         <div class="loader-section section-left"></div>
         <div class="loader-section section-right"></div>
      </div>
      <!-- End Page Loading -->
       
      <!-- Wrapper Start -->
      <div class="wrapper">
         <!-- Main Header Start -->
         <header class="main-header">
             
            <!-- Logo Start -->
            <div class="seipkon-logo">
               <a href="/">
               <img src="<%=request.getContextPath() %>/adminResources/image/logo.png" alt="logo">
               </a>
            </div>
            <!-- Logo End -->
             
            <!-- Header Top Start -->
              <jsp:include page="header.jsp"></jsp:include>
            <!-- Header Top End -->
             
         </header>
         <!-- Main Header End -->
          
         <!-- Sidebar Start -->
         <aside class="seipkon-main-sidebar">
             <jsp:include page="menu.jsp"></jsp:include>
         </aside>
         <!-- End Sidebar -->
          
         <!-- Right Side Content Start -->
         <section id="content" class="seipkon-content-wrapper">
            <div class="page-content">
               <div class="container-fluid">
                   
                  <!-- Breadcromb Row Start -->
                  <div class="row">
                     <div class="col-md-12">
                        <div class="breadcromb-area">
                           <div class="row">
                              <div class="col-md-6 col-sm-6">
                                 <div class="seipkon-breadcromb-left">
                                    <h3>form validation</h3>
                                 </div>
                              </div>
                              <div class="col-md-6 col-sm-6">
                                 <div class="seipkon-breadcromb-right">
                                    <ul>
                                       <li><a href="/">home</a></li>
                                       <li>forms</li>
                                       <li>form validation</li>
                                    </ul>
                                 </div>
                              </div>
                           </div>
                        </div>
                     </div>
                  </div>
                  <!-- End Breadcromb Row -->
                   
                  <!-- Validation Form Row Start -->
                  <div class="row">
                     <div class="col-md-12">
                        <div class="page-box">
                           <div class="form-example">
                              <h3>Form Validation</h3>
                              <div class="form-validation-box">
                                 <div class="form-wrap">
                                    <form data-parsley-validate >
                                       <div class="row">
                                 
                                          <div class="col-md-6">
                                             
                                             
                                             
                                             
                                             <div class="form-group">
                                                <label class="control-label">Cityname:</label>
                                             <select class="form-control">
                                               <option>a</option>
                                               <option>b</option>
                                               <option>c</option>
                                               <option>d</option>
                                               </Select>
                                             </div>
                                          </div>
                                          </div>
                                          <div class="row">
                                          <div class="col-md-6">
                                             <div class="form-group">
                                                <label class="control-label">Areaname:</label>
                                               <Select class="form-control">
                                               <option>x</option>
                                               <option>y</option>
                                               <option>z</option>
                                               <option>w</option>
                                               </Select>
                                             </div>
                                          </div>
                                          </div>
                                          <div class="row">
                                          <div class="col-md-6">
                                             <div class="form-group">
                                                <label class="control-label">BoothNumber:</label>
                                               <Select class="form-control">
                                               <option>1</option>
                                               <option>2</option>
                                               <option>3</option>
                                               <option>4</option>
                                               </Select>
                                             </div>
                                          </div>
                                          </div>
                                          <div class="row">
                                          <div class="col-md-6">
                                             <div class="form-group">
                                                <label class="control-label">Staff</label>
                                                <input type="text" placeholder="Staff Name" class="form-control"  id="t1">
                                               
                                                <br><input type="button" value="Add" onclick="fn()" id="a1"/></br>
                                               </div>
                                               </div>
                                               </div>
                                               <div class="row">
                                              <div class="col-md-6">
                                             <div class="form-group">  
                                              
												<br><input type="button" value="Update" onclick="fn3()" style="display:none" id="u1"/></br>
												<table width="600px" height="60px" border="4" id="d0" style="display:none">
												<tr>
												<td width="200px">FNAME</td>
												<td width="400px">LN</td>
												
												
												
												</tr>
												</table>
                                             </div>
                                          </div>
                                           </div>
                                          
                                       <div class="row">
                                          <div class="col-md-12">
                                             <div class="form-group">
                                                <div class="row">
                                                   <div class="col-md-12">
                                                      <div class="form-layout-submit">
                                                         <button type="submit" class="btn btn-info" >Submit</button>
                                                         
                                                      </div>
                                                   </div>
                                                </div>
                                             </div>
                                          </div>
                                       </div>
                                    </form>
                                 </div>
                              </div>
                           </div>
                        </div>
                     </div>
                  </div>
                  <!-- End Validation Form Row -->
                   
               </div>
            </div>
             
            <!-- Footer Area Start -->
            <jsp:include page="footer.jsp"></jsp:include>
            <!-- End Footer Area -->
             
         </section>
         <!-- End Right Side Content -->
          
      </div>
      <!-- End Wrapper -->
       
       
      <!-- jQuery -->
      <script src="<%=request.getContextPath() %>/adminResources/js/jquery-3.1.0.min.js"></script>
      <!-- Bootstrap JS -->
      <script src="<%=request.getContextPath() %>/adminResources/js/bootstrap.min.js"></script>
      <!-- Perfect Scrollbar JS -->
      <script src="<%=request.getContextPath() %>/adminResources/js/jquery-perfect-scrollbar.min.js"></script>
      <!-- Toggles JS -->
      <script src="<%=request.getContextPath() %>/adminResources/js/jquery.maskedinput.min.js"></script>
      <!-- Select2 JS -->
      <script src="<%=request.getContextPath() %>/adminResources/js/select2.full.js"></script>
      <!-- Jquery parsley JS -->
      <script src="<%=request.getContextPath() %>/adminResources/js/parsley.min.js"></script>
      <!-- Custom JS -->
      <script src="<%=request.getContextPath() %>/adminResources/js/seipkon.js"></script>
   </body>

</html>
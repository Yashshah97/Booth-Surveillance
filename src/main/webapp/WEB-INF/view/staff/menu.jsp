<%@ taglib  prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<nav id="sidebar">
               <!-- Sidebar Profile Start -->
               <div class="sidebar-profile clearfix">
                  <div class="profile-avatar">
                     <img src="<%=request.getContextPath() %>/document/demo/${sessionScope.fileName}" alt="profile" />
                  </div>
                  <div class="profile-info">
                     <h3>${sessionScope.fname}</h3>
                     <p>Welcome ${sessionScope.fname} !</p>
                  </div>
               </div>
               <!-- Sidebar Profile End -->
                
               <!-- Menu Section Start -->
               <div class="menu-section">
                  <h3>General</h3>
                  <ul class="list-unstyled components">
                     <li class="active">
                        <a href="index">
                        <i class="fa fa-dashboard"></i>
                        Dashboard
                        </a>
                     </li>
                     <li>
                        <a href="#city" data-toggle="collapse" aria-expanded="false">
                        <i class="fa fa-shopping-cart"></i>
                       Manage City
                        </a>
                        <ul class="collapse list-unstyled" id="city">
                           
                           <a href="viewcity" class="collapse list-unstyled">view city</a>
                           <li><a href="viewcity">view city</a></li>
                        </ul>
                     </li>
                       <li>
                        <a href="#area" data-toggle="collapse" aria-expanded="false">
                        <i class="fa fa-shopping-cart"></i>
                        Manage Area
                        </a>
                        <ul class="collapse list-unstyled" id="area">
                          
                           
                           
                           <li><a href="viewarea">viewarea</a></li>
                        </ul>
                     </li>
                       <li>
                        <a href="#boothdetails" data-toggle="collapse" aria-expanded="false">
                        <i class="fa fa-shopping-cart"></i>
                       Manage Booth
                        </a>
                        <ul class="collapse list-unstyled" id="boothdetails">
                          
                           
                           
                           <li><a href="viewbooth">viewboothdetails</a></li>
                        </ul>
                     </li>
                       <li>
                        <a href="#ManageStaff" data-toggle="collapse" aria-expanded="false">
                        <i class="fa fa-shopping-cart"></i>
                        ManageStaff
                        </a>
                        <ul class="collapse list-unstyled" id="ManageStaff">
                           
                           
                           <li><a href="viewStaff">viewStaff</a></li>
                        </ul>
                     </li>
                      <li>
                        <a href="#allocate" data-toggle="collapse" aria-expanded="false">
                        <i class="fa fa-shopping-cart"></i>
                        Allocate Staff
                        </a>
                        <ul class="collapse list-unstyled" id="allocate">
                         
                           
                           <li><a href="viewallocate">ViewAllocatedStaff</a></li>
                          
                        </ul>
                     </li>
                     
                     <li>
                        <a href="#apps" data-toggle="collapse" aria-expanded="false">
                        <i class="fa fa-th"></i>
                        Feedback
                        </a>
                        <ul class="collapse list-unstyled" id="apps">
                           <li><a href="feedback">Feedback</a></li>
                           <li><a href="viewfeedback">ViewFeedback</a></li>
                          
                        </ul>
                     </li>
                     <li>
                        <a href="#mail" data-toggle="collapse" aria-expanded="false">
                        <i class="fa fa-envelope"></i>
                        mailbox
                        </a>
                        <ul class="collapse list-unstyled" id="mail">
                           <li><a href="compose.html">compose</a></li>
                           <li><a href="inbox.html">inbox</a></li>
                           <li><a href="mail-read.html">read</a></li>
                        </ul>
                     </li>
                     <li>
                        <a href="#ui_elements" data-toggle="collapse" aria-expanded="false">
                        <i class="fa fa-laptop"></i>
                        UI Elements
                        </a>
                        <ul class="collapse list-unstyled" id="ui_elements">
                           <li><a href="ui_button.html">Button</a></li>
                           <li><a href="ui_panels.html">Panels</a></li>
                           <li>
                              <a href="#icon" data-toggle="collapse" aria-expanded="false">
                              Icons
                              </a>
                              <ul class="collapse list-unstyled" id="icon">
                                 <li><a href="icon-fontawesome.html">Font awesome</a></li>
                                 <li><a href="icon-glyphicons.html">Glyphicons</a></li>
                                 <li><a href="icon-themify.html">Themify Icons</a></li>
                              </ul>
                           </li>
                           <li><a href="ui_tab_accordion.html">Tabs & Accordions</a></li>
                           <li><a href="ui_sweet_alerts.html">sweet Alerts</a></li>
                           <li><a href="ui_progressbars.html">Progress bars</a></li>
                           <li><a href="ui_carousel.html">Carousel</a></li>
                           <li><a href="ui_breadcrumbs.html">breadcrumbs</a></li>
                           <li><a href="ui_pagination.html">pagination</a></li>
                        </ul>
                     </li>
                  </ul>
               </div>
               <!-- Menu Section End -->
                
               <!-- Menu Section Start -->
               <div class="menu-section">
                  <h3>Forms,Table & widget</h3>
                  <ul class="list-unstyled components">
                     <li>
                        <a href="seipcon_widget.html">
                        <i class="fa fa-th"></i>
                        Widgets
                        </a>
                     </li>
                     <li>
                        <a href="#charts" data-toggle="collapse" aria-expanded="false">
                        <i class="fa fa-pie-chart"></i>
                        Charts
                        </a>
                        <ul class="collapse list-unstyled" id="charts">
                           <li><a href="chartsjs.html">chart js</a></li>
                           <li><a href="morrris.html">morris</a></li>
                           <li><a href="sparkline.html">sparkline</a></li>
                        </ul>
                     </li>
                     <li>
                        <a href="#forms" data-toggle="collapse" aria-expanded="false">
                        <i class="fa fa-edit"></i>
                        Forms
                        </a>
                        <ul class="collapse list-unstyled" id="forms">
                           <li><a href="general-form.html">General Form</a></li>
                           <li><a href="advance-components.html">Advance Components</a></li>
                           <li><a href="form-layouts.html">Form layouts</a></li>
                           <li><a href="formvalidation">form validation</a></li>
                           <li><a href="form-wizards.html">form wizards</a></li>
                        </ul>
                     </li>
                     <li>
                        <a href="#table" data-toggle="collapse" aria-expanded="false">
                        <i class="fa fa-table"></i>
                        Tables
                        </a>
                        <ul class="collapse list-unstyled" id="table">
                           <li><a href="basic-table.html">basic table</a></li>
                           <li><a href="advancetable">table Advance</a></li>
                        </ul>
                     </li>
                  </ul>
               </div>
               <!-- Menu Section End -->
                
               <!-- Menu Section Start -->
               <div class="menu-section">
                  <h3>Extra components</h3>
                  <ul class="list-unstyled components">
                     <li>
                        <a href="#ex_components" data-toggle="collapse" aria-expanded="false">
                        <i class="fa fa-book"></i>
                        Additional Pages
                        </a>
                        <ul class="collapse list-unstyled" id="ex_components">
                           <li><a href="profile.html">profile page</a></li>
                           <li><a href="invoice.html">Invoice</a></li>
                           <li><a href="gallery.html">gallery</a></li>
                           <li><a href="blank.html">Blank</a></li>
                        </ul>
                     </li>
                     <li>
                        <a href="#ex_authentication" data-toggle="collapse" aria-expanded="false">
                        <i class="fa fa-unlock-alt"></i>
                        Authentication 
                        </a>
                        <ul class="collapse list-unstyled" id="ex_authentication">
                           <li><a href="login">Login</a></li>
                           <li><a href="register">Register</a></li>
                           <li><a href="lockscreen.html">Lockscreen</a></li>
                           <li><a href="recover-pass.html">Recover password</a></li>
                        </ul>
                     </li>
                     <li>
                        <a href="#ex_error" data-toggle="collapse" aria-expanded="false">
                        <i class="fa fa-exclamation-circle"></i>
                        Error Pages 
                        </a>
                        <ul class="collapse list-unstyled" id="ex_error">
                           <li><a href="error-404.html">404 Not Found</a></li>
                           <li><a href="error-505.html">505 Forbidden</a></li>
                           <li><a href="error-500.html">500 Internal Server</a></li>
                           <li><a href="error-503.html">503 Service Unavailable</a></li>
                        </ul>
                     </li>
                     <li>
                        <a href="#ex_multlable" data-toggle="collapse" aria-expanded="false">
                        <i class="fa fa-map-signs"></i>
                        Multilevel
                        </a>
                        <ul class="collapse list-unstyled" id="ex_multlable">
                           <li><a href="#">Level One</a></li>
                           <li>
                              <a href="#ex_multlable_2" data-toggle="collapse" aria-expanded="false">
                              Level One
                              </a>
                              <ul class="collapse list-unstyled" id="ex_multlable_2">
                                 <li><a href="#">Level Two</a></li>
                                 <li>
                                    <a href="#ex_multlable_3" data-toggle="collapse" aria-expanded="false">
                                    Level Two
                                    </a>
                                    <ul class="collapse list-unstyled" id="ex_multlable_3">
                                       <li><a href="#">Level Three</a></li>
                                       <li><a href="#">Level Three</a></li>
                                    </ul>
                                 </li>
                              </ul>
                           </li>
                           <li><a href="#">Level One</a></li>
                        </ul>
                     </li>
                  </ul>
               </div>
               <!-- Menu Section End -->
                
            </nav>

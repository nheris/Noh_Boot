<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>${board} List</title>

    <c:import url="../temp/css.jsp"></c:import>

</head>

<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Sidebar -->
        <c:import url="../temp/sidebar.jsp"></c:import>
        <!-- End of Sidebar -->

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <!-- Topbar -->
                <c:import url="../temp/topbar.jsp"></c:import>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <div class="d-sm-flex align-items-center justify-content-between mb-4">
                        <h1 class="h3 mb-0 text-gray-800">${board}</h1>
                        <a href="#" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                                class="fas fa-download fa-sm text-white-50"></i> Generate Report</a>
                    </div>

                    <!-- Content Row 실제 페이지 내용-->
                    <div class="row">
                        <table class="table table-hover">
                            <thead>
                                <th>No</th>
                                <th>Title</th>
                                <th>Writer</th>
                                <th>Date</th>
                                <th>Hit</th>
                            </thead>
                            <tbody>
								<c:forEach items="${list}" var="vo">
									<tr>
										<td>${vo.boardNum}</td>
										<td><a href="./detail?boardNum=${vo.boardNum}"> ${vo.boardTitle}</a></td>
										<td>${vo.boardWriter}</td>
										<td>${vo.boardDate}</td>
										<td>${vo.boardHit}</td>
									</tr>
								</c:forEach>
                            </tbody>


                        </table>
                    </div>
                    
                    <!-- 페이징 -->
                    <div class="row">
                    	<nav aria-label="Page navigation example">
						  <ul class="pagination">
						    <li class="page-item">
						      <a class="page-link" href="./list?page=${pager.startNum-1}&kind=${kind}&search=${search}" aria-label="Previous">
						        <span aria-hidden="true">&laquo;</span>
						      </a>
						    </li>
						    <c:forEach begin="${pager.startNum}" end="${pager.lastNum}" var="i">
							    <li class="page-item"><a class="page-link" href="./list?page=${i}&kind=${kind}&search=${search}">${i}</a></li>
						    </c:forEach>
						    
						    <li class="page-item">
						      <a class="page-link" href="./list?page=${pager.lastNum+1}&kind=${kind}&search=${search}" aria-label="Next">
						        <span aria-hidden="true">&raquo;</span>
						      </a>
						    </li>
						  </ul>
						</nav>
                        <div>
                            <a href="add" class="btn btn-primary">글쓰기</a>
                        </div>
                    </div>

                </div>
                <!-- /.container-fluid -->

            </div>
            <!-- End of Main Content -->

            <!-- Footer -->
            <c:import url="../temp/footer.jsp"></c:import>
            <!-- End of Footer -->

        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>

 

    <c:import url="../temp/script.jsp"></c:import>

</body>

</html>
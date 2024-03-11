<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title></title>

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
                        <h1 class="h3 mb-0 text-gray-800">Dashboard</h1>
                        <a href="#" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                                class="fas fa-download fa-sm text-white-50"></i> Generate Report</a>
                    </div>

                    <!-- Content Row -->
                    <div class="row">
                    	<!-- Spring Form -->
						<form:form modelAttribute="memberVO" method="post" cssClass="" > <!-- 디자인 class x -->
							<!-- ID --><!-- 타입이미 -->
									<!-- modelAttribute의 겟터이름 -->
							<form:input path="username"/><!-- 알아서 네임도 -->
							<form:errors path="username"></form:errors>
							
							<!-- Password -->
							<form:password path="password"/>
							<form:errors path="password"></form:errors><!-- VO에  @NotBlank 하고 이거 하면 안넘어감-->
							
							<!-- Password 검증 -->
							<%-- <form:password path="password2"/> --%>
							
							<!-- 전화번호 -->
							<form:input path="phone"/>
							
							<!-- 이메일 -->
							<form:input path="email"/>
							
							<!-- 주소 -->
							<form:input path="address"/>
							
							<!-- 이름 -->
							<form:input path="name"/>
							
							<!-- 버튼 -->
							<form:button>가입</form:button><!-- action안써주면 현재주소로 -->
							

						</form:form>
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
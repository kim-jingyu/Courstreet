<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ include file="../includes/header.jsp" %>

<div id="layoutSidenav_content">
    <main>
        <div class="container-fluid px-4">
            <h1 class="mt-4">장소 관리</h1>
            <ol class="breadcrumb mb-4">
                <li class="breadcrumb-item"><a href="../index.jsp">Dashboard</a></li>
                <li class="breadcrumb-item active">Tables</li>
            </ol>
            <div class="card mb-4">
                <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
                    장소 등록
                </button>
                <div class="card-body">
                    DataTables is a third party plugin that is used to generate the demo table below. For more
                    information about DataTables, please visit the
                    <a target="_blank" href="https://datatables.net/">official DataTables documentation</a>
                    .
                </div>
            </div>
            <div class="card mb-4">
                <div class="card-header">
                    <i class="fas fa-table me-1"></i>
                    DataTable Example
                </div>
                <div class="card-body">
                    <table id="datatablesSimple">
                        <thead>
                        <tr>
                            <th>PLACE_ID</th>
                            <th>NAME</th>
                            <th>PHONE</th>
                            <th>START_TIME</th>
                            <th>END_TIME</th>
                            <th>START_AGE</th>
                            <th>END_AGE</th>
                            <th>WITH_WHOM</th>
                            <th>FLOOR</th>
                            <th>LOCATION</th>
                            <th>TYPE</th>
                            <th>CATEGORY</th>
                            <th>THEME1</th>
                            <th>THEME2</th>
                            <th>THEME3</th>
                            <th>WEIGHT1</th>
                            <th>WEIGHT2</th>
                            <th>WEIGHT3</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${list}" var="place">

                            <tr>
                                <td><c:out value="${place.placeId}"/></td>
                                <td><c:out value="${place.name}"/></td>
                                <td><c:out value="${place.phone}"/></td>
                                <td><fmt:formatDate pattern="HH:mm" value="${place.startTime}"/></td>
                                <td><fmt:formatDate pattern="HH:mm" value="${place.endTime}"/></td>
                                <td><c:out value="${place.startAge}"/></td>
                                <td><c:out value="${place.endAge}"/></td>
                                <td><c:out value="${place.withWhom}"/></td>
                                <td><c:out value="${place.floor}"/></td>
                                <td><c:out value="${place.location}"/></td>
                                <td><c:out value="${place.type}"/></td>
                                <td><c:out value="${place.category}"/></td>
                                <td><c:out value="${place.theme1}"/></td>
                                <td><c:out value="${place.theme2}"/></td>
                                <td><c:out value="${place.theme3}"/></td>
                                <td><c:out value="${place.weight1}"/></td>
                                <td><c:out value="${place.weight2}"/></td>
                                <td><c:out value="${place.weight3}"/></td>


                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </main>
    <footer class="py-4 bg-light mt-auto">
        <div class="container-fluid px-4">
            <div class="d-flex align-items-center justify-content-between small">
                <div class="text-muted">Copyright &copy; Your Website 2023</div>
                <div>
                    <a href="#">Privacy Policy</a>
                    &middot;
                    <a href="#">Terms &amp; Conditions</a>
                </div>
            </div>
        </div>
    </footer>
</div>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="exampleModalLabel">Modal title!!!!!</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <%--                TODO required 붙이기--%>

                <form role="form" action="/place/register" method="post">
                    <div class="mb-3">
                        <label for="name" class="form-label required">NAME</label>
                        <input type="text" class="form-control" id="name" name="name" required>
                    </div>
                    <div class="mb-3">
                        <label for="phone" class="form-label">PHONE</label>
                        <input type="tel" class="form-control" id="phone" name="phone">
                    </div>
                    <div class="mb-3">
                        <label for="startTime" class="form-label">START_TIME</label>
                        <input type="time" class="form-control" id="startTime" name="startTime" required>
                    </div>
                    <div class="mb-3">
                        <label for="endTime" class="form-label">END_TIME</label>
                        <input type="time" class="form-control" id="endTime" name="endTime" required>
                    </div>
                    <div class="mb-3">
                        <label for="startAge" class="form-label">START_AGE</label>
                        <input type="number" class="form-control" id="startAge" name="startAge" required>
                    </div>
                    <div class="mb-3">
                        <label for="endAge" class="form-label">END_AGE</label>
                        <input type="number" class="form-control" id="endAge" name="endAge" required>
                    </div>
                    <div class="mb-3">
                        <label for="withWhom" class="form-label">WITH_WHOM</label>
                        <input type="text" class="form-control" id="withWhom" name="withWhom" required>
                    </div>
                    <div class="mb-3">
                        <label for="floor" class="form-label">FLOOR</label>
                        <input type="number" class="form-control" id="floor" name="floor" required>
                    </div>
                    <div class="mb-3">
                        <label for="location" class="form-label">LOCATION</label>
                        <input type="text" class="form-control" id="location" name="location" required>
                    </div>
                    <div class="mb-3">
                        <label for="type" class="form-label">TYPE</label>
                        <input type="text" class="form-control" id="type" name="type" required>
                    </div>
                    <div class="mb-3">
                        <label for="category" class="form-label">CATEGORY</label>
                        <input type="text" class="form-control" id="category" name="category" required>
                    </div>
                    <div class="mb-3">
                        <label for="theme1" class="form-label">THEME1</label>
                        <input type="text" class="form-control" id="theme1" name="theme1" required>
                    </div>
                    <div class="mb-3">
                        <label for="theme2" class="form-label">THEME2</label>
                        <input type="text" class="form-control" id="theme2" name="theme2">
                    </div>
                    <div class="mb-3">
                        <label for="theme3" class="form-label">THEME3</label>
                        <input type="text" class="form-control" id="theme3" name="theme3">
                    </div>
                    <div class="mb-3">
                        <label for="weight1" class="form-label">WEIGHT1</label>
                        <input type="number" class="form-control" id="weight1" name="weight1">
                    </div>
                    <div class="mb-3">
                        <label for="weight2" class="form-label">WEIGHT2</label>
                        <input type="number" class="form-control" id="weight2" name="weight2">
                    </div>
                    <div class="mb-3">
                        <label for="weight3" class="form-label">WEIGHT3</label>
                        <input type="number" class="form-control" id="weight3" name="weight3">
                    </div>
                    <button type="submit" class="btn btn-primary">Save changes</button>
                </form>
            </div>
        </div>
    </div>
</div>
<%@ include file="../includes/footer.jsp" %>

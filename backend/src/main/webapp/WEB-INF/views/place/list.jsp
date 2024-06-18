<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
                            <th>수정</th>
                            <th>삭제</th>
                            <th>ID</th>
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
                                <td>
                                    <button type="button" class="btn btn-warning btn-sm"
                                            onclick="updatePlace(this, '${place.placeId}')">수정</button>
                                </td>
                                <td>
                                    <button type="button" class="btn btn-danger btn-sm"
                                            onclick="deletePlace(this, '${place.placeId}')">Delete</button>
                                </td>
                                <td><c:out value="${place.placeId}"/></td>
                                <td><c:out value="${place.name}"/></td>
                                <td><c:out value="${place.phone}"/></td>
                                <td><fmt:parseDate value="${place.startTime}"
                                               pattern="yyyy-MM-dd'T'HH:mm" var="startTime" type="both"/>
                                <fmt:formatDate pattern="HH:mm" value="${startTime}"/></td>
                                <td><fmt:parseDate value="${place.endTime}"
                                               pattern="yyyy-MM-dd'T'HH:mm" var="endTime" type="both"/>
                                <fmt:formatDate pattern="HH:mm" value="${endTime}"/></td>
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
                <%-- TODO required 붙이기 --%>
                <%-- TODO 모달 입력 화면 조정 --%>
                <%-- TODO 모달 입력 alert 수정 --%>


                <form role="form" action="/place/register" method="post" id="registerForm">
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
                        <input type="time" class="form-control" id="startTime" name="time1" required>
                    </div>
                    <div class="mb-3">
                        <label for="endTime" class="form-label">END_TIME</label>
                        <input type="time" class="form-control" id="endTime" name="time2" required>
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
<script>
    /* 장소 등록 */
    // weight 기본값 설정
    document.querySelector('#registerForm').addEventListener('submit', function(event) {
        if (!document.getElementById('weight1').value) {
            document.getElementById('weight1').value = 0;
        }
        if (!document.getElementById('weight2').value) {
            document.getElementById('weight2').value = 0;
        }
        if (!document.getElementById('weight3').value) {
            document.getElementById('weight3').value = 0;
        }
    });

    // 입력 제약사항 체크
    document.querySelector('#registerForm').addEventListener('submit', function(event) {
        // START_AGE와 END_AGE 값 확인
        const startAge = document.getElementById('startAge').value;
        const endAge = document.getElementById('endAge').value;

        // START_TIME과 END_TIME 값 확인
        const startTime = document.getElementById('startTime').value;
        const endTime = document.getElementById('endTime').value;

        if (parseInt(endAge) < parseInt(startAge)) {
            alert('END_AGE는 START_AGE보다 크거나 같아야 합니다.');
            event.preventDefault();
            return;
        }

        if (parseInt(startAge) < 0 || parseInt(startAge) > parseInt(endAge)) {
            alert('START_AGE는 0 이상이어야 하며, END_AGE 이하여야 합니다.');
            event.preventDefault();
            return;
        }

        if (startTime >= endTime) {
            alert('START_TIME은 END_TIME보다 작아야 합니다.');
            event.preventDefault();
            return;
        }
    });

    /* 장소 삭제 */
    function deletePlace(button, id) {
        if (confirm('장소를 정말 삭제하시겠습니까?')) {
            $.ajax({
                url: '/place/remove/' + id,
                type: 'POST',
                success: function(result) {
                    $(button).closest('tr').remove();
                    alert('장소 삭제 성공');
                },
                error: function(err) {
                    console.log(err)
                    alert("장소 삭제 실패");
                }
            });
        }
    }

    /* 장소 수정 */
    function updatePlace(button, id) {
        const row = button.closest('tr');
        const cells = row.querySelectorAll('td');

        if (button.textContent === 'Save') {
            // If 'Save' is clicked, gather the data and send it to the server
            const updatedData = {
                id: id,
                name: cells[3].querySelector('input').value,
                phone: cells[4].querySelector('input').value,
                startTime: cells[5].querySelector('input').value,
                endTime: cells[6].querySelector('input').value,
                startAge: cells[7].querySelector('input').value,
                endAge: cells[8].querySelector('input').value,
                withWhom: cells[9].querySelector('input').value,
                floor: cells[10].querySelector('input').value,
                location: cells[11].querySelector('input').value,
                type: cells[12].querySelector('input').value,
                category: cells[13].querySelector('input').value,
                theme1: cells[14].querySelector('input').value,
                theme2: cells[15].querySelector('input').value,
                theme3: cells[16].querySelector('input').value,
                weight1: cells[17].querySelector('input').value,
                weight2: cells[18].querySelector('input').value,
                weight3: cells[19].querySelector('input').value,
            };

            // Send the updated data to the server using the Fetch API
            fetch(`/update`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(updatedData)
            })
                .then(response => response.json())
                .then(data => {
                    if (data.success) {
                        alert('Row updated successfully!');
                        // Revert cells to text after saving
                        revertRowToText(cells, updatedData);
                        button.textContent = 'Update';
                    } else {
                        alert('Error updating row: ' + data.message);
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                    alert('Error updating row: ' + error.message);
                });
        } else {
            // Change text content to input fields for editing
            changeRowToInputs(cells);
            button.textContent = 'Save';
        }
    }

    // Function to change row cells to input fields
    function changeRowToInputs(cells) {
        for (let i = 2; i < cells.length; i++) {
            if (cells[i].querySelector('input') === null) { // Skip the update and delete buttons
                const currentText = cells[i].textContent;
                cells[i].innerHTML = `<input type="text" class="form-control" value="${currentText}">`;
            }
        }
    }

    // Function to revert row cells to plain text after saving
    function revertRowToText(cells, updatedData) {
        cells[3].innerHTML = updatedData.name;
        cells[4].innerHTML = updatedData.phone;
        cells[5].innerHTML = updatedData.startTime;
        cells[6].innerHTML = updatedData.endTime;
        cells[7].innerHTML = updatedData.startAge;
        cells[8].innerHTML = updatedData.endAge;
        cells[9].innerHTML = updatedData.withWhom;
        cells[10].innerHTML = updatedData.floor;
        cells[11].innerHTML = updatedData.location;
        cells[12].innerHTML = updatedData.type;
        cells[13].innerHTML = updatedData.category;
        cells[14].innerHTML = updatedData.theme1;
        cells[15].innerHTML = updatedData.theme2;
        cells[16].innerHTML = updatedData.theme3;
        cells[17].innerHTML = updatedData.weight1;
        cells[18].innerHTML = updatedData.weight2;
        cells[19].innerHTML = updatedData.weight3;
    }
</script>
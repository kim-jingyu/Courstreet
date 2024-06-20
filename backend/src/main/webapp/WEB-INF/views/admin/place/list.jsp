<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.hyundairoad.hyundairoad.util.DateTimeFormatterUtil" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Place Management</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        .sidebar {
            width: 210px;
            height: 100vh;
            position: fixed;
            background-color: #343a40;
            color: white;
            display: flex;
            flex-direction: column;
            padding-top: 60px;
            padding-left: 20px;
            padding-right: 20px;
        }
        .sidebar-item {
            padding: 10px;
            margin: 10px 0;
            cursor: pointer;
            color: white;
        }
        .sidebar-item:hover {
            background-color: #495057;
        }
        .content {
            margin-left: 210px;
            font-size: 15px;
            padding: 60px 20px 20px;
        }
        .table-buttons {
            display: flex;
            gap: 5px;
        }
        table {
            width: 100%;
            table-layout: fixed;
            word-wrap: break-word;
        }
        th, td {
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
            padding: 0.5rem 0 !important;
        }
        nav.navbar {
            position: fixed;
            top: 0;
            width: 100%;
            z-index: 1000;
        }
    </style>
    <!-- jQuery 먼저 로드 -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha384-KyZXEAg3QhqLMpG8r+Knujsl5/7n2nE2+5ir2V1+PS0zlNQ2FPLkF6SoIfjpHjoD" crossorigin="anonymous"></script>
    <!-- DataTables CSS 및 JS 로드 -->
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.min.css">
    <script src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js"></script>
    <!-- Bootstrap CSS 및 JS 로드 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="#">관리자 페이지</a>
    <div class="container-fluid">
        <form class="d-flex ms-auto">
            <input class="form-control me-2" type="search" placeholder="Search for..." aria-label="Search">
            <button class="btn btn-outline-success" type="submit">Search</button>
        </form>
        <button class="btn btn-outline-light ms-2">로그아웃</button>
    </div>
</nav>

<div class="sidebar">
    <div class="sidebar-item">Dashboard</div>
    <div class="sidebar-item">Layouts</div>
    <div class="sidebar-item">Pages</div>
    <div class="sidebar-item">Charts</div>
    <div class="sidebar-item">Tables</div>
</div>

<div class="content">
    <h1 class="mt-4">장소 리스트</h1>
    <button type="button" class="btn btn-primary mb-4" data-bs-toggle="modal" data-bs-target="#addPlaceModal">장소 추가</button>
    <table id="placeTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 50px;">ID</th>
            <th>Name</th>
            <th>Phone</th>
            <th style="width: 60px;">Start<br/>Time</th>
            <th style="width: 60px;">End<br/>Time</th>
            <th style="width: 60px;">Start<br/>Age</th>
            <th style="width: 60px;">End<br/>Age</th>
            <th style="width: 50px;">With<br/>Whom</th>
            <th style="width: 50px;">Floor</th>
            <th style="width: 70px;">Location</th>
            <th>Category</th>
            <th>Theme1</th>
            <th>Theme2</th>
            <th style="width: 70px;">Theme3</th>
            <th style="width: 70px;">Weight1</th>
            <th style="width: 70px;">Weight2</th>
            <th style="width: 70px;">Weight3</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${list}" var="place">
            <tr>
                <td><c:out value="${place.placeId}"/></td>
                <td><c:out value="${place.name}"/></td>
                <td><c:out value="${place.phone}"/></td>
                <td>${DateTimeFormatterUtil.formatTime(place.startTime)}</td>
                <td>${DateTimeFormatterUtil.formatTime(place.endTime)}</td>
                <td><c:out value="${place.startAge}"/></td>
                <td><c:out value="${place.endAge}"/></td>
                <td><c:out value="${place.withWhom}"/></td>
                <td><c:out value="${place.floor}"/></td>
                <td><c:out value="${place.location}"/></td>
                <td><c:out value="${place.category}"/></td>
                <td><c:out value="${place.theme1}"/></td>
                <td><c:out value="${place.theme2}"/></td>
                <td><c:out value="${place.theme3}"/></td>
                <td><c:out value="${place.weight1}"/></td>
                <td><c:out value="${place.weight2}"/></td>
                <td><c:out value="${place.weight3}"/></td>
                <td class="table-buttons">
                    <button type="button" class="btn btn-warning btn-sm" onclick="editPlace(${place.placeId})">수정</button>
                    <button type="button" class="btn btn-danger btn-sm" onclick="deletePlace(${place.placeId})">삭제</button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<!-- Add Place Modal -->
<div class="modal fade" id="addPlaceModal" tabindex="-1" aria-labelledby="addPlaceModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="addPlaceModalLabel">장소 추가</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form id="addPlaceForm">
                    <div class="mb-3">
                        <label for="placeName" class="form-label">Name</label>
                        <input type="text" class="form-control" id="placeName" required>
                    </div>
                    <div class="mb-3">
                        <label for="placePhone" class="form-label">Phone</label>
                        <input type="text" class="form-control" id="placePhone" required>
                    </div>
                    <div class="mb-3">
                        <label for="placeStartTime" class="form-label">Start Time</label>
                        <input type="time" class="form-control" id="placeStartTime" required>
                    </div>
                    <div class="mb-3">
                        <label for="placeEndTime" class="form-label">End Time</label>
                        <input type="time" class="form-control" id="placeEndTime" required>
                    </div>
                    <div class="mb-3">
                        <label for="placeStartAge" class="form-label">Start Age</label>
                        <input type="number" class="form-control" id="placeStartAge" required>
                    </div>
                    <div class="mb-3">
                        <label for="placeEndAge" class="form-label">End Age</label>
                        <input type="number" class="form-control" id="placeEndAge" required>
                    </div>
                    <div class="mb-3">
                        <label for="placeWithWhom" class="form-label">With Whom</label>
                        <input type="text" class="form-control" id="placeWithWhom" required>
                    </div>
                    <div class="mb-3">
                        <label for="placeFloor" class="form-label">Floor</label>
                        <input type="number" class="form-control" id="placeFloor" required>
                    </div>
                    <div class="mb-3">
                        <label for="placeLocation" class="form-label">Location</label>
                        <input type="text" class="form-control" id="placeLocation" required>
                    </div>
                    <div class="mb-3">
                        <label for="placeCategory" class="form-label">Category</label>
                        <input type="text" class="form-control" id="placeCategory" required>
                    </div>
                    <div class="mb-3">
                        <label for="placeTheme1" class="form-label">Theme1</label>
                        <input type="text" class="form-control" id="placeTheme1" required>
                    </div>
                    <div class="mb-3">
                        <label for="placeTheme2" class="form-label">Theme2</label>
                        <input type="text" class="form-control" id="placeTheme2">
                    </div>
                    <div class="mb-3">
                        <label for="placeTheme3" class="form-label">Theme3</label>
                        <input type="text" class="form-control" id="placeTheme3">
                    </div>
                    <div class="mb-3">
                        <label for="placeWeight1" class="form-label">Weight1</label>
                        <input type="number" class="form-control" id="placeWeight1">
                    </div>
                    <div class="mb-3">
                        <label for="placeWeight2" class="form-label">Weight2</label>
                        <input type="number" class="form-control" id="placeWeight2">
                    </div>
                    <div class="mb-3">
                        <label for="placeWeight3" class="form-label">Weight3</label>
                        <input type="number" class="form-control" id="placeWeight3">
                    </div>
                    <button type="submit" class="btn btn-primary">Save changes</button>
                </form>
            </div>
        </div>
    </div>
</div>

<script>
    $(document).ready(function() {
        $('#placeTable').DataTable({
            "pageLength": 5,
            "columnDefs": [
                { "width": "50px", "targets": 0 },  // ID column
                { "width": "60px", "targets": 3 },  // Start Time column
                { "width": "60px", "targets": 4 },  // End Time column
                { "width": "60px", "targets": 5 },  // Start Age column
                { "width": "60px", "targets": 6 },  // End Age column
                { "width": "50px", "targets": 7 },  // With Whom column
                { "width": "50px", "targets": 8 },  // Floor column
                { "width": "70px", "targets": 9 },  // Location column
                { "width": "70px", "targets": 13 }, // Theme3 column
                { "width": "70px", "targets": 14 }, // Weight1 column
                { "width": "70px", "targets": 15 }, // Weight2 column
                { "width": "70px", "targets": 16 }  // Weight3 column
            ]
        });
    });

    $('#addPlaceForm').on('submit', function(event) {
        event.preventDefault();
        const place = {
            name: $('#placeName').val(),
            phone: $('#placePhone').val(),
            startTime: $('#placeStartTime').val(),
            endTime: $('#placeEndTime').val(),
            startAge: $('#placeStartAge').val(),
            endAge: $('#placeEndAge').val(),
            withWhom: $('#placeWithWhom').val(),
            floor: $('#placeFloor').val(),
            location: $('#placeLocation').val(),
            category: $('#placeCategory').val(),
            theme1: $('#placeTheme1').val(),
            theme2: $('#placeTheme2').val(),
            theme3: $('#placeTheme3').val(),
            weight1: $('#placeWeight1').val(),
            weight2: $('#placeWeight2').val(),
            weight3: $('#placeWeight3').val()
        };

        $.ajax({
            url: '/admin/place/register',
            type: 'POST',
            data: JSON.stringify(place),
            contentType: 'application/json; charset=UTF-8',
            success: function(response) {
                $('#placeTable').DataTable().row.add([
                    place.name,
                    place.phone,
                    place.startTime,
                    place.endTime,
                    place.startAge,
                    place.endAge,
                    place.withWhom,
                    place.floor,
                    place.location,
                    place.category,
                    place.theme1,
                    place.theme2,
                    place.theme3,
                    place.weight1,
                    place.weight2,
                    place.weight3,
                    '<button type="button" class="btn btn-warning btn-sm" onclick="editPlace(' + response.placeId + ')">수정</button>' +
                    '<button type="button" class="btn btn-danger btn-sm" onclick="deletePlace(' + response.placeId + ')">삭제</button>'
                ]).draw(false);

                // 모달 닫기
                $('#addPlaceModal').modal('hide');
            },
            error: function(error) {
                console.error('Error:', error);
            }
        });
    });

    function deletePlace(placeId) {
        if (confirm('정말 삭제하시겠습니까?')) {
            $.ajax({
                url: '/admin/place/remove/' + placeId,
                type: 'DELETE',
                success: function(response) {
                    location.reload();
                },
                error: function(error) {
                    console.error('Error:', error);
                }
            });
        }
    }

    function editPlace(placeId) {
        // 장소 수정 로직 구현
        // 기존 장소 정보를 가져와서 폼에 채운 뒤, 변경 사항을 제출할 수 있도록 합니다.
        $.ajax({
            url: '/admin/place/' + placeId,
            type: 'GET',
            success: function(response) {
                $('#placeName').val(response.name);
                $('#placePhone').val(response.phone);
                $('#placeStartTime').val(response.startTime);
                $('#placeEndTime').val(response.endTime);
                $('#placeStartAge').val(response.startAge);
                $('#placeEndAge').val(response.endAge);
                $('#placeWithWhom').val(response.withWhom);
                $('#placeFloor').val(response.floor);
                $('#placeLocation').val(response.location);
                $('#placeCategory').val(response.category);
                $('#placeTheme1').val(response.theme1);
                $('#placeTheme2').val(response.theme2);
                $('#placeTheme3').val(response.theme3);
                $('#placeWeight1').val(response.weight1);
                $('#placeWeight2').val(response.weight2);
                $('#placeWeight3').val(response.weight3);

                $('#addPlaceModal').modal('show');

                $('#addPlaceForm').off('submit').on('submit', function(event) {
                    event.preventDefault();
                    const updatedPlace = {
                        placeId: placeId,
                        name: $('#placeName').val(),
                        phone: $('#placePhone').val(),
                        startTime: $('#placeStartTime').val(),
                        endTime: $('#placeEndTime').val(),
                        startAge: $('#placeStartAge').val(),
                        endAge: $('#placeEndAge').val(),
                        withWhom: $('#placeWithWhom').val(),
                        floor: $('#placeFloor').val(),
                        location: $('#placeLocation').val(),
                        category: $('#placeCategory').val(),
                        theme1: $('#placeTheme1').val(),
                        theme2: $('#placeTheme2').val(),
                        theme3: $('#placeTheme3').val(),
                        weight1: $('#placeWeight1').val(),
                        weight2: $('#placeWeight2').val(),
                        weight3: $('#placeWeight3').val()
                    };

                    $.ajax({
                        url: '/admin/place/update',
                        type: 'PUT',
                        data: JSON.stringify(updatedPlace),
                        contentType: 'application/json; charset=UTF-8',
                        success: function(response) {
                            location.reload();
                        },
                        error: function(error) {
                            console.error('Error:', error);
                        }
                    });
                });
            },
            error: function(error) {
                console.error('Error:', error);
            }
        });
    }
</script>
</body>
</html>

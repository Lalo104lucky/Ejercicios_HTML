<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Gestión de Personas</title>
    <%@include file="../../layouts/head.jsp" %>
</head>
<body>
<%@include file="../../layouts/navbar.jsp" %>

<h1>Index de Personas</h1>

<div class="container mt-5">
    <div class="row">
        <div class="col-12">
            <c:if test="${param['result']==true}">
                <div class="alert alert-success alert-dismissible fade show" role="alert" id="success-alert">
                    <p><c:out value="${param['message']}"></c:out></p>
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
            </c:if>
            <c:if test="${param['result']==false}">
                <div class="alert alert-danger alert-dismissible fade show" role="alert" id="alert-alert">
                    <p><c:out value="${param['message']}"></c:out></p>
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
            </c:if>
            <div class="card">
                <div class="card-header">
                    <div class="row">
                        <div class="col-6"> PERSONAS</div>
                        <div class="col-6 text-end">
                            <a href="createPerson" class="btn btn-outline-success btn-sm"
                            >Registrar Personas</a>
                        </div>
                    </div>
                </div>
                <div class="card-body">
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Perfil</th>
                            <th scope="col">Nombre</th>
                            <th scope="col">Apellido</th>
                            <th scope="col">Usuario</th>
                            <th scope="col">Rol</th>
                            <th scope="col">Edad</th>
                            <th scope="col">Fecha Nacimiento</th>
                            <th scope="col">Correo</th>
                            <th scope="col">Telefono</th>
                            <th scope="col">Acciones</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${personList}" var="person" varStatus="status">
                            <tr>
                                <th scope="row"><c:out value="${status.count}"></c:out></th>
                                <td><img src="data:image/jpg;base64, ${person.image}" style="height: 50px; width: 50px"
                                         class="rounded-circle"></td>
                                <td><c:out value="${person.name}"></c:out></td>
                                <td><c:out value="${person.lastname}"></c:out></td>
                                <td><c:out value="${person.username}"></c:out></td>
                                <td><c:out value="${person.role}"></c:out></td>
                                <td><c:out value="${person.age}"></c:out></td>
                                <td><fmt:formatDate value="${person.birthday}"
                                                    pattern="dd/mm/yyyy"></fmt:formatDate></td>
                                <td><c:out value="${person.email}"></c:out></td>
                                <td><c:out value="${person.phone}"></c:out></td>
                                <td>
                                    <a href="updatePerson?id=${person.id}" class="btn btn-warning btn-small">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                             fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">
                                            <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
                                            <path fill-rule="evenodd"
                                                  d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/>
                                        </svg>
                                    </a>
                                    <a class="btn btn-danger btn-small"
                                       data-bs-toggle="modal"
                                       data-bs-target="#deletePerson-${person.id}"
                                    >
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                             fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
                                            <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5Zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5Zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6Z"/>
                                            <path d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1ZM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118ZM2.5 3h11V2h-11v1Z"/>
                                        </svg>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>
                </div>
            </div>

        </div>
    </div>
</div>
<c:forEach items="${personList}" var="person" varStatus="status">

<div class="modal fade" id="deletePerson" tabindex="-1" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form method="post" action="deletePerson">
                <input type="hidden" name="id" id="id" value="${person.id}"/>
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Confirmar Eliminación</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    ¿Deseas eliminar a: ${person.name} ${person.lastname}?
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                    <button type="submit" class="btn btn-primary">Confirmar</button>
                </div>
            </form>
        </div>
    </div>
</div>
</c:forEach>
<jsp:include page="../../layouts/footer.jsp"/>
</body>
</html>

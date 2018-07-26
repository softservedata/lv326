<%@include file="/WEB-INF/include/taglib.jsp"%>
<%@include file="/WEB-INF/include/const.jsp"%>

<c:import url="/WEB-INF/views/commons/header.jsp" charEncoding="utf-8" />
<br>
<div class="container">
    <div class="row">
        <div class="col-md-4 offset-md-4 text-center">
            <c:if test="${error ne null}">
                <p style="color: red">${error} </p>
            </c:if>
            <form action="${path}/editcomics" method="post">
                <h2>Edit Comics ${comicsDto.title}</h2>
                <div class="form-group">
                    <input type="hidden" name="idComics" value="${param.idComics}"  class="form-control"/>
                </div>
                <div class="form-group">
                    <input type="text" name="title" class="form-control" value="${comicsDto.title}">
                </div>
                <div class="form-group">
                    <input type="text" name="author" class="form-control" value="${comicsDto.author}">
                </div>
                <div class="form-group">
                    <input type="text" name="publishingOffice" class="form-control" value="${comicsDto.publishingOffice}">
                </div>
                <div class="form-group">
                    <textarea type="text" name="description" class="form-control" rows="3">${comicsDto.description}</textarea>
                </div>
                <button type="submit" name="submit" class="btn btn-primary btn-lg btn-block">Submit</button>
            </form>
            <form action="${path}/comicscancel" method="post">
                <button type="submit" name="cancel" value="Cancel" method="post" class="btn btn-primary btn-lg btn-block">
                    Cancel
                </button>
            </form>
        </div>
    </div>
</div>
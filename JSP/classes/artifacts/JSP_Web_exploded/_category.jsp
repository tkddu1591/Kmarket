<%--
  Created by IntelliJ IDEA.
  User: Java
  Date: 2023-09-12
  Time: 오후 5:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<ul class="category">
    <li><i class="fa fa-bars" aria-hidden="true"></i>카테고리</li>
    <c:forEach var="sessCate1" items="${sessCoates1}">

    <li>
        <a href="${ctxPath}/product/list.do?cate1=${sessCate1.cate1}&cate2=0">
            <c:choose>
                <c:when test="${sessCate1.cate1 eq 10}"><i class="fas fa-tshirt"></i></c:when>
                <c:when test="${sessCate1.cate1 eq 11}"><i class="fas fa-tshirt"></i></c:when>
                <c:when test="${sessCate1.cate1 eq 12}"><i class="fas fa-home"></i></c:when>
                <c:when test="${sessCate1.cate1 eq 13}"><i class="fas fa-utensils"></i></c:when>
                <c:when test="${sessCate1.cate1 eq 14}"><i class="fas fa-home"></i></c:when>
                <c:when test="${sessCate1.cate1 eq 15}"><i class="fas fa-laptop"></i></c:when>
                <c:when test="${sessCate1.cate1 eq 16}"><i class="fas fa-soccer-ball-o"></i></c:when>
                <c:when test="${sessCate1.cate1 eq 17}"><i class="fas fa-car"></i></c:when>
                <c:when test="${sessCate1.cate1 eq 18}"><i class="fas fa-plane"></i></c:when>
            </c:choose>
                ${sessCate1.c1Name}</a>
        <ol>
            <c:forEach var="sessCate2" items="${sessCoates2}">
                <c:if test="${sessCate2.cate1 == sessCate1.cate1}">
                    <li>
                        <a href="${ctxPath}/product/list.do?cate1=${sessCate2.cate1}&cate2=${sessCate2.cate2}">${sessCate2.c2Name} </a>
                    </li>
                </c:if>
            </c:forEach>
        </ol>
    </li>
    </c:forEach>

</ul>
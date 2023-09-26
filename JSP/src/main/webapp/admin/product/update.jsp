<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="./../_header.jsp" %>
<%@include file="./../_aside.jsp" %>

<script type="text/javascript">

const origCate1 = '${product.prodCate1}';
const origCate2 = '${product.prodCate2}';
var isSelected = false;
$(document).ready(function() {
	
	//Main 카테고리를 선택 할때 마다 AJAX를 호출할 수 있지만 DB접속을 매번 해야 하기 때문에 main, sub카테고리 전체을 들고온다.
  	//Sub 카테고리 data
    var subCategoryArray = new Array();
    var subCategoryObject = new Object();

  	//메인 카테고리
    var mainCategorySelectBox = document.getElementById("prodCate1");;

  	//서브 카테고리
    var subCategorySelectBox = document.getElementById("prodCate2");;
    
	$.ajax({
		url: '${ctxPath}/admin/product/setCate2List.do',
		type: 'GET',
		dataType: 'json',
		success: function(data){
			const depth2 = data;
			const depth2keys = Object.keys(data);
			//alert(Object.keys(data[depth2keys[0]])[0]); //첫번째 map 첫번째 key
			for(let i=0; i<depth2keys.length; i++){
				for(let j=0; j<Object.keys(data[depth2keys[i]]).length; j++){

			    	subCategoryObject = new Object();
			    	let mainId = depth2keys[i];
			    	let subId = Object.keys(data[mainId])[j];
					let subName = data[mainId][subId];
			        subCategoryObject.main_category_id = mainId;
			        subCategoryObject.sub_category_id = subId;
			        subCategoryObject.sub_category_name = subName;
			        subCategoryArray.push(subCategoryObject);

			        console.log('c2Name : ' + subCategoryObject.sub_category_name);
				}
			    
			}

		    //두번째 셀렉트 박스 HTML 추가 
		    for(var i=0;i<subCategoryArray.length;i++){
	            if(origCate1 == subCategoryArray[i].main_category_id){
		    		var opt = document.createElement("option");
			    	opt.value = subCategoryArray[i].sub_category_id;
			    	opt.innerHTML =  subCategoryArray[i].sub_category_name;
			    	if(subCategoryArray[i].sub_category_id == origCate2) isSlected = true;
			    	opt.selected = isSlected;
			    	subCategorySelectBox.appendChild(opt);
					isSlected = false;
				}
		    }
		
		}
	});
	
	// depth1 선택값에 따른 depth2 옵션 수정 
	$(document).on("change","select[name='prodCate1']",function(){
	       
	    //두번째 셀렉트 박스를 삭제 시킨다.
	    var subCategorySelectBox = $("select[name='prodCate2']");
	    subCategorySelectBox.children().remove(); //기존 리스트 삭제
	    
	    //선택한 첫번째 박스의 값을 가져와 일치하는 값을 두번째 셀렉트 박스에 넣는다.
	    $("option:selected", this).each(function(){
	        var selectValue = $(this).val(); //main category 에서 선택한 값
	        subCategorySelectBox.append("<option value=''>2차 분류 선택</option>");
	        for(var i=0;i<subCategoryArray.length;i++){
	            if(selectValue == subCategoryArray[i].main_category_id){
	                subCategorySelectBox.append("<option value='"+subCategoryArray[i].sub_category_id+"'>"+subCategoryArray[i].sub_category_name+"</option>");
	                
	            }
        	}
    	});
	       
	});

});
</script>
    <section id="admin-product-register">
        <nav>
            <h3>상품등록</h3>
            <p>
                HOME > 상품관리 > <strong>상품수정</strong>
            </p>
        </nav>
        <!-- 상품등록 컨텐츠 시작 -->
        <article>
            <form action="${ctxPath}/admin/product/update.do" method="post" enctype="multipart/form-data">
		    	<input type="hidden" name="seller" value="${sessUser.uid}">
		    	<input type="hidden" name="no" value="${product.prodNo}">
		    	<!-- <input type="hidden" name="seller" value="${sessUser.uid}"> -->
                <!-- 상품분류 -->
                <section>
                    <h4>상품분류</h4>
                    <p>
                        기본분류는 반드시 선택하셔야 합니다. 하나의 상품에 1개의 분류를 지정 합니다.
                    </p>
                    <table>
                        <tr>
                            <td>1차 분류</td>
                            <td>
                                <select name="prodCate1" id="prodCate1">
                                    <option value="">1차 분류 선택</option>
   									<c:forEach var="sessCate1" items="${sessCoates1}">
                                    	<option value="${sessCate1.cate1}" ${sessCate1.cate1 eq product.prodCate1 ? 'selected' : '' }>${sessCate1.c1Name}</option> 
                                    </c:forEach>                                            
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>2차 분류</td>
                            <td>
                                <select name="prodCate2" id="prodCate2">                                             
                                </select>
                            </td>
                        </tr>
                    </table>
                </section>

                <!-- 기본정보 -->
                <section>
                    <h4>기본정보</h4>
                    <p>
                        기본정보는 반드시 입력해야 합니다.
                    </p>
                    <table>
                        <tr>
                            <td>상품명</td>
                            <td><input type="text" name="prodName" value="${product.prodName}"/></td>
                        </tr>
                        <tr>
                            <td>기본설명</td>
                            <td>
                                <span>상품명 하단에 상품에 대한 추가적인 설명이 필요한 경우에 입력</span>
                                <input type="text" name="descript"  value="${product.descript}"/>
                            </td>
                        </tr>
                        <tr>
                            <td>제조사</td>
                            <td><input type="text" name="company"  value="${product.company}"/></td>
                        </tr>
                        <tr>
                            <td>판매가격</td>
                            <td><input type="number" name="price"  value="${product.price}"/>원</td>
                        </tr>                                    
                        <tr>
                            <td>할인율</td>
                            <td>
                                <span>0을 입력하면 할인율 없음</span>
                                <input type="number" name="discount"  value="${product.discount}"/>
                            </td>
                        </tr>
                        <tr>
                            <td>포인트</td>
                            <td>
                                <span>0을 입력하면 포인트 없음</span>
                                <input type="number" name="point"  value="${product.point}"/>점
                            </td>
                        </tr>
                        <tr>
                            <td>재고수량</td>
                            <td><input type="number" name="stock"  value="${product.stock}"/>개</td>
                        </tr>
                        <tr>
                            <td>배송비</td>
                            <td>
                                <span>0을 입력하면 배송비 무료</span>
                                <input type="number" name="delivery"  value="${product.delivery}"/>원
                            </td>
                        </tr>
                        <tr>
                            <td>상품 썸네일</td>
                            <td>
                                <span>크기 190 x 190, 상품 목록에 출력될 이미지 입니다. </span>
                                <input type="file" name="thumb1New"/>

                                <span>크기 230 x 230, 상품 메인에 출력될 이미지 입니다. </span>
                                <input type="file" name="thumb2New"/>

                                <span>크기 456 x 456, 상품 상세에 출력될 이미지 입니다. </span>
                                <input type="file" name="thumb3New"/>
                                
                            	<div class="fileList">
	                            	<div class="fileImg">
	                            		<span>기존 thumb1 : </span>
	                            		<img src="${ctxPath += product.thumb1}">
	                            		<input type="hidden" name="thumb1" value="${product.thumb1}">
	                            	</div>
	                            	<div class="fileImg">
	                            		<span>기존 thumb2 : </span>
	                            		<img src="${ctxPath += product.thumb2}">
	                            		<input type="hidden" name="thumb2" value="${product.thumb2}">
	                            	</div>
	                            	<div class="fileImg">
	                            		<span>기존 thumb3 : </span>
	                            		<img src="${ctxPath += product.thumb3}">
	                            		<input type="hidden" name="thumb3" value="${product.thumb3}">
	                            	</div>
                            	</div>
                            </td>
                        </tr>
                        <tr>
                            <td>상세 상품정보</td>
                            <td>
                                <span>크기 가로 940px 높이 제약없음, 크기 최대 1MB, 상세페이지 상품정보에 출력될 이미지 입니다.</span>
                                <input type="file" name="detailNew"/>
                            	<div class="fileList">
		                           	<div class="fileImg">
		                           		<span>기존 detail</span>
		                           		<img src="${ctxPath += product.detail}">
		                           		<input type="hidden" name="detail" value="${product.detail}">
	                            	</div>
	                            </div>
                            </td>
                        </tr>
                    </table>                                
                </section>

                <!-- 상품정보 제공 고시 -->
                <section>
                    <h4>상품정보 제공고시</h4>
                    <p>
                        [전자상거래에 관한 상품정보 제공에 관한 고시] 항목에 의거 등록해야 되는 정보입니다.
                    </p>
                    <table>
                        <tr>
                            <td>상품상태</td>
                            <td><input type="text" name="status" value="${product.status}"/></td>
                        </tr>
                        <tr>
                            <td>부가세 면세여부</td>
                            <td><input type="text" name="duty" value="${product.duty}"/></td>
                        </tr>
                        <tr>
                            <td>영수증발행</td>
                            <td><input type="text" name="receipt" value="${product.receipt}"/></td>
                        </tr>
                        <tr>
                            <td>사업자구분</td>
                            <td><input type="text" name="bizType" value="${product.bizType}"/></td>
                        </tr>                                
                        <tr>
                            <td>원산지</td>
                            <td><input type="text" name="origin" value="${product.origin}"/></td>
                        </tr>                                
                    </table>                                
                </section>
                
                <input type="submit" value="수정하기" class="onclick"/>
            </form>
        </article>

        <p class="ico alert">
            <strong>Warning!</strong>
            전자상거래 등에서의 상품 등의 정보제공에 관한 고시에 따라 총 35개 상품군에 대해 상품 특성 등을 양식에 따라 입력할 수 있습니다.
        </p>
        <!-- 상품등록 컨텐츠 끝 -->
    </section>
</main>
<%@ include file="./../_footer.jsp" %>

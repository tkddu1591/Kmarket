<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ include file="./../_header.jsp" %>
<%@ include file="./_aside.jsp" %>
        

<script type="text/javascript">

$(document).ready(function() {
	$(".fileUpload").on("change", function(e){
		var str = $(this).val();
		var fileName = str.split('\\').pop().toLowerCase();
		//alert(fileName);
		
		checkFileName(fileName);
	});

	$(".fileRemove").on("click", function(e){
		var inputFile = $(this).prev();
		var inputFileName = inputFile.attr('name');
		if(inputFile.val != null && confirm(inputFileName + '파일을 삭제하시겠습니까?')){
			inputFile.val('');
		}
	});
	
	// 파일명 검사 
	function checkFileName(str){
	    //1. 확장자 체크
	    var ext =  str.split('.').pop().toLowerCase();
	    if($.inArray(ext, [ 'jpg', 'pdf', 'png', 'jpeg', 'gif']) == -1) {
	 
	        alert(ext+'파일은 업로드 하실 수 없습니다.');
	    }
	    //2. 파일명에 특수문자 체크
	    var pattern =   /[\{\}\/?,;:|*~`!^\+<>@\#$%&\\\=\'\"]/gi;
	    if(pattern.test(str) ){
	        //alert("파일명에 허용된 특수문자는 '-', '_', '(', ')', '[', ']', '.' 입니다.");
	        alert('파일명에 특수문자를 제거해주세요.');
	    }
	}


});
</script>
     
      <article>
        <form action="${ctxPath}/cs/qna/modify.do" method="post" enctype="multipart/form-data">
        	<input type="hidden" name="writer" value="${sessUser.uid}">
        	<input type="hidden" name="no" value="${no}">
	        <table>
	          <tr>
	            <td>문의유형</td>
	            <td>
	              <select name="cate1" id="cate1" readonly>
	                <option value="${dto.cate1}">${dto.c1Name}</option>
	              </select>
	              <select name="cate2" id="cate2" readonly>
	                <option value="${dto.cate2}">${dto.c2Name}</option>
	              </select>
	            </td>
	          </tr>
	          <tr>
	            <td>문의제목</td>                  
	            <td>
	              <input type="text" name="title" value="${dto.title}"/>
	            </td>
	          </tr>                
	          <tr>
	            <td>문의내용</td>                  
	            <td>
	              <textarea name="content" value="${dto.content}"></textarea>
	            </td>
	          </tr>              
	          <tr>
	            <td>파일첨부</td>                  
	            <td class="fileInputList">
	            	<div>
		              	<input type="file" name="file1" accept="image/*,.pdf" class="fileUpload" value="${dto.file[0]}"/>
		              	<a class="fileRemove"><span> [ 파일 삭제 ] </span></a>
				        <c:if test="${dto.file[0] ne null}">
				        	<div>
								<span> 기존 첨부 파일1 :: </span>
		        				<div class="fileImg">
				        			<a href="${ctxPath}/cs/qna/fileDownload.do?fileName=${dto.file[0]}" id="downloadFile" data-fno="${dto.file[0]}">
				        				 <img src="${ctxPath}/upload/${dto.file[0]}">
			        				 </a>
		        				 </div>
		        				 
 				 				 <a href="#" class="file-delete"> [기존 첨부 파일1 삭제 ] </a>
			                	 <input type="hidden" name="existedFile2" value="${article.fileDto.fno}">
			                </div>
				        </c:if>
	            	</div>
	            	<div>
		              	<input type="file" name="file2" accept="image/*,.pdf" class="fileUpload"/>
		              	<a class="fileRemove"><span> [ 파일 삭제 ] </span></a>
				        <c:if test="${dto.file[1] ne null}">
				        	<div>
								<span> 기존 첨부 파일2 :: </span>
		        				<div class="fileImg">
				        			<a href="${ctxPath}/cs/qna/fileDownload.do?fileName=${dto.file[1]}" id="downloadFile" data-fno="${dto.file[1]}">
				        				 <img src="${ctxPath}/upload/${dto.file[1]}">
			        				 </a>
		        				 </div>
		        				 
 				 				 <a href="#" class="file-delete"> [기존 첨부 파일 삭제 ] </a>
			                	 <input type="hidden" name="existedFile2" value="${article.fileDto.fno}">
			                </div>
				        </c:if>
	            	</div>
	            	<div>
	              	<input type="file" name="file3" accept="image/*,.pdf" class="fileUpload"/>
	              	<a class="fileRemove"><span> [ 파일 삭제 ] </span></a>
	            	</div>
	            	<div>
	              	<input type="file" name="file4" accept="image/*,.pdf" class="fileUpload"/>
	              	<a class="fileRemove"><span> [ 파일 삭제 ] </span></a>
	            	</div>
	              <p class="">파일첨부는 최대 4장까지 가능하며, 5MB이하의 GIF, JPG, JPEG, PNG, PDF 형태로 업로드해주세요</p>
	            </td>
	          </tr>
	        </table>
	        <div class="qnaBtnArea">
	          <a href="${ctxPath}/cs/qna/list.do" class="btnList">취소하기</a>
	          <input type="submit" class="btnSubmit" value="등록하기"/>
	        </div>
        </form>
      </article>
    </section>
  </div>
<%@ include file="./../_footer.jsp" %>

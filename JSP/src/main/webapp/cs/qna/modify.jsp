<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ include file="./../_header.jsp" %>
<%@ include file="./_aside.jsp" %>

<script>
const existedFileCntOnLoad = ${fn:length(dto.file)};
</script>
<script src="${ctxPath}/cs/js/fileModifyController.js"></script>
     
      <article>
        <form action="${ctxPath}/cs/qna/modify.do" method="post" enctype="multipart/form-data">
        	<input type="hidden" name="writer" value="${sessUser.uid}">
        	<input type="hidden" name="no" value="${no}">
        	<input type="hidden" name="type" value="modify">
        	
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
	              <textarea name="content">${dto.content}</textarea>
	            </td>
	          </tr>              
	          <tr>
	            <td>파일첨부</td>                  
	            <td class="fileInputList">
	            	<div  class="insert">
		              	<input type="file" id="fileInputBtn"  onchange="addFiles(this);"  name="fileUpload" accept="image/*,.pdf" multiple/>
		              	<div class="file-list">
		              		<c:set var="idx" value="0" />
			              	<c:forEach var="file" items="${dto.file}">
			              		<div class="filebox" id="existedFile${idx = idx+1}">
			              			<p class="name"> [ 기존 첨부파일 ${idx} ] ${file}</p>
			        				<div class="fileImg">
					        			<a href="${ctxPath}/cs/qna/fileDownload.do?fileName=${dto.file[idx-1]}" id="downloadFile" data-fno="${dto.file[idx-1]}">
					        				 <img src="${ctxPath += dto.file[idx-1]}">
				        				 </a>
			        				 </div>
			              			<a class="delete" onclick="deleteExistedFile(${idx})">
			              				<i class="far fa-minus-square"></i>
			              			</a>
			                	 	<input type="hidden" name="existedFile${idx}_2" value="${file}" class="existedFile">
			              		</div>

            					<input type="hidden" name="existedFile${idx}_1" value="${file}">
			              	</c:forEach>
		              	</div>
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

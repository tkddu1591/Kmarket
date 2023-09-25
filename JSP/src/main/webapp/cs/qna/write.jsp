<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ include file="./../_header.jsp" %>
<%@ include file="./_aside.jsp" %>
        
<script>
const ctx = '${ctxPath}';
</script>
<script src="${ctxPath}/cs/js/setType2Category.js"></script>
<script src="${ctxPath}/cs/js/fileController.js"></script>
     
      <article>
        <form action="${ctxPath}/cs/qna/write.do" method="post" enctype="multipart/form-data">
        	<input type="hidden" name="writer" value="${sessUser.uid}">
	        <table>
	          <tr>
	            <td>문의유형</td>
	            <td>
	              <select name="cate1" id="cate1">
	                <option value="">문의유형 선택</option>
	              </select>
	              <select name="cate2" id="cate2">
	                <option value="">상세유형 선택</option>
	              </select>
	            </td>
	          </tr>
	          <tr>
	            <td>문의제목</td>                  
	            <td>
	              <input type="text" name="title" placeholder="제목을 입력하세요."/>
	            </td>
	          </tr>                
	          <tr>
	            <td>문의내용</td>                  
	            <td>
	              <textarea name="content" placeholder="내용을 입력하세요."></textarea>
	            </td>
	          </tr>              
	          <tr>
	            <td>파일첨부</td>                  
	            <td>
	            	<div  class="insert">
		              	<input type="file" id="fileInputBtn"  onchange="addFiles(this);"  name="fileUpload" accept="image/*,.pdf" multiple/>
		              	<div class="file-list"></div>
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

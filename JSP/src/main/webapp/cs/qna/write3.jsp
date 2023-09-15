<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ include file="./../_header.jsp" %>
<%@ include file="./_aside.jsp" %>
        

<script type="text/javascript">

$(document).ready(function() {
	
	//Main 카테고리를 선택 할때 마다 AJAX를 호출할 수 있지만 DB접속을 매번 해야 하기 때문에 main, sub카테고리 전체을 들고온다.
    //Main 카테고리 data
    var mainCategoryArray = new Array();
    var mainCategoryObject = new Object();
  	//Sub 카테고리 data
    var subCategoryArray = new Array();
    var subCategoryObject = new Object();
    
  	//메인 카테고리 셋팅
    var mainCategorySelectBox = document.getElementById("cate1");;
    
	$.ajax({
		url: '${ctxPath}/cs/setCategoryList.do',
		type: 'GET',
		dataType: 'json',
		success: function(data){
			for(let i=0; i<data.depth1.length; i++){
				
				// array data 추가
			    mainCategoryObject = new Object();
			    mainCategoryObject.main_category_id = data["depth1"][i]["cate1"];
			    mainCategoryObject.main_category_name = data["depth1"][i]["c1Name"];
			    mainCategoryArray.push(mainCategoryObject);
			    
			    
			    let cate1 = mainCategoryObject.main_category_id;
			    
			    for(let j=0; j<data["depth2"][cate1].length; j++){
			    	
			    	subCategoryObject = new Object();
			        subCategoryObject.main_category_id = data["depth2"][cate1][j]["cate1"];
			        subCategoryObject.sub_category_id = data["depth2"][cate1][j]["cate2"];
			        subCategoryObject.sub_category_name = data["depth2"][cate1][j]["c2Name"];
			        subCategoryArray.push(subCategoryObject);

			        console.log('c2Name : ' + subCategoryObject.sub_category_name);
			    }
			}

		    //HTML 추가 
		    for(var i=0;i<mainCategoryArray.length;i++){
		    	var opt = document.createElement("option");
		    	opt.value = mainCategoryArray[i].main_category_id;
		    	opt.innerHTML =  mainCategoryArray[i].main_category_name;
		    	mainCategorySelectBox.appendChild(opt);
		    }
		    
		}
	});
	
	// depth1 선택값에 따른 depth2 옵션 수정 
	$(document).on("change","select[name='cate1']",function(){
	       
	    //두번째 셀렉트 박스를 삭제 시킨다.
	    var subCategorySelectBox = $("select[name='cate2']");
	    subCategorySelectBox.children().remove(); //기존 리스트 삭제
	    
	    //선택한 첫번째 박스의 값을 가져와 일치하는 값을 두번째 셀렉트 박스에 넣는다.
	    $("option:selected", this).each(function(){
	        var selectValue = $(this).val(); //main category 에서 선택한 값
	        subCategorySelectBox.append("<option value=''>상세유형 선택</option>");
	        for(var i=0;i<subCategoryArray.length;i++){
	            if(selectValue == subCategoryArray[i].main_category_id){
	            
	                subCategorySelectBox.append("<option value='"+subCategoryArray[i].sub_category_id+"'>"+subCategoryArray[i].sub_category_name+"</option>");
	                
	            }
        	}
    	});
	       
	});



});


</script>

<script type="text/javascript">
// 파일 갯수 제어 
var fileNo = 0;
var filesArr = new Array();

/* 첨부파일 추가 */
function addFiles(obj){
    var maxFileCnt = 4;   // 첨부파일 최대 개수
    var attFileCnt = document.querySelectorAll('.filebox').length;    // 기존 추가된 첨부파일 개수
    var remainFileCnt = maxFileCnt - attFileCnt;    // 추가로 첨부가능한 개수
    var curFileCnt = obj.files.length;  // 현재 선택된 첨부파일 개수

    // 첨부파일 개수 확인
    if (curFileCnt > remainFileCnt) {
        alert("첨부파일은 최대 " + maxFileCnt + "개 까지 첨부 가능합니다.");
    }

    for (var i = 0; i < Math.min(curFileCnt, remainFileCnt); i++) {

        const file = obj.files[i];

        // 첨부파일 검증
        if (validation(file)) {
            // 파일 배열에 담기
            var reader = new FileReader();
            reader.onload = function () {
                filesArr.push(file);
            };
            reader.readAsDataURL(file)

            // 목록 추가
            let htmlData = '';
            htmlData += '<div id="file' + fileNo + '" class="filebox">';
            htmlData += '   <p class="name">' + file.name + '</p>';
            htmlData += '   <a class="delete" onclick="deleteFile(' + fileNo + ');"><i class="far fa-minus-square"></i></a>';
            htmlData += '</div>';
            $('.file-list').append(htmlData);
            fileNo++;
        } else {
            continue;
        }
    }
    // 초기화
    document.querySelector("input[type=file]").value = "";
}

/* 첨부파일 검증 */
function validation(obj){
    const fileTypes = ['application/pdf', 'image/gif', 'image/jpeg', 'image/png', 'image/bmp', 'image/tif', 'application/haansofthwp', 'application/x-hwp'];
    if (obj.name.length > 100) {
        alert("파일명이 100자 이상인 파일은 제외되었습니다.");
        return false;
    } else if (obj.size > (100 * 1024 * 1024)) {
        alert("최대 파일 용량인 100MB를 초과한 파일은 제외되었습니다.");
        return false;
    } else if (obj.name.lastIndexOf('.') == -1) {
        alert("확장자가 없는 파일은 제외되었습니다.");
        return false;
    } else if (!fileTypes.includes(obj.type)) {
        alert("첨부가 불가능한 파일은 제외되었습니다.");
        return false;
    } else {
        return true;
    }
}

/* 첨부파일 삭제 */
function deleteFile(num) {
    document.querySelector("#file" + num).remove();
    filesArr[num].is_delete = true;
}

/* 폼 전송 */
function submitForm() {
    // 폼데이터 담기
    var form = document.querySelector("form");
    var formData = new FormData(form);
    for (var i = 0; i < filesArr.length; i++) {
        // 삭제되지 않은 파일만 폼데이터에 담기
        if (!filesArr[i].is_delete) {
            formData.append("attach_file", filesArr[i]);
        }
    }

    $.ajax({
        method: 'POST',
        url: '/register',
        dataType: 'json',
        data: formData,
        async: true,
        timeout: 30000,
        cache: false,
        headers: {'cache-control': 'no-cache', 'pragma': 'no-cache'},
        success: function () {
            alert("파일업로드 성공");
        },
        error: function (xhr, desc, err) {
            alert('에러가 발생 하였습니다.');
            return;
        }
    })
}
</script>
     
      <article>
      
      [memo]추후추가 -  submit 버튼 전에 file ext 검사 <br>
      [memo]추후추가 - + 버튼 누르면 파일 입력 인풋이 1개 >> 최대 4개까지  
        <form action="${ctxPath}/cs/qna/write3.do" method="post" enctype="multipart/form-data">
        	<input type="hidden" name="writer" value="user">
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
		              	<input type="file"  name="fileUpload" onchange="addFiles(this);"  accept="image/*,.pdf" multiple/>
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

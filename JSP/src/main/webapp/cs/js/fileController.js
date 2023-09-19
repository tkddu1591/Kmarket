
// 파일 갯수 제어 
var fileNo = 0;
var filesArr = new Array();
const dataTransfer = new DataTransfer();


/* 첨부파일 추가 */

function addFiles(obj){

    let fileArr = document.getElementById("fileInputBtn").files; 
  	// =====DataTransfer 파일 관리========
    if(fileArr != null && fileArr.length > 0){
  	// =====DataTransfer 파일 관리========
	
	    var maxFileCnt = 4;   // 첨부파일 최대 개수
	    var attFileCnt = document.querySelectorAll('.filebox').length;    // 기존 추가된 첨부파일 개수
	    var remainFileCnt = maxFileCnt - attFileCnt;    // 추가로 첨부가능한 개수
	    var curFileCnt = obj.files.length;  // 현재 선택된 첨부파일 개수
	
	    
	    // 첨부파일 개수 확인
	    if (curFileCnt > remainFileCnt) {
	        alert("첨부파일은 최대 " + maxFileCnt + "개 까지 첨부 가능합니다.");
	    }
	    
	    
	
	    for (var i = 0; i < Math.min(curFileCnt, remainFileCnt); i++) {
	
    	  	// =====DataTransfer 파일 관리========
            dataTransfer.items.add(fileArr[i]);
    	  	// =====DataTransfer 파일 관리========
	    	
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
	    
	  	// =====DataTransfer 파일 관리========
	    document.getElementById("fileInputBtn").files = dataTransfer.files;
        console.log("dataTransfer =>",dataTransfer.files);
        console.log("input Files =>", document.getElementById("fileInputBtn").files);
	  	// =====DataTransfer 파일 관리========
	    
    }// if(fileArr != null && fileArr.length > 0){}
    
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
	//input data 삭제 
    let targetFile = document.getElementById("fileInputBtn").files[num].lastModified;
     
   // ============DataTransfer================
     for(var i=0; i<dataTransfer.files.length; i++){
         if(dataTransfer.files[i].lastModified==targetFile){
             dataTransfer.items.remove(i);
             break;
         }
     }
     document.getElementById("fileInputBtn").files = dataTransfer.files;

     console.log("dataTransfer 삭제후=>",dataTransfer.files);
     console.log('input FIles 삭제후=>',document.getElementById("fileInputBtn").files);

    
	//html 삭제 
    document.querySelector("#file" + num).remove();
    filesArr[num].is_delete = true;
}

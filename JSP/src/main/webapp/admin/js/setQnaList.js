$(document).ready(function() {
	
	
	$(document).on("change","select[name='cate1']",function(){
	       
	       getList();
	       updatePages();
	       
	});
	$(document).on("change","select[name='cate2']",function(){
	       
	       getList();
	       updatePages();
	       
	});
});

function getList(){
	


	var searchCate1 = $("#cate1").val();
	var searchCate2 = $('#cate2').val();
	if(searchCate1 == ""){
		searchCate1 == null;
	}
	if(searchCate2 == ""){
		searchCate2 == null;
	}
						      
	$.ajax({   
		type : "post",
		url : ctx + "/admin/cs/qna/list.do",
		data :  {
			"cate1": searchCate1,
			"cate2" : searchCate2
		},
		success : function(result) {
			console.log(result);
			$('.listTbody').children().remove();
			const listObj = JSON.parse(result);			
			for(var n of listObj){
										
			var item = "<tr>";

				item += "<td><input type='checkbox' name='chk' value='"+n.qnaNo+"'/></td>";
				item += "<td>"+n.qnaNo+"</td>";
				item += "<td>"+n.c1Name+"</td>";
				item += "<td>"+n.c2Name+"</td>";
				item += "<td><a href='"+ctx+"/admin/cs/qna/view.do?no="+n.qnaNo +"'>" + n.title + "</a></td>";
				item += "<td>"+n.writerMarking+"</td>";
				item += "<td>"+n.rdateSub+"</td>";
				item += "<td>";
				item += "<span class='answer"+n.answerComplete+"'>";
				item += (n.answerComplete == 0 ? "미확인" : n.answerComplete == 1 ? "검토중" : "답변완료");
				item += "</span>";
				item += "</td>";
				item += "</tr>";
										
				$('.listTbody').append(item);
			}
			if(listObj.length == 0){
				
				var item = "<tr>";
				item += "<td colspan='7'>조회 결과가 없습니다.</td>";
				item += "</tr>";
				$('.listTbody').append(item);
			}
			updatePages();						
		},
		error : (request, status, error) => { // 순서 체크해보기 
            console.log("상태코드: " + request.status);
            console.log("메세지: " + request.responseText);
            console.log("에러설명: " + error);
        }
	});
}

function updatePages(){

	var searchCate1 = $("#cate1").val();
	var searchCate2 = $('#cate2').val();
	if(searchCate1 == ""){
		searchCate1 == null;
	}
	if(searchCate2 == ""){
		searchCate2 == null;
	}
	$.ajax({   
		type : "get",
		url : ctx + "/admin/cs/qna/getPageNums.do",
		data :  {
			"cate1": searchCate1,
			"cate2" : searchCate2
		},
		success : function(data) {
			console.log(data);
			const result = JSON.parse(data);
			$('.paging').children().remove();
										
			var item = '';
			if(result.pageGroupStart > 1 ){
				item += '<span class="prev">';
				item += '<a href="'+ctx+'/admin/cs/qna/list.do?cate1='+searchCate1+'&cate2='+searchCate2 +'&pg='+(result.pageGroupStart -1) +'"><&nbsp;이전</a>';
		        item += '</span>'
			}
			for(var i=result.pageGroupStart; i<=result.pageGroupEnd; i++){
				item +='<span class="num ' + (result.currentPage==i?'on':'off') + '">';
			    item +='<a href="'+ctx+'/admin/cs/qna/list.do?cate1='+searchCate1+'&cate2='+searchCate2+ '&pg='+i+'">'+i+'</a>';
			    item +='</span>';
			}
			if(result.pageGroupEnd < result.lastPageNum){
				item += '<span class="next">';
				item += '<a href="'+ctx+'/admin/cs/qna/list.do?cate1='+searchCate1+'&cate2='+searchCate2+'&pg='+(result.pageGroupEnd + 1) +'"><&nbsp;이전</a>';
		        item += '</span>'
			}

			$('.paging').append(item);
			console.log(item);
		},
		error : (request, status, error) => { // 순서 체크해보기 
            console.log("상태코드: " + request.status);
            console.log("메세지: " + request.responseText);
            console.log("에러설명: " + error);
        }
	});
}

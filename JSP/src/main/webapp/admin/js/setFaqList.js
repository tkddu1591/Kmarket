$(document).ready(function() {
	
	$(document).on("change","select[name=cate2]",function(){
	       var cate1 = $('select[name=cate1]').val();
	       var cate2 = $('select[name=cate2]').val();
	       if(cate2 == ''){
			   
		   }else{
			   getList(cate1, cate2);
		   }
	       
	});
});

function getList(cate1, cate2){

	$.ajax({   
		type : "post",
		url : ctx + "/admin/cs/faq/list.do",
		data :  {
			"cate1": cate1,
			"cate2" : cate2
		},
		success : function(result) {
			console.log(result);
			$('.listTbody').children().remove();
			const listObj = JSON.parse(result);			
			for(var n of listObj){
										
			var item = "<tr>";

				item += "<td><input type='checkbox' name='chk' value='"+n.faqNo+"'/></td>";
				item += "<td>"+n.faqNo+"</td>";
				item += "<td>"+n.c1Name+"</td>";
				item += "<td>"+n.c2Name+"</td>";
				item += "<td><a href='"+ctx+"/admin/cs/faq/view.do?no="+n.faqNo +"'>" + n.title + "</a></td>";
				item += "<td>"+n.hit+"</td>";
				item += "<td>"+n.rdateSub+"</td>";
				item += "<td>";
				item += " <a href='"+ctx+"/admin/cs/faq/delete.do?no="+ n.faqNo +"' class='faqDelete'>[삭제]</a>";
				item += "<br>";
				item += " <a href='"+ctx+"/admin/cs/faq/update.do?no="+ n.faqNo + "' class='faqUpdate'>[수정]</a>";
				item += "</td>";
				item += "</tr>";
										
				$('.listTbody').append(item);
			}
			if(listObj.length == 0){
				
				var item = "<tr>";
				item += "<td colspan='8'>조회 결과가 없습니다.</td>";
				item += "</tr>";
				$('.listTbody').append(item);
			}
		},
		error : (request, status, error) => { // 순서 체크해보기 
            console.log("상태코드: " + request.status);
            console.log("메세지: " + request.responseText);
            console.log("에러설명: " + error);
        }
	});
}


$(document).ready(function() {
	
	//Main 카테고리를 선택 할때 마다 AJAX를 호출할 수 있지만 DB접속을 매번 해야 하기 때문에 main, sub카테고리 전체을 들고온다.
    //Main 카테고리 data
    var mainCategoryArray = new Array();
    var mainCategoryObject = new Object();
    
  	//메인 카테고리 셋팅
    var mainCategorySelectBox = document.getElementById("searchCate1");;
    
	$.ajax({
		url: ctx + '/cs/setCsCategory.do',
		type: 'GET',
		dataType: 'json',
		success: function(data){
			for(let i=0; i<data.depth1.length; i++){
				
				if(data["depth1"][i]["cate1"] < 20){ //cate1 값이 20이하인. type1 만.
					// array data 추가
				    mainCategoryObject = new Object();
				    mainCategoryObject.main_category_id = data["depth1"][i]["cate1"];
				    mainCategoryObject.main_category_name = data["depth1"][i]["c1Name"];
				    mainCategoryArray.push(mainCategoryObject);
   
				   
				}
			}

		    //HTML 추가 
		    
			var selectedOption = false;
		    for(var i=0;i<mainCategoryArray.length;i++){
				console.log('controllerCate1 : ' + controllerCate1 + '/ id :  ' + mainCategoryArray[i].main_category_id);
		    	var opt = document.createElement("option");
		    	opt.value = mainCategoryArray[i].main_category_id;
		    	opt.innerHTML =  mainCategoryArray[i].main_category_name;
		    	
				if(mainCategoryArray[i].main_category_id == controllerCate1){
					selectedOption= true; console.log('여기다');	
				}  
		    	opt.selected = selectedOption;
		    	mainCategorySelectBox.appendChild(opt);
		    	selectedOption = false;
		    }
		    
		}
	});
	
	$(document).on("change","select[name='searchCate1']",function(){
	       
	       getList();
	       updatePages();
	       
	});
	$('input[name=searchKeyword]').on('focusout',function(){
	       getList();
	       updatePages();
	});
});

function getList(){
	

	var searchCate1 = $("#searchCate1").val();
	var searchKeyword = $('#searchKeyword').val();
	var searchPg = $('#searchPg').val();
	if(searchCate1 == ""){
		searchCate1 == null;
	}if(searchKeyword == ""){
		searchKeyword == null;
	}if(searchPg == ""){
		searchPg == null;
	}
	console.log("정렬 [" + searchCate1 + "] / 검색 [" + searchKeyword + "] / 페이지 : [ " + searchPg + "]");
						      
	$.ajax({   
		type : "get",
		url : ctx + "/admin/cs/notice/getAjaxList.do",
		data :  {
			"cate1": searchCate1,
			"keyword" : searchKeyword,
			"pg" : searchPg
		},
		success : function(result) {
			console.log(result);
			$('.listTbody').children().remove();
			const listObj = JSON.parse(result);			
			for(var n of listObj){
										
			var item = "<tr>";

				item += "<td><input type='checkbox' name='chk' value='"+n.noticeNo+"'/></td>";
				item += "<td>"+n.noticeNo+"</td>";
				item += "<td>"+n.c1Name+"</td>";
				item += "<td><a href='"+ctx+"/admin/cs/notice/view.do?no="+n.noticeNo +"'>[ "+n.c2Name+" ] " + n.title + "</a></td>";
				item += "<td>"+n.hit+"</td>";
				item += "<td>"+n.rdateSub+"</td>";
				item += "<td>";
				item += " <a href='"+ctx+"/admin/cs/notice/delete.do?no="+ n.noticeNo +"' class='noticeDelete'>[삭제]</a>";
				item += "<br>";
				item += " <a href='"+ctx+"/admin/cs/notice/update.do?no="+ n.noticeNo + "' class='noticeUpdate'>[수정]</a>";
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

	var searchCate1 = $("#searchCate1").val();
	var searchKeyword = $('#searchKeyword').val();
	var searchPg = $('#searchPg').val();
	if(searchCate1 == ""){
		searchCate1 == null;
	}if(searchKeyword == ""){
		searchKeyword == null;
	}if(searchPg == ""){
		searchPg == null;
	}
	$.ajax({   
		type : "get",
		url : ctx + "/admin/cs/notice/getAjaxPage.do",
		data :  {
			"cate1": searchCate1,
			"keyword" : searchKeyword,
			"pg" : searchPg
		},
		success : function(data) {
			console.log(data);
			const result = JSON.parse(data);
			console.log( " / " + result['pageStartNum']);
			$('.paging').children().remove();
										
			var item = '';
			if(result.pageGroupStart > 1 ){
				item += '<span class="prev">';
				item += '<a href="'+ctx+'/admin/cs/notice/list.do?cate1='+searchCate1+'&keyword='+searchKeyword+'&pg='+(result.pageGroupStart -1) +'"><&nbsp;이전</a>';
		        item += '</span>'
			}
			for(var i=result.pageGroupStart; i<=result.pageGroupEnd; i++){
				item +='<span class="num ' + (result.currentPage==i?'on':'off') + '">';
			    item +='<a href="'+ctx+'/admin/cs/notice/list.do?cate1='+searchCate1+'&keyword='+searchKeyword + '&pg='+i+'">'+i+'</a>';
			    item +='</span>';
			}
			if(result.pageGroupEnd < result.lastPageNum){
				item += '<span class="next">';
				item += '<a href="'+ctx+'/admin/cs/notice/list.do?cate1='+searchCate1+'&keyword='+searchKeyword+'&pg='+(result.pageGroupEnd + 1) +'"><&nbsp;이전</a>';
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

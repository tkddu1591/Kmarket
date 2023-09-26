$(document).ready(function() {
	//Main 카테고리를 선택 할때 마다 AJAX를 호출할 수 있지만 DB접속을 매번 해야 하기 때문에 main, sub카테고리 전체을 들고온다.
    //Main 카테고리 data
    var mainCategoryArray = new Array();
    var mainCategoryObject = new Object();
  	//Sub 카테고리 data
    var subCategoryArray = new Array();
    var subCategoryObject = new Object();
    
    var isSlected = false;
    
  	//메인 카테고리 셋팅
    var mainCategorySelectBox = document.getElementById("cate1");;
    var subCategorySelectBox = document.getElementById("cate2");;
    var mainRelatedCategorySelectBox = document.getElementById("relatedCate1");;
    var subRelatedCategorySelectBox = document.getElementById("relatedCate2");;
    
	$.ajax({
		url: ctx + '/cs/setCsCategory.do',
		type: 'GET',
		dataType: 'json',
		success: function(data){
			for(let i=0; i<data.depth1.length; i++){
				
				if(data["depth1"][i]["cate1"] >= 20){ //cate1 값이 20이상인. type2 만.
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
				        //console.log('c2Name : ' + subCategoryObject.sub_category_name);
				    }
				   
				}
			}

		    //HTML 추가 
		    for(var i=0;i<mainCategoryArray.length;i++){
		    	var opt = document.createElement("option");
		    	opt.value = mainCategoryArray[i].main_category_id;
		    	opt.innerHTML =  mainCategoryArray[i].main_category_name;
		    	if(mainCategoryArray[i].main_category_id == origCate1) isSlected = true;
		    	opt.selected = isSlected;
		    	mainCategorySelectBox.appendChild(opt);
				isSlected = false;
		    }
		    //두번째 셀렉트 박스 추가 
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
			    //HTML 추가 
			    for(var i=0;i<mainCategoryArray.length;i++){
			    	var opt = document.createElement("option");
			    	opt.value = mainCategoryArray[i].main_category_id;
			    	opt.innerHTML =  mainCategoryArray[i].main_category_name;
			    	if(mainCategoryArray[i].main_category_id == relatedOrigCate1) isSlected = true;
			    	opt.selected = isSlected;
			    	mainRelatedCategorySelectBox.appendChild(opt);
					isSlected = false;
			    }
			    //두번째 셀렉트 박스 추가 
			    for(var i=0;i<subCategoryArray.length;i++){
		            if(relatedOrigCate1 == subCategoryArray[i].main_category_id){
			    		var opt = document.createElement("option");
				    	opt.value = subCategoryArray[i].sub_category_id;
				    	opt.innerHTML =  subCategoryArray[i].sub_category_name;
				    	if(subCategoryArray[i].sub_category_id == relatedOrigCate2) isSlected = true;
				    	opt.selected = isSlected;
				    	subRelatedCategorySelectBox.appendChild(opt);
						isSlected = false;
					}
			    }
			    setTitle(relatedOrigCate1, relatedOrigCate2);
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
	
	$(document).on("change","select[name=relatedCate1]",function(){
	    var _tempSelect = $("select[name='relatedCate2']");
	    _tempSelect.children().remove(); //기존 리스트 삭제
   		for(var i=0;i<subCategoryArray.length;i++){
            if($('select[name=relatedCate1').val() == subCategoryArray[i].main_category_id){
	    		var opt = document.createElement("option");
		    	opt.value = subCategoryArray[i].sub_category_id;
		    	opt.innerHTML =  subCategoryArray[i].sub_category_name;
		    	subRelatedCategorySelectBox.appendChild(opt);
				isSlected = false;
			}
	    }
	});
	$(document).on("change","select[name=relatedCate2]",function(){
	       var cate1 = $('select[name=relatedCate1]').val();
	       var cate2 = $('select[name=relatedCate2]').val();
	       if(cate2 == ''){
			   
		   }else{
			   setTitle(cate1, cate2);
		   }
	       
	});
});

function setTitle(cate1, cate2){

	$.ajax({   
		type : "post",
		url : ctx + "/admin/cs/faq/list.do",
		data :  {
			"cate1": cate1,
			"cate2" : cate2
		},
		success : function(result) {
			console.log(result);
			$('#relatedTitle').children().remove();
			const listObj = JSON.parse(result);			
			for(var n of listObj){
										
				var item = "<option value='"+ n.faqNo + "' " + (hasRelated? relatedFaq == n.faqNo ? "selected" : "" :"") +">";
					item += n.title;
					item += "</option>";
										
				$('#relatedTitle').append(item);
			}
			if(listObj.length == 0){
				
				
				var item = "<option>";
					item += "조회 가능한 자주묻는질문이 없습니다.";
					item += "</option>";
				$('#relatedTitle').append(item);
			}
		},
		error : (request, status, error) => { // 순서 체크해보기 
            console.log("상태코드: " + request.status);
            console.log("메세지: " + request.responseText);
            console.log("에러설명: " + error);
        }
	});
}


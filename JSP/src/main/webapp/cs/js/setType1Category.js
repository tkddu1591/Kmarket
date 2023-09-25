$(document).ready(function() {
	
	//Main 카테고리를 선택 할때 마다 AJAX를 호출할 수 있지만 DB접속을 매번 해야 하기 때문에 main, sub카테고리 전체을 들고온다.
    //Main 카테고리 data
    var mainCategoryArray = new Array();
    var mainCategoryObject = new Object();
  	//Sub 카테고리 data
    var subCategoryArray = new Array();
    var subCategoryObject = new Object();
    
    var isSelected = false;
    
  	//메인 카테고리 셋팅
    var mainCategorySelectBox = document.getElementById("cate1");;
	$.ajax({
		url: ctx + '/cs/setCsCategory.do',
		type: 'GET',
		dataType: 'json',
		success: function(data){
			for(let i=0; i<data.depth1.length; i++){
				
				if(data["depth1"][i]["cate1"] < 20){ //cate1 값이 20미만인. type1 만.
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
		    	if(cate1 == mainCategoryArray[i].main_category_id) isSelected = true;
		    	opt.selected = isSelected;
		    	mainCategorySelectBox.appendChild(opt);
		    	isSelected = false;
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
	                subCategorySelectBox.append("<option value='"+subCategoryArray[i].sub_category_id+"' " + (cate2 == subCategoryArray[i].sub_category_id ? "selected" : "" ) + ">"+subCategoryArray[i].sub_category_name+"</option>");
	                
	            }
        	}
    	});
	       
	});

});